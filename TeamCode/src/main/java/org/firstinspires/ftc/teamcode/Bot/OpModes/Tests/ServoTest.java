package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.Range;

import java.lang.annotation.Target;

@TeleOp
public class ServoTest extends LinearOpMode {
    private double targetPosition;
    private final double multiplier = 0.0001;
    private int mode = 1;
//    private int countTest = 0;
//    private int aCountTest = 0;
//    private int modeN1Time = 0;
    private Servo servo;
    private ServoController controller;

//    Going to 0 will go clockwise
//    Going to 1 will go counter-clockwise
    @Override
    public void runOpMode(){

        servo = hardwareMap.get(Servo.class, "servo");
//        controller = new ServoController()

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        servo.setPosition(0);

        while(opModeIsActive()){
//            countTest+=1;
            if(Math.abs(gamepad1.left_stick_y) > 0.05){
                targetPosition += gamepad1.left_stick_y*multiplier;
                if(targetPosition > 1){
                    targetPosition = 1;
                }
                else if(targetPosition < 0){
                    targetPosition = 0;
                }

            }
            if(mode == 1) {
                if (gamepad1.a) {
                    servo.setPosition(targetPosition);
//                    aCountTest+=1;
                }
            }
            else{
                servo.setPosition(targetPosition);
//                modeN1Time+=1;
            }
            if(gamepad1.x){
                mode*=-1;
            }

//            telemetry.addData("aWasPressed", aCountTest);
//            telemetry.addData("modeN1Time", modeN1Time);
            telemetry.addData("TargetPosition", targetPosition*100 + "%");
            telemetry.addData("CurrentPosition", servo.getPosition());
            telemetry.addData("CurrentMode", mode);
//            telemetry.addData("CountTest", countTest);

            telemetry.update();
        }

    }
}
