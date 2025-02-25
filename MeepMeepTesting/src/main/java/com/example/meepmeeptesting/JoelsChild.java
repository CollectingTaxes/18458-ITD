package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class JoelsChild {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(50, 50, Math.toRadians(180), Math.toRadians(180), 12)
                .setDimensions(12, 12)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-17.5, 64, Math.toRadians(270)))
                .strafeToLinearHeading(new Vector2d(-8,32), Math.toRadians(270))
                .waitSeconds(0.15)
                .strafeToLinearHeading(new Vector2d(-10,32), Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(-49, 33, Math.toRadians(90)), Math.toRadians(270))
                .waitSeconds(0.15)
                .strafeTo(new Vector2d(-49, 50))
                .waitSeconds(0.15)
                .strafeToLinearHeading(new Vector2d(-60, 34), Math.toRadians(90))
                .waitSeconds(0.15)
                .strafeTo(new Vector2d(-60, 50))
                .waitSeconds(0.15)
                .strafeToLinearHeading(new Vector2d(-61, 36), Math.toRadians(30))
                .waitSeconds(0.15)
                .splineToLinearHeading(new Pose2d(-47, 63, Math.toRadians(90)), Math.toRadians(90))

                //CYCLE1
                .waitSeconds(0.15)
                .splineToLinearHeading(new Pose2d(-8,32, Math.toRadians(90)), Math.toRadians(270))

                .waitSeconds(0.15)
                .strafeToLinearHeading(new Vector2d(-47.5, 60), Math.toRadians(90))

                //CYCLE2
                .waitSeconds(0.15)
                .splineToLinearHeading(new Pose2d(-8,32, Math.toRadians(90)), Math.toRadians(270))

                .waitSeconds(0.15)
                .strafeToLinearHeading(new Vector2d(-47.5, 60), Math.toRadians(90))

                //CYCLE3
                .waitSeconds(0.15)
                .splineToLinearHeading(new Pose2d(-8,32, Math.toRadians(90)), Math.toRadians(270))

                .waitSeconds(0.15)
                .strafeToLinearHeading(new Vector2d(-47.5, 60), Math.toRadians(90))

                //CYCLE3
                .waitSeconds(0.15)
                .splineToLinearHeading(new Pose2d(-8,32, Math.toRadians(90)), Math.toRadians(270))

                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
