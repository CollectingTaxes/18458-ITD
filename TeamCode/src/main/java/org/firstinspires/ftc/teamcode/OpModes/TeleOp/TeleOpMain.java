package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.IntakeSpecimens;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;

@Config
@TeleOp
public class TeleOpMain extends CommandOpMode {

    public GamepadEx gamepad1;
    public GamepadEx gamepad2;

    private Arm arm;
    private Claw claw;

    @Override
    public void initialize() {
       arm =  new Arm(hardwareMap, "leftArmServo", "rightArmServo");
       claw = new Claw(hardwareMap, "clawServo");
    }

    public void configureButtons() {
        Button intake = (new GamepadButton(gamepad2, GamepadKeys.Button.A))
                .whenPressed(new IntakeSpecimens(claw, arm));
    }
}
