package org.firstinspires.ftc.teamcode.Bot.Drivetrain;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Bot.Setup;
import org.firstinspires.ftc.teamcode.PedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.PedroPathing.localization.ThreeWheelLocalizer;
import org.firstinspires.ftc.teamcode.PedroPathing.pathGeneration.MathFunctions;
import org.firstinspires.ftc.teamcode.PedroPathing.pathGeneration.Vector;

import java.util.ArrayList;
import java.util.List;

public class Drivetrain {
    public Pose2d currentPos;

    protected DcMotorEx leftFront;
    protected DcMotorEx rightFront;
    protected DcMotorEx leftRear;
    protected DcMotorEx rightRear;

    private Vector driveVector;
    private Vector headingVector;
    private Follower follower;

    private IMU imu;
    private VoltageSensor batteryVoltageSensor;

    private List<Integer> lastEncPositions = new ArrayList<>();
    private List<Integer> lastEncVels = new ArrayList<>();


    public Drivetrain(HardwareMap hardwareMap){
        follower = new Follower(hardwareMap, false);

        driveVector = new Vector();
        headingVector = new Vector();

        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightRear.setDirection(DcMotorEx.Direction.REVERSE); // rightRear, rightFront reverse for comp bot
        rightFront.setDirection(DcMotorEx.Direction.REVERSE); // rightRear, leftRear reverse for test bot
    }

    public void init(Pose2d startPose) {
        currentPos = startPose;
    }
    public void update(double x, double y){
        driveVector.setOrthogonalComponents(-y, -x);
        driveVector.setMagnitude(MathFunctions.clamp(driveVector.getMagnitude(), 0, 1));
        driveVector.rotateVector(follower.getPose().getHeading());

        headingVector.setComponents(-x, follower.getPose().getHeading());

        follower.setMovementVectors(follower.getCentripetalForceCorrection(), headingVector, driveVector);
        follower.update();
    }
    public void telemetry(){
        Setup.telemetry.addData("Drivetrain currentPos", currentPos);
    }

    public double getHeadingIMU() {
        return 0;
    }
    public void setCurrentPose(Pose2d pose) {
        currentPos = pose;
    }
    public Pose2d getCurrentPose() {
        return currentPos;
    }
}
