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
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.SpecArm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

import java.util.List;

public class ResetActions {
    enum Reset {
        RESET,
        DONE
    }
    public SpecArm specArm;
    public Arm arm;
    public Wrist wrist;
    public Claw claw;
    public Slides slides;

    Reset reset = Reset.RESET;

    public ResetActions(OpMode opMode) {
        specArm = new SpecArm(opMode);
        arm = new Arm(opMode);
        wrist = new Wrist(opMode);
        claw = new Claw(opMode);
        slides = new Slides(opMode);
    }

    private boolean wasInputPressed = false;
    public void action(List<Action> runningActions, FtcDashboard dashboard, boolean input) {

        if (input && !wasInputPressed) {

            switch (reset) {
                case RESET:
                    runningActions.add(
                            new SequentialAction(
                                   new InstantAction(specArm::nuetral),
                                    new InstantAction(specArm::open),
                                    new InstantAction(specArm::intake),
                                    new InstantAction(arm::reset),
                                    new InstantAction(claw::open),
                                    new InstantAction(wrist::neutralGrab),
                                    new InstantAction(slides::liftRest)
                                    )
                    );
                    reset = Reset.DONE;
                    break;
                case DONE:
                    reset = Reset.RESET;
                    break;
            }
        }
        wasInputPressed = input;
    }
}
