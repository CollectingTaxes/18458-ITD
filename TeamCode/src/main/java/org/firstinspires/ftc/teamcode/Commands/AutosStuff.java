package org.firstinspires.ftc.teamcode.Commands;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;

public class AutosStuff {
    public Servo leftArm, rightArm;

    public HardwareMap hardwareMap;
    public Telemetry telemetry;

    public static double INTAKE = Arm.grabPose;
    public static double OUTTAKE = Arm.resetPose;

    public AutosStuff(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;

        leftArm = (Servo) hardwareMap.get("leftArm");
        rightArm = (Servo) hardwareMap.get("rightArm");

        leftArm.setDirection(Servo.Direction.FORWARD);
        rightArm.setDirection(Servo.Direction.REVERSE);

    }
    public void INTAKE() {
        leftArm.setPosition(INTAKE);
        rightArm.setPosition(INTAKE);
    }
    public void OUTTAKE() {
        leftArm.setPosition(OUTTAKE);
        rightArm.setPosition(OUTTAKE);
    }
}
