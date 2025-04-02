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
                .setConstraints(40, 40, Math.toRadians(180), Math.toRadians(180), 12)
                .setDimensions(12, 12)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-6, 63, Math.toRadians(270)))

                .strafeToLinearHeading(new Vector2d(-2,29), Math.toRadians(270))
                    .waitSeconds(0.25)

                //GRAB ONE
                //USE PARALLEL COMMAND GROUP FOR THE DRIVETRAIN MOVEMENT AND THE OUTTAKING

                .strafeToLinearHeading(new Vector2d(-48, 50), Math.toRadians(270))
                        .waitSeconds(0.15)

                //GRAB TWO
                .strafeToLinearHeading(new Vector2d(-56, 50), Math.toRadians(270))
                     .waitSeconds(0.15)

                //GRAB THREE
                .strafeToLinearHeading(new Vector2d(-62, 50), Math.toRadians(255))
                     .waitSeconds(0.15)

                //CYCLE ONE
                .strafeToLinearHeading(new Vector2d(-32, 60), Math.toRadians(270))
                     .waitSeconds(0.15)
                .strafeToLinearHeading(new Vector2d(-9, 32), Math.toRadians(270))

                //CYCLE TWO
                .strafeToLinearHeading(new Vector2d(-32, 60), Math.toRadians(270))
                        .waitSeconds(0.15)
                .strafeToLinearHeading(new Vector2d(-9, 32), Math.toRadians(270))

                //CYCLE THREE
                .strafeToLinearHeading(new Vector2d(-32, 60), Math.toRadians(270))
                        .waitSeconds(0.15)
                .strafeToLinearHeading(new Vector2d(-9, 32), Math.toRadians(270))

                //CYCLE FOUR
                .strafeToLinearHeading(new Vector2d(-32, 60), Math.toRadians(270))
                        .waitSeconds(0.15)
                .strafeToLinearHeading(new Vector2d(-9, 32), Math.toRadians(270))

/*
           //CYCLE FIVE???
                        .waitSeconds(0.15)
                .strafeToLinearHeading(new Vector2d(-8, 32), Math.toRadians(270))
                        .waitSeconds(0.5)

                .strafeToLinearHeading(new Vector2d(-32, 60), Math.toRadians(270))
                        .waitSeconds(0.15)
                .strafeToLinearHeading(new Vector2d(-8, 40), Math.toRadians(270))

                //CYCLE SIX???????
                // 1+6 = 140 PTs, 28.54 seconds with 50, 29.26s with 47.5, 30.08 with 45, but this speed depends on how fast the slides extend out

                // ADDS ABOUT 10 seconds
                .waitSeconds(0.15)
                .strafeToLinearHeading(new Vector2d(-8, 32), Math.toRadians(270))
                .waitSeconds(0.5)

                .strafeToLinearHeading(new Vector2d(-32, 60), Math.toRadians(270))
                .waitSeconds(0.15)
                .strafeToLinearHeading(new Vector2d(-8, 40), Math.toRadians(270))
 */
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.5f)
                .addEntity(myBot)
                .start();
    }
}
