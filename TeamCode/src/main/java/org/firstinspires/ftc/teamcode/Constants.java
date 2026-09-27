package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Constants {
    public static class MecanumConstants {
        //Changed
        public static final DcMotorEx.Direction kLeftFrontDirection = DcMotorEx.Direction.REVERSE;
        public static final DcMotorEx.Direction kLeftBackDirection = DcMotorEx.Direction.FORWARD;
        public static final DcMotorEx.Direction kRightFrontDirection = DcMotorEx.Direction.REVERSE;
        public static final DcMotorEx.Direction kRightBackDirection = DcMotorEx.Direction.FORWARD;



        //normal before change
//        public static final DcMotorEx.Direction kLeftFrontDirection = DcMotorEx.Direction.FORWARD;
//        public static final DcMotorEx.Direction kLeftBackDirection = DcMotorEx.Direction.REVERSE;
//        public static final DcMotorEx.Direction kRightFrontDirection = DcMotorEx.Direction.FORWARD;
//        public static final DcMotorEx.Direction kRightBackDirection = DcMotorEx.Direction.REVERSE;
        public static final double kCountsPerRotation = 384.5;
        public static final double kDriveGearReduction = 20.0;
        public static final double kWheelDiameter = 3.77953;
        public static final double kWheelCircumference = Math.PI * kWheelDiameter;
        public static final double kCountsPerInch = (kCountsPerRotation * kDriveGearReduction) / kWheelCircumference;

    }

    public static class PayloadConstants {
        public static final DcMotorEx.Direction kFlywheelDirection = DcMotorEx.Direction.FORWARD;
        public static final DcMotorEx.Direction kIntakeDirection = DcMotorEx.Direction.FORWARD;
        public static final DcMotorEx.Direction kStorageMotor1Direction = DcMotorEx.Direction.FORWARD;

        public static final int kServoClosedPosition = 0;
        public static final int kServoOpenPosition = 20;
    }

    public static class AprilTagConstants {
        public static final int kBlueGoalID = 20;
        public static final int kRedGoalID = 24;
        public static final double kAlignmentOffset = 8.84;
        public static final double kFlywheelEquationA = 8.006546538;
        public static final double kFlywheelEquationB = -105.4470598;
        public static final double kFlywheelEquationC = 1475.151779;
    }
}
