package org.firstinspires.ftc.teamcode.Commandbase.Commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.HardwareSubsystem;

import java.util.List;


public class SlideIntakeActions {

    enum CycleState {
        GRABBING,
        EXTENDED,
        INIT
    }

    public HardwareSubsystem extendo;

    CycleState clawState = CycleState.INIT;


    public SlideIntakeActions(OpMode opMode) {
        extendo = new HardwareSubsystem(opMode);
    }

    private boolean wasInputPressed = false;

    public void actionTeleOp(List<Action> runningActions, FtcDashboard dashboard, boolean input, boolean advancedControl) {

        if (input && !wasInputPressed) {

            switch (clawState) {
                case INIT:
                    runningActions.add(
                            new InstantAction(extendo::armReset)
                    );

                    clawState = CycleState.EXTENDED;

                case EXTENDED:
                    if (advancedControl) {
                        runningActions.add(
                                new SequentialAction(
                                        new InstantAction(extendo::hover),
                                        new InstantAction(extendo::Zintake),
                                        new InstantAction(extendo::SampOpen),
                                        new InstantAction(extendo::liftHigh)
                                )
                        );
                    } else runningActions.add(
                                new InstantAction(extendo::hover)
                        );
                    clawState = CycleState.GRABBING;
                    break;

                // TOGGLE, SEPERATING BECAUSE I MIGHT GO MAD STARING AT THIS
                case GRABBING:

                    if (advancedControl) {
                        runningActions.add(
                                new SequentialAction(
                                        new InstantAction(extendo::armGrab),
                                        new SleepAction(0.1),
                                        new InstantAction(extendo::SampClose),
                                        new SleepAction(0.2),
                                        new InstantAction(extendo::hover),
                                        new InstantAction(extendo::liftRest)
                                )
                        );

                    } else runningActions.add(
                            new InstantAction(extendo::hover)
                        );
                    clawState = CycleState.EXTENDED;
                    break;
            }
        }
        wasInputPressed = input;
    }

    public void actionAuto(List<Action> runningActions, FtcDashboard dashboard, boolean intake) {
        if (intake) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(extendo::hover),
                            new InstantAction(extendo::Zintake),
                            new InstantAction(extendo::SampOpen),
                            new InstantAction(extendo::liftHigh)
                    )
            );

        } else if (!intake) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(extendo::armGrab),
                            new SleepAction(0.1),
                            new InstantAction(extendo::SampClose),
                            new SleepAction(0.2),
                            new InstantAction(extendo::liftRest),
                            new InstantAction(extendo::armReset)
                    )
            );
        }
    }
}

