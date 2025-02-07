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

    enum Path {
        START,
        PUSH1,
        PUSH2,
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

        Pose2d StartPose = new Pose2d(-17.5, 64, Math.toRadians(270));
        Pose2d Preload = new Pose2d(-8, 32, Math.toRadians(270));
        Pose2d FirstPush = new Pose2d(-50, 55, Math.toRadians(90));
        Pose2d SecondPush = new Pose2d(-63, 55, Math.toRadians(90));
        Pose2d Cycle = new Pose2d(-30, 60, Math.toRadians(180));
        Pose2d SecondSpec = new Pose2d(-7, 32, Math.toRadians(270));
        Pose2d ThirdSpec = new Pose2d(-3, 32, Math.toRadians(270));



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
                       slides.liftHigh();
                        Actions.runBlocking(
                                drive.actionBuilder(StartPose)
                                        .splineToLinearHeading(new Pose2d(-8,32, Math.toRadians(270)), Math.toRadians(270))
                                        .waitSeconds(0.25)
                                        .build());
                        slides.liftRest();
                        sleep(100);
                        claw.open();

                    path = Path.PUSH1;
                    case PUSH1:
                        Actions.runBlocking(
                                drive.actionBuilder(Preload)
                                        .turn(Math.toRadians(180))
                                        .strafeToLinearHeading(new Vector2d(-25.5, 30), Math.toRadians(90))
                                        .splineToLinearHeading(new Pose2d(-50 , 10, Math.toRadians(90)), Math.toRadians(90))
                                        .waitSeconds(0.25)
                                        .strafeToConstantHeading(new Vector2d(-50, 55))
                                        .build());
                        path = Path.PUSH2;
                    case PUSH2:
                        Actions.runBlocking(
                                drive.actionBuilder(FirstPush)
                                        .strafeToConstantHeading(new Vector2d(-50, 19))
                                        .splineToLinearHeading(new Pose2d(-57, 19, Math.toRadians(90)), Math.toRadians(90))
                                        .waitSeconds(0.25)
                                        .strafeToConstantHeading(new Vector2d(-63, 55))
                                        .build());
                        path = Path.CYCLE1START;
                    case CYCLE1START:
                        Actions.runBlocking(
                                drive.actionBuilder(SecondPush)
                                        .strafeToLinearHeading(new Vector2d(-30, 60), Math.toRadians(180))
                                        .build());
                        arm.grab();
                        sleep(400);
                        claw.grab();

                        path = Path.CYCLE1END;
                    case CYCLE1END:
                        sleep(200);
                        slides.liftHigh();
                        arm.reset();

                        Actions.runBlocking(
                                drive.actionBuilder(Cycle)
                                        .setTangent(0)
                                        .splineToLinearHeading(new Pose2d(-7, 32, Math.toRadians(270)), Math.toRadians(270))
                                        .build());
                        path = Path.CYCLE2START;
                    case CYCLE2START:
                        slides.liftRest();
                        sleep(150);
                        claw.open();

                        Actions.runBlocking(
                                drive.actionBuilder(SecondSpec)
                                        .strafeToLinearHeading(new Vector2d(-32, 60), Math.toRadians(180))
                                        .build());

                        arm.grab();
                        sleep(450);
                        claw.grab();
                        path = Path.CYCLE2END;
                    case CYCLE2END:
                        sleep(250);
                        slides.liftHigh();
                        arm.reset();

                        Actions.runBlocking(
                                drive.actionBuilder(Cycle)
                                        .setTangent(0)
                                        .splineToLinearHeading(new Pose2d(-3, 32, Math.toRadians(270)), Math.toRadians(270))
                                        .build());
                        path = Path.CYCLE3START;
                    case CYCLE3START:
                        slides.liftRest();
                        sleep(150);
                        claw.open();

                        Actions.runBlocking(
                                drive.actionBuilder(ThirdSpec)
                                        .strafeToLinearHeading(new Vector2d(-32, 60), Math.toRadians(180))
                                        .build());

                        arm.grab();
                        sleep(450);
                        claw.grab();
                        path = Path.CYCLE3END;
                    case CYCLE3END:
                        sleep(250);
                        slides.liftHigh();
                        arm.reset();

                        Actions.runBlocking(
                                drive.actionBuilder(Cycle)
                                        .setTangent(0)
                                        .splineToLinearHeading(new Pose2d(-10, 32, Math.toRadians(270)), Math.toRadians(270))
                                        .build());
                        path = Path.END;
                    case END:
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
