package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Commandbase.Commands.ClawActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.HangActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.ResetActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SlideActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SlideIntakeActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SpecCycleActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.TestingCommands;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.WristAction;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Hang;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.RTPSpecArm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.SpecArm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Testing;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

import java.util.ArrayList;
import java.util.List;

@TeleOp
public class TestingOpMode extends OpMode {
    public SpecArm spec;
    public TestingCommands testingCommands;

    private final FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();

    @Override
    public void init() {
        testingCommands = new TestingCommands(this);
    }

    @Override
    public void loop() {
        testingCommands.actionTeleOp(runningActions, dash, gamepad2.a);

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
