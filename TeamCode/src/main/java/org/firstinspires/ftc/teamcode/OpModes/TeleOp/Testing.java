package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

import java.util.ArrayList;
import java.util.List;

public class Testing extends OpMode {
    public Claw claw;
    public Slides slides;
    public Telemetry telemetry;

    private final FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();

    @Override
    public void init() {
        claw = new Claw(this);

    }

    @Override
    public void loop() {



        if (gamepad2.dpad_up) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(claw::grab)
                    )
            );
        }
        else if (gamepad2.dpad_down) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(claw::open)
                    )
            );
        } else if (gamepad2.a) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(claw::open )
                    )
            );
        }
        if (gamepad2.x) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(slides::test)
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
