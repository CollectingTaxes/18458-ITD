package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
public class SampleDetector extends SubsystemBase implements HardwareDevice {

    private ColorSensor colorSensor;
    private Telemetry telemetry;

    public int BLUE_RGB = 500;
    public int RED_RGB = 500;

    public SampleDetector(HardwareMap hardwareMap , Telemetry tl) {
        this.colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
        this.telemetry = tl;
        this.colorSensor.enableLed(true);
    }

    public void periodic() {
//        telemetry.addData("\tAlpha:", colorSensor.alpha());
//        telemetry.addData("\tRed:", colorSensor.red());
//        telemetry.addData("\tGreen:", colorSensor.green());
//        telemetry.addData("\tBlue:", colorSensor.blue());

        //TODO: TELEMETRY
    }

    public boolean grabbedRedSample() {
        return (colorSensor.blue() > RED_RGB);
    }
    public boolean grabbedBlueSample() {
        return (colorSensor.red() > BLUE_RGB);
    }
    public boolean grabbedYellowSample() {
        return (colorSensor.red() > 500 && colorSensor.green() > 500);
    }
//    public boolean distance() {
//        if (colorSensor.getDistance(DistanceUnit.CM) <= 7) {
//            return (true);
//        }
//        else return (false);
//    }
    @Override
    public void disable() {
        colorSensor.close();}

    @Override
    public String getDeviceType() {
        return "Color Sensor";}
}