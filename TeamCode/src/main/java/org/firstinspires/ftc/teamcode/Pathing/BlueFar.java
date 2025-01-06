//package org.firstinspires.ftc.teamcode.Pathing;
//
//import org.firstinspires.ftc.teamcode.pedropathing.pathgen.BezierCurve;
//import org.firstinspires.ftc.teamcode.pedropathing.pathgen.BezierLine;
//import org.firstinspires.ftc.teamcode.pedropathing.pathgen.PathBuilder;
//import org.firstinspires.ftc.teamcode.pedropathing.pathgen.Point;
//
//public class BlueFar {
//
//    public BlueFar() {
//        PathBuilder builder = new PathBuilder();
//
//        builder
//                .addPath(
//                        // Line 1
//                        new BezierCurve(
//                                new Point(7.289, 54.044, Point.CARTESIAN),
//                                new Point(50.844, 40.178, Point.CARTESIAN),
//                                new Point(55.822, 23.467, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
//                .addPath(
//                        // Line 2
//                        new BezierLine(
//                                new Point(55.822, 23.467, Point.CARTESIAN),
//                                new Point(12.800, 24.000, Point.CARTESIAN)
//                        )
//                )
//                .setTangentHeadingInterpolation()
//                .addPath(
//                        // Line 3
//                        new BezierCurve(
//                                new Point(12.800, 24.000, Point.CARTESIAN),
//                                new Point(58.311, 23.467, Point.CARTESIAN),
//                                new Point(55.111, 13.867, Point.CARTESIAN)
//                        )
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(180))
//                .addPath(
//                        // Line 4
//                        new BezierLine(
//                                new Point(55.111, 13.867, Point.CARTESIAN),
//                                new Point(12.444, 14.400, Point.CARTESIAN)
//                        )
//                )
//                .setTangentHeadingInterpolation()
//                .addPath(
//                        // Line 5
//                        new BezierCurve(
//                                new Point(12.444, 14.400, Point.CARTESIAN),
//                                new Point(24.178, 75.022, Point.CARTESIAN),
//                                new Point(41.956, 73.067, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(0))
//                .addPath(
//                        // Line 6
//                        new BezierLine(
//                                new Point(41.956, 73.067, Point.CARTESIAN),
//                                new Point(5.333, 16.533, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(90))
//                .addPath(
//                        // Line 7
//                        new BezierCurve(
//                                new Point(5.333, 16.533, Point.CARTESIAN),
//                                new Point(24.000, 69.867, Point.CARTESIAN),
//                                new Point(41.778, 69.156, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(0))
//                .addPath(
//                        // Line 8
//                        new BezierLine(
//                                new Point(41.778, 69.156, Point.CARTESIAN),
//                                new Point(5.333, 16.711, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(90))
//                .addPath(
//                        // Line 9
//                        new BezierCurve(
//                                new Point(5.333, 16.711, Point.CARTESIAN),
//                                new Point(24.000, 66.133, Point.CARTESIAN),
//                                new Point(41.067, 64.533, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(0))
//                .addPath(
//                        // Line 10
//                        new BezierLine(
//                                new Point(41.067, 64.533, Point.CARTESIAN),
//                                new Point(5.333, 16.533, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(90))
//                .addPath(
//                        // Line 11
//                        new BezierCurve(
//                                new Point(5.333, 16.533, Point.CARTESIAN),
//                                new Point(23.822, 62.578, Point.CARTESIAN),
//                                new Point(40.889, 60.267, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(0))
//                .addPath(
//                        // PARK
//                        new BezierLine(
//                                new Point(41.067, 64.533, Point.CARTESIAN),
//                                new Point(5.333, 16.533, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(90));
//
//    }
//}