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

@TeleOp(name="Red Tele-Op")
public class REDTeleop extends OpMode {
    private FlywheelSubsystem flywheelSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private MecanumSubsystem mecanumSubsystem;
    private StorageSubsystem storageSubsystem;   //not needed

    private IntakeCommand intakeCommand;
    private StorageCommand storageCommand;
    private Limelight3A limelight3A;
    private AprilTagFlywheelCommand aprilTagFlywheelCommand;
    private AprilTagAlignmentCommand aprilTagAlignmentCommand;


    @Override
    public void init() {
        flywheelSubsystem = new FlywheelSubsystem(this);
        intakeSubsystem = new IntakeSubsystem(this);
        mecanumSubsystem = new MecanumSubsystem(this.hardwareMap, this);
        storageSubsystem = new StorageSubsystem( this);

        intakeCommand = new IntakeCommand(intakeSubsystem, storageSubsystem, gamepad1, gamepad2);
        storageCommand = new StorageCommand(storageSubsystem, intakeSubsystem, gamepad1);

        limelight3A = hardwareMap.get(Limelight3A.class, "limelight");
        limelight3A.setPollRateHz(60);
        limelight3A.pipelineSwitch(1);
        limelight3A.start();

        aprilTagAlignmentCommand = new AprilTagAlignmentCommand(mecanumSubsystem, limelight3A, gamepad2, telemetry);
        aprilTagFlywheelCommand= new AprilTagFlywheelCommand(flywheelSubsystem, gamepad1, telemetry, limelight3A);
    }

    @Override
    public void loop() {
        aprilTagFlywheelCommand.operate(gamepad1, flywheelSubsystem);
        storageCommand.operate(gamepad1);

        intakeCommand.operate(gamepad1, gamepad2);

        mecanumSubsystem.operate(gamepad2, telemetry);
        aprilTagAlignmentCommand.operate(gamepad2);
    }

    @Override
    public void stop() {
        aprilTagFlywheelCommand.shutdown();
        storageCommand.shutdown();
        intakeCommand.shutdown();

        mecanumSubsystem.shutdown();
        intakeSubsystem.shutdown();
        storageSubsystem.shutdown();
        flywheelSubsystem.shutdown();
    }
}
