package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
public class Sensor {
    private DistanceSensor distanceSensor;
    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    public Sensor(OpMode opMode) {

        this.hardwareMap = opMode.hardwareMap;

        this.distanceSensor = (DistanceSensor) hardwareMap.get("Sensor");

        telemetry.addData("Distance:", distanceSensor.getDistance(DistanceUnit.INCH));
    }
    public boolean distanceDoesNotEqual(double Unit) {
        return distanceSensor.getDistance(DistanceUnit.INCH) <= Unit;
    }
}