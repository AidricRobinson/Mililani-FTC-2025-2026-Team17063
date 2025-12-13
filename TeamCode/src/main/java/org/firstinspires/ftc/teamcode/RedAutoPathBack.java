package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Commands.AutoCameraAlign;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.AutoFlywheelCommand;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.AutoIntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.AutoLimelightFlywheel;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.AutoMecanumCommand;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.AutoPIDFlywheelCommand;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.AutoPayloadCommand;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.AutoReverseIntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.AutoSlowMecanumCommand;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.AutoSlowStrafeCommand;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.AutoStorageCommand;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.AutoStrafeCommand;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.AutoTurnCommand;
import org.firstinspires.ftc.teamcode.Subsystems.FlywheelSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.StorageSubsystem;
@Autonomous(name="RED - BACK", group="Linear OpMode")
public class RedAutoPathBack extends LinearOpMode {
    private FlywheelSubsystem flywheelSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private MecanumSubsystem mecanumSubsystem;
    private StorageSubsystem storageSubsystem;

    private AutoFlywheelCommand autoFlywheelCommand;
    private AutoIntakeCommand autoIntakeCommand;
    private AutoMecanumCommand autoMecanumCommand;
    private AutoStorageCommand autoStorageCommand;
    private AutoStrafeCommand autoStrafeCommand;
    private AutoTurnCommand autoTurnCommand;
    private AutoPayloadCommand autoPayloadCommand;
    private AutoSlowMecanumCommand autoSlowMecanumCommand;
    private AutoSlowStrafeCommand autoSlowStrafeCommand;
    private AutoReverseIntakeCommand autoReverseIntakeCommand;
    private AutoPIDFlywheelCommand autoPIDFlywheelCommand;

    private ElapsedTime elapsedTime;

    private AutoLimelightFlywheel autoLimelightFlywheel;
    private Limelight3A limelight3A;
    private AutoCameraAlign autoCameraAlign;


    public void runOpMode(){
        limelight3A = hardwareMap.get(Limelight3A.class, "limelight");
        limelight3A.setPollRateHz(60);
        limelight3A.pipelineSwitch(1);
        limelight3A.start();

        elapsedTime = new ElapsedTime();

        flywheelSubsystem = new FlywheelSubsystem(this);
        intakeSubsystem = new IntakeSubsystem(this);
        mecanumSubsystem = new MecanumSubsystem(this.hardwareMap, this);
        storageSubsystem = new StorageSubsystem(this);

        autoReverseIntakeCommand = new AutoReverseIntakeCommand(intakeSubsystem, elapsedTime, this);
        autoLimelightFlywheel = new AutoLimelightFlywheel(flywheelSubsystem, limelight3A);
        autoFlywheelCommand = new AutoFlywheelCommand(flywheelSubsystem, elapsedTime, this, telemetry);
        autoIntakeCommand = new AutoIntakeCommand(intakeSubsystem, elapsedTime, this);
        autoStorageCommand = new AutoStorageCommand(storageSubsystem, elapsedTime, this);
        autoMecanumCommand = new AutoMecanumCommand(mecanumSubsystem, this);
        autoSlowMecanumCommand = new AutoSlowMecanumCommand(mecanumSubsystem, this, elapsedTime);
        autoStrafeCommand = new AutoStrafeCommand(mecanumSubsystem, this);
        autoTurnCommand = new AutoTurnCommand(mecanumSubsystem, this);
        autoSlowStrafeCommand = new AutoSlowStrafeCommand(mecanumSubsystem, this, elapsedTime);
        autoPIDFlywheelCommand = new AutoPIDFlywheelCommand(autoLimelightFlywheel, flywheelSubsystem, storageSubsystem, intakeSubsystem, elapsedTime, this, telemetry);

        autoCameraAlign = new AutoCameraAlign(mecanumSubsystem, limelight3A);

        autoPayloadCommand = new AutoPayloadCommand(intakeSubsystem, storageSubsystem, flywheelSubsystem, elapsedTime, this);

        waitForStart();

////////////////////////////////////////////////////////////////////////////////////////////////////
// START OF FIRST CYCLE
        storageSubsystem.setServoPosition(0);
        autoStrafeCommand.operate(0.5, "LEFT");
        autoMecanumCommand.operate(-3000, "FORWARD");
        autoTurnCommand.operate(-45, "RIGHT");
        autoCameraAlign.operate();
        autoPIDFlywheelCommand.operate(1900, 2000);
        autoTurnCommand.operate(-45, "RIGHT");
        autoStrafeCommand.operate(-1, "RIGHT");

////////////////////////////////////////////////////////////////////////////////////////////////////
// SHUTDOWNS

        flywheelSubsystem.shutdown();
        intakeSubsystem.shutdown();
        mecanumSubsystem.shutdown();
        storageSubsystem.shutdown();
    }
}
