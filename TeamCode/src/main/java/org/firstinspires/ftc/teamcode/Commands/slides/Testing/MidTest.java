package org.firstinspires.ftc.teamcode.Commands.slides.Testing;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.Slides;

public class MidTest extends SequentialCommandGroup {
    public MidTest(Slides slideV2) {
        addCommands(
                new InstantCommand(slideV2::liftRest)
        );
    }
}
