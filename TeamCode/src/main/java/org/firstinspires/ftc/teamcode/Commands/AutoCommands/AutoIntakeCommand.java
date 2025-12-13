package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class AutoIntakeCommand {
    IntakeSubsystem intakeSubsystem;
    ElapsedTime elapsedTime;
    LinearOpMode linearOpMode;

     public AutoIntakeCommand (IntakeSubsystem intakeSubsystem, ElapsedTime elapsedTime, LinearOpMode linearOpMode) {
         this.intakeSubsystem = intakeSubsystem;
         this.elapsedTime = elapsedTime;
         this.linearOpMode = linearOpMode;
     }

    public void operate(){
//        intakeSubsystem.setIntake(counts);
        double start = elapsedTime.milliseconds();
        while(elapsedTime.milliseconds() < 1 + start && linearOpMode.opModeIsActive()){
            double powerOutput = 1;
            intakeSubsystem.setIntakePower(powerOutput);
        }
    }
    public void shutdown(){
        intakeSubsystem.setIntakePower(0);
    }


}
