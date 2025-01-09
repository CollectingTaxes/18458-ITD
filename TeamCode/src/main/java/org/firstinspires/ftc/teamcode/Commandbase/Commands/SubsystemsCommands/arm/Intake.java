package org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.arm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;

public class Intake extends SequentialCommandGroup {
    public Intake(Arm arm) {
        addCommands(
                new InstantCommand(arm::reset)
        );
    }
}

