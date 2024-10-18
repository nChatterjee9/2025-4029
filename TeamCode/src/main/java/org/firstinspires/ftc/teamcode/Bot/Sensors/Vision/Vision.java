package org.firstinspires.ftc.teamcode.Bot.Sensors.Vision;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Bot.InitStates.HardwareStates;
import org.firstinspires.ftc.teamcode.Bot.Sensors.Vision.Processors.ColorProcessor;
import org.firstinspires.ftc.teamcode.Bot.Sensors.Vision.Processors.ContourProcessor;
import org.firstinspires.ftc.teamcode.Bot.Setup;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.HashMap;

public class Vision {
    private HashMap<String, HardwareStates> visionStates;
    public AprilTagProcessor aprilTag;
    public ColorProcessor colorProcessor;
    public ContourProcessor contourProcessor;

    public VisionPortal visionPortal;

    public Vision(HashMap<String, HardwareStates> sensorStates){
        this.visionStates = sensorStates;
        aprilTag = null;
        visionPortal = null;
        colorProcessor = null;
    }

    public void init(){
        if(this.visionStates.get("webcam").isEnabled){
//            aprilTag = new AprilTagProcessor.Builder().build();
//            colorProcessor = new ColorProcessor();
            contourProcessor = new ContourProcessor();
            visionPortal = new VisionPortal.Builder()
                    .setCamera(Setup.hardwareMap.get(WebcamName.class, "webcam"))
                    .addProcessors(contourProcessor)
                    .build();


        }
    }

    public void telemetry(){

    }
}
