package org.firstinspires.ftc.teamcode.RoadRunner.trajectorysequence;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.RoadRunner.container.TrajectorySequenceContainer;
import org.firstinspires.ftc.teamcode.RoadRunner.util.PoseStorage;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;

import java.util.ArrayList;
import java.util.List;

public class TrajectorySequenceContainerFollowCommand extends CommandBase implements Command {
    private final Drive drive;
    private final TrajectorySequence trajectorySequence;
    private final List<MarkerCommand> markerCommands = new ArrayList<>();

    public TrajectorySequenceContainerFollowCommand(Drive drive, TrajectorySequenceContainer trajectorySequenceContainer, MarkerCommand... markerCommands) {
        this.drive = drive;
        TrajectorySequenceBuilder trajectorySequenceBuilder = trajectorySequenceContainer.getBuilder(PoseStorage.trajectoryPose);
        for (MarkerCommand markerCommand: markerCommands) {
            if (markerCommand.getClass() == DisplacementCommand.class) {
                DisplacementCommand displacementCommand = (DisplacementCommand) markerCommand;
                trajectorySequenceBuilder
                        .addDisplacementMarker(
                                displacementCommand.displacement,
                                markerCommand::start
                        );
            }
            else if (markerCommand.getClass() == MarkerCommand.class) {
                markerCommand.start();
            }
        }

        for (MarkerCommand markerCommand : markerCommands) {
            this.markerCommands.add(markerCommand);
            m_requirements.addAll(markerCommand.getRequirements());
        }
        trajectorySequence = trajectorySequenceBuilder.build();
        PoseStorage.trajectoryPose = trajectorySequence.end();
    }

    @Override
    public void initialize() {
        drive.followTrajectorySequenceAsync(trajectorySequence);
    }

    @Override
    public void execute() {
        if (markerCommands.isEmpty()) {
            return;
        }

        for (MarkerCommand markerCommand: markerCommands) {
            switch (markerCommand.getState()) {
                case WAIT_FOR_START:
                    break;
                case INITIALIZE:
                    markerCommand.initialize();
                    break;
                case EXECUTE:
                    markerCommand.execute();
                    if (markerCommand.isFinished()) {
                        markerCommand.end(false);
                    }
                case FINISHED:
                    break;
            }
        }

        drive.update();
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted && !markerCommands.isEmpty()) {
            for (MarkerCommand markerCommand: markerCommands) {
                if (markerCommand.getState() == MarkerCommand.State.INITIALIZE) {
                    markerCommand.end(true);
                }
                if (markerCommand.getState() == MarkerCommand.State.EXECUTE) {
                    markerCommand.end(true);
                }
            }
        }
        drive.stop();

    }

    @Override
    public boolean isFinished() {
        boolean isMarkersFinished = true;
        for (MarkerCommand markerCommand: markerCommands) {
            isMarkersFinished = isMarkersFinished && markerCommand.isFinished();
        }
        return isMarkersFinished && !drive.isBusy();
    }

}