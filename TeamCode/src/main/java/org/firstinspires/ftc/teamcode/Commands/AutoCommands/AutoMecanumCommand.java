package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;

public class AutoMecanumCommand {
    MecanumSubsystem mecanumSubsystem;
    PIDController pidController;
    LinearOpMode linearOpMode;
    public AutoMecanumCommand (MecanumSubsystem mecanumSubsystem, LinearOpMode linearOpMode) {
        this.mecanumSubsystem = mecanumSubsystem;
        pidController = new PIDController(0.0011, 0, 0, 0.1);
        this.linearOpMode = linearOpMode;
    }
    public void operate (double distance, String direction) {
        int motorTarget = (int)(distance ); // * Constants.MecanumConstants.kCountsPerInch
        pidController.createSetPoint(motorTarget + (mecanumSubsystem.encoderReading()[0]));

        while (true && linearOpMode.opModeIsActive()) {
            pidController.setProcessVariable(mecanumSubsystem.encoderReading()[0]);

            if (Math.abs(pidController.getError()) < 10) {
                break;
            }

            double powerOutput = pidController.getOutput();
            mecanumSubsystem.autoLeft(powerOutput);
            mecanumSubsystem.autoRight(powerOutput);
        }
        mecanumSubsystem.shutdown();
    }
    public void shutdown() {
        mecanumSubsystem.shutdown();
    }


}
