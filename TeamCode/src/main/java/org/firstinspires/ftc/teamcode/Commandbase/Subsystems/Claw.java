package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Claw {

    // testing to see if you can change booleans
    public static boolean REVERSED = false;

    public boolean clawStateGrabbed = true;

    public static double GRAB = 0.25, OPEN = 0;

    Telemetry telemetry;
    public Servo claw;


    public Claw(OpMode opMode) {
        this.claw = (Servo) opMode.hardwareMap.get("claw");

        claw.setDirection(Servo.Direction.REVERSE);

        this.telemetry = telemetry;

        grab();
    }


    public void grab() {
        claw.setPosition(GRAB);
        clawStateGrabbed = true;
    }

    public void open() {
        claw.setPosition(OPEN);
        clawStateGrabbed = false;
    }
}
