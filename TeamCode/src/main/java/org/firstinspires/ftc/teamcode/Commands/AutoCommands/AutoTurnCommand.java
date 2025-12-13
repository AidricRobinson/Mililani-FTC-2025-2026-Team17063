package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptAprilTag;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;

public class AutoTurnCommand {
    MecanumSubsystem mecanumSubsystem;
    PIDController pidController;
    LinearOpMode linearOpMode;
    public AutoTurnCommand(MecanumSubsystem mecanumSubsystem, LinearOpMode linearOpMode){
        this.mecanumSubsystem = mecanumSubsystem;
        this.linearOpMode = linearOpMode;

        pidController = new PIDController(0.0051, 0, 0, 0.15);

    }
    public void operate(double targetAngle, String direction){
        int motortarget = (int)(targetAngle);
        pidController.createSetPoint(motortarget + (mecanumSubsystem.getYaw()));
        while(true && linearOpMode.opModeIsActive()){
            pidController.setProcessVariable(mecanumSubsystem.getYaw());
            if(Math.abs(pidController.getError()) < 5){
                break;
            }
            double powerOutput = pidController.getOutput();
            mecanumSubsystem.autoLeft(powerOutput);
            mecanumSubsystem.autoRight(-powerOutput);


        }
        mecanumSubsystem.shutdown();
    }
    public void shutdown(){
        mecanumSubsystem.shutdown();
    }
}
