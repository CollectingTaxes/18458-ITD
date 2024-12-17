//package org.firstinspires.ftc.teamcode.OpModes.Autos;
//
//
//import com.acmerobotics.dashboard.config.Config;
//import com.arcrobotics.ftclib.command.ParallelCommandGroup;
//import com.arcrobotics.ftclib.command.SequentialCommandGroup;
//import com.arcrobotics.ftclib.command.WaitCommand;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//
//import org.firstinspires.ftc.teamcode.RoadRunner.container.TrajectorySequenceContainer;
//import org.firstinspires.ftc.teamcode.RoadRunner.drive.DriveConstants;
//import org.firstinspires.ftc.teamcode.RoadRunner.drive.StrafeChassis;
//import org.firstinspires.ftc.teamcode.RoadRunner.trajectorysequence.container.LineToLinearHeading;
//import org.firstinspires.ftc.teamcode.RoadRunner.trajectorysequence.container.Pose2dContainer;
//import org.firstinspires.ftc.teamcode.RoadRunner.trajectorysequence.container.SplineToConstantHeading;
//import org.firstinspires.ftc.teamcode.RoadRunner.util.MatchOpMode;
//import org.firstinspires.ftc.teamcode.RoadRunner.util.PoseStorage;
//import org.firstinspires.ftc.teamcode.Subsystems.Arm;
//import org.firstinspires.ftc.teamcode.Subsystems.Claw;
//import org.firstinspires.ftc.teamcode.Subsystems.Drive;
//import org.firstinspires.ftc.teamcode.Subsystems.Slides;
//
//
//@Autonomous
//public class BlueClose extends MatchOpMode {
//
//    // Subsystems
//    private Drive drive;
//    private Slides slide;
//    private Arm arm;
//    private Claw claw;
//
//    @Override
//    public void robotInit() {
//
//        slide = new Slides(hardwareMap, telemetry);
//        arm = new Arm(hardwareMap, telemetry);
//
//        claw = new Claw(hardwareMap, telemetry);
//
//        drive = new Drive(new StrafeChassis(hardwareMap, telemetry, true), telemetry, hardwareMap);
//        drive.init();
//
//        while (!isStarted() & !isStopRequested()) {
//            telemetry.update();
//        }
//        this.matchStart();
//
//    }
//    @Override
//    public void matchStart() {
//
//        drive.setPoseEstimate(BlueCloseConstants.Path.start.startPose.getPose());
//        PoseStorage.trajectoryPose = BlueCloseConstants.Path.start.startPose.getPose();
//        schedule(
//                new SequentialCommandGroup(
//
//                        new ParallelCommandGroup(
//                        ),
//
//                        new WaitCommand(1000),
//
//                        new ParallelCommandGroup(
//
//                        ),
//
//                        new WaitCommand(1000),
//
//                        new ParallelCommandGroup(
//
//                        ),
//
//                        new WaitCommand(1000),
//
//                        new ParallelCommandGroup(
//
//                        ),
//
//
//
//                        run(() -> PoseStorage.currentPose = drive.getPoseEstimate()),
//
//                        /* Save Pose and end opmode*/
//
//                        run(this::stop)
//                )
//        );
//    }
//
//    @Config
//    public static class BlueCloseConstants {
//
//        public static Speed speed;
//
//        public static class Speed {
//            public static double baseVel = DriveConstants.MAX_VEL; // value
//            public static double baseAccel = DriveConstants.MAX_ACCEL; // value
//            public static double turnVel = DriveConstants.MAX_ANG_VEL; // value
//            public static double turnAccel = DriveConstants.MAX_ANG_ACCEL; // value
//
//            static TrajectorySequenceConstraints getDropConstraints() {
//                return new TrajectorySequenceConstraints(
//                        (s, a, b, c) -> {
//                            if (s > 18) {
//                                return baseVel * 0.4;
//                            } else {
//                                return baseVel;
//                            }
//
//                        },
//                        (s, a, b, c) -> baseAccel,
//                        turnVel,
//                        turnAccel
//                );
//            }
//
//            static TrajectorySequenceConstraints getBaseConstraints() {
//                return new TrajectorySequenceConstraints(baseVel, baseAccel, turnVel, turnAccel);
//            }
//        }
//
//            public static Path path;
//
//            public static class Path {
//                public static Start start;
//
//                public static class Start {
//                    public static Pose2dContainer startPose = new Pose2dContainer(17, 63, 90);
//
//                    static TrajectorySequenceContainer start = new TrajectorySequenceContainer(Speed::getBaseConstraints);
//
//                public static double heading = 180;
//                public static double endHeading = 0;
//
//
//                public static class closeCycleStart {
//                    public static SplineToConstantHeading a = new SplineToConstantHeading(17, 61, 180);
//                    public static LineToLinearHeading b = new LineToLinearHeading(-24, 61, 180);
//                    public static SplineToConstantHeading c = new SplineToConstantHeading(-54, 35, 180);
//                    static TrajectorySequenceContainer startCycle = new TrajectorySequenceContainer(BlueCloseConstants.Speed::getBaseConstraints, a, b, c);
//                }
//
//                public static class closeCycleEnd {
//                    public static SplineToConstantHeading b = new SplineToConstantHeading(-24, 61, 0);
//                    public static LineToLinearHeading c = new LineToLinearHeading(16, 61, 180);
//                    public static SplineToConstantHeading d = new SplineToConstantHeading(49, 36, 0);
//
//                    static TrajectorySequenceContainer endCycle = new TrajectorySequenceContainer(Speed::getBaseConstraints, a, b, c, d);
//                }
//                public static class farCycleStart {
//                    public static SplineToConstantHeading a = new SplineToConstantHeading(17, 11, 180);
//                    public static LineToLinearHeading b = new LineToLinearHeading(-24, 11, 180);
//                    public static SplineToConstantHeading c = new SplineToConstantHeading(-58, 11, 180);
//                    static TrajectorySequenceContainer startCycle = new TrajectorySequenceContainer(Speed::getBaseConstraints, a, b, c);
//                }
//
//                public static class farCycleEnd {
//                    public static Back a = new Back(2);
//                    public static SplineToConstantHeading b = new SplineToConstantHeading(-24, 11, 0);
//                    public static LineToLinearHeading c = new LineToLinearHeading(16, 11, 180);
//                    public static SplineToConstantHeading d = new SplineToConstantHeading(49, 36, 0);
//
//                    static TrajectorySequenceContainer endCycle = new TrajectorySequenceContainer(Speed::getBaseConstraints, a, b, c, d);
//                }
//            }
//
//    }
//}