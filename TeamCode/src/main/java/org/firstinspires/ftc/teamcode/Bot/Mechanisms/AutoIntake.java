package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import static org.firstinspires.ftc.teamcode.Bot.Setup.hardwareMap;
import static org.firstinspires.ftc.teamcode.Bot.Setup.telemetry;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Bot.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Bot.Sensors.Vision.Pipelines.Contour;
import org.opencv.core.Point;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

public class AutoIntake {

    private OpenCvCamera webcam;
    private boolean isBlue;
    private Drivetrain drivetrain;
    private Contour pipeline;
    //0 IS IN INTAKE, 1 IS AT TRANSFER
    private boolean[] intakeSensors = new boolean[2];
    private Point centerPoint;
    private boolean properInitialization = true;

    private final double turnSpeed = 0.25;
    private final double moveSpeed = 0.05;
    private final double intakeSpeed = 0.1;

    public AutoIntake(boolean isBlue, String webcamName, Drivetrain drivetrain){
        int cameraMoniterViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        OpenCvWebcam webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "webcam"), cameraMoniterViewId);
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
                properInitialization = false;
                telemetry.addLine("WEBCAM ERROR!!! Code: " + errorCode);
            }
        });
        webcam.setPipeline(pipeline);
        //todo INTAKE SENSORS
    }

    public boolean intake(){
        if(properInitialization) {
            if (intakeSensors[1]) return true;
            centerPoint = pipeline.getCenterPoint();
            moveBot(centerPoint, drivetrain);
            return false;
        } else {
            telemetry.addLine("WEBCAM INITIALIZATION ERROR!");
            return true;
        }
    }

    private void moveBot(Point centerPoint, Drivetrain drivetrain){
        int distanceRot = (int)centerPoint.x - 1280/2;
        int distance = (int)centerPoint.y - 720/2;
        if(Math.abs(distanceRot) > 20) {
            intakeMechanism(-1);
            drivetrain.setTeleOpTargets(0, 0, -distanceRot / 640.0 * turnSpeed);
            telemetry.addLine("Center Point: " + -distanceRot / 640.0);
            telemetry.update();
        } else if(Math.abs(distance) > 20){
            intakeMechanism(-1);
            drivetrain.localMovement(distance / 360.0 * moveSpeed, 0);
        } else if(intakeSensors[0]){
            intakeMechanism(0);
            drivetrain.setTeleOpTargets(0,0,0);
        } else {
            intakeMechanism(1);
            drivetrain.localMovement(intakeSpeed,0);
        }
        drivetrain.update(false);
    }

    private void detectIntake(){
        //todo INTAKE SENSORS
    }

    private void intakeMechanism(int intake){
        if((intakeSensors[0] && !intakeSensors[1]) || intake > 0){
            //todo INTAKE ON
        } else if(intakeSensors[1] || intake < 0){
            //todo INTAKE OFF
        }
    }
}
