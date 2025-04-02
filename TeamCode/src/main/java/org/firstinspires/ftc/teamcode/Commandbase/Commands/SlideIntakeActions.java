package org.firstinspires.ftc.teamcode.Commandbase.Commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

import java.util.List;


public class SlideIntakeActions {

    enum CycleState {
        GRABBING,
        EXTENDED,
        INIT
    }

    public Arm arm;
    public Claw claw;
    public Wrist wrist;
    public Slides slide;
    public Testing testing;

    CycleState clawState = CycleState.INIT;


    public SlideIntakeActions(OpMode opMode) {

        arm = new Arm(opMode);
        claw = new Claw(opMode);
        wrist = new Wrist(opMode);

    }

    private boolean wasInputPressed = false;

    public void actionTeleOp(List<Action> runningActions, FtcDashboard dashboard, boolean input, boolean advancedControl) {

        if (input && !wasInputPressed) {

            switch (clawState) {
                case INIT:
                    runningActions.add(
                            new InstantAction(arm::reset)
                    );

                    clawState = CycleState.EXTENDED;

                case EXTENDED:
                    if (advancedControl) {
                        runningActions.add(
                                new SequentialAction(
                                        new InstantAction(arm::specGrab),
                                        new InstantAction(claw::open)
                                )
                        );
                    } else runningActions.add(
                                new InstantAction(claw::open)
                        );
                    clawState = CycleState.GRABBING;
                    break;

                // TOGGLE, SEPERATING BECAUSE I MIGHT GO MAD STARING AT THIS
                case GRABBING:

                    if (advancedControl) {
                        runningActions.add(
                                new SequentialAction(
                                        new InstantAction(arm::grab),
                                        new SleepAction(0.1),
                                        new InstantAction(claw::grab),
                                        new ParallelAction(
                                                new InstantAction(arm::reset),
                                                new InstantAction(slide::liftRest)
                                        )
                                )
                        );

                    } else runningActions.add(
                            new InstantAction(claw::grab)
                        );
                    clawState = CycleState.EXTENDED;
                    break;
            }
        }
        wasInputPressed = input;
    }

    public void actionAuto(List<Action> runningActions, FtcDashboard dashboard, boolean intake, Action action) {
        if (intake) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(slide::liftHigh),
                            new InstantAction(arm::grab))
            );

        } else if (!intake) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(arm::grab),
                            new SleepAction(0.1),
                            new InstantAction(claw::grab),
                            new ParallelAction(
                                    new InstantAction(arm::reset),
                                    new InstantAction(slide::liftRest),
                                    action
                            )
                    )
            );
        }
    }
}

