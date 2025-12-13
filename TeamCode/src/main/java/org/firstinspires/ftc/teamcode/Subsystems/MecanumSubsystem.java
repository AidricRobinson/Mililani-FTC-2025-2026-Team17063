package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.BNO055IMUNew;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Constants;
import org.opencv.core.Mat;

import java.util.Base64;


public class MecanumSubsystem {
    private final IMU imu;

    private DcMotorEx leftFront;
    private DcMotorEx rightFront;
    private DcMotorEx leftBack;
    private DcMotorEx rightBack;
    private boolean slowModeOn;
//    private Limelight3A limelight;
//    private LLResult result;
    private DcMotorEx xEncoder;
    private DcMotorEx yEncoder;


    public MecanumSubsystem(HardwareMap hardwareMap, OpMode opMode) {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");


        imu = opMode.hardwareMap.get(IMU.class, "imu");
//        BHI260IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
//                RevHubOrientationOnRobot.LogoFacingDirection.UP,
//                RevHubOrientationOnRobot.UsbFacingDirection.RIGHT));
        BHI260IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
               RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.RIGHT));
        imu.initialize(parameters);

//        xEncoder = hardwareMap.get(DcMotorEx.class, "xEncoder");
//        yEncoder = hardwareMap.get(DcMotorEx.class, "yEncoder");


        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftFront.setDirection(Constants.MecanumConstants.kLeftFrontDirection);
        leftBack.setDirection(Constants.MecanumConstants.kLeftBackDirection);
        rightFront.setDirection(Constants.MecanumConstants.kRightBackDirection);
        rightBack.setDirection(Constants.MecanumConstants.kRightFrontDirection);

//        limelight = hardwareMap.get(Limelight3A.class, "limelight");
//        limelight.setPollRateHz(60);
//        limelight.start();
//        limelight.pipelineSwitch(0);

//        result = limelight.getLatestResult();
//        result.getPipelineIndex();


    }
    public void operate(Gamepad gamepad, Telemetry telemetry){
        double y = gamepad.left_stick_y;
        double x = gamepad.left_stick_x;
        double rx = gamepad.right_stick_x;

        double botHeading = getYawRadius();
        double rotX = x * Math.cos(botHeading) - y * Math.sin(botHeading);
        double rotY = x * Math.sin(botHeading) + y * Math.cos(botHeading);

//        double tx = result.getTx(); // How far left or right the target is
        double kp = 0.05;
        double kFF = 0.05;

//        if (gamepad.a) {
//            while (Math.abs(tx) < 1) {
//                double output = kp * tx + kFF;
//
//                leftFront.setPower(output);
//                leftBack.setPower(output);
//                rightFront.setPower(-output);
//                rightBack.setPower(-output);
//            }
//        }


        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);


//
//        if (gamepad.right_bumper){     //Regular
//            leftFront.setPower(((y+x-rx) / denominator) * .5);
//            leftBack.setPower(((y-x-rx) / denominator) * .5);
//            rightFront.setPower(((y-x+rx) / denominator) * .5);
//            rightBack.setPower(((y+x+rx) / denominator) * .5);
//            slowModeOn = true;
//        }
//
//        else{    //regular
//            leftFront.setPower((y-x-rx) / denominator);
//            leftBack.setPower((y+x-rx) / denominator);
//            rightFront.setPower((y+x+rx) / denominator);
//            rightBack.setPower((y-x+rx) / denominator);
//            slowModeOn = false;
//        }

        if (gamepad.right_bumper){    //Field Oriented
            leftFront.setPower(((rotY-rotX-rx) / denominator) * .5);
            leftBack.setPower(((rotY+rotX-rx) / denominator) * .5);
            rightFront.setPower(((rotY+rotX+rx) / denominator) * .5);
            rightBack.setPower(((rotY-rotX+rx) / denominator) * .5);
            slowModeOn = true;
        }

        else{    //Field oriented
            leftFront.setPower((rotY-rotX-rx) / denominator);
            leftBack.setPower((rotY+rotX-rx) / denominator);
            rightFront.setPower((rotY+rotX+rx) / denominator);
            rightBack.setPower((rotY-rotX+rx) / denominator);
            slowModeOn = false;
        }

//        telemetry.addData("Slow mode is: ", slowModeOn);
//        telemetry.update();
    }

//    public double getXEncoder(){
//        return xEncoder.getCurrentPosition();
//    }
//    public double getYEncoder() {
//        return yEncoder.getCurrentPosition();
//    }
//    public void resetEncoder(){
//        xEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        yEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//    }
//    public double[] encoderReading () {
//        double[] encoderReading = new double[4];
//        encoderReading[0] = leftFront.getCurrentPosition();
//        encoderReading[1] = leftBack.getCurrentPosition();
//        encoderReading[2] = rightFront.getCurrentPosition();
//        encoderReading[3] = rightBack.getCurrentPosition();
//
//        return encoderReading;
//    }

    public void autoLeft(double power) {
        leftFront.setPower(power);
        leftBack.setPower(power);
    }

    public void autoRight(double power) {
        rightFront.setPower(power);
        rightBack.setPower(power);
    }
    public double[] encoderReading(){
        double[] encoderReading = new double[4];
        encoderReading[0] = leftFront.getCurrentPosition();
        encoderReading[1] = leftBack.getCurrentPosition();
        encoderReading[2] = rightFront.getCurrentPosition();
        encoderReading[3] = rightBack.getCurrentPosition();
        return encoderReading;
    }

    public double getYaw() {
        return imu.getRobotOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;
    }
//    public double getPitch() {
//        return imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).secondAngle;
//    }
//    public double getRollRadians() {
//        return imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.RADIANS).firstAngle;
//    }
//    public double getRollDegrees() {
//        return imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).firstAngle;
//    }
    public double getYawRadius(){
        return imu.getRobotOrientation(AxesReference.EXTRINSIC,AxesOrder.XYZ, AngleUnit.RADIANS).thirdAngle;
    }


    public void autoStrafe(double power) {
        rightFront.setPower(-power);
        leftBack.setPower(-power);
        leftFront.setPower(power);
        rightBack.setPower(power);
    }

    public void shutdown(){
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
    }


}
