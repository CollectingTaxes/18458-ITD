package org.firstinspires.ftc.teamcode.Commandbase.Commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;

import java.util.List;


public class ClawOpen {

    enum ClawState {
        OPEN,
        CLOSED,
    }

    public Claw claw;
    public Arm arm;

    private boolean wasLeftBumperPressed = false;

    ClawState clawState = ClawState.OPEN;


    public ClawOpen(OpMode opMode) {

        claw = new Claw(opMode);

    }

    public void teleOp(List<Action> runningActions, FtcDashboard dashboard, Gamepad gamepad, boolean advancedControl) {
        //DELETE ADVANCEDCONTROL IF IT STOPS WORKING

        if (gamepad.left_bumper && !wasLeftBumperPressed) {

            switch (clawState) {
                case OPEN:
                        runningActions.add(
                                new SequentialAction(
                                        new InstantAction(claw::grab)
                                )
                        );
                        clawState = ClawState.CLOSED;
                        break;

                // TOGGLE, SEPERATING BECAUSE I MIGHT GO MAD STARING AT THIS
                case CLOSED:
                    if (!advancedControl) {
                        runningActions.add(
                                new SequentialAction(
                                        new InstantAction(claw::open)
                                )
                        );
                        clawState = ClawState.CLOSED;
                        break;
                    }
                    else {
                        runningActions.add(
                                new SequentialAction(
                                        new InstantAction(claw::open),
                                        new InstantAction(arm::grab)
                                )
                        );
                    }

                    clawState = ClawState.OPEN;
                    break;
            }
        }
        wasLeftBumperPressed = gamepad.left_bumper;
    }
}
