package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.OldStuff.RoadRunner.drive.StrafeChassis;

@Autonomous
public class Auto extends LinearOpMode {
    public DcMotor leftFront, leftRear, rightFront, rightRear, leftSlide, rightSlide;
    public Servo claw;

    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = (DcMotor) hardwareMap.get("leftFront");
        rightFront = (DcMotor) hardwareMap.get("rightFront");
        rightRear = (DcMotor) hardwareMap.get("rightRear");
        leftRear = (DcMotor) hardwareMap.get("leftRear");

        leftSlide = (DcMotor) hardwareMap.get("leftSlide");
        rightSlide = (DcMotor) hardwareMap.get("rightSlide");

        claw = (Servo) hardwareMap.get("Claw");

        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        rightSlide.setDirection(DcMotorSimple.Direction.FORWARD);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        claw.setDirection(Servo.Direction.REVERSE);

        while(opModeIsActive()) {
            claw.setPosition(1);
            motorRun(0.5, 0.5, 0.5, 0.5);
            sleep(3000);
            claw.setPosition(0.5);
            slidesRaiseUp(100);
        }
    }
    public void motorRun(double leftRearPower, double rightRearPower, double leftFrontPower, double rightFrontPower) {
        leftFront.setPower(leftFrontPower);
        rightFront.setPower(rightFrontPower);
        leftRear.setPower(leftRearPower);
        rightRear.setPower(rightRearPower);
    }
    public void slidesRaiseUp(int targetPose) {
        leftSlide.setTargetPosition(targetPose);
        rightSlide.setTargetPosition(targetPose);
    }
}
