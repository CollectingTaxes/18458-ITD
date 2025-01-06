package org.firstinspires.ftc.teamcode.OldStuff.RoadRunner.drive.opmode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.OldStuff.RoadRunner.drive.StrafeChassis;

/*
 * This is a simple routine to test turning capabilities.
 */
@Config
public class TurnTest extends LinearOpMode {
    public static double ANGLE = 90; // deg

    @Override
    public void runOpMode() throws InterruptedException {
        StrafeChassis drive = new StrafeChassis(hardwareMap, telemetry, false);

        waitForStart();

        if (isStopRequested()) return;

        drive.turn(Math.toRadians(ANGLE));
    }
}
