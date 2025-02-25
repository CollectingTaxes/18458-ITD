package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.ClawOpen;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.Intake;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.Outtake;

import java.util.ArrayList;
import java.util.List;

@TeleOp
public class JoelSoloTeleOp extends OpMode {

    public Claw claw;
    public Wrist wrist;
    public Drive drivetrain;
    public Arm arm;
    public Slides slides;
    public Telemetry telemetry;
    public Outtake outtake;
    public Intake intake;
    public ClawOpen clawOpen;

    private final FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();

    @Override
    public void init() {
        outtake = new Outtake(this);
        drivetrain = new Drive(this);
        intake = new Intake(this);
        clawOpen = new ClawOpen(this);

    }

    @Override
    public void loop() {

        drivetrain.teleOp(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, 1, gamepad1.a, gamepad1.left_bumper);

        outtake.teleOp(runningActions, dash, gamepad2);
        intake.teleOp(runningActions, dash, gamepad2);
        clawOpen.teleOp(runningActions, dash, gamepad2, true);

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
