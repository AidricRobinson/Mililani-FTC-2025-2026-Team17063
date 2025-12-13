package org.firstinspires.ftc.teamcode.Commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Subsystems.FlywheelSubsystem;

public class FlywheelCommand {
    FlywheelSubsystem flywheelSubsystem;
    PIDController pidController;
    Gamepad gamepad;
    Telemetry telemetry;


//    boolean isButtonPressedA;
//    boolean flywheelOnA;
//    boolean isButtonPressedB;
//    boolean flywheelonB;

    public FlywheelCommand(FlywheelSubsystem flywheelSubsystem, Gamepad gamepad, Telemetry telemetry) {
        this.flywheelSubsystem = flywheelSubsystem;
        this.gamepad = gamepad;
        this.telemetry = telemetry;
        pidController = new PIDController(0.005, 0.0000015, 0, 0.05);


//        isButtonPressedA = false;
//        flywheelOnA = false;
//        isButtonPressedB = false;
//        flywheelonB = false;

    }

    public void operate() {

        if (gamepad.a) {
            int motorTarget = 1000;
            pidController.createSetPoint(motorTarget);
            pidController.setProcessVariable(flywheelSubsystem.getRPM());
            double powerOutput = pidController.getOutput();
            flywheelSubsystem.setFlywheelPower(powerOutput);
        }
        else if (gamepad.b) {
            int motorTarget = 1050;
            pidController.createSetPoint(motorTarget);
            pidController.setProcessVariable(flywheelSubsystem.getRPM());
            double powerOutput = pidController.getOutput();
            flywheelSubsystem.setFlywheelPower(powerOutput);
        }
        else if (gamepad.x) {
            int motorTarget = 1100;
            pidController.createSetPoint(motorTarget);
            pidController.setProcessVariable(flywheelSubsystem.getRPM());
            double powerOutput = pidController.getOutput();
            flywheelSubsystem.setFlywheelPower(powerOutput);
        }
        else if (gamepad.y) {
            int motorTarget = 1150;
            pidController.createSetPoint(motorTarget);
            pidController.setProcessVariable(flywheelSubsystem.getRPM());
            double powerOutput = pidController.getOutput();
            flywheelSubsystem.setFlywheelPower(powerOutput);
        }
        else {
            flywheelSubsystem.setFlywheelPower(0);
        }



    }

    public void shutdown(){
        flywheelSubsystem.shutdown();
    }
}