package org.firstinspires.ftc.teamcode.RoadRunner.trajectorysequence.container;

public class SetReversed extends PathSegment {
    public volatile boolean reversed;
    public SetReversed(boolean reversed) {
        this.reversed = reversed;
    }
}