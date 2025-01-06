package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commandbase.Commands.arm.Intake;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.arm.Score;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.claw.Grab;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.claw.Release;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.drive.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.drive.SlowDriveCommand;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.slides.SlideHigh;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.slides.SlideReset;
import org.firstinspires.ftc.teamcode.GamepadTrigger;
import org.firstinspires.ftc.teamcode.OldStuff.RoadRunner.drive.StrafeChassis;
import org.firstinspires.ftc.teamcode.OldStuff.RoadRunner.util.MatchOpMode;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;

@Config
@TeleOp
public class SoloTeleOp extends MatchOpMode {

    private GamepadEx driverGamepad; //Driver 1
    private GamepadEx operatorGamepad; // Driver 2

    private Claw claw;
    private Slides slide;
    private Arm arm;
    private Drive drivetrain;

    //Drive drive = new Drive(this);

    @Override
    public void robotInit() {
        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);

        arm = new Arm(hardwareMap, telemetry);
        slide = new Slides(hardwareMap, telemetry);
        claw = new Claw(hardwareMap, telemetry);

        drivetrain = new Drive(new StrafeChassis(hardwareMap, telemetry, true), telemetry, hardwareMap);

        drivetrain.init();
    }

    @Override
    public void configureButtons() {
        drivetrain.setDefaultCommand(new DefaultDriveCommand(drivetrain, driverGamepad, true));

        //Button recenterIMU = (new GamepadButton(driverGamepad, GamepadKeys.Button.A))
        //.whenPressed(new InstantCommand(drivetrain::reInitializeIMU));

        Button recenterIMU2 = (new GamepadButton(driverGamepad, GamepadKeys.Button.X))
                .whenPressed(new InstantCommand(drivetrain::reInitializeIMU));

        Button slowMode = (new GamepadButton(driverGamepad, GamepadKeys.Button.LEFT_BUMPER))
                .whileHeld(new SlowDriveCommand(drivetrain, driverGamepad, true));

        //slide.setDefaultCommand(new SlideMoveManual(slide, operatorGamepad::getLeftY));

        Button slideReset = new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new SlideReset(slide, claw, arm));

        /*Button slideLow = new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(new SlideLow(slide, claw, arm));

        Button slideMid = new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(new SlideMid(slide, claw, arm));*/

        Button slideHigh = new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_UP)
                .whenPressed(new SlideHigh(slide, claw, arm));

        Button score = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER))
                .whileHeld(new Score(arm))
                .whenReleased(new Intake(arm));

        Button Claw = new GamepadButton(driverGamepad, GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new Grab(claw));
        Button ClawOuttake = new GamepadButton(driverGamepad, GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new Release(claw));

    }
    @Override
    public void matchStart() {

    }
}
