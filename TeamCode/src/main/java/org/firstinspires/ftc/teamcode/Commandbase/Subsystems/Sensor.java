package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SensorColor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Sensor extends SubsystemBase {

    private final SensorColor colorSensor;
    Telemetry telemetry;

    public Sensor(final HardwareMap hardwareMap, final String name) {
        colorSensor = hardwareMap.get(SensorColor.class, name);
    }

    @Override
    public void periodic() {
        
    }
    public boolean returnRed() {
        return (colorSensor.red() > 500);
    }
}
