package org.firstinspires.ftc.teamcode.Commands.slides.Testing;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.Slides;

public class HighTest extends SequentialCommandGroup {
    public HighTest(Slides slideV2) {
        addCommands(
                new InstantCommand(slideV2::liftHigh)
        );
    }
}
