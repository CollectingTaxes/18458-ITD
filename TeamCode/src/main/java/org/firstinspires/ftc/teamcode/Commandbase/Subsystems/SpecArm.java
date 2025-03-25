package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.pedropathing.localization.Encoder;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class SpecArm {
    private final DcMotorEx specArm;
    private final Servo specClaw;
    private final Servo specWrist;
    private final Motor.Encoder encoder;
    private final HardwareMap hardwareMap;
    private Telemetry telemetry;

    public boolean usingPIDFArm = true;

    //Start at 0.001 then go up
    public PIDController armController;
    public static double p = 0.0008, i = 0.035, d = 0.000005, f = 0.005;
    public static int armTarget;
    public double pos;

    public static double OPEN = 0, GRAB = 0.34;
    public static double NEUTRAL = 0, SCORE = 0.66, POWER = 0.8;
    public static int INTAKE = 0, OUTTAKE = 3200, SPEC = 2200, FLOOR = 5000;

    public SpecArm (OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.telemetry = opMode.telemetry;
        this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        this.specArm = (DcMotorEx) hardwareMap.get("specArm");
        this.specWrist = (Servo) hardwareMap.get("specWrist");
        this.specClaw = (Servo) hardwareMap.get("specClaw");
        encoder = new MotorEx(hardwareMap, "leftRear").encoder;
        encoder.setDirection(Motor.Direction.REVERSE);
        specClaw.setDirection(Servo.Direction.FORWARD);
        specWrist.setDirection(Servo.Direction.FORWARD);

        //encoder.reset();
        specArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        armController = new PIDController(p, i, d);
    }

    public void update() {
        if (usingPIDFArm) {
            armController.setPID(p, i, d);

            specArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

            double pid_output = armController.calculate(getPos(), armTarget);
            double power = pid_output + f;

            if (getPos() < 50 && armTarget < 50) {
                specArm.setPower(0);
            } else {
                specArm.setPower(power);
            }
        }
    }

    public double getPos() {
        pos = encoder.getPosition() / 4;
        return pos;
    }

    public int getTarget() {
        return armTarget;
    }

    public void setTarget(int b) {
        usingPIDFArm = true;
        armTarget = b;
    }

    public void init() {
        armController.setPID(p,i,d);
        armTarget = 0;
    }

    public void start() {
        armTarget = 0;
    }

    public void grab() {
        specClaw.setPosition(GRAB);
    }

    public void open() {
        specClaw.setPosition(OPEN);
    }
    public void nuetral() {
        specWrist.setPosition(NEUTRAL);
    }

    public void score() {
        specWrist.setPosition(SCORE);
    }
    public void intake() {
        setTarget(INTAKE);
    }
    public void outtake() {
        setTarget(OUTTAKE);
    }
    public void spec() {
        setTarget(SPEC);
    }
    public void grabFromFloor() {
        setTarget(FLOOR);
    }
}