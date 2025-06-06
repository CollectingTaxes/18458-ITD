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
public class Queso extends LinearOpMode {
    public HardwareSubsystem robot;
    public SpecCycleActions specCycleActions;
    public WristAction wristAction;
    public ClawActions clawActions;
    public SlideIntakeActions intakeActions;
    public FoldInAction foldInAction;
    public FiveSpecPath drive;

    enum Path {
        START,
        PRELOAD,
        GRAB1,
        GRAB2,
        GRAB3,
        CYCLE1,
        CYCLE2,
        CYCLE3,
        CYCLE4,
        CYCLE5,
        END
    }

    public static long GRAB_TIME = 800;
    public static long ARM_TIME = 75;
    public static long CLAW_TIME = 200;
    public static long DROP_TIME = 750;

    public static double MinAccel = -57.5, MaxAccel = 57.5, WheelVel = 57.5;

    public static Vector2d PRELOAD = new Vector2d(7, 32);
    public static Vector2d FIRSTGRAB = new Vector2d(-47.4, 52);
    public static Vector2d SECONDGRAB = new Vector2d(-34, 37);
    public static Vector2d THIRDGRAB = new Vector2d(-43.5, 34);
    public static Vector2d HPZONE = new Vector2d(-8.5, 56);
    public static Vector2d CYCLE = new Vector2d(18, 58.5);
    public static Vector2d PARK = new Vector2d(29, 31);
    public static Vector2d FIRSTSPEC = new Vector2d(21 , 26);
    public static Vector2d SECONDSPEC = new Vector2d(23, 26.5);
    public static Vector2d THIRDSPEC = new Vector2d(26, 26.5);
    public static Vector2d FOURTHSPEC = new Vector2d(27 , 26);
    public static Vector2d FIFTHSPEC = new Vector2d(30, 26.5);

    public static Vector2d LASTGRAB = new Vector2d(-43, 60.5);

    public static Pose2d StartPose = new Pose2d(-40, 60.5, Math.toRadians(270));
    public static Pose2d FirstGrab = new Pose2d(FIRSTGRAB, Math.toRadians(270));
    public static Pose2d SecondGrab = new Pose2d(SECONDGRAB, Math.toRadians(207));
    public static Pose2d ThirdGrab = new Pose2d(THIRDGRAB, Math.toRadians(198));
    public static Pose2d HPZone = new Pose2d(HPZONE, Math.toRadians(235));
    public static Pose2d Cycle = new Pose2d(CYCLE, Math.toRadians(270));
    public static Pose2d FirstSpec = new Pose2d(FIRSTSPEC, Math.toRadians(270));
    public static Pose2d SecondSpec = new Pose2d(SECONDSPEC, Math.toRadians(270));
    public static Pose2d ThirdSpec = new Pose2d(THIRDSPEC, Math.toRadians(270));
    public static Pose2d FourthSpec = new Pose2d(FOURTHSPEC, Math.toRadians(270));
    public static Pose2d FifthSpec = new Pose2d(FIFTHSPEC, Math.toRadians(270));
    public static Pose2d LastGrab = new Pose2d(LASTGRAB, Math.toRadians(270));

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
        robot.specOpen();
        robot.Intake();

        while (!opModeIsActive() && !isStopRequested()) {
            waitForStart();
            if (opModeIsActive()) {

                switch (path) {
                    case GRAB1:
                        drive.StrafeToConstantHeading(FIRSTGRAB, StartPose);
                        IntakeAction();
                        sleep(GRAB_TIME);
                        OuttakeAction();
                        drive.StrafeToLinearHeading(HPZONE, Math.toRadians(235), FirstGrab);
                        robot.SampOpen();
                        path = Path.GRAB2;
                    case GRAB2:
                        drive.StrafeToLinearHeading(SECONDGRAB, Math.toRadians(207), HPZone);
                        IntakeAction();
                        robot.horizontalGrab();
                        sleep(GRAB_TIME);
                        OuttakeAction();
                        robot.neutralGrab();
                        drive.StrafeToLinearHeading(HPZONE, Math.toRadians(235), SecondGrab);
                        robot.SampOpen();
                        path = Path.GRAB3;
                    case GRAB3:
                        drive.StrafeToLinearHeading(THIRDGRAB, Math.toRadians(198), HPZone);
                        IntakeAction();
                        robot.horizontalGrab();
                        sleep(GRAB_TIME);
                        OuttakeAction();
                        robot.neutralGrab();
                        drive.StrafeToLinearHeading(HPZONE, Math.toRadians(235), ThirdGrab);
                        robot.SampOpen();
                    case CYCLE1:
                        robot.Zintake();
                        robot.neutralGrab();
                        drive.StrafeToLinearHeading(CYCLE, Math.toRadians(270), HPZone);
                        robot.specGrab();
                        sleep(CLAW_TIME);
                        robot.Outtake();
                        drive.StrafeFast(FIRSTSPEC, Cycle, MinAccel, MaxAccel, WheelVel);
                        robot.Intake();
                        sleep(ARM_TIME);
                        robot.specOpen();
                        path = Path.CYCLE2;
                    case CYCLE2:
                        drive.StrafeToConstantHeading(CYCLE, FirstSpec);
                        robot.specGrab();
                        sleep(CLAW_TIME);
                        robot.Outtake();
                        drive.StrafeFast(SECONDSPEC, Cycle, MinAccel, MaxAccel, WheelVel);
                        robot.Intake();
                        sleep(ARM_TIME);
                        robot.specOpen();
                        path = Path.CYCLE3;
                    case CYCLE3:
                        drive.StrafeToConstantHeading(CYCLE, SecondSpec);
                        robot.specGrab();
                        sleep(CLAW_TIME);
                        robot.Outtake();
                        drive.StrafeFast(THIRDSPEC, Cycle, MinAccel, MaxAccel, WheelVel);
                        robot.Intake();
                        sleep(ARM_TIME);
                        robot.specOpen();
                        path = Path.CYCLE4;
                    case CYCLE4:
                        drive.StrafeToConstantHeading(CYCLE, ThirdSpec);
                        robot.specGrab();
                        sleep(CLAW_TIME);
                        robot.Outtake();
                        drive.StrafeFast(FOURTHSPEC, Cycle, MinAccel, MaxAccel, WheelVel);
                        robot.Intake();
                        sleep(ARM_TIME);
                        robot.specOpen();
                        path = Path.CYCLE5;
                    case CYCLE5:
                        drive.StrafeToConstantHeading(CYCLE, FourthSpec);
                        robot.specGrab();
                        sleep(CLAW_TIME);
                        robot.Last();
                        drive.StrafeFast(FIFTHSPEC, Cycle, MinAccel, MaxAccel, WheelVel);
                        robot.Intake();
                        sleep(ARM_TIME);
                        robot.specOpen();
                    case END:
                        robot.SampClose();
                        drive.StrafeToConstantHeading(PARK, FifthSpec);
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