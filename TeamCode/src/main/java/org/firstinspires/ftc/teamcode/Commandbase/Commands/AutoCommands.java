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

public class AutoCommands {
    public Claw claw;
    public Wrist wrist;
    public Arm arm;
    public Slides slides;
    public SpecArm spec;

    public AutoCommands(OpMode opMode) {
        spec = new SpecArm(opMode);
        arm = new Arm(opMode);
        wrist = new Wrist(opMode);
        slides = new Slides(opMode);
        claw = new Claw(opMode);

        /*spec.init();
        spec.update();*/
    }

    public void specOuttake(List<Action> runningActions, FtcDashboard dashboard) {
        runningActions.add(
                new SequentialAction(
                        new InstantAction(spec::outtake)
                )
        );
    }

}
