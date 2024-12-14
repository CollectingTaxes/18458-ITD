package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SensorColor;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Arm extends SubsystemBase {

    public static boolean REVERSED = false;

    public static double resetPose = 0.45,
            grabPose = 0.105,
            emergencyPose = 0.095;

    Telemetry telemetry;
    private final SimpleServo leftArm, rightArm;

    public Arm(final HardwareMap hMap, Telemetry telemetry) {
        this.leftArm = new SimpleServo(hMap, "leftArm", 0, 360);
        this.rightArm = new SimpleServo(hMap, "rightArm", 0, 360);

        leftArm.setInverted(REVERSED);
        rightArm.setInverted(true);

        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        telemetry.addData("ArmPose", leftArm.getPosition());
    }

    public void grab() {
        leftArm.setPosition(grabPose);
        rightArm.setPosition(grabPose);
    }

    public void reset() {
        leftArm.setPosition(resetPose);
        rightArm.setPosition(resetPose);
    }
    public void holyMolyStringingBroke() {

        leftArm.setPosition(emergencyPose);
        rightArm.setPosition(emergencyPose);
    }
}
