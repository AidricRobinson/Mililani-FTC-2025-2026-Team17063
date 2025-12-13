package org.firstinspires.ftc.teamcode.Commands;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;

public class LimeLightCommand {
    Gamepad gamepad;
    HardwareMap hardwareMap;
    OpMode opMode;
    Limelight3A limelight;
    MecanumSubsystem mecanumSubsystem;
    LLResult result;
    PIDController pidController;
    public LimeLightCommand(HardwareMap hardwareMap, OpMode opMode, Gamepad gamepad){
        this.limelight = limelight;
        this.mecanumSubsystem = mecanumSubsystem;
        this.result = result;
        this.gamepad = gamepad;
        this.pidController = pidController;


        limelight = hardwareMap.get(Limelight3A.class, "LimeLight");
        limelight.setPollRateHz(60);
        limelight.start();
        limelight.pipelineSwitch(0);

        result = limelight.getLatestResult();
        result.getPipelineIndex();
    }
    public void operate(Gamepad gamepad){
        if(gamepad.right_bumper){
            double tx = result.getTx();
            while(Math.abs(tx) < 1){
                pidController.setProcessVariable(tx);
                double kp = .05;
                double KFF = .05;
                double output = kp * (tx) + KFF;

                mecanumSubsystem.autoLeft(-output);
                mecanumSubsystem.autoRight(output);
                tx = result.getTx();
            }
        }
    }
    public void shutdown(){
        mecanumSubsystem.shutdown();
    }
}
