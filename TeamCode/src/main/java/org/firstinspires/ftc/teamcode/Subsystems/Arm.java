package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SensorColor;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm extends SubsystemBase {

    private final ServoEx rightArm, leftArm;

    Telemetry telemetry;

    public double INTAKE = 0.5, OUTTAKE = 1, REST = 0;

    public Arm(final HardwareMap hardwareMap, final String name, final String nameOther ) {
        rightArm = hardwareMap.get(ServoEx.class, name);
        rightArm.setInverted(true);

        leftArm = hardwareMap.get(ServoEx.class, nameOther);
        leftArm.setInverted(false);
    }

    @Override
    public void periodic() {
        telemetry.addData("Left Arm:", leftArm.getPosition());
        telemetry.addData("Right Arm", rightArm.getPosition());
    }
    public void intake() {
        leftArm.setPosition(INTAKE);
        rightArm.setPosition(INTAKE);
    }
    public void outtake() {
        leftArm.setPosition(OUTTAKE);
        rightArm.setPosition(OUTTAKE);

    }
    public void rest() {
        leftArm.setPosition(REST);
        rightArm.setPosition(REST);
    }
}
