package org.firstinspires.ftc.teamcode.Commands;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;

public class AutoCameraAlign {
    MecanumSubsystem mecanumSubsystem;
    LLResult result;
    Limelight3A limelight;
    private boolean blueToggle;
    private boolean redToggle;

    public AutoCameraAlign(MecanumSubsystem mecanumSubsystem, Limelight3A limelight) {
        this.mecanumSubsystem = mecanumSubsystem;
        this.limelight = limelight;


        int blueID = Constants.AprilTagConstants.kBlueGoalID;
        int redID = Constants.AprilTagConstants.kRedGoalID;


    }

    public void operate () {
        result = limelight.getLatestResult();

        if (result != null && result.isValid()) {
            while (true) {
                result = limelight.getLatestResult();
                double tx = 0;
                if (result != null && result.isValid()) {
                    tx = result.getTx() - 8;
                }
                else {
                    tx = 0;
                }
                double kP = 0.0051;
                double kFF = 0.15;
                double output = kP * tx + Math.copySign(kFF, tx);

                mecanumSubsystem.autoLeft(-output);
                mecanumSubsystem.autoRight(+output);

                if (result == null) {
                    break;
                }
                else if (!result.isValid()) {
                    break;
                }
                else if (Math.abs(tx) < 0.15) {
                    break;
                }


            }

        }


    }

}
