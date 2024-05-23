package org.firstinspires.ftc.teamcode.pedroPathing.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.Servo;

//import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.ServoMechanism;
//import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeServo;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierLine;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathBuilder;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathChain;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;
@Autonomous
public class simpleBasicAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
//       Servo intake = hardwareMap.get(Servo.class,"intakeServo");
       Follower follower = new Follower(hardwareMap);
       PathChain pb = follower.pathBuilder()
                   .addPath(new BezierCurve(new Point(0,0,Point.CARTESIAN), new Point(24, 24, Point.CARTESIAN), new Point(0, 12, Point.CARTESIAN)))
//                   .setConstantHeadingInterpolation()

                   .addPath(new BezierLine(new Point(0, 12, Point.CARTESIAN), new Point(12, 12, Point.CARTESIAN)))
                    .addPath(new BezierLine(new Point(12,12,Point.CARTESIAN), new Point (0,0,Point.CARTESIAN)))
//                   .setConstantHeadingInterpolation(firstCycleStackPose.getHeading())
//                   .setPathEndTimeoutConstraint(0)
                   .build();
       follower.followPath(pb);
       telemetry.addLine("Path 1 built");
       telemetry.update();
       waitForStart();
       telemetry.addLine("Starting");
       while(!follower.atParametricEnd() && opModeIsActive() && !isStopRequested()){
           follower.update();
           if(follower.getCurrentPathNumber() == 1){
//               intake.setPosition(IntakeServo.POS_STACK_6);
           } else if(follower.getCurrentPathNumber() == 2){
//               intake.setPosition(IntakeServo.POS_TELE_OP);
           } else {
//               intake.setPosition(IntakeServo.POS_EJECT);
           }
           telemetry.addLine("Following Path 1, subset: " + follower.getCurrentPath());
           telemetry.update();
       }
       PathChain nextPath = follower.pathBuilder()
               .addPath(new BezierCurve(new Point(0,0,Point.CARTESIAN), new Point(24,Math.toRadians(90),Point.POLAR), new Point(12,Math.toRadians(190),Point.POLAR)))
               .setConstantHeadingInterpolation(Math.toRadians(120))
               .addPath(new BezierCurve(new Point(12, Math.toRadians(180), Point.POLAR), new Point(0,0,Point.POLAR), new Point(12,Math.toRadians(-180), Point.POLAR)))
               .setConstantHeadingInterpolation(Math.toRadians(180))
               .build();
        follower.followPath(nextPath);
        telemetry.addLine("NEXT PATH");
       while(!follower.atParametricEnd() && opModeIsActive() && !isStopRequested()){
            follower.update();
            if(follower.getCurrentPathNumber() == 1){
//                intake.setPosition(IntakeServo.POS_STACK_6);
            } else if(follower.getCurrentPathNumber() == 2){
//                intake.setPosition(IntakeServo.POS_TELE_OP);
            }
            telemetry.addLine("Following Path 2, subset: " + follower.getCurrentPath());
            telemetry.update();
        }

    }
}
