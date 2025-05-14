package org.firstinspires.ftc.teamcode.Commandbase.Commands;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RoadRunner.StrafeChassis;

public class FiveSpecPath {
    public StrafeChassis drive;
    public HardwareMap hardwareMap;

    public static Pose2d StartPose = new Pose2d(-6, 60.5, Math.toRadians(270));

    public FiveSpecPath(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        drive = new StrafeChassis(hardwareMap, StartPose);
    }

    public void StrafeToLinearHeading(Vector2d point, double heading, Pose2d pose) {
        Actions.runBlocking(
                drive.actionBuilder(pose)
                        .strafeToLinearHeading(point, heading)
                        .build());
        }

    public void StrafeToConstantHeading(Vector2d point, Pose2d pose) {
        Actions.runBlocking(
                drive.actionBuilder(pose)
                        .strafeToConstantHeading(point,
                                null,
                                new ProfileAccelConstraint(-47.5, 47.5))
                        .build());

    }

    public void SplineToConstantHeading(Vector2d point, double tangent, Pose2d pose) {
        Actions.runBlocking(
                drive.actionBuilder(pose)
                        .splineToConstantHeading(point, Math.toRadians(tangent))
                        .build());
    }
    public void StrafeFast(Vector2d point, Pose2d pose, double minAccel, double maxAccel, double wheelVel) {

        Actions.runBlocking(
                drive.actionBuilder(pose)
                        .strafeToConstantHeading(point,
                                new TranslationalVelConstraint(wheelVel),
                                new ProfileAccelConstraint(minAccel, maxAccel))
                        .build()
        );
    }
    public void StrafeFastWAction(Vector2d point, Pose2d pose, double minAccel, double maxAccel, double wheelVel, Action movement) {
        Actions.runBlocking(
                drive.actionBuilder(pose)
                        .afterTime(0.25, movement)
                        .strafeToConstantHeading(point,
                                new TranslationalVelConstraint(wheelVel),
                                new ProfileAccelConstraint(minAccel, maxAccel))
                        .build()
        );
    }

    public void StrafeWAction(Vector2d point, Pose2d pose, Action movement, double time) {
        Actions.runBlocking(
                drive.actionBuilder(pose)
                        .afterTime(time, movement)
                        .strafeToConstantHeading(point)
                        .build()
        );
    }
}
