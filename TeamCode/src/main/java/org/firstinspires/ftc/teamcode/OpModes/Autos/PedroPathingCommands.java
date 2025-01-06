package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

public class PedroPathingCommands {


    /*
    HARDWARE
     */
    private Servo grabber, wrist, leftArm, rightArm;
    private DcMotor leftSlide, rightSlide;
    private ColorRangeSensor sampleDetector;
    /*
    OTHER
     */
    Telemetry telemetry;
    HardwareMap hardwareMap;

    public PedroPathingCommands(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.telemetry = opMode.telemetry;

        grabber.setDirection(Servo.Direction.REVERSE);

        //TODO: DIRECTIONS COULD BE WRONG
//        leftArm.setDirection(Servo.Direction.FORWARD);
//        rightArm.setDirection(Servo.Direction.REVERSE);
//
//        wrist.setDirection();
//
//        leftSlide.setDirection();
//        rightSlide.setDirection();

        grabber = (Servo) hardwareMap.get("Claw");

        leftArm = (Servo) hardwareMap.get("leftArm");
        rightArm = (Servo) hardwareMap.get("rightArm");

        wrist = (Servo) hardwareMap.get("Wrist");

        leftSlide = (DcMotor) hardwareMap.get("leftSlide");
        rightSlide = (DcMotor) hardwareMap.get("rightSlide");

        sampleDetector = (ColorRangeSensor) hardwareMap.get("colorSensor");
    }
    //TODO: SEE IF I CAN DO THREAD.SLEEP AND THE AUTO STILL RUNS
    public void clawGrabsSamplesArmOuttakes() {
        //CLAW GRABS
        grabber.setPosition(Claw.GRAB);

        //ARM GOES TO OUTTAKE POSE
        armPoses(Arm.RESET_POSE);

        //WRIST IS SURE ITS IN THE NEUTRAL POSE
        wrist.setPosition(Wrist.NEUTRAL_POSE);
    }
    public void scoringSpecimens() throws InterruptedException{
        //SLIDES RAISE HIGH
        slidesPoses(Slides.High);
        Thread.sleep(100);
        slidesPoses(Slides.Reset);
        Thread.sleep(100);
        grabber.setPosition(Claw.OPEN);
    }

    /*
    UTIL METHODS
     */
    public void armPoses(double armPose) {
        leftArm.setPosition(armPose);
        rightArm.setPosition(armPose);
    }
    public void slidesPoses(int slidePose) {
        leftSlide.setTargetPosition(slidePose);
        rightSlide.setTargetPosition(slidePose);
    }
}
