package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.robocol.Command;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;

public class AutoSlowStrafeCommand {
    MecanumSubsystem mecanumSubsystem;
    PIDController pidController;
    LinearOpMode linearOpMode;
    ElapsedTime elapsedTime;

    public AutoSlowStrafeCommand(MecanumSubsystem mecanumSubsystem, LinearOpMode linearOpMode, ElapsedTime elapsedTime) {
        this.mecanumSubsystem = mecanumSubsystem;
        this.linearOpMode = linearOpMode;
        pidController = new PIDController(0.0001, 0, 0, 0.1);
        this.elapsedTime = elapsedTime;
    }

    public void operate (double distance, String direction) {
        double motorTarget = (distance * Constants.MecanumConstants.kCountsPerInch) + mecanumSubsystem.encoderReading()[0];
        pidController.createSetPoint(motorTarget);



        while (true && linearOpMode.opModeIsActive()) {
            pidController.setProcessVariable(mecanumSubsystem.encoderReading()[0]);
            double powerOutput = pidController.getOutput();
            mecanumSubsystem.autoStrafe(powerOutput);



            if (Math.abs(pidController.getError()) < 5) {
                break;
            }

        }
        mecanumSubsystem.shutdown();
    }

    public void shutdown() {
        mecanumSubsystem.shutdown();
    }
}
