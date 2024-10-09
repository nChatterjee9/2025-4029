package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.Range;


@TeleOp
public class CombinedTest extends LinearOpMode {

    private int mode = 1;

    private double servoTargetPosition;
    private final double multiplier = 0.001;
    private int servoMode = 1;
    //    private int countTest = 0;
//    private int aCountTest = 0;
//    private int modeN1Time = 0;
    private final int numServos = 1;
    private Servo[] servo;
    private ServoController controller;
    private int currentServo = 0;

    private int altCounter = 0;

    private final int motorIncrement = 10000;
    private int motorMode = 1;
    private final int numMotors = 1;
    private double motorVelocity = 1;
    private double motorTargetPosition;
    private DcMotor[] motor;
    private int currentMotor = 0;

    @Override
    public void runOpMode(){

        servo = new Servo[numServos];
        for(int i = 0; i<servo.length; i++) {
            servo[i] = hardwareMap.get(Servo.class, "servo" + i);
        }


        motor = new DcMotor[numMotors];
        for(int i = 0; i<motor.length; i++){
            motor[i] = hardwareMap.get(DcMotor.class, "motor"+i);
            motor[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

//        controller = new ServoController()

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()){

//            countTest+=1;

            if(gamepad1.y){
                if(altCounter<1000) {
                    mode *= -1;
                    motorMode = 1;
                    servoMode = 1;
                    altCounter=0;
                }
                altCounter++;
            }
            else if(!gamepad1.y && !gamepad1.x){
                altCounter=0;
            }

            telemetry.addData("CurrentMode", mode);

            if(mode == 1){

//                Motor Mode
                if(gamepad1.x){
                    if(altCounter > 1000) {
                        motorMode *= -1;
                        altCounter = 0;
                    }
                    altCounter++;
                }

//                Current Motor
                if(gamepad1.left_bumper){
                    currentMotor -=1;
                    currentMotor %= numMotors;
                }
                if(gamepad1.right_bumper){
                    currentMotor -=1;
                    currentMotor %= numMotors;
                }

//                Velocity
                if (gamepad1.right_stick_y < -0.4) {
                    motorVelocity = Range.clip(motorVelocity * (1 / 0.999), -1, 1);
                }
                if (gamepad1.right_stick_y > 0.4) {
                    motorVelocity = motorVelocity * 0.999;
                }

//                Target Position
                if(Math.abs(gamepad1.left_stick_y) > 0.05){
                    motorTargetPosition -= gamepad1.left_stick_y/motorIncrement;
                }

//                Run
                if(motorMode == 1){
                    if(gamepad1.a){

                            motor[currentMotor].setTargetPosition((int)motorTargetPosition);
                            motor[currentMotor].setMode(DcMotor.RunMode.RUN_TO_POSITION);
                            motor[currentMotor].setPower(motorVelocity);

                    } else {
                        motor[currentMotor].setPower(0);
                    }
                }
                else{
                    motor[currentMotor].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    motor[currentMotor].setPower(motorVelocity);
                }

//                Telemetry
                telemetry.addData("Velocity", motorVelocity);
                telemetry.addData("TargetPosition", motorTargetPosition);
                telemetry.addData("MotorMode", motorMode);
                telemetry.addData("CurrentMotor", currentMotor);
                telemetry.addData("NumberOfMotors", numMotors);


            } else {

//                Servo Mode
                if(gamepad1.x){
                    if(altCounter > 1000) {
                        servoMode *= -1;
                        altCounter = 0;
                    }
                    altCounter++;
                }

//                Current Servo
                if(gamepad1.left_bumper){
                    currentServo -=1;
                    currentServo %= numServos;
                }
                if(gamepad1.right_bumper){
                    currentServo -=1;
                    currentServo %= numServos;
                }

//                Target Position
                if(Math.abs(gamepad1.left_stick_y) > 0.05){
                    servoTargetPosition += gamepad1.left_stick_y*multiplier;
                    if(servoTargetPosition > 1){
                        servoTargetPosition = 1;
                    }
                    else if(servoTargetPosition < 0){
                        servoTargetPosition = 0;
                    }
                }

//                Run
                if(servoMode == 1) {
                    if (gamepad1.a) {
                        servo[currentServo].setPosition(servoTargetPosition);
//                    aCountTest+=1;
                    }
                }
                else{
                    servo[currentServo].setPosition(servoTargetPosition);
//                modeN1Time+=1;
                }

//                Telemetry
//            telemetry.addData("aWasPressed", aCountTest);
//            telemetry.addData("modeN1Time", modeN1Time);
                telemetry.addData("TargetPosition", servoTargetPosition*100 + "%");
                telemetry.addData("CurrentPosition", servo[currentServo].getPosition());
                telemetry.addData("CurrentMode", servoMode);

            }

//            telemetry.addData("CountTest", countTest);

            telemetry.update();
        }

    }
}
