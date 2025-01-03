package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import static org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit.CM;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Demonstration extends LinearOpMode {
    Servo servo;
    ColorRangeSensor colorSensor;

    @Override
    public void runOpMode() throws InterruptedException {
        hardwareMap.get(ColorRangeSensor.class, "colorSensor");
        hardwareMap.get(Servo.class, "Claw");

        waitForStart();
        while(opModeIsActive()) {
            if (colorSensor.getDistance(CM) == 0.636)
                servo.setPosition(0.24);
        }
    }
}
