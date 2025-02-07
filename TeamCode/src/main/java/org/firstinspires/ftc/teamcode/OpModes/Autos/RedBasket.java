package org.firstinspires.ftc.teamcode.OpModes.Autos;

import android.os.Build;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.spline.PoseWithCurvature;
import com.pedropathing.localization.Pose;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;
import org.firstinspires.ftc.teamcode.RoadRunner.StrafeChassis;

import java.util.ArrayList;
import java.util.List;

@Autonomous
public class RedBasket extends LinearOpMode {
    public Claw claw;
    public Wrist wrist;
    public StrafeChassis drivetrain;
    public Arm arm;
    public Slides slides;
    public Telemetry telemetry;
    public List<Action> runningActions = new ArrayList<>();
    private final FtcDashboard dash = FtcDashboard.getInstance();

    enum Path {
        STARTING,
        PRELOAD,
        TOYELLOWBLOCKS,
        CYCLEONE,
        CYCLETWO,
        CYCLETHREE,
        END,
    }
    Path path = Path.STARTING;
    @Override
    public void runOpMode() throws InterruptedException {
        arm = new Arm(this);
        claw = new Claw(this);
        drivetrain = new StrafeChassis(hardwareMap, new Pose2d(0, 0, Math.toRadians(0)));
        slides = new Slides(this);
        wrist = new Wrist(this);

        Pose2d starting = new Pose2d(-6, -64, -300);
        Pose2d preload = new Pose2d(-7, 30, -300);
        Pose2d toYellowBlocks = new Pose2d(-48, -36, Math.toRadians(90));
        Pose2d cycleOne = new Pose2d(-58, -36, Math.toRadians(90));
        Pose2d cycleTwo = new Pose2d(-52, -52, Math.toRadians(220));
        Pose2d cycleThree = new Pose2d(-61, -34, Math.toRadians(145));
        Pose2d end = new Pose2d(-52, -52, Math.toRadians(220));

        waitForStart();


        if (opModeIsActive()) {

            switch (path) {
                case STARTING:
                    Actions.runBlocking(
                            drivetrain.actionBuilder(starting)
                                    .strafeToConstantHeading(new Vector2d(-7, -30))
                                    .waitSeconds(0.5)
                                    .build()
                    );
                    path = Path.PRELOAD;
                    case PRELOAD:
                    Actions.runBlocking(
                            drivetrain.actionBuilder(preload)
                                    .strafeToConstantHeading(new Vector2d(-25, -35))
                                    .splineToConstantHeading(new Vector2d(-48, -36), Math.toRadians(90))
                                    .waitSeconds(0.5)
                                    .build()
                    );
                    path = Path.TOYELLOWBLOCKS;
                    case TOYELLOWBLOCKS:
                    Actions.runBlocking(
                            drivetrain.actionBuilder(toYellowBlocks)
                                    .turnTo(Math.toRadians(220))
                                    .strafeToConstantHeading(new Vector2d(-52, -52))
                                    .waitSeconds(0.5)
                                    .build()
                    );
                    path = Path.CYCLEONE;
                    case CYCLEONE:
                    Actions.runBlocking(
                            drivetrain.actionBuilder(cycleOne)
                                    .turnTo(Math.toRadians(90))
                                    .strafeToConstantHeading(new Vector2d(-58, -36))
                                    .waitSeconds(0.5)

                                    .build()
                    );
                    path = Path.CYCLETWO;
                    case CYCLETWO:
                    Actions.runBlocking(
                            drivetrain.actionBuilder(cycleTwo)

                                    .turnTo(Math.toRadians(220))
                                    .strafeToConstantHeading(new Vector2d(-52, -52))
                                    .waitSeconds(0.5)

                                    .build()
                    );
                    path = Path.CYCLETHREE;
                    case CYCLETHREE:
                    Actions.runBlocking(
                            drivetrain.actionBuilder(cycleThree)

                                    .turnTo(Math.toRadians(145))
                                    .strafeToConstantHeading(new Vector2d(-61, -34))
                                    .waitSeconds(0.5)

                                    .build()
                    );
                    path = Path.END;
                    case END:
                    Actions.runBlocking(
                            drivetrain.actionBuilder(end)
                                    .turnTo(Math.toRadians(220))
                                    .strafeToConstantHeading(new Vector2d(-52, -52))
                                    .build()

                    );

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