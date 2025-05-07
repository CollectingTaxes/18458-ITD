package org.firstinspires.ftc.teamcode.Commandbase.Commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.HardwareSubsystem;

import java.util.List;

public class ClawActions {
    enum ClawState {
        CLOSED,
        OPENED
    }

    HardwareSubsystem claw;

    ClawState clawState = ClawState.CLOSED;

    public ClawActions(OpMode opMode) {
        claw = new HardwareSubsystem(opMode);
    }
    private boolean wasInputPressed = false;

    public void action(List<Action> runningActions, FtcDashboard dashboard, boolean input) {

        if (input && !wasInputPressed) {
            switch (clawState) {
                case CLOSED:
                    runningActions.add(
                            new InstantAction(claw::SampOpen)
                    );
                    clawState = ClawState.OPENED;
                    break;
                case OPENED:
                    runningActions.add(
                            new InstantAction(claw::SampClose)
                    );
                    clawState = ClawState.CLOSED;
                    break;
            }
        }
        wasInputPressed = input;
    }
}
