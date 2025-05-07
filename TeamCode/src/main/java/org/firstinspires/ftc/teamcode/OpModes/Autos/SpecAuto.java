package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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
public class SpecAuto extends LinearOpMode {
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
        END
    }

    public static long GRAB_TIME = 450;
    public static long ARM_TIME = 250;
    public static long CLAW_TIME = 200;
    public static long LINE_UP = 250;

    public static double MinAccel = -30, MaxAccel = 30, WheelVel = 30;

    public static Vector2d PRELOAD = new Vector2d(-2, 29);
    public static Vector2d FIRSTGRAB = new Vector2d(-47, 52);
    public static Vector2d SECONDGRAB = new Vector2d(-59, 52);
    public static Vector2d THIRDGRAB = new Vector2d(-62, 52);
    public static Vector2d HPZONE = new Vector2d(-54, 57);
    public static Vector2d CYCLE = new Vector2d(-32, 63);
    public static Vector2d PARK = new Vector2d(-8, 48);
    public static Vector2d FIRSTSPEC = new Vector2d(-3, 29);
    public static Vector2d SECONDSPEC = new Vector2d(0, 29);
    public static Vector2d THIRDSPEC = new Vector2d(3, 29);
    public static Vector2d FOURTHSPEC = new Vector2d(6, 29);

    public static Pose2d StartPose = new Pose2d(-6, 63.5, Math.toRadians(270));
    public static Pose2d Preload = new Pose2d(PRELOAD, Math.toRadians(270));
    public static Pose2d FirstGrab = new Pose2d(FIRSTGRAB, Math.toRadians(270));
    public static Pose2d ThirdGrab = new Pose2d(THIRDGRAB, Math.toRadians(270));
    public static Pose2d SecondGrab = new Pose2d(SECONDGRAB, Math.toRadians(270));
    public static Pose2d HPZone = new Pose2d(HPZONE, Math.toRadians(270));
    public static Pose2d Cycle = new Pose2d(CYCLE, Math.toRadians(270));
    public static Pose2d FirstSpec = new Pose2d(FIRSTSPEC, Math.toRadians(270));
    public static Pose2d SecondSpec = new Pose2d(SECONDSPEC, Math.toRadians(270));
    public static Pose2d ThirdSpec = new Pose2d(THIRDSPEC, Math.toRadians(270));
    public static Pose2d FourthSpec = new Pose2d(FOURTHSPEC, Math.toRadians(270));

    Path path = Path.PRELOAD;

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


        while (!opModeIsActive() && !isStopRequested()) {

            waitForStart();

            if (opModeIsActive()) {

                switch (path) {
                    case PRELOAD:
                        robot.Mid();
                        drive.StrafeToConstantHeading(PRELOAD, StartPose);
                        //sleep(LINE_UP);
                        robot.Outtake();
                        robot.specOpen();
                        path = Path.GRAB1;
                    case GRAB1:
                        drive.StrafeToConstantHeading(FIRSTGRAB, Preload);
                        robot.Intake();
                        path = Path.GRAB2;
                    case GRAB2:
                        sleep(LINE_UP);
                        intakeActions.actionAuto(runningActions, dash, true);
                        sleep(CLAW_TIME);
                        intakeActions.actionAuto(runningActions, dash, false);
                        robot.SampOpen();
                        drive.StrafeToConstantHeading(SECONDGRAB, FirstGrab);
                        path = Path.GRAB3;
                    case GRAB3:
                        sleep(LINE_UP);
                        intakeActions.actionAuto(runningActions, dash, true);
                        sleep(CLAW_TIME);
                        intakeActions.actionAuto(runningActions, dash, false);
                        robot.SampOpen();
                        drive.StrafeToConstantHeading(THIRDGRAB, SecondGrab);
                        path = Path.CYCLE1;
                    case CYCLE1:
                        drive.StrafeWAction(CYCLE, ThirdGrab, new InstantAction(robot::specGrab), 0.5);
                        robot.Mid();
                        robot.specNuetral();
                        drive.StrafeToConstantHeading(FIRSTSPEC, Cycle);
                        robot.Outtake();
                        robot.specOpen();
                        path = Path.CYCLE2;
                    case CYCLE2:
                        drive.StrafeWAction(CYCLE, FirstSpec, new InstantAction(robot::RTPIntake), 0.25);

                        drive.StrafeToConstantHeading(FIRSTSPEC, Cycle);
                        robot.Outtake();
                        robot.specOpen();
                        path = Path.CYCLE3;
                   /* case CYCLE3:
                        specServos.nuetral();
                        drive.StrafeFastWAction(CYCLE, SecondSpec,MinAccel, MaxAccel, WheelVel, new InstantAction(spec::Intake));
                        specServos.grab();
                        sleep(GRAB_TIME);
                        spec.Mid();
                        specServos.score();
                        drive.StrafeFast(THIRDSPEC, Cycle, MinAccel, MaxAccel, WheelVel);
                        spec.Outtake();
                        specServos.open();
                        sleep(CLAW_TIME);
                        path = Path.CYCLE4;
                    case CYCLE4:
                        specServos.nuetral();
                        drive.StrafeFastWAction(CYCLE, ThirdSpec, MinAccel, MaxAccel, WheelVel, new InstantAction(spec::Intake));
                        specServos.grab();
                        sleep(GRAB_TIME);
                        spec.Mid();
                        specServos.score();
                        drive.StrafeFast(FOURTHSPEC, Cycle, MinAccel, MaxAccel, WheelVel);
                        spec.Outtake();
                        specServos.open();
                        sleep(CLAW_TIME);
                        path = Path.END;
                    case END:*/
                       /* specServos.nuetral();
                        drive.StrafeToConstantHeading(PARK, FourthSpec);
                        wrist.neutralGrab();
                        arm.specGrab();
                        spec.Intake();*/
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

//public Velconstratint name; name =new TransVelConstraint(num)