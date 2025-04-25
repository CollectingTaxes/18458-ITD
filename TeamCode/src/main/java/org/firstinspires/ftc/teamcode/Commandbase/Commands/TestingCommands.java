package org.firstinspires.ftc.teamcode.Commandbase.Commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.RTPSpecArm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.SpecArm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

import java.util.List;

public class TestingCommands {
    enum SpecArmCycle {
        INTAKE,
        OUTTAKE
    }
    public Claw claw;
    // Wrist

    SpecCycleActions.SpecArmCycle specArmCycle = SpecCycleActions.SpecArmCycle.INTAKE;

    public TestingCommands(OpMode opMode) {
        claw = new Claw(opMode); //Wrist;
    }

    private boolean wasInputPressed = false;
    public void actionTeleOp(List<Action> runningActions, FtcDashboard dashboard, boolean input) {

        if (input && !wasInputPressed) {
            switch (specArmCycle) {
                case INTAKE:
                    runningActions.add(
                            new SequentialAction(
                                    new InstantAction(claw::intake)
                                    //intake
                            )
                    );
                    specArmCycle = SpecCycleActions.SpecArmCycle.OUTTAKE;
                    break;

                case OUTTAKE:
                    runningActions.add(
                            new SequentialAction(
                                    new InstantAction(claw::outtake)
                                    //outtake
                            )
                    );
                    specArmCycle = SpecCycleActions.SpecArmCycle.INTAKE;
                    break;
            }
        }
        wasInputPressed = input;
    }
}
