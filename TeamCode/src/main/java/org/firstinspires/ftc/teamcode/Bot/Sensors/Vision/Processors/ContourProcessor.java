package org.firstinspires.ftc.teamcode.Bot.Sensors.Vision.Processors;

import android.graphics.Canvas;

import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class ContourProcessor implements VisionProcessor {

    private boolean isBlue;

    private Mat HSV;
    private Mat red;
    private Mat red2;
    private Mat blue;

    private Mat dummy;

    private Scalar upperRed = new Scalar(15,255,255);
    private Scalar lowerRed = new Scalar(0,50,50);
    private Scalar upperRed2 = new Scalar(180,255,255);
    private Scalar lowerRed2 = new Scalar(170,50,100);
    private Scalar upperBlue = new Scalar(142,211,186);
    private Scalar lowerBlue = new Scalar(82,130,31);

    private Scalar outlines = new Scalar(0, 0, 255);

    private Point centerPoint = new Point(640,360);
    private double minBoxSize = 1000.0;

    private int currentArrayPos;
    private int biggestBox;
    private double biggestBoxSize;
    private double currentArea;

    private List<MatOfPoint> contours = new ArrayList<>();



    @Override
    public void init(int width, int height, CameraCalibration calibration){
        red = new Mat();
        red2 = new Mat();
        blue = new Mat();
        HSV = new Mat();
        dummy = new Mat();
    }
    @Override
    public Mat processFrame (Mat input, long captureTimeNanos){
        if(input == null) return null;

        Imgproc.cvtColor(input, HSV, Imgproc.COLOR_RGB2HSV);

        if(isBlue) {
            Core.inRange(HSV, lowerBlue, upperBlue, blue);
        } else {
            Core.inRange(HSV, lowerRed, upperRed, red);
            Core.inRange(HSV, lowerRed2, upperRed2, red2);

            Core.add(red, red2, red);
        }
        if(isBlue){
            Imgproc.findContours(blue, contours, dummy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        } else {
            Imgproc.findContours(red, contours, dummy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        }
        if(!contours.isEmpty()) {
            currentArrayPos = 0;
            biggestBox = 0;
            biggestBoxSize = -1;
            currentArea = -1;
            for (MatOfPoint contour : contours) {
                currentArea = Imgproc.contourArea(contour);
                if (currentArea > biggestBoxSize && currentArea > minBoxSize) {
                    biggestBox = currentArrayPos;
                    biggestBoxSize = currentArea;
                }
                currentArrayPos++;
            }
            Imgproc.cvtColor(HSV, input, Imgproc.COLOR_HSV2RGB);
        } else {
            centerPoint = new Point(-1, -1);
        }
        return input;
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpToCanvasPx, float scaleCanvasDensity, Object userContext){
        if(biggestBoxSize != -1) {
            Rect boundingRect = Imgproc.boundingRect(contours.get(biggestBox));
            Imgproc.putText(HSV, "Detected Area: " + biggestBoxSize, new Point(boundingRect.tl().x, boundingRect.tl().y + boundingRect.height), 2, 1, outlines);
            Imgproc.rectangle(HSV, boundingRect.tl(), boundingRect.br(), outlines, 2);
            centerPoint = new Point(boundingRect.tl().x + boundingRect.width / 2, boundingRect.tl().y + boundingRect.height / 2);
            Imgproc.circle(HSV, centerPoint, 10, outlines, 2);

        } else {
            centerPoint = new Point(-1, -1);
        }
    }
}
