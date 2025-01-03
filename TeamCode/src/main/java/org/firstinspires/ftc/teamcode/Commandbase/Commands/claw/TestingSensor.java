package org.firstinspires.ftc.teamcode.Commandbase.Commands.claw;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.SampleDetector;

public class TestingSensor extends SequentialCommandGroup {
    public TestingSensor(Claw claw, SampleDetector colorSensor) {
        addRequirements(claw, colorSensor);
        addCommands(
                new WaitUntilCommand(colorSensor::grabbedYellowSample),
                new ConditionalCommand(
                        new SequentialCommandGroup(
                                new InstantCommand(claw::GRAB)

                        ),
                        new InstantCommand(),
                        ()-> (colorSensor.grabbedYellowSample() && claw.clawStateOpen())
                )
        );
    }
}