package org.firstinspires.ftc.teamcode.Commandbase.Commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.HardwareSubsystem;

import java.util.List;


public class FoldInAction {

    public HardwareSubsystem arm;


    public FoldInAction(OpMode opMode) {
        arm = new HardwareSubsystem(opMode);

    }

    private boolean wasInputPressed = false;

    public void actionTeleOp(List<Action> runningActions, FtcDashboard dashboard, boolean input, boolean advancedControl) {

        if (input && !wasInputPressed) {

            if (advancedControl) {
                runningActions.add(
                        new SequentialAction(
                                new InstantAction(arm::armReset)
                        )
                );
            }
        }
        wasInputPressed = input;
    }
}

