package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.outoftheboxrobotics.photoncore.Photon;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.Commandbase.Commands.ClawActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.HangActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.ResetActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SlideActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SpecCycleActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubmersibleActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.WristAction;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Hang;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.SpecArm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;
import org.firstinspires.ftc.teamcode.RoadRunner.StrafeChassis;

import java.util.ArrayList;
import java.util.List;

@TeleOp
@Photon
public class TeleOpMain extends OpMode {

    public Drive drivetrain;
    public Slides slides;
    public SlideActions outtake;
    public SpecCycleActions specCycleActions;
    public WristAction wristAction;
    public ClawActions clawActions;
    public SubmersibleActions submersibleActions;
    public ResetActions resetActions;
    public SpecArm spec;
    public Wrist wrist;
    public Arm arm;
    public Hang hang;
    public HangActions hangActions;

    private final FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();

    @Override
    public void init() {
        outtake = new SlideActions(this);
        specCycleActions = new SpecCycleActions(this);
        drivetrain = new Drive(this);
        slides = new Slides(this);
        wristAction = new WristAction(this);
        wrist = new Wrist(this);
        clawActions = new ClawActions(this);
        submersibleActions = new SubmersibleActions(this);
        resetActions = new ResetActions(this);
        spec = new SpecArm(this);
        arm = new Arm(this);
        hang = new Hang(this);
        hangActions = new HangActions(this);

        wrist.neutralGrab();
        arm.reset();

        telemetry.addLine("y'all got this :)");
    }

    @Override
    public void loop() {

        telemetry.addData("arm pose", spec.getPos());
        spec.update();

        telemetry.addLine();
        telemetry.addData("hang pose", hang.getPos());

        telemetry.addLine();
        telemetry.addData("slide pose", slides.getPos());
        telemetry.addLine();
        telemetry.addData("arm2 pose", arm.getPos());
        telemetry.addData("arm2 target", arm.targetPos());

        telemetry.update();

        hangActions.action(runningActions, dash, gamepad1.right_bumper);

        drivetrain.teleOp(gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, 1, gamepad1.a, gamepad1.left_bumper);

        slides.Manual(-gamepad2.left_stick_y);

        outtake.action(runningActions, dash, gamepad2.dpad_up);

        submersibleActions.action(runningActions, dash, gamepad2.b, true);

        wristAction.action(runningActions, dash, gamepad2.x);

        resetActions.action(runningActions, dash, gamepad2.y);

        clawActions.action(runningActions, dash, gamepad2.left_bumper);

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