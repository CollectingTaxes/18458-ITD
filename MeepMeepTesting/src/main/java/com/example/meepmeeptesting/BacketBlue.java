package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class BacketBlue {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(12,12)
                .build();



        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(8, 60, 300))
              .splineToLinearHeading(new Pose2d(7, 33, Math.toRadians(-90)), Math.toRadians(50))
                .strafeToConstantHeading(new Vector2d(7,35))
                        .waitSeconds(1)
                .strafeTo(new Vector2d(48,39))
             //  .splineToLinearHeading(new Pose2d(48, 39, Math.toRadians(-90)), Math.toRadians(-130))
                .waitSeconds(1)
                .splineToLinearHeading(new Pose2d(55, 53, Math.toRadians(40)), Math.toRadians(70))
                .waitSeconds(1)
                .splineToLinearHeading(new Pose2d(60, 39, Math.toRadians(-90)), Math.toRadians(70))
                .splineToLinearHeading(new Pose2d(56, 54, Math.toRadians(40)), Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(59, 25, Math.toRadians(352)), Math.toRadians(30))
                .splineToLinearHeading(new Pose2d(56, 54, Math.toRadians(40)), Math.toRadians(0))
                .build());








// LineToX, LineToY
        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
