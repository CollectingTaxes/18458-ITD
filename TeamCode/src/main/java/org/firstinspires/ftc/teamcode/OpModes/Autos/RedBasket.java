package org.firstinspires.ftc.teamcode.OpModes.Autos;

import android.os.Build;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

import java.util.ArrayList;
import java.util.List;

@Autonomous
public class RedBasket extends LinearOpMode {
    public Claw claw;
    public Wrist wrist;
    public Drive drivetrain;
    public Arm arm;
    public Slides slides;
    public Telemetry telemetry;
    public List<Action> runningActions = new ArrayList<>();

    private final FtcDashboard dash = FtcDashboard.getInstance();


    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d beginPose = new Pose2d(-12, 62.25, Math.toRadians(90));

        waitForStart();
        while (opModeIsActive()) {
            if (opModeIsActive()) {
                // Preload
                Actions.runBlocking(
                        drivetrain.actionBuilder(beginPose)
                                .strafeTo(new Vector2d(1.5,27.75))
                                .build()
                );
                slides.liftHigh();
                sleep(300);
                arm.grab();
                sleep(200);
                claw.open();
                sleep(200);
                slides.liftRest();

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
}
