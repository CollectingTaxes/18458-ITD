package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Arm  {
    public HardwareMap hardwareMap;

    public static double GRAB_POSE = 0.32;
    public static double RESET_POSE = 0.79;
    public static double SPEC_GRAB = 0.41;
    public static double AUTO_GRAB = 0.41;

    Telemetry telemetry;
    private final Servo leftArm, rightArm;

    public Arm(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;

        this.leftArm = (Servo) hardwareMap.get("leftArm");
        this.rightArm = (Servo) hardwareMap.get("rightArm");

        leftArm.setDirection(Servo.Direction.REVERSE);
        rightArm.setDirection(Servo.Direction.FORWARD);

    }

    public void grab() {
        leftArm.setPosition(GRAB_POSE);
        rightArm.setPosition(GRAB_POSE);
    }
    public void auto() {
        leftArm.setPosition(AUTO_GRAB);
        rightArm.setPosition(AUTO_GRAB);
    }

    public void specGrab() {
        leftArm.setPosition(SPEC_GRAB);
        rightArm.setPosition(SPEC_GRAB);
    }

    public void reset() {
        leftArm.setPosition(RESET_POSE);
        rightArm.setPosition(RESET_POSE);
    }
}
