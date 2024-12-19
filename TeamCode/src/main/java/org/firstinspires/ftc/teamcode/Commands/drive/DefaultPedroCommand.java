package org.firstinspires.ftc.teamcode.Commands.drive;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;

public class DefaultPedroCommand extends CommandBase {
    private Follower follower;
    private GamepadEx gamepadEx;

    public boolean isRoboCentric = false;

    public DefaultPedroCommand(Follower follower, GamepadEx gamepadEx) {
        this.follower = follower;
        this.gamepadEx = gamepadEx;

    }
    @Override
    public void execute() {
        if (gamepadEx.getButton(GamepadKeys.Button.LEFT_BUMPER)) {
            
        }
    }
}
