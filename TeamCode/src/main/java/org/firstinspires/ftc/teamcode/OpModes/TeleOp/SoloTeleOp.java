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
import org.firstinspires.ftc.robotcore.external.Telemetry;

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
public class SoloTeleOp extends OpMode {

    public Claw claw;
    public  Wrist wrist;
    public Drive drivetrain;
    public Arm arm;
    public Slides slides;
    public Telemetry telemetry;

    private final FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();

    @Override
    public void init() {
        arm = new Arm(this);
        claw = new Claw(this);
        drivetrain = new Drive(this);
        slides = new Slides(this);
        wrist = new Wrist(this);

    }

    @Override
    public void loop() {

        drivetrain.teleOp(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, 1, gamepad1.a, gamepad1.left_bumper);


        if (gamepad1.dpad_up) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(claw::grab),
                            new SleepAction(0.4),
                            new InstantAction(arm::reset),
                            new InstantAction(slides::liftHigh)
                    )
            );
        }
        else if (gamepad1.dpad_down) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(arm::reset),
                            new InstantAction(slides::liftRest),
                            new InstantAction(wrist::neutralGrab),
                            new SleepAction(0.15),
                            new InstantAction(claw::open)
                    )
            );
        }
        if (gamepad1.right_bumper) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(arm::specGrab),
                            new InstantAction(claw::open)
                    )
            );
        } else if (gamepad1.left_bumper) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(arm::grab),
                            new SleepAction(0.1),
                            new InstantAction(claw::grab),
                            new SleepAction(0.35),
                            new InstantAction(arm::reset)

                    )
            );
        }
        if (gamepad1.b) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(claw::open)
                    )
            );
        }

        if (gamepad1.x) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(wrist::horizontalGrab)
                    )
            );
        } else if (gamepad1.y) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(wrist::neutralGrab)
                    )
            );
        }

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