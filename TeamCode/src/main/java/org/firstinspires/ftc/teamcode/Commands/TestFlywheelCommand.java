package org.firstinspires.ftc.teamcode.Commands;

import android.sax.StartElementListener;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Subsystems.FlywheelSubsystem;

public class TestFlywheelCommand {
    FlywheelSubsystem flywheelSubsystem;
    Gamepad gamepad;
    Telemetry telemetry;
    PIDController pidController;
    Limelight3A limelight;
    LLResult result;
    LLResultTypes.FiducialResult Fresult;

    public TestFlywheelCommand(FlywheelSubsystem flywheelSubsystem, Gamepad gamepad, Telemetry telemetry, Limelight3A limelight) {
        this.flywheelSubsystem = flywheelSubsystem;
        pidController = new PIDController(0.0055, 0, 0.0001, 0.2);
        this.gamepad = gamepad;
        this.telemetry = telemetry;
        this.limelight = limelight;
    }



    public void operate() {
        double ta;
        double tx;
        double id;
        result = limelight.getLatestResult();
        if (result != null && result.isValid()) {
            ta = result.getTa();
            tx = result.getTx();
        }
        else{
            ta = 0;
            tx = 0;
        }

        telemetry.addData("Test RPM:", flywheelSubsystem.getSpeed());
        telemetry.addData("TA:", ta);
        telemetry.addData("TX:", tx);
        telemetry.update();

        result = limelight.getLatestResult();








         if (gamepad.x) {
            double motorTarget = flywheelSubsystem.getSpeed();
            pidController.createSetPoint(motorTarget);
            pidController.setProcessVariable(flywheelSubsystem.getRPM());
            double powerOutput = pidController.getOutput();
            flywheelSubsystem.setFlywheelPower(powerOutput);

        }
        else if (gamepad.dpad_up) {
            flywheelSubsystem.incOutput();
        }
        else if (gamepad.dpad_down) {
            flywheelSubsystem.decOutput();
        }
        else {
            flywheelSubsystem.setFlywheelPower(0);
        }



    }

    public void shutdown(){
        flywheelSubsystem.shutdown();
    }
}