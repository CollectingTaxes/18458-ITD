package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slides extends SubsystemBase {
    private final Telemetry telemetry;
    private final MotorEx leftSlide;
    private final MotorEx rightSlide;

    public static int min = -5;
    public static int max = 2500;


    public static int High = 1000;
    public static int Mid = 600;
    public static int Low = 100;
    public static int Reset = 0;
    public int current = 0;

    public Slides (HardwareMap hardwareMap, Telemetry telemetry) {

        leftSlide = new MotorEx(hardwareMap, "leftSlide");
        rightSlide = new MotorEx(hardwareMap, "rightSlide");

        rightSlide.setInverted(false);
        leftSlide.setInverted(true);

        leftSlide.resetEncoder();
        rightSlide.resetEncoder();

        leftSlide.motorEx.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.motorEx.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.telemetry = telemetry;
    }
    @Override
    public void periodic() {
        if(!rightSlide.motorEx.isBusy()){
            leftSlide.motorEx.setPower(0);
            rightSlide.motorEx.setPower(0);

            telemetry.addData("     left encoder: ", getPos());
            telemetry.addData("     right encoder: ", getPos());
        }
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
        leftSlide.motorEx.setTargetPosition(current);
        leftSlide.motorEx.setTargetPositionTolerance(10);
        leftSlide.motorEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftSlide.motorEx.setPower(1);
        rightSlide.motorEx.setTargetPosition(current);
        rightSlide.motorEx.setTargetPositionTolerance(10);
        rightSlide.motorEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightSlide.motorEx.setPower(1);
    }
    public void liftRest() {
        setPos(Reset);
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
