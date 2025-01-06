package org.firstinspires.ftc.teamcode.Commandbase.Commands.sensor;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.SampleDetector;

public class PLEASE_FOR_THE_LOVE_OF_GOD extends CommandBase {
    private Claw claw;
    private SampleDetector sampleDetector;

    public PLEASE_FOR_THE_LOVE_OF_GOD(Claw claw, SampleDetector sampleDetector) {
        this.claw = claw;
        this.sampleDetector = sampleDetector;
        addRequirements(claw, sampleDetector);
    }

    @Override
    public void execute() {
        if (!claw.clawStateGrabbed && sampleDetector.distance()) {
            claw.GRAB();
        }
        else claw.OPEN();
    }
}
