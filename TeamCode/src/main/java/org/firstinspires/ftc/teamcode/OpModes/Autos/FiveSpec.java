package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Commandbase.Commands.SpecCycleActions;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.SpecArm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;
import org.firstinspires.ftc.teamcode.RoadRunner.StrafeChassis;

import java.util.ArrayList;
import java.util.List;

@Config
@Autonomous
public class FiveSpec extends LinearOpMode {

    public Claw claw;
    public Wrist wrist;
    public Arm arm;
    public Slides slides;
    public SpecArm specArm;
    public SpecCycleActions specCycleActions;

    public static double FIRSTSAMPLE_X = -49;
    public static double SECONDSAMPLE_X = -61;


    public static Pose2d StartPose = new Pose2d(-17.5, 64, Math.toRadians(270));
    public static Pose2d Preload = new Pose2d(-8, 32, Math.toRadians(270));
    public static Pose2d FirstGrab = new Pose2d(FIRSTSAMPLE_X, 36, Math.toRadians(90));
    public static Pose2d SecondGrab = new Pose2d(SECONDSAMPLE_X, 60, Math.toRadians(90));
    public static Pose2d ThirdGrab = new Pose2d(-61, 36, Math.toRadians(180));
    public static Pose2d HPZone = new Pose2d(-47, 63, Math.toRadians(90));
    public static Pose2d Cycle = new Pose2d(-8, 32, Math.toRadians(180));


    public static Vector2d PRELOAD = new Vector2d(-8,32);
    public static Vector2d FIRST_GRAB = new Vector2d(FIRSTSAMPLE_X, 36);
    public static Vector2d SECOND_GRAB = new Vector2d(-60, 34);
    public static Vector2d THIRD_GRAB = new Vector2d(-61, 36);
    public static Vector2d HP_ZONE = new Vector2d(-47, 63);
    public static Vector2d CYCLE = new Vector2d(-8, 32);



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

    Path path = Path.START;

    @Override
    public void runOpMode() throws InterruptedException {


        StrafeChassis drive = new StrafeChassis(hardwareMap, StartPose);
        final FtcDashboard dash = FtcDashboard.getInstance();
        List<Action> runningActions = new ArrayList<>();

        arm = new Arm(this);
        claw = new Claw(this);
        slides = new Slides(this);
        wrist = new Wrist(this);
        specArm = new SpecArm(this);

        while (!opModeIsActive() && !isStopRequested()) {
            arm.reset();
            claw.grab();
            slides.liftRest();
            wrist.neutralGrab();

            waitForStart();

            if (opModeIsActive()) {

                switch (path) {
                    case START:
                        sleep(150);
                        slides.liftBigHigh();
                        Actions.runBlocking(
                                drive.actionBuilder(StartPose)
                                        .strafeToLinearHeading(PRELOAD, Math.toRadians(270))
                                        .build());

                        path = Path.GRAB1;

                    case GRAB1:
                        sleep(150);
                        Actions.runBlocking(
                                drive.actionBuilder(Preload)
                                        .strafeToLinearHeading(new Vector2d(-25, 35), Math.toRadians(45))
                                        .strafeToLinearHeading(new Vector2d(-49, 35), Math.toRadians(90))
                                        //NOTE: THIS FUCKING SUS AS HELL
                                        .build());

                        Actions.runBlocking(
                                drive.actionBuilder(FirstGrab)
                                        .strafeToLinearHeading(HUMAN_PLAYER_ZONE_VECTOR(FIRSTSAMPLE_X, 60), Math.toRadians(90))
                                        .build());


                        path = Path.GRAB2;

                    case GRAB2:
                        sleep(150);
                        Actions.runBlocking(
                                drive.actionBuilder(HUMAN_PLAYER_ZONE_POSE(FIRSTSAMPLE_X, 60, 90))
                                        .strafeToLinearHeading(SECOND_GRAB, Math.toRadians(90))
                                        .build());

                        Actions.runBlocking(
                                drive.actionBuilder(SecondGrab)
                                        .strafeTo(HUMAN_PLAYER_ZONE_VECTOR(SECONDSAMPLE_X, 60))
                                        .build());

                        path = Path.GRAB3;

                    case GRAB3:
                        sleep(150);
                        Actions.runBlocking(
                                drive.actionBuilder(HUMAN_PLAYER_ZONE_POSE(SECONDSAMPLE_X, 60, 90))
                                        .strafeToLinearHeading(THIRD_GRAB, Math.toRadians(30))
                                        .build());

                        Actions.runBlocking(
                                drive.actionBuilder(ThirdGrab)
                                        .splineToLinearHeading(HPZone, Math.toRadians(90))
                                        .build());
                        claw.open();

                        path = Path.CYCLE1;

                    case CYCLE1:
                        sleep(150);
                        Actions.runBlocking(
                                drive.actionBuilder(HPZone)
                                        .strafeToLinearHeading(CYCLE, Math.toRadians(270))
                                        .build());

                        Actions.runBlocking(
                                drive.actionBuilder(Cycle)
                                        .strafeToLinearHeading(HP_ZONE, Math.toRadians(270))
                                        .build());

                        path = Path.CYCLE2;
                    case CYCLE2:
                        sleep(150);
                        Actions.runBlocking(
                                drive.actionBuilder(HPZone)
                                        .strafeToLinearHeading(CYCLE, Math.toRadians(270))
                                        .build());


                        Actions.runBlocking(
                                drive.actionBuilder(Cycle)
                                        .strafeToLinearHeading(HP_ZONE, Math.toRadians(270))
                                        .build());

                        path = Path.CYCLE3;
                    case CYCLE3:
                        sleep(150);
                        Actions.runBlocking(
                                drive.actionBuilder(HPZone)
                                        .strafeToLinearHeading(CYCLE, Math.toRadians(270))
                                        .build());


                        Actions.runBlocking(
                                drive.actionBuilder(Cycle)
                                        .strafeToLinearHeading(HP_ZONE, Math.toRadians(270))
                                        .build());

                        path = Path.CYCLE4;
                    case CYCLE4:
                        sleep(150);
                        Actions.runBlocking(
                                drive.actionBuilder(HPZone)
                                        .strafeToLinearHeading(CYCLE, Math.toRadians(270))
                                        .build());

                        Actions.runBlocking(
                                drive.actionBuilder(Cycle)
                                        .strafeToLinearHeading(HP_ZONE, Math.toRadians(270))
                                        .build());

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
    public Vector2d HUMAN_PLAYER_ZONE_VECTOR(double x, double y) {
       Vector2d HPZONE = new Vector2d(x, y);
       return HPZONE;
    }
    public Pose2d HUMAN_PLAYER_ZONE_POSE(double x, double y, double r) {
        Pose2d HPZONE = new Pose2d(x, y, Math.toRadians(r));
        return HPZONE;
    }
}
