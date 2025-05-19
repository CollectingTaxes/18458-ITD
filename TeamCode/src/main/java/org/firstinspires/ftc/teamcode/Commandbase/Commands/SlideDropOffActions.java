package org.firstinspires.ftc.teamcode.Commandbase.Commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.HardwareSubsystem;

import java.util.List;


public class SlideDropOffActions {

    public HardwareSubsystem extendo;


    public SlideDropOffActions(OpMode opMode) {
        extendo = new HardwareSubsystem(opMode);
    }

    private boolean wasInputPressed = false;

    public void actionTeleOp(List<Action> runningActions, FtcDashboard dashboard, boolean input, boolean advancedControl) {

        if (input && !wasInputPressed) {

            if (advancedControl) {
                runningActions.add(
                        new SequentialAction(
                                new InstantAction(extendo::armReset),
                                new InstantAction(extendo::Zouttake),
                                new SleepAction(0.75),
                                new InstantAction(extendo::SampOpen)
                        )
                );
            }
        }
        wasInputPressed = input;
    }
}

