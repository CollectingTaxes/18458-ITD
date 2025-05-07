package org.firstinspires.ftc.teamcode.Commandbase.Commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.HardwareSubsystem;

import java.util.List;

public class WristAction {

    enum WristState {
        NEUTRAL,
        HORIZONTAL
    }

    HardwareSubsystem wrist;

    WristState wristState = WristState.NEUTRAL;

    public WristAction(OpMode opMode) {
        wrist = new HardwareSubsystem(opMode);
    }
    private boolean wasInputPressed = false;

    public void action(List<Action> runningActions, FtcDashboard dashboard, boolean input) {

        if (input && !wasInputPressed) {
            switch (wristState) {
                case NEUTRAL:
                    runningActions.add(
                            new InstantAction(wrist::neutralGrab)
                    );
                    wristState = WristState.HORIZONTAL;
                    break;
                case HORIZONTAL:
                    runningActions.add(
                            new InstantAction(wrist::horizontalGrab)
                    );
                    wristState = WristState.NEUTRAL;
                    break;
            }
        }
        wasInputPressed = input;
    }
}
