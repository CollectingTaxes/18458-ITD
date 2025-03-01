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
    private final Servo specServo;
    private final HardwareMap hardwareMap;

    public static int min = -5;
    public static int max = 2500;

    public static double GRAB = 0.17, OPEN = 0.42;
    public static int INTAKE = 200, OUTTAKE = 100, SPEC_ARM = 500;
    public int current = 0;

    public SpecArm (OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;

        this.specArm = (DcMotor) hardwareMap.get("specArm");
        this.specServo = (Servo) hardwareMap.get("specClaw");

        specServo.setDirection(Servo.Direction.FORWARD);
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

//    public void moveManual(double position) {
//        setPos((int) position);
//    }
    public void grab() {
        specServo.setPosition(GRAB);
    }

    public void open() {
        specServo.setPosition(OPEN);
    }
    public void intake() {
        setPos(INTAKE);
    }
    public void outtake() {
        setPos(OUTTAKE);
    }
}
