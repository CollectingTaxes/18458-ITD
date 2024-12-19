package org.firstinspires.ftc.teamcode.Commands.slides;


import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.Slides;

public class SlideHigh extends SequentialCommandGroup {
    public SlideHigh(Slides slideV2, Claw claw, Arm arm) {
        addCommands(
                new InstantCommand(claw::Score),
                new WaitCommand(200),
                new InstantCommand(arm::reset),
                new InstantCommand(slideV2::liftHigh)
        );
    }
}