package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.robocol.Command;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;

public class AutoStrafeCommand {
    MecanumSubsystem mecanumSubsystem;
    PIDController pidController;
    LinearOpMode linearOpMode;

    public AutoStrafeCommand(MecanumSubsystem mecanumSubsystem, LinearOpMode linearOpMode) {
        this.mecanumSubsystem = mecanumSubsystem;
        this.linearOpMode = linearOpMode;
        pidController = new PIDController(0.00121, 0, 0, 0.15);
    }

    public void operate (double distance, String direction) {
        double motorTarget = (distance * Constants.MecanumConstants.kCountsPerInch) + mecanumSubsystem.encoderReading()[0];
        pidController.createSetPoint(motorTarget);

        while (true && linearOpMode.opModeIsActive()) {
            pidController.setProcessVariable(mecanumSubsystem.encoderReading()[0]);
            double powerOutput = pidController.getOutput();
            mecanumSubsystem.autoStrafe(powerOutput);

            if (Math.abs(pidController.getError()) < 10) {
                break;
            }
        }
        mecanumSubsystem.shutdown();
    }

    public void shutdown() {
        mecanumSubsystem.shutdown();
    }
}
