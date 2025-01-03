package org.firstinspires.ftc.teamcode.Commandbase.Commands.wrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

public class Increment extends SequentialCommandGroup {
    public Increment(Wrist wrist) {
        addCommands(
                new InstantCommand(wrist::incrementing)
        );
    }
}
