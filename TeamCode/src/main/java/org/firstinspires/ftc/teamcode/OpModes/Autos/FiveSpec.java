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

    public static Pose2d StartPose = new Pose2d(-17.5, 64, Math.toRadians(270));
    public static Pose2d Preload = new Pose2d(-8, 33, Math.toRadians(270));
    public static Pose2d ThirdGrab = new Pose2d(-58, 26, Math.toRadians(180));
    public static Pose2d HPZone = new Pose2d(-50, 6, Math.toRadians(90));
    public static Pose2d Cycle = new Pose2d(-30, 60, Math.toRadians(180));
    public static Pose2d FirstSpec = new Pose2d(-5, 33, Math.toRadians(270));
    public static Pose2d SecondSpec = new Pose2d(-7, 32.4, Math.toRadians(270));
    public static Pose2d ThirdSpec = new Pose2d(-12, 32.1, Math.toRadians(270));
    public static Pose2d FourthSpec = new Pose2d(-12, 32.1, Math.toRadians(270));


    public static Vector2d PRELOAD = new Vector2d(-8, 33);
    public static Vector2d BEFORE = new Vector2d(-32, 36);
    public static Vector2d FIRSTSAMPLE = new Vector2d(-42, 18);
    public static Pose2d FIRST = new Pose2d(-50, 60, Math.toRadians(90));
    public static Vector2d SECONDGRAB = new Vector2d(-50, 37);
    public static Vector2d THIRDGRAB = new Vector2d(-58, 26);
    public static Vector2d HPZONE = new Vector2d(-47, 62);
    public static Vector2d CYCLE = new Vector2d(-30, 60);
    public static Vector2d PARK = new Vector2d(-50, 60);
    public static Vector2d FIRSTSPEC = new Vector2d(-5, 33);
    public static Vector2d SECONDSPEC = new Vector2d(-7, 32.4);
    public static Vector2d THIRDSPEC = new Vector2d(-12, 32.1);
    public static Vector2d FOURHSPEC = new Vector2d(-12, 32.1);



    enum Path {
        START,
        GRAB1,
        GRAB2,
        GRAB3,
        CYCLE1START,
        CYCLE1END,
        CYCLE2START,
        CYCLE2END,
        CYCLE3START,
        CYCLE3END,
        CYCLE4START,
        CYCLE4END,
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
                        sleep(175);
                        claw.open();

                        path = Path.GRAB1;
                    case GRAB1:
                        Actions.runBlocking(
                                drive.actionBuilder(Preload)
                                        .strafeToLinearHeading(BEFORE, Math.toRadians(90))
                                        .strafeToLinearHeading(FIRSTSAMPLE, Math.toRadians(90))
                                        .splineToLinearHeading(FIRST, Math.toRadians(90))
                                        .build());
                        path = Path.GRAB2;
                    case GRAB2:
                        Actions.runBlocking(
                                drive.actionBuilder(HPZone)
                                        .strafeToLinearHeading(SECONDGRAB, Math.toRadians(-125))
                                        .build());
                        path = Path.GRAB3;
                    case GRAB3:
                        Actions.runBlocking(
                                drive.actionBuilder(HPZone)
                                        .strafeToLinearHeading(THIRDGRAB, Math.toRadians(180))
                                        .build());
                        arm.grab();
                        sleep(250);
                        claw.grab();
                        Actions.runBlocking(
                                drive.actionBuilder(ThirdGrab)
                                        .strafeToLinearHeading(HPZONE, Math.toRadians(180))
                                        .build());
                        claw.open();
                        arm.reset();
                        wrist.neutralGrab();
                    case CYCLE1START:
                        Actions.runBlocking(
                                drive.actionBuilder(HPZone)
                                        .strafeToLinearHeading(CYCLE, Math.toRadians(180))
                                        .build());
                        arm.specGrab();
                        sleep(550);
                        claw.grab();

                        path = Path.CYCLE1END;
                    case CYCLE1END:
                        sleep(200);
                        slides.liftBigHigh();
                        arm.reset();

                        Actions.runBlocking(
                                drive.actionBuilder(Cycle)
                                        .strafeToLinearHeading(FIRSTSPEC, Math.toRadians(270))
                                        .build());
                        path = Path.CYCLE2START;
                    case CYCLE2START:
                        sleep(200);
                        slides.liftRest();
                        sleep(150);
                        claw.open();

                        Actions.runBlocking(
                                drive.actionBuilder(FirstSpec)
                                        .strafeToLinearHeading(CYCLE, Math.toRadians(180))
                                        .build());

                        arm.specGrab();
                        sleep(550);
                        claw.grab();
                        path = Path.CYCLE2END;
                    case CYCLE2END:
                        sleep(250);
                        slides.liftBigHigh();
                        arm.reset();

                        Actions.runBlocking(
                                drive.actionBuilder(Cycle)
                                        .strafeToLinearHeading(SECONDSPEC, Math.toRadians(270))
                                        .build());
                        path = Path.CYCLE3START;
                    case CYCLE3START:
                        sleep(200);
                        slides.liftRest();
                        sleep(150);
                        claw.open();

                        Actions.runBlocking(
                                drive.actionBuilder(SecondSpec)
                                        .strafeToLinearHeading(CYCLE, Math.toRadians(180))
                                        .build());

                        arm.specGrab();
                        sleep(550);
                        claw.grab();
                        path = Path.CYCLE3END;
                    case CYCLE3END:
                        sleep(250);
                        slides.liftHigh();
                        arm.reset();

                        Actions.runBlocking(
                                drive.actionBuilder(Cycle)
                                        .strafeToLinearHeading(THIRDSPEC, Math.toRadians(270))
                                        .build());
                        path = Path.CYCLE4START;
                    case CYCLE4START:
                        sleep(200);
                        slides.liftRest();
                        sleep(150);
                        claw.open();

                        Actions.runBlocking(
                                drive.actionBuilder(ThirdSpec)
                                        .strafeToLinearHeading(CYCLE, Math.toRadians(180))
                                        .build());

                        arm.specGrab();
                        sleep(550);
                        claw.grab();
                        path = Path.CYCLE4END;
                    case CYCLE4END:
                        sleep(250);
                        slides.liftHigh();
                        arm.reset();

                        Actions.runBlocking(
                                drive.actionBuilder(Cycle)
                                        .strafeToLinearHeading(FOURHSPEC, Math.toRadians(270))
                                        .build());
                        path = Path.END;
                    case END:
                        Actions.runBlocking(
                                drive.actionBuilder(FourthSpec)
                                        .strafeToLinearHeading(PARK, Math.toRadians(270))
                                        .build());
                        break;
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
