package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Commandbase.Commands.FiveSpecPath;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.RTPSpecArm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.SpecArm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

import java.util.ArrayList;
import java.util.List;

@Config
@Autonomous
public class SpecAutoWPreload extends LinearOpMode {
    public Claw claw;
    public Wrist wrist;
    public Arm arm;
    public Slides slides;
    public RTPSpecArm spec;
    public SpecArm specHardware;
    public FiveSpecPath drive;

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
    public static long CLAW_TIME = 100;
    public static long LINE_UP = 250;

    public static double MinAccel = -55, MaxAccel = 55, WheelVel = 55;

    public static Vector2d PRELOAD = new Vector2d(-2, 29);
    public static Vector2d GOINGUP = new Vector2d(-8, 47);
    public static Vector2d FIRSTGRAB = new Vector2d(-44.5, 38.5);
    public static Vector2d SECONDGRAB = new Vector2d(-54.5, 38.8);
    public static Vector2d THIRDGRAB = new Vector2d(-63, 38.5);
    public static Vector2d HPZONE = new Vector2d(-54, 57);
    public static Vector2d CYCLE = new Vector2d(-42, 63);
    public static Vector2d PARK = new Vector2d(-8, 35);
    public static Vector2d FIRSTSPEC = new Vector2d(-2, 29);
    public static Vector2d SECONDSPEC = new Vector2d(0, 29);
    public static Vector2d THIRDSPEC = new Vector2d(3, 29);

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

    Path path = Path.START;

    @Override
    public void runOpMode() {

        final FtcDashboard dash = FtcDashboard.getInstance();
        List<Action> runningActions = new ArrayList<>();


        arm = new Arm(this);
        claw = new Claw(this);
        slides = new Slides(this);
        wrist = new Wrist(this);
        spec = new RTPSpecArm(this);
        specHardware = new SpecArm(this);
        drive = new FiveSpecPath(this);


        while (!opModeIsActive() && !isStopRequested()) {
            arm.reset();
            claw.grab();
            slides.liftRest();
            wrist.neutralGrab();
            spec.Start();
            specHardware.nuetral();
            specHardware.grab();

            waitForStart();

            if (opModeIsActive()) {

                switch (path) {
                    case START:
                        spec.Mid();
                        specHardware.score();
                        drive.StrafeFast(PRELOAD, StartPose, MinAccel, MaxAccel, WheelVel);
                        spec.Outtake();
                        specHardware.open();
                        sleep(CLAW_TIME);
                        spec.Mid();
                        path = Path.GRAB1;
                    case GRAB1:
                        drive.StrafeFast(GOINGUP, Preload, MinAccel, MaxAccel, WheelVel);
                        spec.SemiFloor();
                        drive.StrafeFast(FIRSTGRAB, GoingUp, MinAccel, MaxAccel, WheelVel);
                        sleep(LINE_UP);
                        spec.Floor();
                        specHardware.grab();
                        sleep(GRAB_TIME);
                        spec.Intake();
                        drive.StrafeFast(HPZONE, FirstGrab, MinAccel, MaxAccel, WheelVel);
                        specHardware.open();
                        path = Path.GRAB2;
                    case GRAB2:
                        sleep(ARM_TIME);
                        spec.SemiFloor();
                        drive.StrafeFast(SECONDGRAB, HPZone, MinAccel, MaxAccel, WheelVel);
                        sleep(LINE_UP);
                        spec.Floor();
                        specHardware.grab();
                        sleep(GRAB_TIME);
                        spec.Intake();
                        drive.StrafeToConstantHeading(HPZONE, SecondGrab);
                        specHardware.open();
                        path = Path.GRAB3;
                    case GRAB3:
                        sleep(ARM_TIME);
                        spec.SemiFloor();
                        drive.StrafeToConstantHeading(THIRDGRAB, HPZone);
                        sleep(LINE_UP);
                        spec.Floor();
                        specHardware.grab();
                        sleep(GRAB_TIME);
                        spec.Intake();
                        drive.StrafeFast(HPZONE, ThirdGrab, MinAccel, MaxAccel, WheelVel);
                        specHardware.open();
                        specHardware.nuetral();
                        path = Path.CYCLE1;
                    case CYCLE1:
                        drive.StrafeFast(CYCLE, HPZone, MinAccel, MaxAccel, WheelVel);
                        specHardware.grab();
                        sleep(GRAB_TIME);
                        spec.Mid();
                        specHardware.score();
                        drive.StrafeFast(FIRSTSPEC, Cycle,MinAccel, MaxAccel, WheelVel);
                        spec.Outtake();
                        specHardware.open();
                        sleep(CLAW_TIME);
                        spec.Intake();
                        specHardware.nuetral();
                        path = Path.CYCLE2;
                    case CYCLE2:
                        drive.StrafeFast(CYCLE, FirstSpec,MinAccel, MaxAccel, WheelVel);
                        specHardware.grab();
                        sleep(GRAB_TIME);
                        spec.Mid();
                        specHardware.score();
                        drive.StrafeFast(SECONDSPEC, Cycle,MinAccel, MaxAccel, WheelVel);
                        spec.Outtake();
                        specHardware.open();
                        sleep(CLAW_TIME);
                        spec.Intake();
                        specHardware.nuetral();
                        path = Path.CYCLE3;
                    case CYCLE3:
                        drive.StrafeFast(CYCLE, SecondSpec,MinAccel, MaxAccel, WheelVel);
                        specHardware.grab();
                        sleep(GRAB_TIME);
                        spec.Mid();
                        specHardware.score();
                        drive.StrafeFast(THIRDSPEC, Cycle, MinAccel, MaxAccel, WheelVel);
                        spec.Outtake();
                        specHardware.open();
                        sleep(CLAW_TIME);
                        spec.Intake();
                        specHardware.nuetral();
                        path = Path.CYCLE4;
                    case CYCLE4:
                        drive.StrafeFast(CYCLE, ThirdSpec, MinAccel, MaxAccel, WheelVel);
                        specHardware.grab();
                        sleep(GRAB_TIME);
                        spec.Mid();
                        specHardware.score();
                        drive.StrafeFast(FIRSTSPEC, Cycle, MinAccel, MaxAccel, WheelVel);
                        spec.Outtake();
                        specHardware.open();
                        /*sleep(CLAW_TIME);
                        spec.Intake();*/
                        path = Path.END;
                    case END:
                        drive.StrafeToConstantHeading(PARK, FirstSpec);
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