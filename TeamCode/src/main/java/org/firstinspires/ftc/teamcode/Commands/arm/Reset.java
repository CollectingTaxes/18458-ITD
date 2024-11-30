package org.firstinspires.ftc.teamcode.Commands.arm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.Arm;

public class Reset extends SequentialCommandGroup {
    public Reset(Arm arm) {
        addCommands(
                new InstantCommand(arm::reset)
        );
    }
}

