
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

    public static int High = 1450;
    public static int Mid = 600;
    public static int Low = 100;
    public static int Reset = 0;
    public static int SCOREPOSE = 700;
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
    public void testing() {
        setPos(750);

        while (leftSlide.getCurrentPosition() >= 500) {
            leftSlide.setPower(1);
            rightSlide.setPower(1);
        }
        rightSlide.setPower(0.5);
        leftSlide.setPower(0.5);
    }

    public int getPos() {
        return current;
    }

    public void moveManual(double position) {
        setPos((int) position);
    }

    public void normalize() {
        leftSlide.setTargetPosition(current);
        leftSlide.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftSlide.setPower(1);
        rightSlide.setTargetPosition(current);
        rightSlide.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightSlide.setPower(1);
    }

    public void Manual(double position) {
        if (Math.abs(position) > 0.1) {
            moveManual(getPos() + position * 15);
        }

    }

    //Lift Pose
    public void liftRest() {
        leftSlide.setPower(0.75);
        rightSlide.setPower(0.75);
        setPos(Reset);
    }
    public void liftBigHigh() {
        setPos(1500);
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
    public void test() {
        setPos(SCOREPOSE);
    }
}
