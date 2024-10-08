//package org.firstinspires.ftc.teamcode.PedroPathing.localization.rr;//package org.firstinspires.ftc.teamcode.pedroPathing.localization;
//
//import androidx.annotation.NonNull;
//
//import com.acmerobotics.dashboard.config.Config;
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.acmerobotics.roadrunner.localization.ThreeTrackingWheelLocalizer;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import java.util.Arrays;
//import java.util.List;
//
///*
// * Sample tracking wheel localizer implementation assuming the standard configuration:
// *
// * left on robot is y pos
// *
// * front of robot is x pos
// *
// *    /--------------\
// *    |     ____     |
// *    |     ----     |
// *    | ||        || |
// *    | ||        || |
// *    |              |
// *    |              |
// *    \--------------/
// *
// */
//
///**
// * This class is adapted from the Road Runner StandardTrackingWheelLocalizer class. Later, this will
// * be replaced with a custom localizer. I made some minor changes, so I'm crediting myself as an
// * 'author' of sorts, but really this is pretty much Road Runner's code, just moved to be local to
// * Pedro Pathing to avoid having imports.
// *
// * @author Road Runner dev team
// * @author Anyi Lin - 10158 Scott's Bots
// * @version 1.0, 5/9/2024
// */
//@Config
//public class RoadRunnerThreeWheelLocalizer extends ThreeTrackingWheelLocalizer {
//    public static double TICKS_PER_REV = 8192;
//    public static double WHEEL_RADIUS = 0.6889; // in
//    public static double GEAR_RATIO = 1; // output (wheel) speed / input (encoder) speed
//
//    public static double X_MULTIPLIER = 1;
//    public static double Y_MULTIPLIER = 1;
//
//    public static double LATERAL_DISTANCE = 12.3; // in; distance between the left and right wheels
//    public static double FORWARD_OFFSET = -3.25; // in; offset of the lateral wheel
//
//    private RoadRunnerEncoder leftEncoder, rightEncoder, strafeEncoder;
//
//    private List<Integer> lastEncPositions, lastEncVels;
//
//    public RoadRunnerThreeWheelLocalizer(HardwareMap hardwareMap, List<Integer> lastTrackingEncPositions, List<Integer> lastTrackingEncVels) {
//        super(Arrays.asList(
//                new Pose2d(0, -LATERAL_DISTANCE / 2, 0), // left
//                new Pose2d(0, LATERAL_DISTANCE / 2, 0), // right
//                new Pose2d(FORWARD_OFFSET, 0, Math.toRadians(90)) // front
//        ));
//
//        lastEncPositions = lastTrackingEncPositions;
//        lastEncVels = lastTrackingEncVels;
//
//        // TODO: redo the configs here
//        leftEncoder = new RoadRunnerEncoder(hardwareMap.get(DcMotorEx.class, "rightRear"));
//        rightEncoder = new RoadRunnerEncoder(hardwareMap.get(DcMotorEx.class, "intakeMotor"));
//        strafeEncoder = new RoadRunnerEncoder(hardwareMap.get(DcMotorEx.class, "leftFront"));
//
//        // TODO: reverse any encoders using Encoder.setDirection(Encoder.Direction.REVERSE)
//        rightEncoder.setDirection(RoadRunnerEncoder.Direction.REVERSE);
//        strafeEncoder.setDirection(RoadRunnerEncoder.Direction.FORWARD);
//    }
//
//    public void resetHeading(double heading) {
//        setPoseEstimate(new Pose2d(getPoseEstimate().getX(), getPoseEstimate().getY(), heading));
//    }
//
//    public static double encoderTicksToInches(double ticks) {
//        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
//    }
//
//    @NonNull
//    @Override
//    public List<Double> getWheelPositions() {
//        int leftPos = leftEncoder.getCurrentPosition();
//        int rightPos = rightEncoder.getCurrentPosition();
//        int frontPos = strafeEncoder.getCurrentPosition();
//
//        lastEncPositions.clear();
//        lastEncPositions.add(leftPos);
//        lastEncPositions.add(rightPos);
//        lastEncPositions.add(frontPos);
//
//        return Arrays.asList(
//                encoderTicksToInches(leftPos) * X_MULTIPLIER,
//                encoderTicksToInches(rightPos) * X_MULTIPLIER,
//                encoderTicksToInches(frontPos) * Y_MULTIPLIER
//        );
//    }
//
//    @NonNull
//    @Override
//    public List<Double> getWheelVelocities() {
//        int leftVel = (int) leftEncoder.getCorrectedVelocity();
//        int rightVel = (int) rightEncoder.getCorrectedVelocity();
//        int frontVel = (int) strafeEncoder.getCorrectedVelocity();
//
//        lastEncVels.clear();
//        lastEncVels.add(leftVel);
//        lastEncVels.add(rightVel);
//        lastEncVels.add(frontVel);
//
//        return Arrays.asList(
//                encoderTicksToInches(leftVel) * X_MULTIPLIER,
//                encoderTicksToInches(rightVel) * X_MULTIPLIER,
//                encoderTicksToInches(frontVel) * Y_MULTIPLIER
//        );
//    }
//}
