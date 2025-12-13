package org.firstinspires.ftc.teamcode.Commands;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Subsystems.StorageSubsystem;

public class TestStorageCommand {
    StorageSubsystem storageSubsystem;
    Gamepad gamepad;

    public TestStorageCommand(StorageSubsystem storageSubsystem, Gamepad gamepad){
        this.storageSubsystem = storageSubsystem;
        this.gamepad = gamepad;
    }
    public void operate() {
        if(gamepad.a){
            storageSubsystem.setMotorPower(.25);
        }
        else if(gamepad.b){
            storageSubsystem.setMotorPower(.5);
        }
        else if(gamepad.x){
            storageSubsystem.setMotorPower(.75);
        }
        else if (gamepad.y) {
            storageSubsystem.setMotorPower(1);
        }
        else{
            storageSubsystem.setMotorPower(0);
        }
    }

    public void shutdown(){
        storageSubsystem.shutdown();
    }
}
