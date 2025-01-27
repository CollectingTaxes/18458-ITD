//package org.firstinspires.ftc.teamcode.OpModes.TeleOp;
//
//import com.arcrobotics.ftclib.command.button.Button;
//import com.arcrobotics.ftclib.command.button.GamepadButton;
//import com.arcrobotics.ftclib.gamepad.GamepadEx;
//import com.arcrobotics.ftclib.gamepad.GamepadKeys;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.wrist.Decrement;
//import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.wrist.HorizontalGrab;
//import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.wrist.NeutralGrab;
//import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.wrist.Increment;
//import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;
//import org.firstinspires.ftc.teamcode.OldStuff.RoadRunner.util.MatchOpMode;
//
//@TeleOp
//public class WristTest extends MatchOpMode {
//    private GamepadEx operatorGamepad; // Driver 2
//    private Wrist wrist;
//
//
//    //Drive drive = new Drive(this);
//
//    @Override
//    public void robotInit() {
//        operatorGamepad = new GamepadEx(gamepad2);
//        wrist = new Wrist(hardwareMap, telemetry);
//    }
//
//    @Override
//    public void configureButtons() {
//        Button horizontal = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_LEFT))
//                .whenPressed(new HorizontalGrab(wrist));
//        Button neutral = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_RIGHT))
//                .whenPressed(new NeutralGrab(wrist));
//        Button wristShovement = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_UP))
//                .whenHeld(new Increment(wrist));
//        Button wristNeomment = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_DOWN))
//                .whenHeld(new Decrement(wrist));
//    }
//
//    @Override
//    public void matchStart() {
//
//    }
//}
