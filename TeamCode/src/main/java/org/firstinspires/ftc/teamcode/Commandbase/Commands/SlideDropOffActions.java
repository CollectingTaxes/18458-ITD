package org.firstinspires.ftc.teamcode.Commandbase.Commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

import java.util.List;


public class SlideDropOffActions {

    public Arm arm;
    public Claw claw;
    public Wrist wrist;
    public Slides slide;


    public SlideDropOffActions(OpMode opMode) {

        arm = new Arm(opMode);
        claw = new Claw(opMode);
        wrist = new Wrist(opMode);
        slide = new Slides(opMode);

    }

    private boolean wasInputPressed = false;

    public void actionTeleOp(List<Action> runningActions, FtcDashboard dashboard, boolean input, boolean advancedControl) {

        if (input && !wasInputPressed) {

            if (advancedControl) {
                runningActions.add(
                        new SequentialAction(
                                new InstantAction(arm::specGrab),
                                new InstantAction(slide::liftHigh),
                                new SleepAction(0.25),
                                new InstantAction(claw::open),
                                new SleepAction(0.25),
                                new InstantAction(slide::liftRest)
                        )
                );
            }
        }
        wasInputPressed = input;
    }
}

