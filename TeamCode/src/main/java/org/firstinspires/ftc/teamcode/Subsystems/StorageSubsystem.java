package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.Constants;

public class StorageSubsystem {
    private DcMotorEx motor0;

    public StorageSubsystem(OpMode opMode){
        motor0 = opMode.hardwareMap.get(DcMotorEx.class, "motor0");
    }
    public void setMotorPower(double power){
        motor0.setPower(power);
    }
    public void shutdown(){
        motor0.setPower(0);
    }
}