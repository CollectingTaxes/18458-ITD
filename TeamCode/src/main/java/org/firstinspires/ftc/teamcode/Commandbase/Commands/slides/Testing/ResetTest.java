package org.firstinspires.ftc.teamcode.Commandbase.Commands.slides.Testing;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;

public class ResetTest extends SequentialCommandGroup {
    public ResetTest(Slides slideV2) {
        addCommands(
                new InstantCommand(slideV2::liftRest)
        );
    }
}
