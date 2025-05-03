package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class SpecServos {

    public HardwareMap hardwareMap;
    public static double OPEN = 0, GRAB = 0.34;
    public static double NEUTRAL = 0, SCORE = 0.66;

    public Servo specWrist, specClaw;

    public SpecServos(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;

        this.specClaw = (Servo) hardwareMap.get("sClaw");
        this.specWrist = (Servo) hardwareMap.get("sWrist");
    }
    public void grab() {
        specClaw.setPosition(GRAB);
    }

    public void open() {
        specClaw.setPosition(OPEN);
    }
    public void nuetral() {
        specWrist.setPosition(NEUTRAL);
    }
    public void score() {
        specWrist.setPosition(SCORE);
    }

}
