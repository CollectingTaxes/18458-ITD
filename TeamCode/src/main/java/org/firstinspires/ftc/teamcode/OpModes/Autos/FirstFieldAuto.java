package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Commandbase.Commands.AutoCommands;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.FiveSpecPath;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.RTPSpecArm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Sensor;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.SpecArm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;
import org.firstinspires.ftc.teamcode.RoadRunner.StrafeChassis;

import java.util.ArrayList;
import java.util.List;

@Config
@Autonomous
public class FirstFieldAuto extends LinearOpMode {
    public Claw claw;
    public Wrist wrist;
    public Arm arm;
    public Slides slides;
    public RTPSpecArm spec;
    public SpecArm specHardware;
    public FiveSpecPath drive;
    public AutoCommands commands;

    enum Path {
        START,
        GRAB1,
        GRAB2,
        GRAB3,
        CYCLE1,
        CYCLE2,
        CYCLE3,
        CYCLE4,
        END
    }

    public static long GRAB_TIME = 450;
    public static long ARM_TIME = 250;
    public static long CLAW_TIME = 200;
    public static long LINE_UP = 250;

    public static double MinAccel = -40, MaxAccel = 40, WheelVel = 40;

    public static Vector2d PRELOAD = new Vector2d(-2, 29);
    public static Vector2d GOINGUP = new Vector2d(-8, 47);
    public static Vector2d FIRSTGRAB = new Vector2d(-43, 38.6);
    public static Vector2d SECONDGRAB = new Vector2d(-52.4, 38.8);
    public static Vector2d THIRDGRAB = new Vector2d(-62.5, 38.5);
    public static Vector2d HPZONE = new Vector2d(-54, 57);
    public static Vector2d CYCLE = new Vector2d(-32, 63);
    public static Vector2d PARK = new Vector2d(-8, 48);
    public static Vector2d FIRSTSPEC = new Vector2d(-3, 29);
    public static Vector2d SECONDSPEC = new Vector2d(0, 29);
    public static Vector2d THIRDSPEC = new Vector2d(3, 29);
    public static Vector2d FOURTHSPEC = new Vector2d(6, 29);

    public static Pose2d StartPose = new Pose2d(-6, 63.5, Math.toRadians(90));
    public static Pose2d GoingUp = new Pose2d(GOINGUP, Math.toRadians(90));
    public static Pose2d Preload = new Pose2d(PRELOAD, Math.toRadians(90));
    public static Pose2d FirstGrab = new Pose2d(FIRSTGRAB, Math.toRadians(90));
    public static Pose2d ThirdGrab = new Pose2d(THIRDGRAB, Math.toRadians(90));
    public static Pose2d SecondGrab = new Pose2d(SECONDGRAB, Math.toRadians(90));
    public static Pose2d HPZone = new Pose2d(HPZONE, Math.toRadians(90));
    public static Pose2d Cycle = new Pose2d(CYCLE, Math.toRadians(90));
    public static Pose2d FirstSpec = new Pose2d(FIRSTSPEC, Math.toRadians(90));
    public static Pose2d SecondSpec = new Pose2d(SECONDSPEC, Math.toRadians(90));
    public static Pose2d ThirdSpec = new Pose2d(THIRDSPEC, Math.toRadians(90));
    public static Pose2d FourthSpec = new Pose2d(FOURTHSPEC, Math.toRadians(90));

    Path path = Path.START;

    @Override
    public void runOpMode() throws InterruptedException {

        final FtcDashboard dash = FtcDashboard.getInstance();
        List<Action> runningActions = new ArrayList<>();


        arm = new Arm(this);
        claw = new Claw(this);
        slides = new Slides(this);
        wrist = new Wrist(this);
        spec = new RTPSpecArm(this);
        specHardware = new SpecArm(this);
        drive = new FiveSpecPath(this);
        commands = new AutoCommands(this);


        while (!opModeIsActive() && !isStopRequested()) {
            arm.reset();
            claw.open();
            slides.liftRest();
            wrist.specGrab();
            spec.Intake();
            specHardware.score();
            specHardware.open();

            waitForStart();

            if (opModeIsActive()) {

                switch (path) {
                    case START:
                        spec.SemiFloor();
                        drive.StrafeToConstantHeading(FIRSTGRAB, StartPose);
                        sleep(LINE_UP);
                        path = Path.GRAB1;
                    case GRAB1:
                        spec.Floor();
                        specHardware.grab();
                        sleep(GRAB_TIME);
                        spec.Intake();
                        drive.StrafeToConstantHeading(HPZONE, FirstGrab);
                        specHardware.open();
                        path = Path.GRAB2;
                    case GRAB2:
                        sleep(ARM_TIME);
                        spec.SemiFloor();
                        drive.StrafeToConstantHeading(SECONDGRAB, HPZone);
                        sleep(LINE_UP);
                        spec.Floor();
                        specHardware.grab();
                        sleep(GRAB_TIME);
                        spec.Intake();
                        drive.StrafeToConstantHeading(HPZONE, SecondGrab);
                        specHardware.open();
                        path = Path.CYCLE1;
                    /*case GRAB3:
                        sleep(ARM_TIME);
                        spec.SemiFloor();
                        drive.StrafeToConstantHeading(THIRDGRAB, HPZone);
                        sleep(LINE_UP);
                        spec.Floor();
                        specHardware.grab();
                        sleep(GRAB_TIME);
                        spec.Intake();
                        sleep(ARM_TIME);
                        specHardware.open();
                        drive.StrafeToConstantHeading(HPZONE, ThirdGrab);
                        path = Path.CYCLE1;*/
                    case CYCLE1:
                        specHardware.nuetral();
                        drive.StrafeFast(CYCLE, HPZone, MinAccel, MaxAccel, WheelVel);
                        specHardware.grab();
                        sleep(GRAB_TIME);
                        spec.Mid();
                        specHardware.score();
                        drive.StrafeFast(FIRSTSPEC, Cycle,MinAccel, MaxAccel, WheelVel);
                        spec.Outtake();
                        specHardware.open();
                        sleep(CLAW_TIME);
                        path = Path.CYCLE2;
                    case CYCLE2:
                        specHardware.nuetral();
                        drive.StrafeFastWArm(CYCLE, FirstSpec,MinAccel, MaxAccel, WheelVel, new InstantAction(spec::Intake));
                        specHardware.grab();
                        sleep(GRAB_TIME);
                        spec.Mid();
                        specHardware.score();
                        drive.StrafeFast(SECONDSPEC, Cycle,MinAccel, MaxAccel, WheelVel);
                        spec.Outtake();
                        specHardware.open();
                        sleep(CLAW_TIME);
                        path = Path.CYCLE3;
                    case CYCLE3:
                        specHardware.nuetral();
                        drive.StrafeFastWArm(CYCLE, SecondSpec,MinAccel, MaxAccel, WheelVel, new InstantAction(spec::Intake));
                        specHardware.grab();
                        sleep(GRAB_TIME);
                        spec.Mid();
                        specHardware.score();
                        drive.StrafeFast(THIRDSPEC, Cycle, MinAccel, MaxAccel, WheelVel);
                        spec.Outtake();
                        specHardware.open();
                        sleep(CLAW_TIME);
                        path = Path.CYCLE4;
                    case CYCLE4:
                        specHardware.nuetral();
                        drive.StrafeFastWArm(CYCLE, ThirdSpec, MinAccel, MaxAccel, WheelVel, new InstantAction(spec::Intake));
                        specHardware.grab();
                        sleep(GRAB_TIME);
                        spec.Mid();
                        specHardware.score();
                        drive.StrafeFast(FOURTHSPEC, Cycle, MinAccel, MaxAccel, WheelVel);
                        spec.Outtake();
                        specHardware.open();
                        sleep(CLAW_TIME);
                        path = Path.END;
                    case END:
                        specHardware.nuetral();
                        drive.StrafeToConstantHeading(PARK, FourthSpec);
                        wrist.neutralGrab();
                        arm.specGrab();
                        spec.Intake();
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