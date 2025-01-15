//package org.firstinspires.ftc.teamcode.OpModes.Autos;
//
//import com.arcrobotics.ftclib.command.CommandScheduler;
//import com.arcrobotics.ftclib.command.SequentialCommandGroup;
//import com.pedropathing.follower.Follower;
//import com.pedropathing.localization.Pose;
//import com.pedropathing.pathgen.BezierLine;
//import com.pedropathing.pathgen.PathChain;
//import com.pedropathing.pathgen.Point;
//import com.pedropathing.util.Constants;
//import com.pedropathing.util.Timer;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//
//import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
//import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
//import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;
//
//@Autonomous
//public class LASTRESORT extends OpMode {
//
//    //Paths
//    private Follower follower;
//    private Timer pathTimer;
//    private int pathState;
//    private PathChain scorePreloadPath, moveFromFirstSpecimenScorePath, strafeToSampsPath,
//            moveToSampsPath, moveToFirstSampPath, pushFirstSampPath, moveToSecondSampPath,
//            strafeToSecondSampPath, pushSecondSampPath, moveToThirdSampPath, strafeToThirdSampPath,
//            pushThirdSampPath;
//
//    //Poses
//    private final Pose startPose = new Pose(0.504, 57.802, Math.toRadians(0));
//    private final Pose scorePreloadPose = new Pose(30.319, 57.802, Math.toRadians(0));
//    private final Pose moveFromScorePreloadPose = new Pose(23.319, 57.802, Math.toRadians(0));
//    private final Pose strafeToSampsPose = new Pose(23.319, 25.802, Math.toRadians(0));
//    private final Pose moveToSampsPose = new Pose(50.319, 25.802, Math.toRadians(0));
//
//    private final Pose moveToFirstSampPose = new Pose(50.319, 17.802, Math.toRadians(0));
//    private final Pose pushFirstSampPose = new Pose(11.00, 17.802, Math.toRadians(0));
//
//    private final Pose moveToSecondSampPose = new Pose(50.319, 17.802, Math.toRadians(0));
//    private final Pose strafeToSecondSampPose = new Pose(50.319, 9.802, Math.toRadians(0));
//    private final Pose pushSecondSampPose = new Pose(11.00, 9.802, Math.toRadians(0));
//
//    private final Pose moveToThirdSampPose = new Pose(65.319, 9.802, Math.toRadians(180));
//    private final Pose strafeToThirdSampPose = new Pose(65.319, -8, Math.toRadians(180));
//    private final Pose pushThirdSampPose = new Pose(8.00, -8, Math.toRadians(180));
//
//    @Override
//    public void init() {
//
//        Constants.setConstants(FConstants.class, LConstants.class);
//        Claw claw = new Claw(hardwareMap, telemetry);
//        follower = new Follower(hardwareMap);
//        pathTimer = new Timer();
//        pathTimer.resetTimer();
//
//        follower.setStartingPose(startPose);
//        path();
//
//        CommandScheduler.getInstance().schedule(
//                new SequentialCommandGroup(
//                        new PathFollower(scorePreloadPath.getPath(0)),
//                        new PathFollower(moveFromFirstSpecimenScorePath.getPath(0)),
//                        new PathFollower(strafeToSampsPath.getPath(0)),
//                        new PathFollower(moveToSampsPath.getPath(0)),
//                        new PathFollower(moveToFirstSampPath.getPath(0)),
//                        new PathFollower(pushFirstSampPath.getPath(0)),
//                        new PathFollower(moveToSecondSampPath.getPath(0)),
//                        new PathFollower(strafeToSecondSampPath.getPath(0)),
//                        new PathFollower(pushSecondSampPath.getPath(0)),
//                        new PathFollower(moveToThirdSampPath.getPath(0)),
//                        new PathFollower(strafeToThirdSampPath.getPath(0)),
//                        new PathFollower(pushThirdSampPath.getPath(0))
//                )
//        );
//    }
//
//    public void path() {
//        scorePreloadPath = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(startPose), new Point(scorePreloadPose)))
//                .setLinearHeadingInterpolation(startPose.getHeading(), scorePreloadPose.getHeading())
//                .build();
//
//        moveFromFirstSpecimenScorePath = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(scorePreloadPose), new Point(moveFromScorePreloadPose)))
//                .setLinearHeadingInterpolation(scorePreloadPose.getHeading(), moveFromScorePreloadPose.getHeading())
//                .build();
//
//        strafeToSampsPath = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(moveFromScorePreloadPose), new Point(strafeToSampsPose)))
//                .setLinearHeadingInterpolation(moveFromScorePreloadPose.getHeading(), strafeToSampsPose.getHeading())
//                .build();
//
//        moveToSampsPath = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(strafeToSampsPose), new Point(moveToSampsPose)))
//                .setLinearHeadingInterpolation(strafeToSampsPose.getHeading(), moveToSampsPose.getHeading())
//                .build();
//
//        moveToFirstSampPath = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(moveToSampsPose), new Point(moveToFirstSampPose)))
//                .setLinearHeadingInterpolation(moveToSampsPose.getHeading(), moveToFirstSampPose.getHeading())
//                .build();
//
//        pushFirstSampPath = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(moveToFirstSampPose), new Point(pushFirstSampPose)))
//                .setLinearHeadingInterpolation(moveToFirstSampPose.getHeading(), pushFirstSampPose.getHeading())
//                .build();
//
//        moveToSecondSampPath = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(pushFirstSampPose), new Point(moveToSecondSampPose)))
//                .setLinearHeadingInterpolation(pushFirstSampPose.getHeading(), moveToSecondSampPose.getHeading())
//                .build();
//
//        strafeToSecondSampPath = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(moveToSecondSampPose), new Point(strafeToSecondSampPose)))
//                .setLinearHeadingInterpolation(moveToSecondSampPose.getHeading(), strafeToSecondSampPose.getHeading())
//                .build();
//
//        pushSecondSampPath = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(strafeToSecondSampPose), new Point(pushSecondSampPose)))
//                .setLinearHeadingInterpolation(strafeToSecondSampPose.getHeading(), pushSecondSampPose.getHeading())
//                .build();
//
//        moveToThirdSampPath = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(pushSecondSampPose), new Point(moveToThirdSampPose)))
//                .setLinearHeadingInterpolation(pushSecondSampPose.getHeading(), moveToThirdSampPose.getHeading())
//                .build();
//
//        strafeToThirdSampPath = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(moveToThirdSampPose), new Point(strafeToThirdSampPose)))
//                .setConstantHeadingInterpolation(strafeToThirdSampPose.getHeading())
//                .build();
//
//        pushThirdSampPath = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(strafeToThirdSampPose), new Point(pushThirdSampPose)))
//                .setConstantHeadingInterpolation(pushThirdSampPose.getHeading())
//                .build();
//    }
//
//    @Override
//    public void loop() {
//        follower.update();
//
//        CommandScheduler.getInstance().run();
//
//        telemetry.addData("Path State", pathState);
//        telemetry.addData("X", follower.getPose().getX());
//        telemetry.addData("Y", follower.getPose().getY());
//        telemetry.addData("Heading", follower.getPose().getHeading());
//        telemetry.update();
//    }
//}
