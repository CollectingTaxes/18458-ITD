package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class SpecArm {
    private final DcMotor specArm;
    private final Servo specClaw;
    private final Servo specWrist;
    private final HardwareMap hardwareMap;

    public static int min = -5;
    public static int max = 1000;

    public static double OPEN = 0, GRAB = 0.26;
    public static double NEUTRAL = 0, SCORE = 0.66;
    public static int INTAKE = 0, OUTTAKE = 750, SPEC = 500;
    public int current = 0;

    public SpecArm (OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;

        this.specArm = (DcMotor) hardwareMap.get("specArm");
        this.specWrist = (Servo) hardwareMap.get("specWrist");
        this.specClaw = (Servo) hardwareMap.get("specClaw");

        specClaw.setDirection(Servo.Direction.FORWARD);
        specWrist.setDirection(Servo.Direction.FORWARD);
        specArm.setDirection(DcMotorSimple.Direction.FORWARD);

        specArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        specArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        specArm.setTargetPosition(50);
    }
    public void setPos(int pos) {
        if (pos <= max && pos >= min) current = pos;
        System.out.println(current);
        normalize();
    }
    public void normalize() {
        specArm.setTargetPosition(current);
        specArm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        specArm.setPower(1);

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
        setPos(INTAKE);
    }
    public void outtake() {
        setPos(OUTTAKE);
    }
    public void spec() {
        setPos(SPEC);
    }
}
