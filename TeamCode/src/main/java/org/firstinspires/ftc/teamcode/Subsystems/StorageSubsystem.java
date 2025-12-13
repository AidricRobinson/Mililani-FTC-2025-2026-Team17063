package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;

public class StorageSubsystem {
    private DcMotorEx motor1;
    private Servo door;
//    private DcMotorEx motor2;

    public StorageSubsystem(OpMode opMode){
        door = opMode.hardwareMap.get(Servo.class, "door");

        motor1 = opMode.hardwareMap.get(DcMotorEx.class, "motor1");
//        motor2 = opMode.hardwareMap.get(DcMotorEx.class, "motor2");

        motor1.setDirection(Constants.PayloadConstants.kStorageMotor1Direction);
//        motor2.setDirection(Constants.PayloadConstants.kStorageMotor2Direction);

        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motor1.setPower(0);
//        motor2.setPower(0);
    }
    public void setStorage(int targetCount, String direction){
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        if (direction == "FORWARD"){
           motor1.setTargetPosition(targetCount);
//           motor2.setTargetPosition(targetCount);
        }
        else if(direction == "BACKWARD"){
            motor1.setTargetPosition(-targetCount);
//            motor2.setTargetPosition(-targetCount);
        }
    }

    public void setServoPosition(double position) {
        door.setPosition(position);
    }

    public void setMotorPower(double power){
        motor1.setPower(power);
//        motor2.setPower(power);
    }
//    public void setMotor1Power(double power){
//        motor1.setPower(power);
//    }
//    public void setMotor2Power(double power){
//        motor2.setPower(power);
//    }

    public void operate(){

    }
    public boolean isBusyCheck(){
        boolean isBusy = true;
        if(motor1.isBusy()){

        }
        else{
            isBusy = false;
        }
        return isBusy;
    }

    public void shutdown(){
        motor1.setPower(0);
//        motor2.setPower(0);
    }
}