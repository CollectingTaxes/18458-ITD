package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.outoftheboxrobotics.photoncore.Photon;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Slides {
    private final Telemetry telemetry;
    private final DcMotor leftSlide;
    private final DcMotor rightSlide;
    private final HardwareMap hardwareMap;

    public static int min = -5;
    public static int max = 2500;

    public static int High = 1500;
    public static int Mid = 600;
    public static int Low = 100;
    public static int Reset = 0;
    public int current = 0;

    public Slides (OpMode opMode) {
        this.telemetry = opMode.telemetry;
        this.hardwareMap = opMode.hardwareMap;

        this.leftSlide = (DcMotor) hardwareMap.get("leftSlide");
        this.rightSlide = (DcMotor) hardwareMap.get("rightSlide");


        rightSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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
        leftSlide.setTargetPosition(current);
//        leftSlide.setTargetPositionTolerance(10);
        leftSlide.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftSlide.setPower(1);
        rightSlide.setTargetPosition(current);
//        rightSlide.motorEx.setTargetPositionTolerance(10);
        rightSlide.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightSlide.setPower(1);
    }

    //Lift Pose
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