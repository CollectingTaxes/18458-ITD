package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.slides.SlideMoveManual;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.slides.Testing.HighTest;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.slides.Testing.MidTest;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.slides.Testing.ResetTest;
import org.firstinspires.ftc.teamcode.OldStuff.RoadRunner.util.MatchOpMode;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;

@TeleOp
public class SlideTesting extends MatchOpMode {
    private GamepadEx driverGamepad; //Driver 1
    private GamepadEx operatorGamepad; // Driver 2

    private Slides slide;

    //Drive drive = new Drive(this);

    @Override
    public void robotInit() {
        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);

        slide = new Slides(hardwareMap, telemetry);

    }

    @Override
    public void configureButtons() {

        //Button recenterIMU = (new GamepadButton(driverGamepad, GamepadKeys.Button.A))
        //.whenPressed(new InstantCommand(drivetrain::reInitializeIMU));

        slide.setDefaultCommand(new SlideMoveManual(slide, operatorGamepad::getLeftY));

        Button slideReset = new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new ResetTest(slide));
        Button slideMid = new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(new MidTest(slide));
        Button slideHigh = new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_UP)
                .whenPressed(new HighTest(slide));
        slide.setDefaultCommand(new SlideMoveManual(slide, operatorGamepad::getLeftY));
    }
    @Override
    public void matchStart() {

    }
}
