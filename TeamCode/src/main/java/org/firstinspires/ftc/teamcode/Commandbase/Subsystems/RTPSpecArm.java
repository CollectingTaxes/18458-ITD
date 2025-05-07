
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
public class RTPSpecArm {
    private final Telemetry telemetry;
    private final DcMotor specArm;
    private final HardwareMap hardwareMap;

    public static int min = -5;
    public static int max = 2500;

    public static int Intake = 750;
    public static int Start = 200;
    public static int Mid = 500;
    public static int Outtake = 0;
    public static int semifloor = 1250;
    public static int Floor = 1400;
    public int current = 0;

    public RTPSpecArm (OpMode opMode) {
        this.telemetry = opMode.telemetry;
        this.hardwareMap = opMode.hardwareMap;

        this.specArm = (DcMotor) hardwareMap.get("specArm");


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

    public void normalize() {
        specArm.setTargetPosition(current);
        specArm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        specArm.setPower(1);
    }

    //Lift Pose
    public void Intake() {
        setPos(Intake);
    }

    public void Mid() {
        setPos(Mid);
    }

    public void SemiFloor() {
        setPos(semifloor);
    }

    public void Outtake() {
        setPos(Outtake);
    }

    public void Start() {
        setPos(Start);
    }

    public void Floor() {
        setPos(Floor);
    }
}
