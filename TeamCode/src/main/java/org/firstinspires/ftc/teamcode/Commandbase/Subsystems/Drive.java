
package org.firstinspires.ftc.teamcode.Commandbase.Subsystems;


import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.SubsystemBase;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.RoadRunner.StrafeChassis;

import java.util.List;


public class Drive extends SubsystemBase {
    public final StrafeChassis drive;
    public DcMotor leftFront, rightFront, rightRear, leftRear;
    private Telemetry telemetry;
    private HardwareMap hardwareMap;
    private IMU imu;

    private final int LFVal = 0,
            LRVal = 1,
            RFVal = 2,
            RRVal = 3;
    double[] powers = new double[4];

    public Drive(StrafeChassis drive, HardwareMap hardwareMap) {
        this.drive = drive;
        this.hardwareMap = hardwareMap;
//        imu = hardwareMap.get(BNO055IMU.class, "imu");
    }


    public void init() {
        new Pose2d(0,0,0);

    }

    public void reInitializeIMU() {
        imu.resetYaw();
    }
    public void setMotorPowers(double powers) {
        leftFront.setPower(powers);
        rightFront.setPower(powers);
        leftRear.setPower(powers);
        rightRear.setPower(powers);
    }

    public void setPowers(double leftF, double leftR, double rightR, double rightF) {
        setMotorPowers(leftF, leftR, rightR, rightF);
    }

    public void mecDrive(double y, double x, double rx) {
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        powers [LFVal] = (y + x + rx) / denominator;    //fLPower
        powers [LRVal] = (y - x + rx) / denominator;    //bLPower
        powers [RFVal] = (y - x - rx) / denominator;    //fRPower
        powers [RRVal] = (y + x - rx) / denominator;    //bRPower
        setMotorPowers(powers[LFVal], powers[LRVal], powers[RFVal], powers[RRVal]);
    }

    public void  fieldCentric(double y, double x, double rx) {

        double theta = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double rotX = x * Math.cos(theta) - y * Math.sin(theta);
        double rotY = x * Math.sin(theta) + y * Math.cos(theta);
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        // ^^^^^^ Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio, but only when
        // at least one is out of the range [-1, 1]

        powers [LFVal] = (rotY + rotX - rx) / denominator;
        powers [LRVal] = (rotY - rotX - rx) / denominator;
        powers [RFVal] = (rotY + rotX + rx) / denominator;
        powers [RRVal] = (rotY - rotX + rx) / denominator;


        if(Math.abs(powers[LFVal])<0.25&Math.abs(powers[LRVal])<0.25&Math.abs(powers[RFVal])<0.25&Math.abs(powers[RRVal])<0.25){
            for (int i = 0; i <= 3; i++) {
//                powers[i] = squareInput(powers[i]);
                powers[i] = cubeInput(powers[i]);
            }
        }
        setMotorPowers(powers[LFVal], powers[LRVal], powers[RFVal], powers[RRVal]);
    }
    public void setMotorPowers(double lFP, double lRP, double rFP, double rRP) {

    }

    private double squareInput(double power) {
        return power * Math.abs(power);
    }
    private double cubeInput(double power) {
        return power*Math.abs(power)*Math.abs(power);
    }


    /**
     * Returns minimum range value if the given value is less than
     * the set minimum. If the value is greater than the set maximum,
     * then the method returns the maximum value.
     *
     * value - The value to clip.
     */

    @Override
    public void periodic() {

    }

    public void stop() {
        setPowers(0, 0, 0, 0);
    }

    private double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }

    /**
     * Returns minimum range value if the given value is less than
     * the set minimum. If the value is greater than the set maximum,
     * then the method returns the maximum value.
     *
     * @param value The value to clip.
     */
    public double clipRange(double value) {
        return value <= -1 ? -1
                : value >= 1 ? 1
                : value;
    }

    /**
     * Normalize the wheel speeds
     */
    protected void normalize(double[] wheelSpeeds, double magnitude) {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);
        for (int i = 1; i < wheelSpeeds.length; i++) {
            double temp = Math.abs(wheelSpeeds[i]);
            if (maxMagnitude < temp) {
                maxMagnitude = temp;
            }
        }
        for (int i = 0; i < wheelSpeeds.length; i++) {
            wheelSpeeds[i] = (wheelSpeeds[i] / maxMagnitude) * magnitude;
        }

    }

    /**
     * Normalize the wheel speeds
     */
    protected void normalize(double[] wheelSpeeds) {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);
        for (int i = 1; i < wheelSpeeds.length; i++) {
            double temp = Math.abs(wheelSpeeds[i]);
            if (maxMagnitude < temp) {
                maxMagnitude = temp;
            }
        }
        if(maxMagnitude > 1) {
            for (int i = 0; i < wheelSpeeds.length; i++) {
                wheelSpeeds[i] = (wheelSpeeds[i] / maxMagnitude);
            }
        }
    }
}