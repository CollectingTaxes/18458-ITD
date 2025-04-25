package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Claw {

    // testing to see if you can change booleans
    public static boolean REVERSED = true;

    public HardwareMap hardwareMap;
    public Gamepad gamepad2;
    public boolean clawStateGrabbed = true;

    public static double GRAB = 0.25, OPEN = 0.5;

    public Servo claw, turningThingy;

    public boolean doesDamianHaveBrownHair = false;

    public Claw(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.gamepad2 = opMode.gamepad2;

        this.claw = (Servo) hardwareMap.get("Claw");

        //lateral wrist movement
        this.turningThingy = (Servo) hardwareMap.get("turningThingy");

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
    public void intake() {
        turningThingy.setPosition(0);
    }
    public void outtake() {
        turningThingy.setPosition(0.3);
    }
    public void specIntake() {
        turningThingy.setPosition(0.5);
    }

}
