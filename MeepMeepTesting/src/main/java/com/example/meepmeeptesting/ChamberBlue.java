package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class ChamberBlue {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 12)
                .setDimensions(12, 12)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-17.5, 64, Math.toRadians(270)))
                .splineToLinearHeading(new Pose2d(-8,32, Math.toRadians(270)), Math.toRadians(270))
                .waitSeconds(1)

                        .turn(Math.toRadians(180))
                .strafeToLinearHeading(new Vector2d(-15, 35), Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-50, 15, Math.toRadians(90)), Math.toRadians(90))
                .lineToYConstantHeading(55)
                .waitSeconds(0.25)

                .strafeToConstantHeading(new Vector2d(-50, 19))
                .splineToLinearHeading(new Pose2d(-59, 19, Math.toRadians(90)), Math.toRadians(90))
                .lineToYConstantHeading(55)
                        .waitSeconds(0.5)

                        .strafeToConstantHeading(new Vector2d(-59, 50))
                .splineToLinearHeading(new Pose2d(-32, 60, Math.toRadians(90)), Math.toRadians(0))


                        .splineToLinearHeading(new Pose2d(-7, 30, Math.toRadians(270)), Math.toRadians(270))

                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
