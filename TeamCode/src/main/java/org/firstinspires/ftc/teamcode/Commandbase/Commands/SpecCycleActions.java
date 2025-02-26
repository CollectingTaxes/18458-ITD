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

public class SpecCycleActions {
    public SpecArm specArm


    public SpecCycleActions(OpMode opMode) {
        specArm = new SpecArm(opMode);

    }
    public void action(List<Action> runningActions, FtcDashboard dashboard, boolean input) {

        if (input) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(specArm::intake),
                            new InstantAction(specArm::grab)
                    )
            );
        }
    }
}
