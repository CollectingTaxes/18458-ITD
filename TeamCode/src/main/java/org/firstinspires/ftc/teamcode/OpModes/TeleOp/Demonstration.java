package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import static org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit.CM;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.OpModes.Autos.PedroPathingCommands;

@TeleOp
public class Demonstration extends LinearOpMode {
    PedroPathingCommands pedroPathingCommands;

    @Override
    public void runOpMode() throws InterruptedException {
        pedroPathingCommands = new PedroPathingCommands(this);

        waitForStart();
        while (opModeIsActive()) {
            pedroPathingCommands.scoringSpecimens();
        }
    }
}
