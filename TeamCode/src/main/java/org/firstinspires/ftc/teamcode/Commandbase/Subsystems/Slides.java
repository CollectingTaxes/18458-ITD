package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
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

    public PIDController slideController;
    public static double p = 0.0007, i = 0.25, d = 0, f = 0.02;
    public boolean pid = true;
    public int target;
    public int pos;

    public static int High = 950;
    public static int Mid = 600;
    public static int Low = 300;
    public static int Reset = 0;

    public Slides (OpMode opMode) {
        this.telemetry = opMode.telemetry;
        this.hardwareMap = opMode.hardwareMap;

        this.leftSlide = (DcMotor) hardwareMap.get("leftSlide");
        this.rightSlide = (DcMotor) hardwareMap.get("rightSlide");


        rightSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        slideController = new PIDController(p, i, d);
    }

    public void update() {
        if (pid) {
            slideController.setPID(p, i, d);

            leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

            double pid_output = slideController.calculate(getPos(), target);
            double power = pid_output + f;

            if (getPos() < 50 && target < 50) {
                leftSlide.setPower(0);
                rightSlide.setPower(0);
            } else {
                leftSlide.setPower(power);
                rightSlide.setPower(power);
            }
        } else {
            leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    public void setTarget(int b) {
        pid = true;
        target = b;
    }

    public int getTarget() {
        return target;
    }

    public int getPos() {
        pos = leftSlide.getCurrentPosition();
        return leftSlide.getCurrentPosition();
    }

    public void init() {
        slideController.setPID(p,i,d);
    }

    public void start() {
        target = 0;
    }

    public void moveManual(double position) {
        setTarget((int) position);
    }

    public void Manual(double position) {
        if (Math.abs(position) > 0.1) {
            moveManual(getPos() + position * 25);
        }

    }

    //Lift Pose
    public void liftRest() {
        setTarget(Reset);
    }
    public void liftBigHigh() {
        setTarget(1500);
    }

    public void liftLow() {
        setTarget(Low);
    }

    public void liftMid() {
        setTarget(Mid);
    }

    public void liftHigh() {
        setTarget(High);
    }
    public void test() {
        setTarget(Reset);
    }
}