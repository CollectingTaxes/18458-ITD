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
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 12)
                .setDimensions(13.41339, 14.33071)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-6, 63, Math.toRadians(270)))
                //grab 1
                .strafeToConstantHeading(new Vector2d(56, 58))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(48,40), Math.toRadians(270))
                .waitSeconds(1)

                //cycle
                .strafeToConstantHeading(new Vector2d(64, 53.2))
                .waitSeconds(1)

                //grab 2
                .strafeToConstantHeading(new Vector2d(58, 40))
                .waitSeconds(1)

                //cycle
                .strafeToConstantHeading(new Vector2d(64, 53.2))
                .waitSeconds(1)

                //grab 3
                .strafeToLinearHeading(new Vector2d(61, 40), Math.toRadians(295))
                .waitSeconds(1)

                //cycle
                .strafeToLinearHeading(new Vector2d(64, 53.2), Math.toRadians(270))
                .waitSeconds(1)

                //park
                .splineToLinearHeading(new Pose2d(22, 8, Math.toRadians(180)), Math.toRadians(180))

                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
