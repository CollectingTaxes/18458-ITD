package org.firstinspires.ftc.teamcode.RoadRunner.trajectorysequence.container;

public class Forward extends PathSegment {
    public volatile double distance;
    public Forward(double distance) {
        this.distance = distance;
    }
}