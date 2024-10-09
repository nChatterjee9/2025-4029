package org.firstinspires.ftc.teamcode.Bot.Drivetrain;

import static org.firstinspires.ftc.teamcode.Bot.Setup.telemetry;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.teamcode.Bot.Sensors.IMUStatic;
import org.firstinspires.ftc.teamcode.Bot.Setup;
import org.firstinspires.ftc.teamcode.PedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.PedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.PedroPathing.localization.ThreeWheelLocalizer;
import org.firstinspires.ftc.teamcode.PedroPathing.pathGeneration.MathFunctions;

import java.util.ArrayList;
import java.util.List;
import org.firstinspires.ftc.teamcode.PedroPathing.pathGeneration.Vector;

public class Drivetrain {
    public Pose currentPos;
    public Vector targetDriveVector;
    public Vector targetHeadingVector;
    public double[] teleOpTargets;
    protected Follower follower;
    private IMUStatic imu;
    protected double imuOffset;


    public Drivetrain(){
    }

    public void init(Pose startPose) {
        currentPos = startPose;
        follower = new Follower(Setup.hardwareMap, false);
        follower.setStartingPose(startPose);
        targetDriveVector = new Vector();
        targetHeadingVector = new Vector();
        teleOpTargets = new double[3];
        imu = new IMUStatic();
        telemetry.addLine("Follower: " + follower.driveError);
    }

    public void update(boolean usePeP){
        if(usePeP){
            follower.setMovementVectors(follower.getCentripetalForceCorrection(), targetHeadingVector, targetDriveVector);
            follower.update();
        }else{
            double x = teleOpTargets[0];
            double y = teleOpTargets[1];
            double spin = teleOpTargets[2];
            follower.setMotorPowers(Range.clip(y + x, -1, 1) + spin,
                    Range.clip(y - x, -1, 1) + spin,
                    Range.clip(y + x, -1, 1) - spin,
                    Range.clip(y - x, -1, 1) - spin);
        }
    }

    public void telemetry(){
        telemetry.addData("Drivetrain currentPos", currentPos);
    }

    public void setTargetVectors(double x, double y, double turn){
        double theta = (imu.getYaw(AngleUnit.RADIANS));
        x = MathFunctions.clamp(x,0,1);
        y = MathFunctions.clamp(y,0,1);
        double[] coordinates = CartesianToPolar(x,y);
        coordinates[1] += theta;
        targetDriveVector.setMagnitude(coordinates[0]);
        targetDriveVector.setTheta(coordinates[1]);
//        targetDriveVector.setOrthogonalComponents(-y, -x);
//        targetDriveVector.setMagnitude(MathFunctions.clamp(targetDriveVector.getMagnitude(), 0, 1));
//        targetDriveVector.rotateVector(follower.getPose().getHeading());
        targetHeadingVector.setComponents(turn, follower.getPose().getHeading());
    }

    public void localMovement(double x, double y){
        teleOpTargets[0] = x;
        teleOpTargets[1] = y;
        teleOpTargets[2] = 0;
    }
    public void localMovement(double x, double y, double rot){
        teleOpTargets[0] = x;
        teleOpTargets[1] = y;
        teleOpTargets[2] = rot;
    }
    public void setTeleOpTargets(double x, double y, double theta){
        double target_x = Math.abs(x)>0.04 ? x : 0;
        double target_y = Math.abs(y)>0.04 ? -y : 0;
        double target_spin = Math.abs(theta) > 0.04 ? theta : 0;
        double translateMag = Math.sqrt(x*x + y*y);
        double angle = Math.atan2(y, x);

        angle += (-this.getHeadingIMU() + imuOffset);


        target_x = Math.cos(angle) * translateMag;
        target_y = Math.sin(angle) * translateMag;

        teleOpTargets[0] = target_x;
        teleOpTargets[1] = target_y;
        teleOpTargets[2] = target_spin;
    }


    public double[] CartesianToPolar(double x, double y) {
        return new double[]{Math.sqrt(x * x + y * y), Math.atan2(y, x)};
    }
//    private double[] PolarToCartesian(double r, double theta){
//        return new double[]{};
//    }


    public double getHeadingIMU() {return imu.getYaw(AngleUnit.RADIANS);}
    public void resetIMU(){imu.resetYaw();}

}
