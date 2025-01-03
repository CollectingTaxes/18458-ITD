package org.firstinspires.ftc.teamcode.Commandbase.Commands.slides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;

public class SlideReset extends SequentialCommandGroup {
    public SlideReset (Slides slide, Claw claw, Arm arm) {
        addCommands(
                new InstantCommand(arm::reset),
                new InstantCommand(slide::liftRest),
                new WaitCommand(200),
                new InstantCommand(claw::OPEN)
        );
    }
}
