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

                //26.5 SECONDS WITH 50 VELOCITY, 28.3 SECONDS WITH 45 VELOCITY
                .setConstraints(50, 50, Math.toRadians(180), Math.toRadians(180), 12)
                .setDimensions(12, 12)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-17.5, 64, Math.toRadians(270)))

                .strafeToLinearHeading(new Vector2d(-8,32), Math.toRadians(270))
                .waitSeconds(0.15)

                //GRAB ONE
                .strafeToLinearHeading(new Vector2d(-25, 35), Math.toRadians(45))
                .strafeToLinearHeading(new Vector2d(-49, 35), Math.toRadians(90))
                .waitSeconds(0.15)
                .strafeTo(new Vector2d(-49, 50))
                .waitSeconds(0.15)

                //GRAB TWO
                .strafeToLinearHeading(new Vector2d(-60, 34), Math.toRadians(90))
                .waitSeconds(0.15)
                .strafeTo(new Vector2d(-60, 50))
                .waitSeconds(0.15)

                //GRAB THREE
                .strafeToLinearHeading(new Vector2d(-61, 36), Math.toRadians(30))
                .waitSeconds(0.15)
                .splineToLinearHeading(new Pose2d(-47, 57, Math.toRadians(90)), Math.toRadians(90))
                .waitSeconds(0.15)

                //CYCLE1
                .strafeToLinearHeading(new Vector2d(-8,32), Math.toRadians(90))

                .waitSeconds(0.15)
                .strafeToLinearHeading(new Vector2d(-47.5, 60), Math.toRadians(90))
                .waitSeconds(0.15)

                //CYCLE2
                .strafeToLinearHeading(new Vector2d(-8,32), Math.toRadians(90))

                .waitSeconds(0.15)
                .strafeToLinearHeading(new Vector2d(-47.5, 60), Math.toRadians(90))
                .waitSeconds(0.15)

                //CYCLE3
                .strafeToLinearHeading(new Vector2d(-8,32), Math.toRadians(90))

                .waitSeconds(0.15)
                .strafeToLinearHeading(new Vector2d(-47.5, 60), Math.toRadians(90))

                //CYCLE4
                .waitSeconds(0.15)
                .strafeToLinearHeading(new Vector2d(-8,32), Math.toRadians(90))

//                .waitSeconds(0.15)
//                .strafeToLinearHeading(new Vector2d(-47.5, 60), Math.toRadians(90))

                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.5f)
                .addEntity(myBot)
                .start();
    }
}
