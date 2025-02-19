package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Sensor;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;
import org.firstinspires.ftc.teamcode.RoadRunner.StrafeChassis;

import java.util.ArrayList;
import java.util.List;

@Autonomous
@Config
public class Testing extends LinearOpMode {
    public Claw claw;
    public Wrist wrist;
    public Arm arm;
    public Slides slides;
    public Sensor sensor;
    public static double TESTING = 5;
    public static Pose2d StartPose = new Pose2d(-17.5, 64, Math.toRadians(270));
    public static Pose2d Preload = new Pose2d(-8, 33.6, Math.toRadians(270));
    //Was 41
    public static Pose2d FirstGrab = new Pose2d(-39, 35, Math.toRadians(-130));

    public static Vector2d PRELOAD = new Vector2d(-8, 33.6);
    public static Vector2d FIRSTGRAB = new Vector2d(-39, 35);
    //52.5
    public static Vector2d HPZONE = new Vector2d(-47, 62);

    public boolean cancelAction = false;

    SpecAuto.Path path = SpecAuto.Path.START;

    @Override
    public void runOpMode() throws InterruptedException {


        StrafeChassis drive = new StrafeChassis(hardwareMap, StartPose);
        final FtcDashboard dash = FtcDashboard.getInstance();
        List<Action> runningActions = new ArrayList<>();


        arm = new Arm(this);
        claw = new Claw(this);
        slides = new Slides(this);
        wrist = new Wrist(this);
        sensor = new Sensor(this);

        while (!opModeIsActive() && !isStopRequested()) {
            arm.reset();
            claw.grab();
            slides.liftRest();
            wrist.neutralGrab();

            waitForStart();

            if (opModeIsActive()) {

                switch (path) {
                    case START:
                        slides.liftBigHigh();
                        Actions.runBlocking(
                                drive.actionBuilder(StartPose)
                                        .strafeToLinearHeading(PRELOAD, Math.toRadians(270))

                                        .endTrajectory()
                                        .build());
                        if (sensor.distanceDoesNotEqual(3)) {

                            drive.setDrivePowers(new PoseVelocity2d( new Vector2d(0,0), 0));

                        }
                        slides.liftRest();

                        if (slides.getPos() <= Slides.SCOREPOSE) {

                        claw.open();

                        }
                        path = SpecAuto.Path.GRAB1;

                    case GRAB1:
                        Actions.runBlocking(
                                drive.actionBuilder(Preload)
                                        .strafeToLinearHeading(FIRSTGRAB, Math.toRadians(-130))
                                        .build());
                        arm.grab();
                        wrist.specGrab();
                        sleep(450);
                        claw.grab();
                        sleep(250);
                        arm.specGrab();
                        Actions.runBlocking(
                                drive.actionBuilder(FirstGrab)
                                        .strafeToLinearHeading(HPZONE, Math.toRadians(-180))
                                        .build());
                        path = SpecAuto.Path.GRAB2;
                        break;
                }

                List<Action> newActions = new ArrayList<>();
                for (Action action : runningActions) {
                    TelemetryPacket packet = new TelemetryPacket();
                    action.preview(packet.fieldOverlay());
                    if (!action.run(packet)) {
                        continue;
                    }
                    newActions.add(action);
                    dash.sendTelemetryPacket(packet);
                }
                runningActions = newActions;
            }
        }
    }
}
//public Velconstratint name; name =new TransVelConstraint(num)