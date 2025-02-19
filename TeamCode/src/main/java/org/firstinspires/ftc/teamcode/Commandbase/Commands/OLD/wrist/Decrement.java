package org.firstinspires.ftc.teamcode.Commandbase.Commands.OLD.wrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

public class Decrement extends SequentialCommandGroup {

    public Decrement(Wrist wrist) {
        addCommands(
                new InstantCommand(wrist::decrementing)
        );
    }
}
