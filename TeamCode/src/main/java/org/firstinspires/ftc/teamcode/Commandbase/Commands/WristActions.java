package org.firstinspires.ftc.teamcode.Commandbase.Commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.SpecArm;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Wrist;

import java.util.List;

public class WristActions {
    public enum wristState{
        NUTERALGRAB,
        HORIZONTALGRAB,
        SPECGRAB
    }

    public Wrist wrist;



    public WristActions(OpMode opMode) {
        wrist = new Wrist(opMode);
    }

    public void action(List<Action> runningActions, FtcDashboard dashboard, boolean input)
    {
        if (input) {
            runningActions.add(
                    new SequentialAction(
                            new InstantAction(wrist::neutralGrab)
                    )
            );
        }
    }
}
