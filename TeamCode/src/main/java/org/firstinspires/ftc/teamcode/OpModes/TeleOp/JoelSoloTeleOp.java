package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.ClawActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.IntakeActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.OuttakeActions;

import java.util.ArrayList;
import java.util.List;

@TeleOp
public class JoelSoloTeleOp extends OpMode {

    public Drive drivetrain;
    public Telemetry telemetry;
    public OuttakeActions outtake;
    public IntakeActions intake;
    public ClawActions claw;

    private final FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();

    @Override
    public void init() {
        outtake = new OuttakeActions(this);
        drivetrain = new Drive(this);
        intake = new IntakeActions(this);
        claw = new ClawActions(this);
    }

    @Override
    public void loop() {

        drivetrain.teleOp(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, 1, gamepad1.a, gamepad1.right_bumper);

        outtake.action(runningActions, dash, gamepad1.dpad_down);

        intake.action(runningActions, dash, gamepad1.dpad_up);

        claw.action(runningActions, dash, gamepad1.left_bumper, true);


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
