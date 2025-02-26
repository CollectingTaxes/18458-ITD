package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class BlueSpec {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(40, 40, Math.toRadians(180), Math.toRadians(180), 12)
                .setDimensions(12, 12)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-17.5, 64, Math.toRadians(270)))

                        .strafeToLinearHeading(new Vector2d(-8, 33), Math.toRadians(270))
                        .strafeToLinearHeading(new Vector2d(-32, 36), Math.toRadians(90))

                //1 Sample
                .strafeToLinearHeading(new Vector2d(-42, 18), Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-50, 60, Math.toRadians(90)), Math.toRadians(90))

                //2 Sample
                .strafeToConstantHeading(new Vector2d(-50, 19))
                .splineToLinearHeading(new Pose2d(-59, 19, Math.toRadians(90)), Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(-59, 60))

                //3 Sample
                .strafeToConstantHeading(new Vector2d(-50, 19))
                .splineToLinearHeading(new Pose2d(-66, 19, Math.toRadians(90)), Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(-66, 60))

                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
