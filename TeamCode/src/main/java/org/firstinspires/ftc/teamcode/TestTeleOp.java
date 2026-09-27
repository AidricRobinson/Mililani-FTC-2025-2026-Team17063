package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.AprilTagAlignmentCommand;
import org.firstinspires.ftc.teamcode.Commands.AprilTagFlywheelCommand;
import org.firstinspires.ftc.teamcode.Commands.FlywheelCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.StorageCommand;
import org.firstinspires.ftc.teamcode.Commands.TestFlywheelCommand;
import org.firstinspires.ftc.teamcode.Subsystems.FlywheelSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.StorageSubsystem;

@TeleOp(name="TestTeleOp")
public class TestTeleOp extends OpMode {

    private StorageSubsystem storageSubsystem;
    private TestStorageCommand testStorageCommand;



    @Override
    public void init() {
        storageSubsystem = new StorageSubsystem(this);
        testStorageCommand = new TestStorageCommand(storageSubsystem, gamepad1);


    }

    @Override
    public void loop() {
        testStorageCommand.operate(gamepad1);
    }

    @Override
    public void stop() {
        storageSubsystem.shutdown();
    }
}
