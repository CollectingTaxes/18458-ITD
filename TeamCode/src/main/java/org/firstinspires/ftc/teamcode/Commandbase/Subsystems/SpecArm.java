package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.onbotjava.handlers.admin.SettingsReset;

public class SpecArm {
    private final DcMotor specArm;
    private final Servo specServo;
    private final HardwareMap hardwareMap;

    public static int min = -5;
    public static int max = 2500;

    public static int High = 1400;
    public static int Mid = 600;
    public static int Low = 100;
    public static int Reset = 0;
    public static int SCOREPOSE = 700;
    public int current = 0;

    public SpecArm (OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;

        this.specArm = (DcMotor) hardwareMap.get("specArm");
        this.specServo = (Servo) hardwareMap.get("specClaw");

        specServo.setDirection(Servo.Direction.FORWARD);
        specArm.setDirection(DcMotorSimple.Direction.FORWARD);

        specArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        specArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setPos(int pos) {
        if (pos <= max && pos >= min) current = pos;
        System.out.println(current);
        normalize();
    }

    public int getPos() {
        return current;
    }

    public void moveManual(double position) {
        setPos((int) position);
    }

    public void normalize() {
        specArm.setTargetPosition(current);
        specArm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        specArm.setPower(1);

    }

    public void Manual(double position) {
        if (Math.abs(position) > 0.1) {
            moveManual(getPos() + position * 25);
        }

    }

    //Lift Pose
    public void liftRest() {
        specArm.setPower(0.75);
        setPos(Reset);
    }
    public void liftBigHigh() {
        setPos(1450);
    }

    public void liftLow() {
        setPos(Low);
    }

    public void liftMid() {
        setPos(Mid);
    }

    public void liftHigh() {
        setPos(High);
    }
}
