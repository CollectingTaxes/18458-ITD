package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.outoftheboxrobotics.photoncore.Photon;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.arm.Intake;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.arm.Score;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.claw.Grab;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.claw.Release;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.drive.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.drive.SlowDriveCommand;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.slides.SlideHigh;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.slides.SlideMoveManual;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.slides.SlideReset;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.wrist.HorizontalGrab;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.wrist.NeutralGrab;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.RoadRunner.StrafeChassis;

@TeleOp
@Photon
public class TeleOpMain extends CommandOpMode {

    private GamepadEx driverGamepad; //Driver 1
    private GamepadEx operatorGamepad; // Driver 2

    private Claw claw;
    private Slides slide;
    private Arm arm;
    private Drive drivetrain;
    private Wrist wrist;

    @Override
    public void initialize() {

        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);

        arm = new Arm(hardwareMap, telemetry);
        slide = new Slides(hardwareMap, telemetry);
        claw = new Claw(hardwareMap, telemetry);

        wrist = new Wrist(hardwareMap, telemetry);


        //TRY MECANUM DRIVE FIELD CENTRIC
        drivetrain.init();
        //Button recenterIMU = (new GamepadButton(driverGamepad, GamepadKeys.Button.A))
        //.whenPressed(new InstantCommand(drivetrain::reInitializeIMU));
        operatorGamepad.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(new SlideHigh(slide, claw, arm));


//        Button recenterIMU2 = (new GamepadButton(driverGamepad, GamepadKeys.Button.B))
//                .whenPressed(new InstantCommand(drivetrain::reInitializeIMU));
//
//        Button slowMode = (new GamepadButton(driverGamepad, GamepadKeys.Button.LEFT_BUMPER))
//                .whileHeld(new SlowDriveCommand(drivetrain, driverGamepad, true));
//
//
//        slide.setDefaultCommand(new SlideMoveManual(slide, operatorGamepad::getLeftY));
//
//        Button slideReset = new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_DOWN)
//                .whenPressed(new SlideReset(slide, claw, arm));
//
//        Button slideHigh = new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_UP)
//                .whenPressed(new SlideHigh(slide, claw, arm));
//
//        Button Score = new GamepadButton(operatorGamepad, GamepadKeys.Button.A)
//                .whenPressed(new Score(arm));
//
//        Button Reset = new GamepadButton(operatorGamepad, GamepadKeys.Button.B)
//                .whenPressed(new Intake(arm));
//
//        Button Claw = new GamepadButton(operatorGamepad, GamepadKeys.Button.RIGHT_BUMPER)
//                .whenPressed(new Grab(claw));
//        Button ClawOuttake = new GamepadButton(operatorGamepad, GamepadKeys.Button.LEFT_BUMPER)
//                .whenPressed(new Release(claw));
//
//        Button wristHorizontal = new GamepadButton(operatorGamepad,GamepadKeys.Button.X)
//                .whenPressed(new HorizontalGrab(wrist));
//        Button wristNormal = new GamepadButton(operatorGamepad,GamepadKeys.Button.Y)
//                .whenPressed(new NeutralGrab(wrist));

    }
}