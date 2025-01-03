package org.firstinspires.ftc.teamcode.Commandbase.Commands.claw;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;

public class Release extends SequentialCommandGroup {
    public Release(Claw claw) {
        addCommands(
                new InstantCommand(claw::OPEN)
        );
    }
}
