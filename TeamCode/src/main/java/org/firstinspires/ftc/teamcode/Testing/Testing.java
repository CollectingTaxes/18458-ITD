package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Testing extends LinearOpMode {

    FuckCommandbase drive = new FuckCommandbase(this);
    @Override
    public void runOpMode() throws InterruptedException {
        drive.teleOp();
    }
}
