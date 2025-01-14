package org.firstinspires.ftc.teamcode.Commandbase.Commands.Pathing;


import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.Path;

public class PathFollower extends CommandBase {
    private final Path path;
    Follower follower;

    public PathFollower(Path path) {
        this.path = path;
        addRequirements();
    }

    @Override
    public void execute() {
        if(isFinished())
            follower.followPath(this.path);
    }

    @Override
    public boolean isFinished() {
        return !follower.isBusy();
    }
}
