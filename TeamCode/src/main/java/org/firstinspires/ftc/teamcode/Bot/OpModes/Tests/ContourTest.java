package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Bot.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Bot.Sensors.Vision.Pipelines.Contour;
import org.firstinspires.ftc.teamcode.Bot.Setup;
import org.firstinspires.ftc.teamcode.PedroPathing.localization.Pose;
import org.opencv.core.Point;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;
@TeleOp(name = "Contour vision test", group = "1")
public class ContourTest extends LinearOpMode {
    private Point centerPoint;

    private final double turnSpeed = 0.25;

    @Override
    public void runOpMode() throws InterruptedException {
        Contour contour = new Contour();
        contour.init();
        int cameraMoniterViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        OpenCvWebcam webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "webcam"), cameraMoniterViewId);
//        webcam.setMillisecondsPermissionTimeout(2500);
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
        webcam.setPipeline(contour);
        Drivetrain drivetrain = new Drivetrain();
        Setup setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.TELEOP, Setup.Team.Q1);
        drivetrain.init(new Pose());
        while(opModeInInit()){
            moveBot(contour, drivetrain);
        }
        webcam.closeCameraDeviceAsync(() -> {
            webcam.stopStreaming();
        });
    }

    private void moveBot(Contour contour, Drivetrain drivetrain){
        centerPoint = contour.getCenterPoint();
        int distance = (int)centerPoint.x - 1280/2;
        if(Math.abs(distance) > 20) {
            drivetrain.setTeleOpTargets(0, 0, -distance / 640.0 * turnSpeed);
            telemetry.addLine("Center Point: " + -distance / 640.0);
            telemetry.update();
        } else {
            drivetrain.setTeleOpTargets(0, 0, 0);
        }
        drivetrain.update(false);


    }
}
