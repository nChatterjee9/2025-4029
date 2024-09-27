package org.firstinspires.ftc.teamcode.Bot.Sensors.Vision.Pipelines;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class Contour extends OpenCvPipeline {

    public boolean isBlue = false;

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

    private Point centerPoint = new Point(640,360);
    private double minBoxSize = 1000.0;

    public void init(){
        HSV = new Mat();
        red = new Mat();
        red2 = new Mat();
        blue = new Mat();
        dummy = new Mat();
    }
    @Override
    public Mat processFrame(Mat input) {
        if(input == null) return null;
        Imgproc.cvtColor(input, HSV, Imgproc.COLOR_RGB2HSV);

        Core.inRange(HSV, lowerBlue, upperBlue, blue);
        Core.inRange(HSV, lowerRed, upperRed, red);
        Core.inRange(HSV, lowerRed2, upperRed2, red2);

        Core.add(red, red2, red);

        List<MatOfPoint> contours = new ArrayList<>();
        if(isBlue){
            Imgproc.findContours(blue, contours, dummy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        } else {
            Imgproc.findContours(red, contours, dummy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        }
        if(contours.size() != 0) {
            int currentArrayPos = 0;
            int biggestBox = 0;
            int biggestBoxSize = -1;
            Rect boundingRect;

            for (MatOfPoint contour : contours) {
                boundingRect = Imgproc.boundingRect(contour);

                if (boundingRect.height * boundingRect.width > biggestBoxSize && boundingRect.height * boundingRect.width > minBoxSize) {
                    biggestBox = currentArrayPos;
                    biggestBoxSize = boundingRect.height * boundingRect.width;
                }

                currentArrayPos++;
            }

            boundingRect = Imgproc.boundingRect(contours.get(biggestBox));
            Imgproc.rectangle(HSV, boundingRect.tl(), boundingRect.br(), new Scalar(0, 0, 255), 2);
            centerPoint = new Point(boundingRect.tl().x + boundingRect.width / 2, boundingRect.tl().y + boundingRect.height / 2);
            Imgproc.circle(HSV, centerPoint, 10, new Scalar(0, 0, 255), 2);

            Imgproc.cvtColor(HSV, input, Imgproc.COLOR_HSV2RGB);

        } else {
            centerPoint = new Point(640, 360);
        }
        return input;
    }

    public Point getCenterPoint(){
        return centerPoint;
    }

}
