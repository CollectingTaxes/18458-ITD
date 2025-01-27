package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Arm extends SubsystemBase {

    public static boolean FORWARD = false;

    public static double RESET_POSE = 0.41;
    public static double GRAB_POSE = 0.1;

    Telemetry telemetry;
    private final SimpleServo leftArm, rightArm;

    public Arm(final HardwareMap hMap, Telemetry telemetry) {
        this.leftArm = new SimpleServo(hMap, "leftArm", 0, 360);
        this.rightArm = new SimpleServo(hMap, "rightArm", 0, 360);

        leftArm.setInverted(FORWARD);
        rightArm.setInverted(true);

        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        telemetry.addData("ArmPose", leftArm.getPosition());
    }

    public void grab() {
        leftArm.setPosition(GRAB_POSE);
        rightArm.setPosition(GRAB_POSE);
    }

    public void reset() {
        leftArm.setPosition(RESET_POSE);
        rightArm.setPosition(RESET_POSE);
    }
}
