package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.RoadRunner.container.LineToLinearHeading;
import org.firstinspires.ftc.teamcode.RoadRunner.container.PathSegment;
import org.firstinspires.ftc.teamcode.RoadRunner.container.TrajectorySequenceContainer;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.StrafeChassis;
import org.firstinspires.ftc.teamcode.RoadRunner.trajectorysequence.container.TrajectorySequenceConstraints;
import org.firstinspires.ftc.teamcode.RoadRunner.util.MatchOpMode;
import org.firstinspires.ftc.teamcode.RoadRunner.container.Pose2dContainer;
import org.firstinspires.ftc.teamcode.RoadRunner.util.PoseStorage;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.Slides;

import java.util.function.Supplier;

@Autonomous
public class BlueFar extends MatchOpMode {

    // Subsystems
    public Arm arm;
    public Claw claw;
    public Drive drive;
    public Slides slides;

    @Override
    public void robotInit() {

        slides = new Slides(hardwareMap, telemetry);
        claw = new Claw(hardwareMap, telemetry);
        arm = new Arm(hardwareMap, telemetry);
        drive = new Drive(new StrafeChassis(hardwareMap, telemetry, true), telemetry, hardwareMap);
        drive.init();

        while (!isStarted() & !isStopRequested()) {
            telemetry.update();
        }
        this.matchStart();

    }
    @Override
    public void matchStart() {
        double finalY = 0;
        double finalX = 0;

        drive.setPoseEstimate(BlueFarConstants.Path.Start.startPose.getPose());
        PoseStorage.trajectoryPose = BlueFarConstants.Path.Start.startPose.getPose();
        schedule(
                new SequentialCommandGroup(
                        new ParallelCommandGroup(
                               new TrajectorySequenceContainer(drive, BlueFarConstants.Path.Start.startPose)
                        ),

                        /* EXAMPLE CODE, THIS IS ONE AUTO PATH THING */
//                        new ParallelCommandGroup(
//                                new TrajectorySequenceContainerFollowCommand(drivetrain, BlueCloseConstants.Path.PurplePixel.getPurple(finalX))
//                        ),
//
//                        new WaitCommand(1),
//
//                        new ParallelCommandGroup(
//                                new TrajectorySequenceContainerFollowCommand(drivetrain, BlueCloseConstants.Path.getYellow(finalY))
//                        ),
//
//                        new WaitCommand(1000),
//
//                        new ParallelCommandGroup(
//                                new AutoStack(intake),
//                                new TrajectorySequenceContainerFollowCommand(drivetrain, BlueCloseConstants.Path.closeCycleStart.startCycle)
//                        ),
//
//                        new WaitCommand(1000),
//
//                        new ParallelCommandGroup(
//                                new IntakeOff(intake, wheel),
//                                new TrajectorySequenceContainerFollowCommand(drivetrain, BlueCloseConstants.Path.closeCycleEnd.endCycle)
//                        ),
//
//                        new WaitCommand(1000),
//
//                        new ParallelCommandGroup(
//                                new TrajectorySequenceContainerFollowCommand(drivetrain, BlueCloseConstants.Path.closeCycleStart.startCycle),
//                                new AutoStack(intake)
//                        ),
//
//                        new WaitCommand(1000),
//
//                        new ParallelCommandGroup(
//                                new IntakeOff(intake, wheel),
//                                new TrajectorySequenceContainerFollowCommand(drivetrain, BlueCloseConstants.Path.closeCycleEnd.endCycle)
//                        ),



                        run(() -> PoseStorage.currentPose = drive.getPoseEstimate()),

                        /* Save Pose and end opmode*/

                        run(this::stop)
                )
        );
    }

    @Config
    public static class BlueFarConstants {

        public static Speed speed;

        public static class Speed {
            public static double baseVel = DriveConstants.MAX_VEL; // value
            public static double baseAccel = DriveConstants.MAX_ACCEL; // value
            public static double turnVel = DriveConstants.MAX_ANG_VEL; // value
            public static double turnAccel = DriveConstants.MAX_ANG_ACCEL; // value

            static TrajectorySequenceConstraints getDropConstraints() {
                return new TrajectorySequenceConstraints(
                        (s, a, b, c) -> {
                            if (s > 18) {
                                return baseVel * 0.4;
                            } else {
                                return baseVel;
                            }

                        },
                        (s, a, b, c) -> baseAccel,
                        turnVel,
                        turnAccel
                );
            }

            static TrajectorySequenceConstraints getBaseConstraints() {
                return new TrajectorySequenceConstraints(baseVel, baseAccel, turnVel, turnAccel);
            }
        }

            public static class Path {

                public static class Start {
                public static Pose2dContainer startPose = new Pose2dContainer(-32,61,270);
            }
            public static class OuttakePreLoad {
                    int number = 1;
                public static LineToLinearHeading outtakePreLoad = new LineToLinearHeading(1,33, -90);
                static TrajectorySequenceContainer pathOne = new TrajectorySequenceContainer(BlueFarConstants.Speed::getBaseConstraints, outtakePreLoad);
            }
            public static class Park {
                public static LineToLinearHeading parking = new LineToLinearHeading(-60, 56,-90);
            }
        }
        /*
                This is where the trajectories go

                Here is an example:

                    public static Back a = new Back(2);
                    public static SplineToConstantHeading b = new SplineToConstantHeading(-24, 11, 0);
                    public static LineToLinearHeading c = new LineToLinearHeading(16, 11, 180);
                    public static SplineToConstantHeading d = new SplineToConstantHeading(49, 36, 0);
                    static TrajectorySequenceContainer endCycle = new TrajectorySequenceContainer(BlueCloseConstants.Speed::getBaseConstraints, a, b, c, d);

                 */
    }
}
