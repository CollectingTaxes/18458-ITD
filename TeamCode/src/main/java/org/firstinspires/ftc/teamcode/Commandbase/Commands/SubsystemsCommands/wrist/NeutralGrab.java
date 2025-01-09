package org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.wrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

public class NeutralGrab extends SequentialCommandGroup {

    public NeutralGrab(Wrist wrist) {
        addCommands(
                new InstantCommand(wrist::neutralGrab)
        );
    }
}
