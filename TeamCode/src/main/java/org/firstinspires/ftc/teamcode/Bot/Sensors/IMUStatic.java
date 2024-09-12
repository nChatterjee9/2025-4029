package org.firstinspires.ftc.teamcode.Bot.Sensors;

import static org.firstinspires.ftc.teamcode.Bot.Setup.hardwareMap;

import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class IMUStatic {
    private static com.qualcomm.robotcore.hardware.IMU sensor;
    public IMUStatic(String name, ImuOrientationOnRobot orientation){
        sensor = hardwareMap.get(IMU.class, name);
        sensor.initialize(new IMU.Parameters(orientation));
    }
    public IMUStatic(){

    }
    public void resetYaw(){
        sensor.resetYaw();
    }
    public double getYaw(AngleUnit unit){
        return sensor.getRobotYawPitchRollAngles().getYaw(unit);
    }
    public double getPitch(AngleUnit unit){
        return sensor.getRobotYawPitchRollAngles().getPitch(unit);
    }
    public double getRoll(AngleUnit unit){
        return sensor.getRobotYawPitchRollAngles().getRoll(unit);
    }
    public YawPitchRollAngles getRobotYawPitchRollAngles(){
        return sensor.getRobotYawPitchRollAngles();
    }
    public Orientation getRobotOrientation(AxesReference reference, AxesOrder order, AngleUnit angleUnit){
        return sensor.getRobotOrientation(reference, order, angleUnit);
    }
    public AngularVelocity getRobotAngularVelocity(AngleUnit unit){
        return sensor.getRobotAngularVelocity(unit);
    }
    public Quaternion getRobotOrientationAsQuaternion(){
        return sensor.getRobotOrientationAsQuaternion();
    }
}
