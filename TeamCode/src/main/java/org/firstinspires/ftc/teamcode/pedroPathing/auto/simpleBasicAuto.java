package org.firstinspires.ftc.teamcode.pedroPathing.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierLine;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathBuilder;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathChain;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;

public class simpleBasicAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
       Follower follower = new Follower(hardwareMap);
       PathChain pb = follower.pathBuilder()
                   .addPath(new BezierCurve(new Point(0,0,Point.CARTESIAN), new Point(24, 24, Point.CARTESIAN), new Point(0, 12, Point.CARTESIAN)))
//                   .setConstantHeadingInterpolation();
                   .addPath(new BezierLine(new Point(0, 12, Point.CARTESIAN), new Point(12, 12, Point.CARTESIAN)))
                    .addPath(new BezierLine(new Point(12,12,Point.CARTESIAN), new Point (0,0,Point.CARTESIAN)))
//                   .setConstantHeadingInterpolation(firstCycleStackPose.getHeading())
//                   .setPathEndTimeoutConstraint(0)
                   .build();
       follower.followPath(pb);
       telemetry.addLine("Paths built");
       telemetry.update();
       waitForStart();
       telemetry.addLine("Starting");
       while(!follower.atParametricEnd() && opModeIsActive() && !isStopRequested()){
           follower.update();
           telemetry.addLine("Following Paths");
           telemetry.update();
       }

    }
}
