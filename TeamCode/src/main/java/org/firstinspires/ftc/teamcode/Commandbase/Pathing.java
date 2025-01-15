package org.firstinspires.ftc.teamcode.Commandbase;

import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;

public class Pathing {

    private static Point pointmm(double x, double y) { return new Point(x/25.4,y/25.4,Point.CARTESIAN); }
    private static Point pointin(double x, double y) { return new Point(x,y,Point.CARTESIAN); }
    private static double rad(double deg) { return deg / 180 * Math.PI; }
    public static BezierLine line(Point start, Point end) {return new BezierLine(start, end);}

    private static final Point start = pointmm(0,0);
    private static final Point int2 = pointmm(650, -400);




    public static PathChain TEST() {
        return new PathBuilder()
                .addPath(
                        line(start, int2)
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(-45))
                .build();
    }
}
