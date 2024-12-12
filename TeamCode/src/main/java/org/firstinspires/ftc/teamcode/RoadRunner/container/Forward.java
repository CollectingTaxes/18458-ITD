package org.firstinspires.ftc.teamcode.RoadRunner.container;

public class Forward extends PathSegment {
    public volatile double distance;
    public Forward(double distance) {
        this.distance = distance;
    }
}