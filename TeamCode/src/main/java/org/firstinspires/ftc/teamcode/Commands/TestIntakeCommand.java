package org.firstinspires.ftc.teamcode.Commands;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class TestIntakeCommand {
    IntakeSubsystem intakeSubsystem;
    Gamepad gamepad;

    public TestIntakeCommand(IntakeSubsystem intakeSubsystem,Gamepad gamepad){
        this.intakeSubsystem=intakeSubsystem;
        this.gamepad=gamepad;
    }
    public void operate(){
        if(gamepad.dpad_down){
            intakeSubsystem.setIntakePower(.25);
        }
        else if(gamepad.dpad_right){
            intakeSubsystem.setIntakePower(.5);
        }
        else if(gamepad.dpad_up){
            intakeSubsystem.setIntakePower(.75);
        }
        else if (gamepad.dpad_left){
            intakeSubsystem.setIntakePower(1);
        }
        else{
            intakeSubsystem.setIntakePower(0);
        }
    }
    public void shutdown(){
        intakeSubsystem.shutdown();
    }
}
