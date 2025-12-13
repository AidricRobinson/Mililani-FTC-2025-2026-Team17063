package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.StorageSubsystem;

public class AutoStorageCommand {
    StorageSubsystem storageSubsystem;
    ElapsedTime elapsedTime;
    LinearOpMode linearOpMode;


    public AutoStorageCommand (StorageSubsystem storageSubsystem, ElapsedTime elapsedTime, LinearOpMode linearOpMode) {
        this.storageSubsystem = storageSubsystem;
        this.elapsedTime = elapsedTime;
        this.linearOpMode = linearOpMode;

    }
    public void operate(){
        double startTime = elapsedTime.milliseconds();
        while(elapsedTime.milliseconds() < 1000 + startTime && linearOpMode.opModeIsActive()){
            double powerOutput = 0.5;
            storageSubsystem.setMotorPower(powerOutput);
        }
    }
    public void shutdown(){
        storageSubsystem.setMotorPower(0);
    }
}
