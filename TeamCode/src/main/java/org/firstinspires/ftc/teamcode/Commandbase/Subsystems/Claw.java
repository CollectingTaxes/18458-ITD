package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Claw extends SubsystemBase {

    // testing to see if you can change booleans
    public static boolean REVERSED = false;

    public boolean sensorOn = true;

    public static double GRAB = 0.24, OPEN = 0;

    Telemetry telemetry;
    private static ServoEx Claw;


    public Claw(final HardwareMap hMap, Telemetry telemetry) {
        Claw = new SimpleServo(hMap, "Claw", 0, 360);

        Claw.setInverted(REVERSED);

        this.telemetry = telemetry;
    }
    @Override
    public void periodic() {
        telemetry.addData("ClawPose", Claw.getPosition());
    }

    public void GRAB() {
        Claw.setPosition(GRAB);
        sensorOn = false;
    }

    public void OPEN() {
        Claw.setPosition(OPEN);
        sensorOn = true;
    }

    public boolean clawStateOpen(){
        return (Claw.getPosition()==GRAB);
    }
}
