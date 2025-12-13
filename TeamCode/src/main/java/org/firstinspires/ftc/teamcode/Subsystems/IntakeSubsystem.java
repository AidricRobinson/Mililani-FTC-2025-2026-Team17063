package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Constants;

public class IntakeSubsystem {
    DcMotorEx intakeMotor;

    public IntakeSubsystem(OpMode opMode){
        intakeMotor = opMode.hardwareMap.get(DcMotorEx.class,"intakeMotor");
        intakeMotor.setDirection(Constants.PayloadConstants.kIntakeDirection);
        intakeMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setPower(0);
    }

    public void operate() {

    }

    public void setIntake(int targetCount){
        intakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        intakeMotor.setTargetPosition(targetCount);
    }

    public void setIntakePower (double power){
        intakeMotor.setPower(power);
    }
    public void shutdown(){
        intakeMotor.setPower(0);
    }


    public boolean isBusyCheck(){
        boolean isBusy = true;
        if (intakeMotor.isBusy()){

        }
        else{
            isBusy = false;
        }
        return isBusy;
    }
}
