package org.firstinspires.ftc.teamcode.TeamCode.OpModes.TeleOp;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.TeamCode.Subsystems.Claw;

@Config
@TeleOp
public class TeleOpMain extends CommandOpMode {

    public GamepadEx gamepad1;
    public GamepadEx gamepad2;

    private Arm arm;
    private Claw claw;

    @Override
    public void initialize() {

    }
}
