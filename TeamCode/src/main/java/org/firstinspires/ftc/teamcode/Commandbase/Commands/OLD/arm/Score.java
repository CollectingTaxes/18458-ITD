package org.firstinspires.ftc.teamcode.Commandbase.Commands.OLD.arm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;

public class Score extends SequentialCommandGroup {
    public Score(Arm arm) {
        addCommands(
                new InstantCommand(arm::grab)
        );
    }
}
