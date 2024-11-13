package org.firstinspires.ftc.teamcode.TeamCode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SensorColor;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw extends SubsystemBase {

    private final ServoEx clawServo;
    Telemetry telemetry;

    public Claw (final HardwareMap hardwareMap, final String name) {
        clawServo = hardwareMap.get(ServoEx.class, name);
    }

    @Override
    public void periodic() {

    }
    public void intake() {
        clawServo.setPosition(0);
    }
    public void outtake() {
        clawServo.setPosition(1);
    }
}
