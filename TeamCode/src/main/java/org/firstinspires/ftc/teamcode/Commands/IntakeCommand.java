package org.firstinspires.ftc.teamcode.Commands;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.StorageSubsystem;

public class IntakeCommand {
    IntakeSubsystem intakeSubsystem;
    StorageSubsystem storageSubsystem;
    Gamepad gamepad1;
    Gamepad gamepad2;

    public IntakeCommand(IntakeSubsystem intakeSubsystem, StorageSubsystem storageSubsystem, Gamepad gamepad1, Gamepad gamepad2){
        this.intakeSubsystem=intakeSubsystem;
        this.storageSubsystem = storageSubsystem;
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }
    public void operate(Gamepad gamepad1, Gamepad gamepad2){
        if(!gamepad1.dpad_right){
            if(gamepad1.right_bumper || gamepad2.right_trigger > 0.3){
                intakeSubsystem.setIntakePower(1);
            }

            else{
                intakeSubsystem.setIntakePower(0);
            }
        }
        else if(gamepad1.dpad_right){
            if(gamepad1.right_bumper){
                intakeSubsystem.setIntakePower(-1);
            }
            else{
                intakeSubsystem.setIntakePower(0);
            }
        }
        else{
            intakeSubsystem.setIntakePower(0);
        }
    }
    public void shutdown(){
        intakeSubsystem.shutdown();
    }
}
