package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Commandbase.Commands.Testing;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.AutoCommands;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.FiveSpecPath;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SpecCycleActions;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.RTPSpecArm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.SpecArm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

import java.util.ArrayList;
import java.util.List;

public class JoelAuto extends LinearOpMode {
    public Claw claw;
    public Wrist wrist;
    public Arm arm;
    public Slides slides;
    public RTPSpecArm spec;
    public SpecArm specHardware;
    public FiveSpecPath drive;
    public AutoCommands commands;
    public SpecCycleActions specCycleActions;
    public Testing testing;

    enum Path {
        START,
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

    public static double MinAccel = -40, MaxAccel = 40, WheelVel = 40;

    public static Vector2d PRELOAD = new Vector2d(-2, 29);
    public static Pose2d StartPose = new Pose2d(-6, 63.5, Math.toRadians(90));

    public static Vector2d FIRSTGRAB = new Vector2d(-48, 50);
    public static Pose2d FirstGrab = new Pose2d(FIRSTGRAB, Math.toRadians(90));

    public static Vector2d SECONDGRAB = new Vector2d(-56, 50);
    public static Pose2d SecondGrab = new Pose2d(SECONDGRAB, Math.toRadians(90));

    public static Vector2d THIRDGRAB = new Vector2d(-62, 50);
    public static Pose2d ThirdGrab = new Pose2d(THIRDGRAB, Math.toRadians(255));

    public static Vector2d HPZONE = new Vector2d(-54, 57);
    public static Pose2d HPZone = new Pose2d(HPZONE, Math.toRadians(90));

    public static Vector2d CYCLE = new Vector2d(-32, 60);
    public static Pose2d Cycle = new Pose2d(CYCLE, Math.toRadians(90));

    public static Vector2d FIRSTSPEC = new Vector2d(-2, 29);
    public static Pose2d FirstSpec = new Pose2d(FIRSTSPEC, Math.toRadians(90));

    public static Vector2d SECONDSPEC = new Vector2d(0, 29);
    public static Pose2d SecondSpec = new Pose2d(SECONDSPEC, Math.toRadians(90));

    public static Vector2d THIRDSPEC = new Vector2d(3, 29);
    public static Pose2d ThirdSpec = new Pose2d(THIRDSPEC, Math.toRadians(90));

    public static Vector2d FOURTHSPEC = new Vector2d(6, 29);
    public static Pose2d FourthSpec = new Pose2d(FOURTHSPEC, Math.toRadians(90));

    public static Vector2d PARK = new Vector2d(-8, 48);


    Path path = Path.START;

    @Override
    public void runOpMode() throws InterruptedException {

        final FtcDashboard dash = FtcDashboard.getInstance();
        List<Action> runningActions = new ArrayList<>();


        arm = new Arm(this);
        claw = new Claw(this);
        slides = new Slides(this);
        wrist = new Wrist(this);
        spec = new RTPSpecArm(this);
        specHardware = new SpecArm(this);
        drive = new FiveSpecPath(this);
        commands = new AutoCommands(this);
        specCycleActions = new SpecCycleActions(this);


        while (!opModeIsActive() && !isStopRequested()) {
            arm.reset();
            claw.open();
            slides.liftRest();
            wrist.specGrab();
            spec.Intake();
            specHardware.score();
            specHardware.open();

            waitForStart();

            if (opModeIsActive()) {
                switch (path) {
                    case START:
                        drive.StrafeToConstantHeading(PRELOAD, StartPose);
                        specCycleActions.actionAuto(runningActions, dash, true);

                    case GRAB1:
                        drive.StrafeToConstantHeading(FIRSTGRAB, FirstGrab);
                        //SUB-ACTIONS GO HERE, remember drivetrain actions for parallel command groups

                    case GRAB2:
                        drive.StrafeToConstantHeading(SECONDGRAB, SecondGrab);

                    case GRAB3:
                        drive.StrafeToLinearHeading(THIRDGRAB, Math.toRadians(225), ThirdGrab);

                    case CYCLE1:
                        drive.StrafeToLinearHeading(CYCLE, Math.toRadians(270), Cycle);
                        specCycleActions.actionAuto(runningActions, dash, false);
                        drive.StrafeToConstantHeading(FIRSTSPEC, FirstSpec);
                        specCycleActions.actionAuto(runningActions, dash, true);

                    case CYCLE2:
                        drive.StrafeToConstantHeading(CYCLE, Cycle);

                        specCycleActions.actionAuto(runningActions, dash, false);
                        drive.StrafeToConstantHeading(SECONDSPEC, SecondSpec);
                        specCycleActions.actionAuto(runningActions, dash, true);
                        
                    case CYCLE3:
                        drive.StrafeToConstantHeading(CYCLE, Cycle);
                        specCycleActions.actionAuto(runningActions, dash, false);
                        drive.StrafeToConstantHeading(THIRDGRAB, ThirdGrab);
                        specCycleActions.actionAuto(runningActions, dash, true);

                    case CYCLE4:
                        drive.StrafeToConstantHeading(CYCLE, Cycle);
                        specCycleActions.actionAuto(runningActions, dash, false);
                        drive.StrafeToConstantHeading(FOURTHSPEC, FourthSpec);
                        specCycleActions.actionAuto(runningActions, dash, true);

                    case END:
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
