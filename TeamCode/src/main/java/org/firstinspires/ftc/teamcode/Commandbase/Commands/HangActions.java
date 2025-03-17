package org.firstinspires.ftc.teamcode.Commandbase.Commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Hang;

import java.util.List;

public class HangActions {
    enum HANG {
        LINEUP,
        HANG
    }
    public Hang hang;

    HANG hanging = HANG.LINEUP;

    public HangActions(OpMode opMode) {
        hang = new Hang(opMode);
    }

    private boolean wasInputPressed = false;
    public void action(List<Action> runningActions, FtcDashboard dashboard, boolean input) {

        if (input && !wasInputPressed) {

            switch (hanging) {
                case LINEUP:
                    runningActions.add(
                            new SequentialAction(
                                    new InstantAction(hang::setLineup)
                            )
                    );
                    hanging = HANG.HANG;
                    break;
                case HANG:
                    runningActions.add(
                            new SequentialAction(
                                    new InstantAction(hang::setHang)
                            )
                    );
                    hanging = HANG.LINEUP;
                    break;
            }
        }
        wasInputPressed = input;
    }
}
