package org.firstinspires.ftc.teamcode.Commandbase.Commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Slides;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

import java.util.List;

public class SlideActions {

    enum BasketCycleState {
        SCORING,
        OUTTAKE,
        INIT
    }
    public Slides slides;
    public Claw claw;
    public Arm arm;
    public Wrist wrist;

    BasketCycleState basketCycleState = BasketCycleState.OUTTAKE;


    public SlideActions(OpMode opMode) {
        slides = new Slides(opMode);
        arm = new Arm(opMode);
        wrist = new Wrist(opMode);
        claw = new Claw(opMode);
    }
    public void action(List<Action> runningActions, FtcDashboard dashboard, boolean input) {

        if (input) {
            switch (basketCycleState) {
//                case INIT:
//                    runningActions.add(
//                            new InstantAction(slides::liftRest)
//                    );
//                    basketCycleState = BasketCycleState.OUTTAKE;
//                    break;
                case OUTTAKE :
                    runningActions.add(
                            new InstantAction(slides::liftHigh)
                    );
                    basketCycleState = BasketCycleState.SCORING;
                    break;

                case SCORING:
                    runningActions.add(
                            new SequentialAction(
                                    new InstantAction(arm::reset),
                                    new InstantAction(wrist::horizontalGrab),
                                    new InstantAction(claw::open),
                                    new SleepAction(0.15),
                                    new InstantAction(slides::liftRest)
                            )
                    );
                    basketCycleState = BasketCycleState.OUTTAKE;
                    break;
            }
        }
    }
}
