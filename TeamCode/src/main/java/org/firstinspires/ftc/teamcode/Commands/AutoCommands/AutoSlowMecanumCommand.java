package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;

public class AutoSlowMecanumCommand {
    MecanumSubsystem mecanumSubsystem;
    PIDController pidController;
    LinearOpMode linearOpMode;
    ElapsedTime elapsedTime;
    public AutoSlowMecanumCommand (MecanumSubsystem mecanumSubsystem, LinearOpMode linearOpMode, ElapsedTime elapsedTime) {
        this.mecanumSubsystem = mecanumSubsystem;
        pidController = new PIDController(0.0002, 0, 0, 0.1);
        this.linearOpMode = linearOpMode;
        this.elapsedTime = elapsedTime;
    }
    public void operate (double distance, String direction) {
        int motorTarget = (int)(distance ); // * Constants.MecanumConstants.kCountsPerInch
        pidController.createSetPoint(motorTarget + (mecanumSubsystem.encoderReading()[0]));


        while (true && linearOpMode.opModeIsActive()) {
            pidController.setProcessVariable(mecanumSubsystem.encoderReading()[0]);

            if (Math.abs(pidController.getError()) < 20) {
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
