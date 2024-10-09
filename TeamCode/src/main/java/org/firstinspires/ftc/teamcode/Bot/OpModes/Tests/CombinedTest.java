//package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.ServoController;
//import com.qualcomm.robotcore.util.Range;
//
//import java.lang.annotation.Target;
//
//
//@TeleOp
//public class CombinedTest extends LinearOpMode {
//    private double targetPosition;
//    private final double multiplier = 0.001;
//    private int mode = 1;
//    //    private int countTest = 0;
////    private int aCountTest = 0;
////    private int modeN1Time = 0;
//    private Servo servo;
//    private Servo[] servo;
//    private ServoController controller;
//
//    @Override
//    public void runOpMode(){
//
//        servo = hardwareMap.get(Servo.class, "servo");
//        combined = new Object[2];
//        int servoCounter = 0;
//        int motorCounter = 0;
//        for(int i = 0; i<combined.length; i++){
//            if(combined[i] instanceof Servo){
//                combined[i] = hardwareMap.get(Servo.class, "servo"+servoCounter);
//            }
//            else if(combined[i] instanceof DcMotor){
//                combined[i] = hardwareMap.get(DcMotor.class, "motor"+motorCounter);
//                combined[i]).setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE;
//                ((DcMotor) combined[i]).setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            }
//        }
////        controller = new ServoController()
//
//        telemetry.addData("Status", "Initialized");
//        telemetry.update();
//
//        waitForStart();
//
//        while(opModeIsActive()){
////            countTest+=1;
//            if(Math.abs(gamepad1.left_stick_y) > 0.05){
//                targetPosition += gamepad1.left_stick_y*multiplier;
//                if(targetPosition > 1){
//                    targetPosition = 1;
//                }
//                else if(targetPosition < 0){
//                    targetPosition = 0;
//                }
//
//            }
//            if(mode == 1) {
//                if (gamepad1.a) {
//                    servo.setPosition(targetPosition);
////                    aCountTest+=1;
//                }
//            }
//            else{
//                servo.setPosition(targetPosition);
////                modeN1Time+=1;
//            }
//            if(gamepad1.x){
//                mode*=-1;
//            }
//
////            telemetry.addData("aWasPressed", aCountTest);
////            telemetry.addData("modeN1Time", modeN1Time);
//            telemetry.addData("TargetPosition", targetPosition*100 + "%");
//            telemetry.addData("CurrentPosition", servo.getPosition());
//            telemetry.addData("CurrentMode", mode);
////            telemetry.addData("CountTest", countTest);
//
//            telemetry.update();
//        }
//
//    }
//}
