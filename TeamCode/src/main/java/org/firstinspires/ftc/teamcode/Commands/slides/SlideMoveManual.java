package org.firstinspires.ftc.teamcode.Commands.slides;


import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Slides;

import java.util.function.Supplier;

public class SlideMoveManual extends CommandBase {
    private final Slides slides;
    private final Supplier<Double> doubleSupplier;
    public SlideMoveManual(Slides slides, Supplier<Double> doubleSupplier) {
        this.slides = slides;
        this.doubleSupplier = doubleSupplier;
        addRequirements(slides);
    }
    @Override
    public void execute() {
        double position = doubleSupplier.get();
        if (Math.abs(position) > 0.1) {
            slides.moveManual(slides.getPos() + position * 25);
        }
    }
}
