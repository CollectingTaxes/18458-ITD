package org.firstinspires.ftc.teamcode.Commandbase.Commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.SpecArm;

import java.util.List;

public class SpecCycleActions {
    enum SpecArmCycle {
        INTAKE,
        OUTTAKE
    }
    public SpecArm specArm;

    SpecArmCycle specArmCycle = SpecArmCycle.INTAKE;

    public SpecCycleActions(OpMode opMode) {
        specArm = new SpecArm(opMode);
    }

    private boolean wasInputPressed = false;
    public void action(List<Action> runningActions, FtcDashboard dashboard, boolean input) {

        if (input && !wasInputPressed) {

            switch (specArmCycle) {
                case INTAKE:
                runningActions.add(
                        new SequentialAction(
                                new InstantAction(specArm::intake),
                                new InstantAction(specArm::grab)
                        )
                );
                specArmCycle = SpecArmCycle.OUTTAKE;
                break;

                case OUTTAKE:
                    runningActions.add(
                            new SequentialAction(
                                    new InstantAction(specArm::outtake),
                                    new SleepAction(0.1),
                                    new InstantAction(specArm::open),
                                    new SleepAction(0.1),
                                    new InstantAction(specArm::intake)
                            )
                    );
                    specArmCycle = SpecArmCycle.INTAKE;
                    break;
            }
        }
        wasInputPressed = input;
    }
}
