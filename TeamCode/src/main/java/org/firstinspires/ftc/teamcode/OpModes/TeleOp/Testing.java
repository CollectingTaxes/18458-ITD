package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.ClawActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SlideActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SpecCycleActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubmersibleActions;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.WristAction;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.RTPSpecArm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.SpecArm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

import java.util.ArrayList;
import java.util.List;

@TeleOp
public class Testing extends OpMode {

    public SpecArm specArm;
    public Slides slides;
    public Arm arm;
    public Wrist wrist;

    private final FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();

    @Override
    public void init() {
        specArm = new SpecArm(this);
        slides = new Slides(this);
        arm = new Arm(this);
        wrist = new Wrist(this);

        arm.reset();
    }

    @Override
    public void loop() {
        telemetry.addData("slide pose", slides.getPos());

        telemetry.addLine();
        telemetry.addData("arm pose", arm.getPos());

        if (gamepad1.dpad_up) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(specArm::grab)
                    )
            );
        }


        if (gamepad1.dpad_left) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(specArm::open)
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
