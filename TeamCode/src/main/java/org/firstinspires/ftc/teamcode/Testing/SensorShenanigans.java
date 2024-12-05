package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SensorShenanigans {
    public TouchSensor sensor;
    public Servo servo;
    public HardwareMap hardwareMap;
    public Telemetry telemetry;

    public static double grabPose = 0,
            scorePose = 0.225;
    public static double resetPose = 0;

    public SensorShenanigans(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.telemetry = opMode.telemetry;

        servo = (Servo) hardwareMap.get("Servo");
        sensor = (TouchSensor) hardwareMap.get("Sensor");

        servo.setDirection(Servo.Direction.REVERSE);
        servo.setPosition(scorePose);;
    }
    public void teleOp() {
        if (sensor.isPressed()) {
            servo.setPosition(resetPose);
        }
        else servo.setPosition(scorePose);
    }
}
