package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.FlywheelSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.StorageSubsystem;

public class AutoPayloadCommand {
    IntakeSubsystem intakeSubsystem;
    StorageSubsystem storageSubsystem;
    FlywheelSubsystem flywheelSubsystem;
    ElapsedTime elapsedTime;
    LinearOpMode linearOpMode;
    int openPosition = Constants.PayloadConstants.kServoOpenPosition;
    int closedPosition = Constants.PayloadConstants.kServoOpenPosition;

    public AutoPayloadCommand (IntakeSubsystem intakeSubsystem, StorageSubsystem storageSubsystem, FlywheelSubsystem flywheelSubsystem, ElapsedTime elapsedTime, LinearOpMode linearOpMode) {
        this.intakeSubsystem = intakeSubsystem;
        this.storageSubsystem = storageSubsystem;
        this.flywheelSubsystem = flywheelSubsystem;
        this.elapsedTime = elapsedTime;
        this.linearOpMode = linearOpMode;
    }

    public void operate(){
        double start = elapsedTime.milliseconds();
        while (elapsedTime.milliseconds() < 1000 && linearOpMode.opModeIsActive()) {
            double intakeOutput = -1;
            double storageOutput = -1;
            intakeSubsystem.setIntakePower(intakeOutput);
            storageSubsystem.setMotorPower(storageOutput);
        }
        intakeSubsystem.setIntakePower(0);
        storageSubsystem.setMotorPower(0);
//        intakeSubsystem.setIntake(counts);


        while(elapsedTime.milliseconds() < 4000 + start && linearOpMode.opModeIsActive()){ //apple apple apple apple apple
            double intakeOutput = 0.8;
            double storageOutput = 1;

            intakeSubsystem.setIntakePower(intakeOutput);
            storageSubsystem.setMotorPower(storageOutput);
            storageSubsystem.setServoPosition(openPosition);
        }
        storageSubsystem.setServoPosition(closedPosition);
        intakeSubsystem.setIntakePower(0);
        storageSubsystem.setMotorPower(0);
        flywheelSubsystem.setFlywheelPower(0);
    }
    public void shutdown(){
        intakeSubsystem.setIntakePower(0);
        storageSubsystem.setMotorPower(0);
        flywheelSubsystem.setFlywheelPower(0);
    }


}
