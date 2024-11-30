package org.firstinspires.ftc.teamcode.Commands.claw;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.Claw;

public class Grab extends SequentialCommandGroup {
    public Grab(Claw claw) {
        addCommands(
                new InstantCommand(claw::Score)
        );
    }
}