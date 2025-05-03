
package org.firstinspires.ftc.teamcode.Commandbase.Commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.SpecArm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.SpecServos;

import java.util.List;

public class SpecCycleActions {
    enum SpecArmCycle {
        INTAKE,
        OUTTAKE
    }
    public SpecArm specArm;
    public SpecServos servos;

    SpecArmCycle specArmCycle = SpecArmCycle.INTAKE;

    public SpecCycleActions(OpMode opMode) {
        specArm = new SpecArm(opMode);
        servos = new SpecServos(opMode);
    }

    private boolean wasInputPressed = false;
    public void action(List<Action> runningActions, FtcDashboard dashboard, boolean input) {

        if (input && !wasInputPressed) {

            switch (specArmCycle) {
                case INTAKE:
                    runningActions.add(
                            new SequentialAction(
                                    new InstantAction(servos::grab),
                                    new SleepAction(0.4),
                                    new InstantAction(specArm::spec),
                                    new InstantAction(servos::nuetral)
                            )
                    );
                    specArmCycle = SpecArmCycle.OUTTAKE;
                    break;

                case OUTTAKE:
                    runningActions.add(
                            new SequentialAction(
                                    new InstantAction(specArm::outtake),
                                    //new SleepAction(0.4),
                                    new InstantAction(servos::open),
                                    new SleepAction(0.4),
                                    new InstantAction(specArm::intake),
                                    new SleepAction(0.4),
                                    new InstantAction(servos::score)
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
                            new InstantAction(servos::grab),
                            new SleepAction(0.4),
                            new InstantAction(specArm::spec),
                            new InstantAction(servos::nuetral)
                    )
            );

        } else if (!intake) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(specArm::outtake),
                            //new SleepAction(0.4),
                            new InstantAction(servos::open),
                            new SleepAction(0.4),
                            new InstantAction(specArm::intake),
                            new SleepAction(0.4),
                            new InstantAction(servos::score)
                    )
            );
        }
    }
}
