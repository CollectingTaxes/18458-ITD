package org.firstinspires.ftc.teamcode.Commandbase.Commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.SpecArm;

import java.util.List;


public class ClawActions {

    enum ClawState {
        GRABBING,
        OPENING,
    }

    public SpecArm specArm;

    private boolean wasInputPressed = false;
    private boolean armIsGrabbing = false;

    ClawState clawState = ClawState.GRABBING;


    public ClawActions(OpMode opMode) {

        specArm = new SpecArm(opMode);

    }

    public void action(List<Action> runningActions, FtcDashboard dashboard, boolean input, boolean advancedControl) {
        //DELETE ADVANCEDCONTROL IF IT STOPS WORKING

        if (input && !wasInputPressed) {

            switch (clawState) {
                case GRABBING:
                    if (advancedControl && armIsGrabbing) {
                        runningActions.add(
                                new SequentialAction(
                                        new InstantAction(specArm::grab),
                                        new SleepAction(0.15),
                                        new InstantAction(specArm::reset)
                                )
                        );
                        clawState = ClawState.OPENING;
                        break;
                    } else {
                        runningActions.add(
                                new SequentialAction(
                                        new InstantAction(specArm::grab)
                                )
                        );
                        clawState = ClawState.OPENING;
                        break;
                    }
                    /*

                    THIS IS FOR SPACING AND I DON'T GO INSANE FROM HOW COMPLICATED (NOT) THIS IS. ALSO ADD SOMETHING FOR DROPPING A SPEC INTO THE SUBZONE, THE IF LOGIC IS COMPLICATED

                     */
                case OPENING:
                    if (!advancedControl) {
                        runningActions.add(
                                new SequentialAction(
                                        new InstantAction(specArm::open)
                                )
                        );
                        clawState = ClawState.OPENING;
                        break;
                    }

                    else {
                        runningActions.add(
                                new SequentialAction(
                                        new InstantAction(specArm::open),
                                        new InstantAction(specArm::specArmPose)
                                )
                        );
                    }
                    armIsGrabbing = true;

                    clawState = ClawState.GRABBING;
                    break;
            }
        }
        wasInputPressed = input;
    }
}
