package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.ClawActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.WristAction;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubmersibleActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SpecCycleActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SlideActions;

import java.util.ArrayList;
import java.util.List;

@TeleOp
public class JoelSoloTeleOp extends OpMode {

    public Drive drivetrain;
    public Telemetry telemetry;
    public SlideActions outtake;
    public SpecCycleActions intake;
    public SubmersibleActions subActions;
    public WristAction wristAction;
    public ClawActions clawActions;
    public SpecCycleActions specCycleActions;

    private final FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();

    @Override
    public void init() {
        outtake = new SlideActions(this);
        drivetrain = new Drive(this);
        intake = new SpecCycleActions(this);
        subActions = new SubmersibleActions(this);
        wristAction = new WristAction(this);
        clawActions = new ClawActions(this);
        specCycleActions = new SpecCycleActions(this);
    }

    @Override
    public void loop() {

        drivetrain.teleOp(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, 1, gamepad1.a, gamepad1.right_bumper);

        outtake.action(runningActions, dash, gamepad1.dpad_down);

        intake.action(runningActions, dash, gamepad1.dpad_up);

        subActions.action(runningActions, dash, gamepad1.b, true);

        wristAction.action(runningActions, dash, gamepad1.x);

        clawActions.action(runningActions, dash, gamepad1.left_bumper);

        //specCycleActions.action(runningActions, dash, gamepad1.b);

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
