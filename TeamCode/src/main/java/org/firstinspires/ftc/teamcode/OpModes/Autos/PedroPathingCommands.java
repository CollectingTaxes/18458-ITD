package org.firstinspires.ftc.teamcode.OpModes.Autos;


import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;

public class PedroPathingCommands {

    public static Claw claw;

    public static Command grabSamples() {
        return new InstantCommand(claw::open);
    }
}
