package org.firstinspires.ftc.teamcode.Commandbase.Commands.Pathing;

import com.pedropathing.commands.FollowPath;
import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.PathChain;

public class BlueClose extends FollowPath {
    public BlueClose(Follower follower, PathChain pathChain) {
        super(follower, pathChain);
    }
}
