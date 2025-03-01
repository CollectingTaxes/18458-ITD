package org.firstinspires.ftc.teamcode.Commandbase.Commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;

import java.util.List;

public class ClawActions {
    enum ClawState {
        CLOSED,
        OPENED
    }

    Claw claw;

    ClawState clawState = ClawState.CLOSED;

    public ClawActions(OpMode opMode) {
        claw = new Claw(opMode);
    }
    private boolean wasInputPressed = false;

    public void action(List<Action> runningActions, FtcDashboard dashboard, boolean input) {

        if (input && !wasInputPressed) {
            switch (clawState) {
                case CLOSED:
                    runningActions.add(
                            new InstantAction(claw::open)
                    );
                    clawState = ClawState.OPENED;
                    break;
                case OPENED:
                    runningActions.add(
                            new InstantAction(claw::grab)
                    );
                    clawState = ClawState.CLOSED;
                    break;
            }
        }
        wasInputPressed = input;
    }
}
