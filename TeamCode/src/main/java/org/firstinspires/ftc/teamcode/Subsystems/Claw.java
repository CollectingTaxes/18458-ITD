package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SensorColor;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Claw extends SubsystemBase {

    private final ServoEx clawServo;
    public double opened = 0.5;
    public double closed = 0;
    public String state;
    Telemetry telemetry;

    public Claw (final HardwareMap hardwareMap, final String name) {
        clawServo = hardwareMap.get(ServoEx.class, name);
    }

    @Override
    public void periodic() {
        telemetry.addData("Claw position ", clawServo.getPosition());
        telemetry.addData("Claw is ", clawState(state));

    }
    public void intake() {
        clawServo.setPosition(opened);
        clawState("Opened");
    }
    public void outtake() {
        clawServo.setPosition(closed);
        clawState("Closed");
    }
    public String clawState(String state) {
        return state;
    }
}
