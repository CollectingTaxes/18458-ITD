package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import android.hardware.camera2.params.TonemapCurve;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.ftc.Encoder;
import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@Config
public class SpecArm {
    private final DcMotor specArm;
    private final Servo specClaw;
    private final Servo specWrist;
    private final HardwareMap hardwareMap;
    private final Encoder revEncoderAllahHelpMe;

    public static int min = -5;
    public static int max = 1000;

    public static double OPEN = 0, GRAB = 0.26;
    public static double NEUTRAL = 0, SCORE = 0.66, POWER = 0.8;
    public static int INTAKE = -5, OUTTAKE = 80, SPEC = 50, FLOOR = 30;
    public int current = 0;

    public SpecArm (OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;

        this.specArm = (DcMotor) hardwareMap.get("specArm");
        this.specWrist = (Servo) hardwareMap.get("specWrist");
        this.specClaw = (Servo) hardwareMap.get("specClaw");

        specClaw.setDirection(Servo.Direction.FORWARD);
        specWrist.setDirection(Servo.Direction.FORWARD);
        specArm.setDirection(DcMotorSimple.Direction.FORWARD);

        specArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        revEncoderAllahHelpMe = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, "leftRear")));
        revEncoderAllahHelpMe.setDirection(DcMotorSimple.Direction.FORWARD);

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
        specArm.setPower(POWER);

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
    public void grabFromFloor() {
        setPos(FLOOR);
    }
}
