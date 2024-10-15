package org.firstinspires.ftc.teamcode.Bot.Sensors.Vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Bot.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Bot.Setup;
import org.firstinspires.ftc.teamcode.PedroPathing.localization.Pose;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

public class Camera {
    private boolean isBlue;
    private String camName;
    private OpenCvPipeline pipeline;
    private HardwareMap hardwareMap;
    private OpenCvWebcam webcam;
    private int cameraMoniterViewId;
    public Camera(String cameraName, String teamColor, OpenCvPipeline startingPipeline, HardwareMap hardwareMap){
        camName = cameraName;
        if(teamColor.toUpperCase().equals("BLUE")){
            isBlue = true;
        } else {
            isBlue = false;
        }
        pipeline = startingPipeline;
        this.hardwareMap = hardwareMap;
        cameraMoniterViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
    }
    public Camera(String teamColor, HardwareMap hardwareMap){
        camName = "webcam";
        if(teamColor.toUpperCase().equals("BLUE")){
            isBlue = true;
        } else {
            isBlue = false;
        }
        pipeline = null;
        this.hardwareMap = hardwareMap;
        cameraMoniterViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
    }
    public void init(){
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, camName), cameraMoniterViewId);
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


    }
    public void setPipeline(OpenCvPipeline newPipeline){
        webcam.setPipeline(newPipeline);
        pipeline = newPipeline;
    }
    public OpenCvPipeline getPipeline(){
        return pipeline;
    }
    public void close(){
        webcam.closeCameraDeviceAsync(() -> {
            webcam.stopStreaming();
        });
    }

}
