package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;

public class IntakeSpecimens extends SequentialCommandGroup {
    public IntakeSpecimens (Claw claw, Arm arm) {

        addCommands(
                new InstantCommand(arm::intake),
                new InstantCommand(claw::intake)
        );
    }
}
