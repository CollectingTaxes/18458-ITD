package org.firstinspires.ftc.teamcode.Commandbase.Commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;

import java.util.List;

public class UpDownWrist {

    enum StateWrist {
        UP,
        DOWN
    }

    Claw claw;

    StateWrist stateWrist = StateWrist.UP;

    public UpDownWrist(OpMode opMode) {
        claw = new Claw(opMode);
    }
    private boolean wasInputPressed = false;

    public void action(List<Action> runningActions, FtcDashboard dashboard, boolean input) {

        if (input && !wasInputPressed) {
            switch (stateWrist) {
                case UP:
                    runningActions.add(
                            new InstantAction(claw::outtake)
                    );
                    stateWrist = StateWrist.DOWN;
                    break;
                case DOWN:
                    runningActions.add(
                            new InstantAction(claw::intake)
                    );
                    stateWrist = StateWrist.UP;
                    break;
            }
        }
        wasInputPressed = input;
    }
}
