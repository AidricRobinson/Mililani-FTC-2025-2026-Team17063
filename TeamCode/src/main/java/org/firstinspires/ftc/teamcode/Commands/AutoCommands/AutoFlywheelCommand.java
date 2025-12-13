package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Subsystems.FlywheelSubsystem;

public class AutoFlywheelCommand {
    FlywheelSubsystem flywheelSubsystem;
    PIDController pidController;
    ElapsedTime elapsedTime;
    LinearOpMode linearOpMode;
    Telemetry telemetry;
    public AutoFlywheelCommand (FlywheelSubsystem flywheelSubsystem, ElapsedTime elapsedTime, LinearOpMode linearOpMode, Telemetry telemetry) {
        this.flywheelSubsystem = flywheelSubsystem;
        pidController = new PIDController(0.01, 0.0001, 0, 0.25);
        this.elapsedTime = elapsedTime;
        this.linearOpMode = linearOpMode;
        this.telemetry = telemetry;
    }
    public void operate(double rpm, double pause) {

        pidController.createSetPoint(rpm);


        double start = elapsedTime.milliseconds();
        while(elapsedTime.milliseconds() < pause + start && linearOpMode.opModeIsActive()){
//            pidController.setProcessVariable(flywheelSubsystem.getRPM());
//            double powerOutput = pidController.getOutput();
//            flywheelSubsystem.setFlywheelPower(powerOutput);


        }
        flywheelSubsystem.setSpeed(rpm);


    }

    public void shutdown(){
        flywheelSubsystem.setFlywheelPower(0);
    }
}
