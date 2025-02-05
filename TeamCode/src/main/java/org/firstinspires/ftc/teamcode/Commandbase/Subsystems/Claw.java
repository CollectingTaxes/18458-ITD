package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Claw {

    // testing to see if you can change booleans
    public static boolean REVERSED = true;

    public HardwareMap hardwareMap;
    public Gamepad gamepad2;
    public boolean clawStateGrabbed = true;

    public static double OPEN = 0.2, GRAB = 0.5;

    public Servo claw;


    public Claw(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;

        this.claw = (Servo) hardwareMap.get("Claw");

        claw.setDirection(Servo.Direction.REVERSE);

        grab();
    }
    public void teleOp() {
        if (gamepad2.b) {
            grab();
        }
        else if (gamepad2.a) {
            open();
        }
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
