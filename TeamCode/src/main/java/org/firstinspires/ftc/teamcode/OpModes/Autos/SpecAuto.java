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
public class SpecAuto extends LinearOpMode {

    public Claw claw;
    public Wrist wrist;
    public Arm arm;
    public Slides slides;

    public static Pose2d StartPose = new Pose2d(-17.5, 64, Math.toRadians(270));
    public static Pose2d Preload = new Pose2d(-8, 33.6, Math.toRadians(270));
    //Was 41
    public static Pose2d FirstGrab = new Pose2d(-39, 35, Math.toRadians(-130));
    public static Pose2d SecondGrab = new Pose2d(-50, 37, Math.toRadians(-125));
    public static Pose2d HPZone = new Pose2d(-47, 62, Math.toRadians(180));
    public static Pose2d Cycle = new Pose2d(-30, 60, Math.toRadians(180));
    public static Pose2d FirstSpec = new Pose2d(-3, 33, Math.toRadians(270));
    public static Pose2d SecondSpec = new Pose2d(-7, 33, Math.toRadians(270));
    public static Pose2d ThirdSpec = new Pose2d(-12, 32.1, Math.toRadians(270));

    public static Vector2d PRELOAD = new Vector2d(-8, 33.575);
    public static Vector2d FIRSTGRAB = new Vector2d(-39, 35);
    //52.5
    public static Vector2d SECONDGRAB = new Vector2d(-50, 37);
    public static Vector2d HPZONE = new Vector2d(-47, 62);
    public static Vector2d CYCLE = new Vector2d(-30, 60);
    public static Vector2d PARK = new Vector2d(-50, 60);
    public static Vector2d FIRSTSPEC = new Vector2d(-3, 33);
    public static Vector2d SECONDSPEC = new Vector2d(-7, 33);
    public static Vector2d THIRDSPEC = new Vector2d(-12, 32.1);



    enum Path {
        START,
        GRAB1,
        GRAB2,
        CYCLE1START,
        CYCLE1END,
        CYCLE2START,
        CYCLE2END,
        CYCLE3START,
        CYCLE3END,
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
                                        //.waitSeconds(0.25)
                                        .build());
                        slides.liftRest();
                        sleep(175);
                        claw.open();

                        path = Path.GRAB1;
                    case GRAB1:
                        Actions.runBlocking(
                                drive.actionBuilder(Preload)
                                        .strafeToLinearHeading(FIRSTGRAB, Math.toRadians(-130))
                                        .build());
                        arm.grab();
                        wrist.specGrab();
                        sleep(450);
                        claw.grab();
                        sleep(250);
                        arm.specGrab();
                        Actions.runBlocking(
                                drive.actionBuilder(FirstGrab)
                                        .strafeToLinearHeading(HPZONE, Math.toRadians(-180))
                                        .build());
                        path = Path.GRAB2;
                    case GRAB2:
                        claw.open();
                        sleep(250);
                        Actions.runBlocking(
                                drive.actionBuilder(HPZone)
                                        .strafeToLinearHeading(SECONDGRAB, Math.toRadians(-125))
                                        .build());
                        arm.grab();
                        sleep(500);
                        claw.grab();
                        sleep(250);
                        arm.specGrab();
                        Actions.runBlocking(
                                drive.actionBuilder(SecondGrab)
                                        .strafeToLinearHeading(HPZONE, Math.toRadians(-180))
                                        .build());
                        claw.open();
                        arm.reset();
                        wrist.neutralGrab();
                        path = Path.CYCLE1START;
                    case CYCLE1START:
                        Actions.runBlocking(
                                drive.actionBuilder(HPZone)
                                        .strafeToLinearHeading(CYCLE, Math.toRadians(180))
                                        .build());
                        arm.grab();
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

                        arm.grab();
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

                        arm.grab();
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


                        sleep(200);
                        slides.liftRest();
                        sleep(150);
                        claw.open();
                                path = Path.END;
                    case END:
                        Actions.runBlocking(
                                drive.actionBuilder(ThirdSpec)
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
