package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.apache.commons.math3.analysis.function.Min;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.ClawActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.FiveSpecPath;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.FoldInAction;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SlideDropOffActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SlideIntakeActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SpecCycleActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.WristAction;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.HardwareSubsystem;

import java.util.ArrayList;
import java.util.List;

@Config
@Autonomous
public class FeedAuto extends LinearOpMode {
    public HardwareSubsystem robot;
    public SpecCycleActions specCycleActions;
    public WristAction wristAction;
    public ClawActions clawActions;
    public SlideIntakeActions intakeActions;
    public FoldInAction foldInAction;
    public FiveSpecPath drive;

    enum Path {
        GRAB1,
        GRAB2,
        GRAB3,
        END
    }

    public static long GRAB_TIME = 800;

    public static long WAIT_TIME = 1000;

    public static Vector2d PRELOAD = new Vector2d(7, 32);
    public static Vector2d FIRSTGRAB = new Vector2d(-47.7, 52);
    public static Vector2d SECONDGRAB = new Vector2d(-35, 37);
    public static Vector2d THIRDGRAB = new Vector2d(-42, 34);
    public static Vector2d HPZONE = new Vector2d(-15, 54);
    public static Vector2d PARK = new Vector2d(-40, 60.5);

    public static Pose2d StartPose = new Pose2d(-40, 60.5, Math.toRadians(270));
    public static Pose2d FirstGrab = new Pose2d(FIRSTGRAB, Math.toRadians(270));
    public static Pose2d SecondGrab = new Pose2d(SECONDGRAB, Math.toRadians(205));
    public static Pose2d ThirdGrab = new Pose2d(THIRDGRAB, Math.toRadians(200));
    public static Pose2d HPZone = new Pose2d(HPZONE, Math.toRadians(235));

    Path path = Path.GRAB1;

    public void IntakeAction() {
        robot.hover();
        robot.Zintake();
        robot.SampOpen();
        robot.liftHigh();
    }

    public void OuttakeAction() {
        robot.armGrab();
        sleep(100);
        robot.SampClose();
        sleep(200);
        robot.armReset();
        robot.Zouttake();
        robot.liftRest();
    }

    @Override
    public void runOpMode() throws InterruptedException {

        final FtcDashboard dash = FtcDashboard.getInstance();
        List<Action> runningActions = new ArrayList<>();

        robot = new HardwareSubsystem(this);
        specCycleActions = new SpecCycleActions(this);
        wristAction = new WristAction(this);
        clawActions = new ClawActions(this);
        intakeActions = new SlideIntakeActions(this);
        foldInAction = new FoldInAction(this);
        drive = new FiveSpecPath(this);

        robot.robotInit();
        robot.specGrab();
        robot.Intake();

        while (!opModeIsActive() && !isStopRequested()) {
            waitForStart();
            if (opModeIsActive()) {

                switch (path) {
                    case GRAB1:
                        sleep(WAIT_TIME);
                        drive.StrafeToConstantHeading(FIRSTGRAB, StartPose);
                        IntakeAction();
                        sleep(GRAB_TIME);
                        OuttakeAction();
                        drive.StrafeToLinearHeading(HPZONE, Math.toRadians(235), FirstGrab);
                        robot.SampOpen();
                        path = Path.GRAB2;
                    case GRAB2:
                        drive.StrafeToLinearHeading(SECONDGRAB, Math.toRadians(205), HPZone);
                        IntakeAction();
                        robot.horizontalGrab();
                        sleep(GRAB_TIME);
                        OuttakeAction();
                        drive.StrafeToLinearHeading(HPZONE, Math.toRadians(235), SecondGrab);
                        robot.SampOpen();
                        path = Path.GRAB3;
                    case GRAB3:
                        drive.StrafeToLinearHeading(THIRDGRAB, Math.toRadians(200), HPZone);
                        IntakeAction();
                        robot.horizontalGrab();
                        sleep(GRAB_TIME);
                        OuttakeAction();
                        drive.StrafeToLinearHeading(HPZONE, Math.toRadians(235), ThirdGrab);
                        robot.SampOpen();
                        path = Path.END;
                    case END:
                        robot.SampClose();
                        drive.StrafeToConstantHeading(PARK, HPZone);
                }

                List<Action> newActions = new ArrayList<>();
                for (Action action : runningActions) {
                    TelemetryPacket packet = new TelemetryPacket();
                    action.preview(packet.fieldOverlay());
                    if (!action.run(packet)) {
                        continue;
                    }
                    newActions.add(action);
                    dash.sendTelemetryPacket(packet);
                }
                runningActions = newActions;
            }
        }
    }
}