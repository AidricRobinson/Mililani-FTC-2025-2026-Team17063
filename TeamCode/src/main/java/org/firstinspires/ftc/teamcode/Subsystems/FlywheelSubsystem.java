package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class FlywheelSubsystem {
    DcMotorEx flywheelMotor;
    private double output;

    public FlywheelSubsystem(OpMode opMode){
        flywheelMotor = opMode.hardwareMap.get(DcMotorEx.class, "flywheelMotor");

        flywheelMotor.setDirection(Constants.PayloadConstants.kFlywheelDirection);

        flywheelMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        flywheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        flywheelMotor.setPower(0);
        output = 1000;
    }

    public void operate() {
    }

    public void incOutput () {
        output += 1;
    }

    public void decOutput () {
        output -= 1;
    }

    public double getSpeed () {
        return output;
    }

    public double getRPM() {
        return flywheelMotor.getVelocity(AngleUnit.DEGREES);
    }
//    public void setFlywheel(int targetCount){
//        flywheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        flywheelMotor.setTargetPosition(targetCount);
//    }

    public void setSpeed(double rpm) {
        flywheelMotor.setVelocity(rpm, AngleUnit.DEGREES);
    }

    public boolean isBusyCheck(){
        boolean isBusy = true;
        if (flywheelMotor.isBusy()){

        }
        else{
            isBusy = false;
        }
        return isBusy;
    }

    public void setFlywheelPower(double power){
        flywheelMotor.setPower(power);
    }
    public void shutdown(){
        flywheelMotor.setPower(0);
    }
}
