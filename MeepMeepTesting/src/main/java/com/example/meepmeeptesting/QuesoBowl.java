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
                .setConstraints(47.5, 47.5, Math.toRadians(180), Math.toRadians(180), 12)
                .setDimensions(12, 12)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-40, 60.5, Math.toRadians(270)))
                //grab 1
                .strafeToConstantHeading(new Vector2d(-47.8, 50))

                .waitSeconds(1)

                //hp
                .strafeToLinearHeading(new Vector2d(-15, 54), Math.toRadians(235))

                //grab 2
                .strafeToLinearHeading(new Vector2d(-36, 37), Math.toRadians(205))

                .waitSeconds(1)

                //hp
                .strafeToLinearHeading(new Vector2d(-15, 54), Math.toRadians(235))

                //grab 3
                .strafeToLinearHeading(new Vector2d(-46, 34), Math.toRadians(205))

                .waitSeconds(1)

                .strafeToLinearHeading(new Vector2d(-15, 54), Math.toRadians(235))

                .strafeToLinearHeading(new Vector2d(18, 63), Math.toRadians(270))

                .strafeToConstantHeading(new Vector2d(20, 32))

                .strafeToConstantHeading(new Vector2d(18, 63))

                .strafeToConstantHeading(new Vector2d(22, 32))

                .strafeToConstantHeading(new Vector2d(18, 63))

                .strafeToConstantHeading(new Vector2d(24, 32))

                .strafeToConstantHeading(new Vector2d(18, 63))

                .strafeToConstantHeading(new Vector2d(26, 32))

                .strafeToConstantHeading(new Vector2d(18, 63))

                .strafeToConstantHeading(new Vector2d(28, 32))

                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
