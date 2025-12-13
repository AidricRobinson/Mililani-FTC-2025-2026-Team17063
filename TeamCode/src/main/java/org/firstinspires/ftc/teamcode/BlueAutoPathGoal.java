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
@Autonomous(name="BLUE - GOAL", group="Linear OpMode")
public class BlueAutoPathGoal extends LinearOpMode {
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
        elapsedTime = new ElapsedTime();

        flywheelSubsystem = new FlywheelSubsystem(this);
        intakeSubsystem = new IntakeSubsystem(this);
        mecanumSubsystem = new MecanumSubsystem(this.hardwareMap, this);
        storageSubsystem = new StorageSubsystem(this);

        autoReverseIntakeCommand = new AutoReverseIntakeCommand(intakeSubsystem, elapsedTime, this);
        limelight3A = hardwareMap.get(Limelight3A.class, "limelight");
        limelight3A.setPollRateHz(60);
        limelight3A.pipelineSwitch(0);
        limelight3A.start();

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

        autoPayloadCommand = new AutoPayloadCommand(intakeSubsystem, storageSubsystem, flywheelSubsystem, elapsedTime, this);

        autoCameraAlign = new AutoCameraAlign(mecanumSubsystem, limelight3A);

        waitForStart();

////////////////////////////////////////////////////////////////////////////////////////////////////
// START OF FIRST CYCLE
        storageSubsystem.setServoPosition(0);

        autoMecanumCommand.operate(1050, "BACKWARD");
        autoTurnCommand.operate(-7.5, "RIGHT");
        autoCameraAlign.operate();
        autoPIDFlywheelCommand.operate(1165, 1000);

// END OF FIRST CYCLE
////////////////////////////////////////////////////////////////////////////////////////////////////
// START OF SECOND CYCLE
        autoMecanumCommand.operate(300, "BACKWARD");
        autoTurnCommand.operate(40, "LEFT");
        autoStrafeCommand.operate(1.35, "LEFT");
// INTAKE CLOSE SPIKE LINE
        autoIntakeCommand.operate();
        autoSlowMecanumCommand.operate(-1200, "FORWARD");
        autoIntakeCommand.shutdown();
        autoReverseIntakeCommand.operate();
        autoMecanumCommand.operate(1000, "BACKWARD");

        autoStrafeCommand.operate(-1.2, "RIGHT");

// SCORE SECOND CYCLE

        autoTurnCommand.operate(-40, "RIGHT");
        autoCameraAlign.operate();

        autoPIDFlywheelCommand.operate(1207.5, 1000);

        storageSubsystem.setServoPosition(0);

// END OF SECOND CYCLE
////////////////////////////////////////////////////////////////////////////////////////////////////
// START OF THIRD CYCLE
        autoTurnCommand.operate(40, "LEFT");
        autoStrafeCommand.operate(3.2, "LEFT");
// INTAKE MIDDLE SPIKE LINE
        autoIntakeCommand.operate();
        autoSlowMecanumCommand.operate(-1500, "FORWARD");
        autoIntakeCommand.shutdown();
        autoReverseIntakeCommand.operate();
        autoMecanumCommand.operate(1250, "BACKWARD");

        autoStrafeCommand.operate(-2.9, "RIGHT");

// SCORE THIRD CYCLE

        autoTurnCommand.operate(-40, "RIGHT");
        autoCameraAlign.operate();

        autoPIDFlywheelCommand.operate(1160, 1250);
        storageSubsystem.setServoPosition(0);

// END OF THIRD CYCLE
////////////////////////////////////////////////////////////////////////////////////////////////////
// FIELD ORIENTED ALIGNMENT
        autoStrafeCommand.operate(-1, "RIGHT");
////////////////////////////////////////////////////////////////////////////////////////////////////
// SHUTDOWNS

        flywheelSubsystem.shutdown();
        intakeSubsystem.shutdown();
        mecanumSubsystem.shutdown();
        storageSubsystem.shutdown();
    }
}
