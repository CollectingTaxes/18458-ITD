package org.firstinspires.ftc.teamcode.OpModes.Autos;


import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;

import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.pedropathing.commands.FollowPath;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import org.firstinspires.ftc.teamcode.Commandbase.Commands.SubsystemsCommands.claw.Grab;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


import org.firstinspires.ftc.teamcode.Commandbase.Pathing;
import org.firstinspires.ftc.teamcode.Commandbase.Subsystems.Claw;

/**
 * This is an example auto that showcases movement and control of two servos autonomously.
 * It is a 0+4 (Specimen + Sample) bucket auto. It scores a neutral preload and then pickups 3 samples from the ground and scores them before parking.
 * There are examples of different ways to build paths.
 * A path progression method has been created and can advance based on time, position, or other factors.
 *
 * @author Baron Henderson - 20077 The Indubitables
 * @version 2.0, 11/28/2024
 */

@Autonomous(name = "Example Auto Blue", group = "Examples")
public class ExampleAuto extends CommandOpMode {
    private Follower follower;
    private Grab grab;
    private Claw claw = new Claw(hardwareMap, telemetry);

    @Override
    public void initialize() {
        this.follower = new Follower(hardwareMap);
        this.follower.setStartingPose(new Pose(0,0,0));

        schedule(
                //TODO it might die
                new WaitUntilCommand(this::opModeIsActive),
                new FollowPath(follower, Pathing.TEST()).alongWith(
                new Grab(claw))
        );
    }
}