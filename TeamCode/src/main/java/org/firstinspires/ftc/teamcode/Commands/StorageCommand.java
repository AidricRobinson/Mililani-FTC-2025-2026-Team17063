package org.firstinspires.ftc.teamcode.Commands;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.StorageSubsystem;

public class StorageCommand {
    StorageSubsystem storageSubsystem;
    IntakeSubsystem intakeSubsystem;
    Gamepad gamepad;

    boolean isButtonPressed;
    boolean storageOn;

    int closedPosition = Constants.PayloadConstants.kServoClosedPosition;
    int openPosition = Constants.PayloadConstants.kServoOpenPosition;

    public StorageCommand(StorageSubsystem storageSubsystem, IntakeSubsystem intakeSubsystem, Gamepad gamepad){
        this.storageSubsystem = storageSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        this.gamepad = gamepad;

        isButtonPressed = false;
        storageOn = false;
    }
    public void operate(Gamepad gamepad) {
//        if (gamepad.left_bumper && !isButtonPressed) {
//            storageOn = !storageOn;
//        }
//        if (storageOn) {
//            storageSubsystem.setMotorPower(0.3);
//        }
        if (gamepad.left_bumper) {
            storageSubsystem.setMotorPower(1);
            intakeSubsystem.setIntakePower(1);
            storageSubsystem.setServoPosition(openPosition);
        }
        else if (gamepad.dpad_right && gamepad.left_bumper) {
            storageSubsystem.setMotorPower(-1);
        }
        else{
            storageSubsystem.setMotorPower(0);
            intakeSubsystem.setIntakePower(0);
            storageSubsystem.setServoPosition(closedPosition);
        }
//        isButtonPressed = gamepad.left_bumper;
    }

    public void shutdown(){
        storageSubsystem.shutdown();
    }
}
