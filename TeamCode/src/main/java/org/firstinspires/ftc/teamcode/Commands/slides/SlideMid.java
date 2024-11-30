package org.firstinspires.ftc.teamcode.Commands.slides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.Slides;

public class SlideMid extends SequentialCommandGroup {
    public SlideMid(Slides slideV2, Claw claw, Arm arm) {
        addCommands(
                new InstantCommand(arm::reset)
        );
        addCommands(
                new InstantCommand(slideV2::liftMid)
        );
    }
}
