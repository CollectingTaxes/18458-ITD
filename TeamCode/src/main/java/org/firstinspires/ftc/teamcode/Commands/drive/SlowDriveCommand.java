package org.firstinspires.ftc.teamcode.Commands.drive;

import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Subsystems.Drive;

public class SlowDriveCommand extends DefaultDriveCommand {
    public SlowDriveCommand(Drive drive, GamepadEx driverGamepad, boolean isFieldCentric) {
        super(drive, driverGamepad, isFieldCentric);
        this.multiplier = 0.3;
    }
}