package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Hang {
    private Telemetry telemetry;
    private DcMotor Hang;
    private HardwareMap hardwareMap;


    public static int min = -5;
    public static int max = 2500;
    public static int reset = 0, hang = 0, lineup = 0;
    public int current = 0;

    public Hang (OpMode opMode) {
        this.telemetry = opMode.telemetry;
        this.hardwareMap = opMode.hardwareMap;

        this.Hang= (DcMotor) hardwareMap.get("leftHang");

        Hang.setDirection(DcMotorSimple.Direction.FORWARD);
        Hang.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hang.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setPos(int pos) {
        if (pos <= max && pos >= min) current = pos;
        System.out.println(current);
        normalize();
    }

    public int getPos() {
        return current;
    }

    public void normalize() {
        Hang.setTargetPosition(current);
        Hang.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        Hang.setPower(1);
    }

    public void setHang() {
        setPos(hang);
    }

    public void setReset() {
        setPos(reset);
    }

    public void setLineup() {
        setPos(lineup);
    }


}
