package org.firstinspires.ftc.teamcode.OpModes.Autos;

import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierLine;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathBuilder;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;

public class GeneratedPath {

    public GeneratedPath() {
        PathBuilder builder = new PathBuilder();

        builder
                .addPath(
                        // Line 1
                        new BezierLine(
                                new Point(9.757, 84.983, Point.CARTESIAN),
                                new Point(44.463, 81.930, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .addPath(
                        // Line 2
                        new BezierCurve(
                                new Point(44.463, 81.930, Point.CARTESIAN),
                                new Point(2.134, 95.802, Point.CARTESIAN),
                                new Point(58.335, 23.595, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .addPath(
                        // Line 3
                        new BezierLine(
                                new Point(58.335, 23.595, Point.CARTESIAN),
                                new Point(12.805, 23.417, Point.CARTESIAN)
                        )
                )
                .setTangentHeadingInterpolation()
                .addPath(
                        // Line 4
                        new BezierCurve(
                                new Point(12.805, 23.417, Point.CARTESIAN),
                                new Point(39.661, 41.024, Point.CARTESIAN),
                                new Point(56.912, 12.390, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .addPath(
                        // Line 5
                        new BezierLine(
                                new Point(56.912, 12.390, Point.CARTESIAN),
                                new Point(12.094, 12.924, Point.CARTESIAN)
                        )
                )
                .setTangentHeadingInterpolation()
                .addPath(
                        // Line 6
                        new BezierCurve(
                                new Point(12.094, 12.924, Point.CARTESIAN),
                                new Point(30.235, 32.310, Point.CARTESIAN),
                                new Point(56.912, 6.165, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .addPath(
                        // Line 7
                        new BezierLine(
                                new Point(56.912, 6.165, Point.CARTESIAN),
                                new Point(12.627, 6.521, Point.CARTESIAN)
                        )
                )
                .setTangentHeadingInterpolation()
                .addPath(
                        // Line 8
                        new BezierLine(
                                new Point(12.627, 6.521, Point.CARTESIAN),
                                new Point(40.194, 76.594, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(0))
                .addPath(
                        // Line 9
                        new BezierLine(
                                new Point(40.194, 76.594, Point.CARTESIAN),
                                new Point(8.181, 24.128, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(180))
                .addPath(
                        // Line 10
                        new BezierLine(
                                new Point(8.181, 24.128, Point.CARTESIAN),
                                new Point(39.127, 69.480, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(0))
                .addPath(
                        // Line 11
                        new BezierLine(
                                new Point(39.127, 69.480, Point.CARTESIAN),
                                new Point(8.359, 23.951, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(180))
                .addPath(
                        // Line 12
                        new BezierLine(
                                new Point(8.359, 23.951, Point.CARTESIAN),
                                new Point(38.772, 62.366, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(0));
    }
}


