package org.firstinspires.ftc.teamcode.Commandbase.Commands.OLD.slides.Testing;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;

public class MidTest extends SequentialCommandGroup {
    public MidTest(Slides slideV2) {
        addCommands(
                new InstantCommand(slideV2::liftRest)
        );
    }
}
