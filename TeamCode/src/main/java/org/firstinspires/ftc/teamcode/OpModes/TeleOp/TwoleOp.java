package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TwoleOp extends CommandOpMode {

    MecanumDrive mecanumDrive;

    @Override
    public void initialize() {
        mecanumDrive.driveFieldCentric(1,1,1, Math.toRadians(180));
    }
}
