package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ServoTest extends OpMode {
    public Servo servo, servo1;

    public static double init = 0;

    @Override
    public void init() {
        servo = hardwareMap.get(Servo.class, "servo");
        servo1 = hardwareMap.get(Servo.class, "servo1");

        servo.setDirection(Servo.Direction.FORWARD);
        servo1.setDirection(Servo.Direction.REVERSE);

        servo.setPosition(init);
        servo1.setPosition(init);
    }

    @Override
    public void loop() {

        if (gamepad1.right_bumper) {
            servo.setPosition(servo.getPosition() + 0.001);
            //servo1.setPosition(servo1.getPosition() + 0.001);
        } else if (gamepad1.left_bumper) {
            servo.setPosition(servo.getPosition() - 0.001);
            //servo1.setPosition(servo1.getPosition() - 0.001);
        }
        telemetry.addData("servo pos", servo.getPosition());
        telemetry.update();
    }
}
