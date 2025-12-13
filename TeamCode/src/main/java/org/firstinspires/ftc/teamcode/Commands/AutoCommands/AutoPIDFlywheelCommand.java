package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.AprilTagFlywheelCommand;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Subsystems.FlywheelSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.StorageSubsystem;

public class AutoPIDFlywheelCommand {
    FlywheelSubsystem flywheelSubsystem;
    StorageSubsystem storageSubsystem;
    IntakeSubsystem intakeSubsystem;
    PIDController pidController;
    ElapsedTime elapsedTime;
    LinearOpMode linearOpMode;
    Telemetry telemetry;
    AutoLimelightFlywheel autoLimelightFlywheel;
    double start;
    public AutoPIDFlywheelCommand (AutoLimelightFlywheel autolimelightFlywheel, FlywheelSubsystem flywheelSubsystem, StorageSubsystem storageSubsystem, IntakeSubsystem intakeSubsystem, ElapsedTime elapsedTime, LinearOpMode linearOpMode, Telemetry telemetry) {
        this.autoLimelightFlywheel = autolimelightFlywheel;
        this.flywheelSubsystem = flywheelSubsystem;
        this.storageSubsystem = storageSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        pidController = new PIDController(0.00525, 0.000002, 0, 0.01);
        this.elapsedTime = elapsedTime;
        this.linearOpMode = linearOpMode;
        this.telemetry = telemetry;
        start = 0;
    }
    public void operate(double rpm, double pause) {
        double openPosition = Constants.PayloadConstants.kServoOpenPosition;


        pidController.createSetPoint(rpm);

        start = elapsedTime.milliseconds();
        while(elapsedTime.milliseconds() < (pause + start) && linearOpMode.opModeIsActive()){
//            pidController.createSetPoint(rpm);
//            pidController.setProcessVariable(flywheelSubsystem.getRPM());
//            double powerOutput = pidController.getOutput();
//            flywheelSubsystem.setFlywheelPower(powerOutput);
            autoLimelightFlywheel.operate();





        }
        start = elapsedTime.milliseconds();
        while(elapsedTime.milliseconds() < 3000 + start && linearOpMode.opModeIsActive()){
//            pidController.createSetPoint(rpm);
//            pidController.setProcessVariable(flywheelSubsystem.getRPM());
//            double powerOutput = pidController.getOutput();
//            flywheelSubsystem.setFlywheelPower(powerOutput);

            autoLimelightFlywheel.operate();

            double intakeOutput = 1;
            double storageOutput = 1;

            intakeSubsystem.setIntakePower(intakeOutput);
            storageSubsystem.setMotorPower(storageOutput);
            storageSubsystem.setServoPosition(openPosition);


        }
        intakeSubsystem.setIntakePower(0);
        storageSubsystem.setMotorPower(0);
        flywheelSubsystem.setFlywheelPower(0);
        autoLimelightFlywheel.shutdown();
        storageSubsystem.setServoPosition(Constants.PayloadConstants.kServoClosedPosition);


    }

    public void shutdown(){
        flywheelSubsystem.setFlywheelPower(0);
    }
}
