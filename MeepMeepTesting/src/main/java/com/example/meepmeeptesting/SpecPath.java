package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class SpecPath {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(47.5, 47.5, Math.toRadians(180), Math.toRadians(180), 12)
                .setDimensions(12, 12)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-17.5, 64, Math.toRadians(270)))
                //preload
                .strafeToLinearHeading(new Vector2d(-8,32), Math.toRadians(270))
                .waitSeconds(1)

                //first grab
                .strafeToConstantHeading(new Vector2d(-47, 52))
                .waitSeconds(1)

                //second grab
                .strafeToConstantHeading(new Vector2d(-59, 52))
                .waitSeconds(1)

                //third grab
                .strafeToLinearHeading(new Vector2d(-62, 52), Math.toRadians(245))


                .waitSeconds(0.25)

                        .strafeToLinearHeading(new Vector2d(-54,57), Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(-7, 32), Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(-47, 59), Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(-7, 32), Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(-47, 59), Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(-7, 32), Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(-47, 59), Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(-7, 32), Math.toRadians(270))

                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
