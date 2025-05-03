package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.Commandbase.Commands.ClawActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SlideDropOffActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SpecCycleActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SlideIntakeActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.WristAction;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.SpecArm;

import java.util.ArrayList;
import java.util.List;

@TeleOp
public class TeleOpMain extends OpMode {

    public Drive drivetrain;
    public Slides slides;
    public SpecCycleActions specCycleActions;
    public WristAction wristAction;
    public ClawActions clawActions;
    public SlideIntakeActions slideIntakeActions;
    public SlideDropOffActions slideDropOffActions;
    public SpecArm spec;

    private final FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();

    @Override
    public void init() {
        spec = new SpecArm(this);
        specCycleActions = new SpecCycleActions(this);
        drivetrain = new Drive(this);
        slides = new Slides(this);
        wristAction = new WristAction(this);
        clawActions = new ClawActions(this);
        slideIntakeActions = new SlideIntakeActions(this);
        slideDropOffActions = new SlideDropOffActions(this);

        spec.init();

        telemetry.addLine("y'all got this :)");
    }

    @Override
    public void loop() {

        telemetry.addData("arm pose", spec.getArmPos());
        telemetry.addData("arm target", spec.getTarget());
        spec.update();

        telemetry.addLine();
        telemetry.addData("slide pose", slides.getPos());

        telemetry.update();

        drivetrain.teleOp(gamepad1.left_stick_y, -gamepad1.left_stick_x, gamepad1.right_stick_x, 1, gamepad1.a, gamepad1.left_bumper);

        slides.Manual(-gamepad2.left_stick_y);

        slideIntakeActions.actionTeleOp(runningActions, dash, gamepad2.b, true);

        slideDropOffActions.actionTeleOp(runningActions, dash, gamepad2.y, true);

        wristAction.action(runningActions, dash, gamepad2.x);

        spec.update();

        specCycleActions.action(runningActions, dash, gamepad2.a);

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