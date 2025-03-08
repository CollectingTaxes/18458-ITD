package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class SpecArm implements ArmPID {
    private final DcMotor specArm;
    private final Servo specClaw;
    private final Servo specWrist;
    private final HardwareMap hardwareMap;

    public boolean usingPIDFArm = true;
    public static double armManualPower;

    public static double PositiveArm = 0.3;
    public static double NegativeArm = -0.3;

    public AnalogInput armMovement;
    public AbsoluteAnalogEncoder armEncoder;
    public static double armRatio = 4;
    //If the arm starts at an offset, Make sure to record that
    public static double armOffset = 0;

    //Start at 0.001 then go up
    public PIDController armController;
    public static double p = 0, i = 0, d = 0, f = 0;
    public static int armTarget;
    double armPos;
    double pid, targetArmAngle, ff, currentArmAngle, armPower;

    public double ticks_in_degree = 144.0 / 180.0;

    public static int min = -5;
    public static int max = 1000;

    public static double OPEN = 0, GRAB = 0.26;
    public static double NEUTRAL = 0, SCORE = 0.66, POWER = 0.8;
    public static int INTAKE = -5, OUTTAKE = 80, SPEC = 50, FLOOR = 30;
    public double MAX_POWER = 1;

    public SpecArm (OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;

        this.specArm = (DcMotor) hardwareMap.get("specArm");
        this.specWrist = (Servo) hardwareMap.get("specWrist");
        this.specClaw = (Servo) hardwareMap.get("specClaw");

        specClaw.setDirection(Servo.Direction.FORWARD);
        specWrist.setDirection(Servo.Direction.FORWARD);
        specArm.setDirection(DcMotorSimple.Direction.FORWARD);

        armMovement = hardwareMap.get(AnalogInput.class, "armMovement");
        armEncoder = new AbsoluteAnalogEncoder(armMovement, 3.3, armOffset, armRatio);

        //Config this
        armEncoder.setInverted(false);

        armController = new PIDController(p, i, d);
    }
    public void armSetPower(double pow) {
        if(pow > MAX_POWER) pow = MAX_POWER;
        if (pow < MAX_POWER) pow = -MAX_POWER;
        specArm.setPower(pow);
    }

    public void armManualPositive() {
        usingPIDFArm = false;
        armManualPower = PositiveArm;
    }

    public void armManualNegative() {
        usingPIDFArm = false;
        armManualPower = NegativeArm;
    }

    public double setArmPID(int target) {
        armController.setPID(p, i, d);
        armPos = armEncoder.getCurrentPosition();
        pid = armController.calculate(armPos, target);
        targetArmAngle = target;
        ff = (Math.sin(Math.toRadians(targetArmAngle))) * f;
        currentArmAngle = Math.toRadians((armPos) / ticks_in_degree);

        armPower = pid + ff;

        return armPower;
    }

    @Override
    public void update() {
        if (usingPIDFArm) {
            armSetPower(setArmPID(armTarget));
        } else {
            if (!(armEncoder.getCurrentPosition() > max && (armManualPower > 0))) {
                armSetPower(armManualPower);
            } else {
                armSetPower(0);
            }
        }
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
        armTarget = INTAKE;
    }
    public void outtake() {
        armTarget = OUTTAKE;
    }
    public void spec() {
        armTarget = SPEC;

    }
    public void grabFromFloor() {
        armTarget = FLOOR;
    }
}
