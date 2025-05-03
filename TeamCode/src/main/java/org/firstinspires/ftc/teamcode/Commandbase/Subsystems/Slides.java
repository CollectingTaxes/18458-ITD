
package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
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
    private final DcMotor extendoSlide;
    private final HardwareMap hardwareMap;

    public static int min = -5;
    public static int max = 500;

    public static int High = 500;
    public static int Reset = 0;
    public int current = 0;

    public Slides (OpMode opMode) {
        this.telemetry = opMode.telemetry;
        this.hardwareMap = opMode.hardwareMap;

        this.extendoSlide = (DcMotor) hardwareMap.get("rightSlide");

        extendoSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        extendoSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        extendoSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void setPos(int pos) {
        if (pos <= max && pos >= min) current = pos;
        System.out.println(current);
        normalize();
    }
    public void testing() {
        setPos(750);

        while (extendoSlide.getCurrentPosition() >= 500) {
            extendoSlide.setPower(1);
        }
        extendoSlide.setPower(0.5);
    }

    public int getPos() {
        return current;
    }

    public void moveManual(double position) {
        setPos(extendoSlide.getCurrentPosition() + (int) position);
    }

    public void normalize() {
        extendoSlide.setTargetPosition(current);
        extendoSlide.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        extendoSlide.setPower(1);
    }

    public void Manual(double position) {
        extendoSlide.setPower(0.75);
        if (Math.abs(position) > 0.1) {
            moveManual(getPos() + position * 15);
        }
    }

    //Lift Pose
    public void liftRest() {
        setPos(Reset);
    }

    public void liftHigh() {
        setPos(High);
    }
}
