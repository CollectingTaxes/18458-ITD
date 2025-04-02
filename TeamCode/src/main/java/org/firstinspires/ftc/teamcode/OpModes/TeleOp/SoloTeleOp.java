package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commandbase.Commands.ClawActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.ResetActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SlideActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SpecCycleActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SlideIntakeActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.WristAction;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.SpecArm;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;

import java.util.ArrayList;
import java.util.List;

@TeleOp
public class SoloTeleOp extends OpMode {

    public Drive drivetrain;
    public Slides slides;
    public SlideActions outtake;
    public SpecCycleActions specCycleActions;
    public WristAction wrist;
    public ClawActions clawActions;
    public SlideIntakeActions slideIntakeActions;
    public ResetActions resetActions;
    public SpecArm spec;
    public Arm arm;

    private final FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();

    @Override
    public void init() {
        outtake = new SlideActions(this);
        specCycleActions = new SpecCycleActions(this);
        drivetrain = new Drive(this);
        slides = new Slides(this);
        wrist = new WristAction(this);
        clawActions = new ClawActions(this);
        slideIntakeActions = new SlideIntakeActions(this);
        resetActions = new ResetActions(this);
        spec = new SpecArm(this);
        arm = new Arm(this);

        spec.init();
        telemetry.addLine("y'all got this :)");
    }

    @Override
    public void loop() {

        telemetry.addData("arm pose", spec.getPos());
        spec.update();

        telemetry.addLine();
        telemetry.addData("slide pose", slides.getPos());

        telemetry.addLine();
        telemetry.addData("arm pose", arm.getPos());

        drivetrain.teleOp(gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, 1, gamepad1.a, gamepad1.left_bumper);

        slides.Manual(-gamepad2.left_stick_y);

        outtake.action(runningActions, dash, gamepad1.dpad_down);

        slideIntakeActions.actionTeleOp(runningActions, dash, gamepad1.b, true);

        wrist.action(runningActions, dash, gamepad1.x);

        resetActions.action(runningActions, dash, gamepad1.y);

        clawActions.action(runningActions, dash, gamepad1.right_bumper);

        specCycleActions.actionTeleOp(runningActions, dash, gamepad1.dpad_up);

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