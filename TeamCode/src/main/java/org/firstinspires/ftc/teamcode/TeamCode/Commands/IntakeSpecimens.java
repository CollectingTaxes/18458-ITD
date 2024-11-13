package org.firstinspires.ftc.teamcode.TeamCode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.Claw;

public class IntakeSpecimens extends SequentialCommandGroup {
    public IntakeSpecimens (Claw claw, Arm arm) {

        addCommands(
                new InstantCommand(claw::intake),
                new InstantCommand(arm::intake)
        );
    }
}
