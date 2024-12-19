package org.firstinspires.ftc.teamcode.Commands.arm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;

public class Score extends SequentialCommandGroup {
    public Score(Arm arm, Claw claw) {
        addCommands(
                new InstantCommand(claw::Reset),
                new InstantCommand(arm::grab)
        );
    }
}
