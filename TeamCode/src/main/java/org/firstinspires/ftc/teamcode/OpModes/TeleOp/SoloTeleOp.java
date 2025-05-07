package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.Commandbase.Commands.ClawActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.FoldInAction;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SlideDropOffActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SpecCycleActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SlideIntakeActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.WristAction;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.HardwareSubsystem;

import java.util.ArrayList;
import java.util.List;

@TeleOp
public class SoloTeleOp extends OpMode {
    public HardwareSubsystem robot;
    public SpecCycleActions specCycleActions;
    public WristAction wristAction;
    public ClawActions clawActions;
    public SlideIntakeActions slideIntakeActions;
    public SlideDropOffActions slideDropOffActions;
    public FoldInAction foldInAction;
    public Drive drive;


    private final FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();

    @Override
    public void init() {
        robot = new HardwareSubsystem(this);
        specCycleActions = new SpecCycleActions(this);
        wristAction = new WristAction(this);
        clawActions = new ClawActions(this);
        slideIntakeActions = new SlideIntakeActions(this);
        slideDropOffActions = new SlideDropOffActions(this);
        foldInAction = new FoldInAction(this);
        drive = new Drive(this);

        robot.init();
        robot.robotInit();

        telemetry.addLine("y'all got this :)");
    }

    @Override
    public void loop() {

        telemetry.addData("arm pose", robot.getArmPos());
        robot.update();

        telemetry.addLine();
        telemetry.addData("slide pose", robot.getPos());
        telemetry.update();

        drive.teleOp(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, 1, gamepad1.a, gamepad1.left_bumper);

        slideIntakeActions.actionTeleOp(runningActions, dash, gamepad1.b, true);

        slideDropOffActions.actionTeleOp(runningActions, dash, gamepad1.y, true);

        wristAction.action(runningActions, dash, gamepad1.x);

        specCycleActions.action(runningActions, dash, gamepad1.right_bumper);

        foldInAction.actionTeleOp(runningActions, dash, gamepad1.left_bumper, true);

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