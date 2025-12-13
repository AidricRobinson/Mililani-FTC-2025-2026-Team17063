package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Subsystems.FlywheelSubsystem;

public class AutoLimelightFlywheel {
    FlywheelSubsystem flywheelSubsystem;

    PIDController pidController;
    Limelight3A limelight;
    LLResult result;
    double rpm;

    public AutoLimelightFlywheel(FlywheelSubsystem flywheelSubsystem, Limelight3A limelight) {
        this.flywheelSubsystem = flywheelSubsystem;
        pidController = new PIDController(0.0055, 0, 0.0001, 0.2);

        this.limelight = limelight;
        rpm = 0;
    }

    public void operate() {
        double a = Constants.AprilTagConstants.kFlywheelEquationA;
        double b = Constants.AprilTagConstants.kFlywheelEquationB;
        double c = Constants.AprilTagConstants.kFlywheelEquationC;
        result = limelight.getLatestResult();
        rpm = 0;
        if (result != null && result.isValid()) {
            double x = result.getTa();
            rpm = (a * Math.pow(x, 2)) + (b*x) + (c);
        }
        if (result != null && result.isValid()) {
            pidController.createSetPoint(rpm);
            pidController.setProcessVariable(flywheelSubsystem.getRPM());
            double powerOutput = pidController.getOutput();
            flywheelSubsystem.setFlywheelPower(powerOutput);
        }


    }

    public void shutdown(){
        flywheelSubsystem.shutdown();
    }
}
