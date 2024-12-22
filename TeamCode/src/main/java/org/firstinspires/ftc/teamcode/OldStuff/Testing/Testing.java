package org.firstinspires.ftc.teamcode.OldStuff.Testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp
public class Testing extends LinearOpMode {

    TouchSensor touchSensor;// Touch sensor Object
    Servo servo;

    @Override
    public void runOpMode() {

        // get a reference to our touchSensor object.
        touchSensor = hardwareMap.get(TouchSensor.class, "Sensor");
        servo = (Servo) hardwareMap.get("Servo");

        // wait for the start button to be pressed.
        waitForStart();

        // while the OpMode is active, loop and read whether the sensor is being pressed.
        // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
        while (opModeIsActive()) {

            // send the info back to driver station using telemetry function.
            if (touchSensor.isPressed()) {
                servo.setPosition(0);
                telemetry.addData("Touch Sensor", "Is Pressed");
            } else {
                telemetry.addData("Touch Sensor", "Is Not Pressed");
                servo.setPosition(0.225);
            }

            telemetry.update();
        }
    }
}
