package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import static com.qualcomm.robotcore.hardware.Servo.Direction.FORWARD;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Wrist {
    public static double NEUTRAL_POSE = 0.837;
    public static double HORIZONTAL_GRAB_POSE = 0.509;
    private final Servo wrist;
    public HardwareMap hardwareMap;
    public double rotating = 0.15;


    public Wrist(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.wrist = (Servo) hardwareMap.get("wrist");

        wrist.setDirection(FORWARD);
    }
    public void neutralGrab() {
        wrist.setPosition(NEUTRAL_POSE);
    }

    public void horizontalGrab() {
        wrist.setPosition(HORIZONTAL_GRAB_POSE);
    }
    public void incrementing() {
        wrist.setPosition(wrist.getPosition() + rotating);
    }
    public void decrementing() {
        wrist.setPosition(wrist.getPosition() - rotating);
    }
}
