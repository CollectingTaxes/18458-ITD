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
public class RedBasket extends OpMode {
    public Claw claw;
    public Wrist wrist;
    public StrafeChassis drivetrain;
    public Arm arm;
    public Slides slides;
    public Telemetry telemetry;
    public List<Action> runningActions = new ArrayList<>();
    private final FtcDashboard dash = FtcDashboard.getInstance();

    @Override
    public void init() {

        arm = new Arm(this);
        claw = new Claw(this);
        drivetrain = new StrafeChassis(hardwareMap, new Pose2d(0,0,Math.toRadians(0)));
        slides = new Slides(this);
        wrist = new Wrist(this);

    }

    @Override
    public void loop() {

        Actions.runBlocking(
                drivetrain.actionBuilder(new Pose2d(0,0,Math.toRadians(0)))
                        .strafeTo(new Vector2d(0,10))
                        .build()
                );
        runningActions.add(
                new SequentialAction(
                        new InstantAction(slides::liftHigh),
                        new SleepAction(100),
                        new InstantAction(slides::liftRest)
                )
        );
        Actions.runBlocking(
                drivetrain.actionBuilder(new Pose2d(0,0,Math.toRadians(0)))
                        .strafeTo(new Vector2d(20,0))
                        .build()
        );
        stop();

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
