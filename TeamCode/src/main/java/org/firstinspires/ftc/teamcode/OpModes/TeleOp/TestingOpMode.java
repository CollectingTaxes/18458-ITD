package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commandbase.Commands.Testing;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;

import java.util.ArrayList;
import java.util.List;

@TeleOp
public class TestingOpMode extends OpMode {

    public Testing testing;
    public Claw claw;

    private final FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();

    @Override
    public void init() {
        testing = new Testing(this);
        claw = new Claw(this);
    }

    @Override
    public void loop() {

        if (gamepad1.dpad_up) {
            runningActions.add(
                    new SequentialAction(
                            testing.openClaw()

                    )
            );
        } else if (gamepad1.dpad_down) {
            runningActions.add(
                    new SequentialAction(
                            testing.closeClaw()
                    )
            );
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
