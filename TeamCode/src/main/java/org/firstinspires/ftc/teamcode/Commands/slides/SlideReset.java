package org.firstinspires.ftc.teamcode.Commands.slides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.Slides;

public class SlideReset extends SequentialCommandGroup {
    public SlideReset (Slides slide, Claw claw, Arm arm) {
        addCommands(
                new InstantCommand(arm::reset),
                new InstantCommand(claw::Reset),
                new InstantCommand(slide::liftRest)
        );
    }
}
