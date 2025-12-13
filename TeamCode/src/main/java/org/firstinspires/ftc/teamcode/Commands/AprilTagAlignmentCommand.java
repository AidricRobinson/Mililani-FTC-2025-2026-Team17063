package org.firstinspires.ftc.teamcode.Commands;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;

public class AprilTagAlignmentCommand {
    MecanumSubsystem mecanumSubsystem;
    Gamepad gamepad;
    LLResult result;
    Telemetry telemetry;
    Limelight3A limelight;
    private boolean blueToggle;
    private boolean redToggle;

    public AprilTagAlignmentCommand(MecanumSubsystem mecanumSubsystem, Limelight3A limelight, Gamepad gamepad, Telemetry telemetry) {
        this.mecanumSubsystem = mecanumSubsystem;
        this.limelight = limelight;
        this.gamepad = gamepad;
        this.telemetry = telemetry;

        int blueID = Constants.AprilTagConstants.kBlueGoalID;
        int redID = Constants.AprilTagConstants.kRedGoalID;


    }

    public void operate (Gamepad gamepad) {
        result = limelight.getLatestResult();

        if (gamepad.a && result != null && result.isValid()) {
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
                if (gamepad.b) {
                    break;
                }
                else if (result == null) {
                    break;
                }
                else if (!result.isValid()) {
                    break;
                }
                else if (Math.abs(tx) < 0.1) {
                    break;
                }


            }

        }


    }

}
