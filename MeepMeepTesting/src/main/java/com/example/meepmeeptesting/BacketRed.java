package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class BacketRed {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(500);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(12,12)
                .build();



        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-6, -64, -300))
                .strafeToConstantHeading(new Vector2d(-7,-30))
                .waitSeconds(0.5)

                .strafeToConstantHeading(new Vector2d(-25, -35))
                .splineToConstantHeading(new Vector2d(-48, -36), Math.toRadians(90))
                .waitSeconds(0.5)

                .turnTo(Math.toRadians(220))
                .strafeToConstantHeading(new Vector2d(-52, -52))
                .waitSeconds(0.5)

                .turnTo(Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(-58, -36))
                .waitSeconds(0.5)

                .turnTo(Math.toRadians(220))
                .strafeToConstantHeading(new Vector2d(-52, -52))
                .waitSeconds(0.5)

                .turnTo(Math.toRadians(145))
                .strafeToConstantHeading(new Vector2d(-61, -34))
                .waitSeconds(0.5)

                .turnTo(Math.toRadians(220))
                .strafeToConstantHeading(new Vector2d(-52, -52))
                .build());

// LineToX, LineToY
        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
