package org.firstinspires.ftc.teamcode.Commandbase.Commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

import java.util.List;


public class SubmersibleActions {

    enum ClawState {
        GRABBING,
        OPENED,
        INIT
    }

    public Arm arm;
    public Claw claw;
    public Wrist wrist;

    ClawState clawState = ClawState.INIT;


    public SubmersibleActions(OpMode opMode) {

        arm = new Arm(opMode);
        claw = new Claw(opMode);
        wrist = new Wrist(opMode);

    }

    private boolean wasInputPressed = false;

    public void action(List<Action> runningActions, FtcDashboard dashboard, boolean input, boolean advancedControl) {

        if (input && !wasInputPressed) {

            switch (clawState) {
                case INIT:
                    runningActions.add(
                            new InstantAction(claw::open)
                    );
                    clawState = ClawState.OPENED;

                case OPENED:
                    if (advancedControl) {
                        runningActions.add(
                                new SequentialAction(
                                        new InstantAction(claw::grab),
                                        new InstantAction(wrist::horizontalGrab)
                                )
                        );
                    } else {
                        runningActions.add(
                                new InstantAction(claw::grab)
                        );
                    }
                    clawState = ClawState.GRABBING;
                    break;

                // TOGGLE, SEPERATING BECAUSE I MIGHT GO MAD STARING AT THIS
                case GRABBING:

                    if (advancedControl) {
                        runningActions.add(
                                new SequentialAction(
                                        new InstantAction(claw::open),
                                        new InstantAction(wrist::neutralGrab)
                                )
                        );
                    } else {
                        runningActions.add(
                                new SequentialAction(
                                        new InstantAction(claw::open)
                                )
                        );
                    }

                    clawState = ClawState.OPENED;
                    break;
            }
        }
        wasInputPressed = input;
    }
}

