package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Hang {
    private Telemetry telemetry;
    private DcMotor leftHang;
    private DcMotor rightHang;
    private HardwareMap hardwareMap;


    public static int up = -5;
    public static int down = 2500;
    public static int Reset = 0;
    public int current = 0;

    public Hang (OpMode opMode) {
        this.telemetry = opMode.telemetry;
        this.hardwareMap = opMode.hardwareMap;

        this.leftHang= (DcMotor) hardwareMap.get("leftHang");
        this.rightHang = (DcMotor) hardwareMap.get("rightHang");


        rightHang.setDirection(DcMotorSimple.Direction.FORWARD);
        leftHang.setDirection(DcMotorSimple.Direction.REVERSE);

        leftHang.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightHang.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
    public void motorPowers(double powers) {
        leftHang.setPower(powers);
        rightHang.setPower(powers);
    }
    public void extend() {
        motorPowers(1);
    }
    public void fuck() {
        motorPowers(-1);
    }
}
