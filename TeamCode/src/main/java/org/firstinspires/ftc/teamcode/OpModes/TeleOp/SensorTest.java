package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorOctoQuad;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.drive.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.sensor.PLEASE_FOR_THE_LOVE_OF_GOD;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.SampleDetector;
import org.firstinspires.ftc.teamcode.OldStuff.RoadRunner.drive.StrafeChassis;
import org.firstinspires.ftc.teamcode.OldStuff.RoadRunner.util.MatchOpMode;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.OldStuff.Testing.Testing;

@Config
@TeleOp
public class SensorTest extends MatchOpMode {

    private SampleDetector sampleDetector;
    private Claw claw;
    private Drive drivetrain;
    private GamepadEx driverGamepad;

    @Override
    public void robotInit() {
        sampleDetector = new SampleDetector(hardwareMap, telemetry);
        claw = new Claw(hardwareMap, telemetry);
        drivetrain = new Drive(new StrafeChassis(hardwareMap, telemetry, true), telemetry, hardwareMap);
    }

    @Override
    public void configureButtons() {
        claw.setDefaultCommand(new PLEASE_FOR_THE_LOVE_OF_GOD(claw, sampleDetector));

    }
    @Override
    public void matchStart() {

    }
}