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
        // create 2 buttons, one for 50% and other for 100% power. It should be off when not pressing
        //gamepad.a is boolean that is true when your clicking the button a
        //storageSubsystem.setMotorPower( percent power);
        //use if and else
        //good luck
        if (gamepad.a == true) {
            storageSubsystem.setMotorPower(0.5);
        } else if (gamepad.b == true) {
            storageSubsystem.setMotorPower(1);
        } else {
            storageSubsystem.setMotorPower(0);
        }
    }














        }
    }

    public void shutdown(){
        storageSubsystem.shutdown();
    }
}
