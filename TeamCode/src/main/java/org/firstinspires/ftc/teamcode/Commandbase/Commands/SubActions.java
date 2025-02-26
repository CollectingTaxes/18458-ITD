package org.firstinspires.ftc.teamcode.Commandbase.Commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;

import java.util.List;


public class SubActions {

    enum ClawState {
        GRABBING,
        OPENED,
    }

    public Arm arm;
    public Claw claw;

    private boolean wasInputPressed = false;

    ClawState clawState = ClawState.GRABBING;


    public SubActions(OpMode opMode) {

        arm = new Arm(opMode);
        claw = new Claw(opMode);

    }

    public void action(List<Action> runningActions, FtcDashboard dashboard, boolean input, boolean advancedControl) {
        //DELETE ADVANCEDCONTROL IF IT STOPS WORKING

        if (input && !wasInputPressed) {

            switch (clawState) {
                case GRABBING:
                    if (!advancedControl) {
                        runningActions.add(
                                new SequentialAction(
                                        new InstantAction(claw::grab)
                                )
                        );
                        clawState = ClawState.OPENED;
                        break;

                    } else {
                        runningActions.add(
                                new SequentialAction(
                                        new InstantAction(arm::grab),
                                        new InstantAction(claw::grab),
                                        new InstantAction(arm::specGrab),
                                        new SleepAction(0.15),
                                        new InstantAction(arm::reset)
                                )
                        );
                        clawState = ClawState.OPENED;
                        break;
                    }
                    /*

                    THIS IS FOR SPACING AND I DON'T GO INSANE FROM HOW COMPLICATED (NOT) THIS IS. ALSO ADD SOMETHING FOR DROPPING A SPEC INTO THE SUBZONE, THE IF LOGIC IS COMPLICATED

                     */
                case OPENED:
                    if (!advancedControl) {
                        runningActions.add(
                                new SequentialAction(

                                )
                        );
                        clawState = ClawState.OPENED;
                        break;
                    }

                    else {
                        runningActions.add(
                                new SequentialAction(
                                        new InstantAction(claw::open),
                                        new InstantAction(arm::specGrab)
                                )
                        );
                    }

                    clawState = ClawState.GRABBING;
                    break;
            }
        }
        wasInputPressed = input;
    }
}
