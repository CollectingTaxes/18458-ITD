package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@Config
//TODO: TALK TO CARLOS ABOUT CONTROLS FOR THE WRIST
public class Wrist extends SubsystemBase {

    // testing to see if you can change booleans
    public static boolean REVERSED = false;

    public boolean sensorOn = true;

    public static double NEUTRAL_POSE = 0.14;
    public double HORIZONTAL_GRAB_POSE = 0.45;

    Telemetry telemetry;
    private static ServoEx wrist;
    private static CRServo meh;

    /*37.5 degrees of rotation is = 45
        1 = 300
        0 = 0
        37.5 degrees is 1/8 of a rotation for a 300* servo
        37.5 degrees = 0.125
        45 degrees = 0.15
         */
    public double rotating = 0.15;


    public Wrist(final HardwareMap hMap, Telemetry telemetry) {
        wrist = new SimpleServo(hMap, "Wrist", 0, 360);

        wrist.setInverted(REVERSED);

        this.telemetry = telemetry;
    }
    @Override
    public void periodic() {
        telemetry.addData("Wrist Pose: ", wrist.getPosition());
        telemetry.addData("State of Wrist wire: ", holyMolyServoWireIsTangledUp());
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
    public String holyMolyServoWireIsTangledUp() {
        if (wrist.getPosition() <= 0.375) {
            return "SERVO WIRE TWISTING AROUND ARM" +
                    "SERVO WIRE TWISTING AROUND ARM";
        }
        else return "We good gang.";
    }
}
