package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import static com.qualcomm.robotcore.hardware.Servo.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.Servo.Direction.REVERSE;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@Config
//TODO: TALK TO CARLOS ABOUT CONTROLS FOR THE WRIST
public class Wrist {

    // testing to see if you can change booleans
    public static boolean REVERSED = false;

    public boolean sensorOn = true;

    public static double NEUTRAL_POSE = 0.615;
    public static double AUTO_POSE = 0.18;
    public static double HORIZONTAL_GRAB_POSE = 0.27;

    Telemetry telemetry;
    private static Servo wrist;
    public HardwareMap hardwareMap;
    /*37.5 degrees of rotation is = 45
        1 = 300
        0 = 0
        37.5 degrees is 1/8 of a rotation for a 300* servo
        37.5 degrees = 0.125
        45 degrees = 0.15
         */
    public double rotating = 0.15;


    public Wrist(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.wrist = (Servo) hardwareMap.get("wrist");

        wrist.setDirection(REVERSE);

        this.telemetry = telemetry;
    }
    public void teleOp() {

    }

    public void neutralGrab() {
        wrist.setPosition(NEUTRAL_POSE);
    }
    public void specGrab() {
        wrist.setPosition(AUTO_POSE);
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
        if (wrist.getPosition() <= 0.5) {
            return "SERVO WIRE TWISTING AROUND ARM" +
                    "SERVO WIRE TWISTING AROUND ARM";
        }
        else return "We good gang.";
    }
}
