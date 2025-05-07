
package org.firstinspires.ftc.teamcode.Commandbase.Commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.HardwareSubsystem;

import java.util.List;

public class SpecCycleActions {
    enum SpecArmCycle {
        INTAKE,
        OUTTAKE
    }
    public HardwareSubsystem specCycle;

    SpecArmCycle specArmCycle = SpecArmCycle.INTAKE;

    public SpecCycleActions(OpMode opMode) {
        specCycle = new HardwareSubsystem(opMode);
    }

    private boolean wasInputPressed = false;
    public void action(List<Action> runningActions, FtcDashboard dashboard, boolean input) {

        if (input && !wasInputPressed) {

            switch (specArmCycle) {
                case INTAKE:
                    runningActions.add(
                            new SequentialAction(
                                    new InstantAction(specCycle::specGrab),
                                    new SleepAction(0.3),
                                    new InstantAction(specCycle::spec),
                                    new InstantAction(specCycle::specNuetral)
                            )
                    );
                    specArmCycle = SpecArmCycle.OUTTAKE;
                    break;

                case OUTTAKE:
                    runningActions.add(
                            new SequentialAction(
                                    new InstantAction(specCycle::outtake),
                                    new SleepAction(0.1),
                                    new InstantAction(specCycle::specOpen),
                                    new SleepAction(0.4),
                                    new InstantAction(specCycle::intake),
                                    new SleepAction(0.1),
                                    new InstantAction(specCycle::specScore)
                            )
                    );
                    specArmCycle = SpecArmCycle.INTAKE;
                    break;
            }
        }
        wasInputPressed = input;
    }
    public void actionAuto(List<Action> runningActions, FtcDashboard dashboard, boolean intake) {
        if (intake) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(specCycle::specGrab),
                            new SleepAction(0.3),
                            new InstantAction(specCycle::spec),
                            new InstantAction(specCycle::specNuetral)
                    )
            );

        } else if (!intake) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(specCycle::outtake),
                            new SleepAction(0.1),
                            new InstantAction(specCycle::specOpen),
                            new SleepAction(0.4),
                            new InstantAction(specCycle::intake),
                            new SleepAction(0.1),
                            new InstantAction(specCycle::specScore)
                    )
            );
        }
    }
}
