package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.VelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;
import org.firstinspires.ftc.teamcode.RoadRunner.StrafeChassis;

import java.util.ArrayList;
import java.util.List;

@Config
@Autonomous
public class BlueSpec extends LinearOpMode {

    public Claw claw;
    public Wrist wrist;
    public Arm arm;
    public Slides slides;

    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d StartPose = new Pose2d(-17.5, 64, Math.toRadians(270));
        Pose2d Preload = new Pose2d(-8, 30, Math.toRadians(270));
        Pose2d FirstPush = new Pose2d(-50, 55, Math.toRadians(90));
        Pose2d SecondPush = new Pose2d(-59, 55, Math.toRadians(90));
        Pose2d Cycle = new Pose2d(-32, 60, Math.toRadians(180));
        Pose2d Score = new Pose2d(-17.5, 64, Math.toRadians(270));

        StrafeChassis drive = new StrafeChassis(hardwareMap, StartPose);
        List<Action> runningActions = new ArrayList<>();


        arm = new Arm(this);
        claw = new Claw(this);
        slides = new Slides(this);
        wrist = new Wrist(this);

        while (!opModeIsActive() && !isStopRequested()) {
            arm.reset();
            claw.open();
            slides.liftRest();
            wrist.neutralGrab();

            waitForStart();

            if (opModeIsActive()) {
                //PreLoad
                Actions.runBlocking(
                        drive.actionBuilder(StartPose)
                                .splineToLinearHeading(new Pose2d(-8,32, Math.toRadians(270)), Math.toRadians(270))
                                .build());

                //First Push
                Actions.runBlocking(
                        drive.actionBuilder(Preload)
                                .turn(Math.toRadians(180))
                                .strafeToLinearHeading(new Vector2d(-18, 35), Math.toRadians(90))
                                .splineToLinearHeading(new Pose2d(-50, 15, Math.toRadians(90)), Math.toRadians(90))
                                .lineToYConstantHeading(55)
                                .build());

                //Second Push
                Actions.runBlocking(
                        drive.actionBuilder(FirstPush)
                                .strafeToConstantHeading(new Vector2d(-50, 19))
                                .splineToLinearHeading(new Pose2d(-59, 19, Math.toRadians(90)), Math.toRadians(90))
                                .lineToYConstantHeading(55)
                                .build());

                //Cycle 1
                Actions.runBlocking(
                        drive.actionBuilder(SecondPush)
                                .strafeToLinearHeading(new Vector2d(-32, 60), Math.toRadians(180))
                                .build());

                //Cycle 1 End
                Actions.runBlocking(
                        drive.actionBuilder(SecondPush)
                                .splineToLinearHeading(new Pose2d(-7, 30, Math.toRadians(270)), Math.toRadians(270))
                                .build());




                  /*runningActions.add(
                          new SequentialAction(
                                  new InstantAction(slides::liftHigh)
                          )
                  );*/


            }


        }
    }
}
