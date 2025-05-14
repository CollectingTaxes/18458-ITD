package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class QuesoBowl {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 12)
                .setDimensions(13.41339, 14.33071)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-40, 60.5, Math.toRadians(270)))
                //grab 1
                .strafeToConstantHeading(new Vector2d(-47.8, 50))

                .strafeToLinearHeading(new Vector2d(11, 58), Math.toRadians(215))

                .strafeToLinearHeading(new Vector2d(-36, 37), Math.toRadians(205))

                .strafeToLinearHeading(new Vector2d(11, 58), Math.toRadians(215))

                .strafeToLinearHeading(new Vector2d(16, 63), Math.toRadians(270))

                .strafeToConstantHeading(new Vector2d(12, 32))

                .strafeToConstantHeading(new Vector2d(16, 63))

                .strafeToConstantHeading(new Vector2d(14, 32))

                .strafeToConstantHeading(new Vector2d(16, 63))

                .strafeToConstantHeading(new Vector2d(16, 32))

                .strafeToConstantHeading(new Vector2d(16, 63))

                .strafeToConstantHeading(new Vector2d(18, 32))

                .strafeToConstantHeading(new Vector2d(16, 63))

                .strafeToConstantHeading(new Vector2d(20, 32))

                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
