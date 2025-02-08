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
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 12)
                .setDimensions(12, 12)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-17.5, 64, Math.toRadians(270)))

                //preload
                .splineToLinearHeading(new Pose2d(-8,32, Math.toRadians(270)), Math.toRadians(270))
                .waitSeconds(1)

                //first grab
                .strafeToLinearHeading(new Vector2d(-39, 37), Math.toRadians(-135))
                .strafeToLinearHeading(new Vector2d(-47, 59), Math.toRadians(-180))

                //second grab
                .strafeToLinearHeading(new Vector2d(-52, 37), Math.toRadians(-125))
                .strafeToLinearHeading(new Vector2d(-47, 62), Math.toRadians(90))

                //.strafeToLinearHeading(new Vector2d(-32, 60), Math.toRadians(90))


                .waitSeconds(0.25)

                .strafeToLinearHeading(new Vector2d(-7, 32), Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(-47, 62), Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-7, 32), Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(-47, 62), Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-7, 32), Math.toRadians(270))

                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
