package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.arm.Score;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;

@Config
@TeleOp
public class TeleOpMain extends OpMode {
    Follower follower;
    Arm arm = new Arm(hardwareMap, telemetry);
    GamepadEx operatorGamepad;


    @Override
    public void init() {
        follower = new Follower(this);
        follower.startTeleopDrive();
    }

    @Override
    public void loop() {
        follower.setTeleOpMovementVectors(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, false);

        Button Score = new GamepadButton(operatorGamepad, GamepadKeys.Button.A)
                .whenPressed(new Score(arm));
    }
}
