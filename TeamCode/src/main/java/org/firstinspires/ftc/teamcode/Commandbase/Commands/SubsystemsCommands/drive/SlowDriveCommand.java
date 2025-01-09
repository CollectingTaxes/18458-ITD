package org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.drive;

import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Drive;

public class SlowDriveCommand extends DefaultDriveCommand {
    public SlowDriveCommand(Drive drive, GamepadEx driverGamepad, boolean isFieldCentric) {
        super(drive, driverGamepad, isFieldCentric);
        this.multiplier = 0.3;
    }
}