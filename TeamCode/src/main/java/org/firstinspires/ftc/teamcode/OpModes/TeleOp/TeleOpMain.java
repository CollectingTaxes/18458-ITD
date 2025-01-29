package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.outoftheboxrobotics.photoncore.Photon;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.apache.commons.math3.geometry.euclidean.twod.Line;
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
public class TeleOpMain extends LinearOpMode {

    private Claw claw;
    private Slides slide;
    private Arm arm;
    private Drive drivetrain;
    private Wrist wrist;


    @Override
    public void runOpMode() throws InterruptedException {

        if (gamepad1.dpad_up) {
            new InstantAction(arm::reset);

        }
    }
}