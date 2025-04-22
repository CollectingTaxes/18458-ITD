package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Testing {
    private Servo servo;
    public HardwareMap hardwareMap;

    public Testing(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;

        this.servo = (Servo) hardwareMap.get("specClaw");
    }

    public class OpenClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            servo.setPosition(0.5);
            return false;
        }
    }
    public Action openClaw() {
        return new OpenClaw();
    }

    public class CloseClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            servo.setPosition(0);
            return false;
        }
    }
    public Action closeClaw() {
        return new CloseClaw();
    }
}



