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

    public static double FIRSTSAMPLE_X = -49;
    public static double SECONDSAMPLE_X = -61;


    public static Pose2d StartPose = new Pose2d(-17.5, 64, Math.toRadians(270));
    public static Pose2d Preload = new Pose2d(-8, 32, Math.toRadians(270));
    public static Pose2d FirstGrab = new Pose2d(FIRSTSAMPLE_X, 36, Math.toRadians(90));
    public static Pose2d SecondGrab = new Pose2d(SECONDSAMPLE_X, 60, Math.toRadians(90));
    public static Pose2d ThirdGrab = new Pose2d(-61, 36, Math.toRadians(180));
    public static Pose2d HPZone = new Pose2d(-50, 6, Math.toRadians(90));
    public static Pose2d Cycle = new Pose2d(-30, 60, Math.toRadians(180));
    public static Pose2d FirstSpec = new Pose2d(-5, 33, Math.toRadians(270));
    public static Pose2d SecondSpec = new Pose2d(-7, 32.4, Math.toRadians(270));
    public static Pose2d ThirdSpec = new Pose2d(-12, 32.1, Math.toRadians(270));
    public static Pose2d FourthSpec = new Pose2d(-12, 32.1, Math.toRadians(270));


    public static Vector2d PRELOAD = new Vector2d(-8,32);
    //TODO: MAKE A DOUBLE FOR THE HUMAN PLAYER ZONE
    public static Vector2d FIRST_GRAB = new Vector2d(FIRSTSAMPLE_X, 36);
    public static Vector2d SECOND_GRAB = new Vector2d(-60, 34);
    public static Vector2d THIRD_GRAB = new Vector2d(-61, 36);
//    public static Vector2d HP_ZONE = new Vector2d(-47, 62);
    public static Vector2d CYCLE = new Vector2d(-30, 60);
    public static Vector2d PARK = new Vector2d(-50, 60);
    public static Vector2d FIRST_SPEC = new Vector2d(-5, 33);
    public static Vector2d SECOND_SPEC = new Vector2d(-7, 32.4);
    public static Vector2d THIRD_SPEC = new Vector2d(-12, 32.1);
    public static Vector2d FOURTH_SPEC = new Vector2d(-12, 32.1);



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

        StrafeChassis.PARAMS.maxWheelVel = 50;
        StrafeChassis.PARAMS.minProfileAccel = -50;
        StrafeChassis.PARAMS.maxProfileAccel = 50;

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

                        slides.liftBigHigh();
                        Actions.runBlocking(
                                drive.actionBuilder(StartPose)
                                        .strafeToLinearHeading(PRELOAD, Math.toRadians(270))
                                        .build());
                        slides.liftRest();
                        sleep(150);
                        claw.open();

                        path = Path.GRAB1;

                    case GRAB1:
                        Actions.runBlocking(
                                drive.actionBuilder(Preload)
                                        .strafeToLinearHeading(FIRST_GRAB, Math.toRadians(90))
                                        .build());
                        specArm.intake();
                        sleep(150);
                        specArm.grab();
                        Actions.runBlocking(
                                drive.actionBuilder(FirstGrab)
                                        .strafeToLinearHeading(HUMAN_PLAYER_ZONE_VECTOR(FIRSTSAMPLE_X, 60), Math.toRadians(90))
                                        .build());

                        specArm.outtake();
                        specArm.open();

                        path = Path.GRAB2;

                    case GRAB2:
                        Actions.runBlocking(
                                drive.actionBuilder(HUMAN_PLAYER_ZONE_POSE(FIRSTSAMPLE_X, 60, 90))
                                        .strafeToLinearHeading(SECOND_GRAB, Math.toRadians(90))
                                        .build());
                        specArm.intake();
                        sleep(150);
                        specArm.grab();

                        Actions.runBlocking(
                                drive.actionBuilder(SecondGrab)
                                        .strafeTo(HUMAN_PLAYER_ZONE_VECTOR(SECONDSAMPLE_X, 60))
                                        .build());
                        specArm.outtake();
                        specArm.open();

                        path = Path.GRAB3;

                    case GRAB3:
                        Actions.runBlocking(
                                drive.actionBuilder(HUMAN_PLAYER_ZONE_POSE(SECONDSAMPLE_X, 60, 90))
                                        .strafeToLinearHeading(THIRD_GRAB, Math.toRadians(30))
                                        .build());
                        arm.grab();
                        claw.grab();
                        arm.reset();
//
//                        Actions.runBlocking(
//                                drive.actionBuilder(ThirdGrab)
//
//                        );
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
