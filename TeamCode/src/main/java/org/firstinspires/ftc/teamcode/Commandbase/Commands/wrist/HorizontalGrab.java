package org.firstinspires.ftc.teamcode.Commandbase.Commands.wrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

public class HorizontalGrab extends SequentialCommandGroup {
    public HorizontalGrab(Wrist wrist) {
        addCommands(
                new InstantCommand(wrist::horizontalGrab)
        );
    }
}
