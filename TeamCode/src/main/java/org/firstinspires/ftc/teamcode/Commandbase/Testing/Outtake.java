package org.firstinspires.ftc.teamcode.Commandbase.Testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

import java.util.List;

public class Outtake {
    public Slides slides;
    public Claw claw;
    public Arm arm;
    public Wrist wrist;


    public Outtake(OpMode opMode) {
        slides = new Slides(opMode);
        arm = new Arm(opMode);
        wrist = new Wrist(opMode);
        claw = new Claw(opMode);
    }
    public void teleOp(List<Action> runningActions, FtcDashboard dashboard, Gamepad gamepad) {

        if (gamepad.dpad_up) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(arm::reset),
                            new InstantAction(slides::liftRest),
                            new InstantAction(wrist::neutralGrab),
                            new SleepAction(0.15),
                            new InstantAction(claw::open)
                    )
            );
        }
    }
}
