package org.firstinspires.ftc.teamcode.PedroPathing.localization;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.PedroPathing.pathGeneration.MathFunctions;
import org.firstinspires.ftc.teamcode.PedroPathing.pathGeneration.Vector;
import org.firstinspires.ftc.teamcode.PedroPathing.util.NanoTimer;

import java.util.Collections;
import java.util.List;

public class ThreeWheelCustom extends Localizer{

    private HardwareMap hardwareMap;
    private Pose startPose;
    private Pose displacementPose;
    private Pose currentVelocity;
    private Matrix prevRotationMatrix;
    private NanoTimer timer;
    private long deltaTimeNano;
    private Encoder leftEncoder;
    private Encoder rightEncoder;
    private Encoder strafeEncoder;
    private Pose leftEncoderPose;
    private Pose rightEncoderPose;
    private Pose strafeEncoderPose;
    private double totalHeading;
    public static double FORWARD_TICKS_TO_INCHES = 0.005;
    public static double STRAFE_TICKS_TO_INCHES = 0.005;
    public static double TURN_TICKS_TO_RADIANS = 0.005;

    public ThreeWheelCustom(HardwareMap map){
        this(map, new Pose());
    }
    public ThreeWheelCustom(HardwareMap map, Pose startPose){
        leftEncoderPose = new Pose(0, 12.3/2, Math.toRadians(90));
        rightEncoderPose = new Pose(0, -12.3/2, Math.toRadians(90));
        strafeEncoderPose = new Pose(-3.25, 0, 0);

        hardwareMap = map;

        leftEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "rightRear"));
        rightEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "intakeMotor"));
        strafeEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "leftFront"));

        leftEncoder.setDirection(Encoder.FORWARD);
        rightEncoder.setDirection(Encoder.REVERSE);
        strafeEncoder.setDirection(Encoder.FORWARD);

        setStartPose(startPose);

        timer = new NanoTimer();
        deltaTimeNano = 1;
        displacementPose = new Pose();
        currentVelocity = new Pose();
        totalHeading = 0;
    }
    @Override
    public Pose getPose() {
        return MathFunctions.addPoses(startPose, displacementPose);
    }

    @Override
    public Pose getVelocity() {
        return currentVelocity.copy();
    }

    @Override
    public Vector getVelocityVector() {
        return currentVelocity.getVector();
    }

    @Override
    public void setStartPose(Pose setStart) {
        startPose = setStart;
    }

    @Override
    public void setPose(Pose setPose) {
        displacementPose = MathFunctions.subtractPoses(setPose, startPose);
        resetEncoders();
    }

    @Override
    public void update() {

    }

    @Override
    public double getTotalHeading() {
        return 0;
    }

    @Override
    public double getForwardMultiplier() {
        return 0;
    }

    @Override
    public double getLateralMultiplier() {
        return 0;
    }

    @Override
    public double getTurningMultiplier() {
        return 0;
    }

    @Override
    public List<Double> getWheelPositions() {
        return Collections.emptyList();
    }
}
