package org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.slides.Testing;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;

public class HighTest extends SequentialCommandGroup {
    public HighTest(Slides slideV2) {
        addCommands(
                new InstantCommand(slideV2::liftHigh)
        );
    }
}
