package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.outoftheboxrobotics.photoncore.Photon;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.arm.Intake;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.arm.Score;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.claw.Grab;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.claw.Release;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.drive.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.drive.SlowDriveCommand;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.slides.SlideHigh;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.slides.SlideReset;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.wrist.HorizontalGrab;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.wrist.NeutralGrab;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.RoadRunner.StrafeChassis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@TeleOp
public class TeleOpMain extends OpMode {

    private Claw claw;
    private Wrist wrist;
    public StrafeChassis drivetrain;
    public Arm arm;
    private Slides slides;
    public double heading;

    private final FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();

    @Override
    public void init() {
        if (gamepad1.x) {
            heading = Math.toRadians(270);
        }
        else heading = Math.toRadians(90);
        arm = new Arm(this);
        claw = new Claw(this);
        drivetrain = new StrafeChassis(hardwareMap, new Pose2d(0,0, heading));
        slides = new Slides(this);

    }

    @Override
    public void loop() {


        if (gamepad2.dpad_up) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(arm::reset),
                            new InstantAction(slides::liftHigh)
                    )
            );
        }
        if (gamepad2.dpad_down) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(arm::reset),
                            new InstantAction(slides::liftRest),
                            new SleepAction(500),
                            new InstantAction(claw::open)
                    )
            );
        }
        if (gamepad2.b) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(arm::reset)
                    )
            );
        } else if (gamepad2.a) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(arm::grab)
                    )
            );
        }
        if (gamepad2.right_bumper) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(claw::open)
                    )
            );
        } else if (gamepad2.left_bumper) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(claw::grab)
                    )
            );
        }
        if (gamepad2.x) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(wrist::horizontalGrab)
                    )
            );
        } else if (gamepad2.y) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(wrist::neutralGrab)
                    )
            );
        }
//        slides.moveManual();


        drivetrain.setDrivePowers(new PoseVelocity2d(
                new Vector2d(
                        -gamepad1.left_stick_y,
                        -gamepad1.left_stick_x),
                gamepad1.right_stick_y
                )
        );
        List<Action> newActions = new ArrayList<>();
        for (Action action : runningActions) {
            TelemetryPacket packet = new TelemetryPacket();
            action.preview(packet.fieldOverlay());
            if (!action.run(packet)) {
                continue;
            }
            newActions.add(action);
            dash.sendTelemetryPacket(packet);
        }
        runningActions = newActions;
    }
}