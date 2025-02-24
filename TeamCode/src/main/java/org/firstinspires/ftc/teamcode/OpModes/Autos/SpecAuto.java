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
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Sensor;
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
    //public Sensor sensor;

    public static Pose2d StartPose = new Pose2d(-17.5, 64, Math.toRadians(270));
    public static Pose2d Preload = new Pose2d(-8, 33, Math.toRadians(270));
    public static Pose2d FirstGrab = new Pose2d(-40, 35, Math.toRadians(-132));
    public static Pose2d SecondGrab = new Pose2d(-50, 36, Math.toRadians(-132));
    public static Pose2d HPZone = new Pose2d(-47, 64, Math.toRadians(180));
    public static Pose2d Cycle = new Pose2d(-26, 63.5, Math.toRadians(180));
    public static Pose2d FirstSpec = new Pose2d(-5, 32.4, Math.toRadians(270));
    public static Pose2d SecondSpec = new Pose2d(-7.5, 31.8, Math.toRadians(270));
    public static Pose2d ThirdSpec = new Pose2d(-3, 30.8, Math.toRadians(270));

    public static Vector2d PRELOAD = new Vector2d(-8, 33);
    public static Vector2d FIRSTGRAB = new Vector2d(-40, 35);
    public static Vector2d SECONDGRAB = new Vector2d(-50, 36);
    public static Vector2d HPZONE = new Vector2d(-47, 64);
    public static Vector2d CYCLE = new Vector2d(-26, 63.5);
    public static Vector2d PARK = new Vector2d(-8, 35);
    public static Vector2d FIRSTSPEC = new Vector2d(-5, 32.4);
    public static Vector2d SECONDSPEC = new Vector2d(-7.5, 31.8);
    public static Vector2d THIRDSPEC = new Vector2d(-3, 30.8);

    public static double DISTANCE = 4;



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
        //sensor = new Sensor(this);

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
                                        .strafeToLinearHeading(FIRSTGRAB, Math.toRadians(-130))
                                        .build());
                        arm.grab();
                        wrist.specGrab();
                        sleep(250);
                        claw.grab();
                        sleep(350);
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
                        sleep(250);
                        claw.grab();
                        sleep(350);
                        arm.specGrab();
                        Actions.runBlocking(
                                drive.actionBuilder(SecondGrab)
                                        .strafeToLinearHeading(HPZONE, Math.toRadians(-180))
                                        .build());
                        claw.open();
                        sleep(250);
                        arm.reset();
                        wrist.neutralGrab();
                        path = Path.CYCLE1START;
                    case CYCLE1START:
                        Actions.runBlocking(
                                drive.actionBuilder(HPZone)
                                        .strafeToLinearHeading(CYCLE, Math.toRadians(180))
                                        .build());
                        arm.specGrab();
                        sleep(250);
                        arm.auto();
                        sleep(150);
                        claw.grab();

                        path = Path.CYCLE1END;
                    case CYCLE1END:
                        sleep(350);
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
                        sleep(250);
                        arm.auto();
                        sleep(250);
                        claw.grab();
                        path = Path.CYCLE2END;
                    case CYCLE2END:
                        sleep(350);
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
                        sleep(250);
                        arm.auto();
                        sleep(250);
                        claw.grab();
                        path = Path.CYCLE3END;
                    case CYCLE3END:
                        sleep(350);
                        slides.liftBigHigh();
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
                        slides.liftRest();
                        wrist.neutralGrab();
                        arm.reset();
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
