package org.firstinspires.ftc.teamcode.Bot;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.MotorExample;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.RunToPosMotorExample;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.ServoExample;
import org.firstinspires.ftc.teamcode.Bot.Sensors.Sensors;
import org.firstinspires.ftc.teamcode.Bot.InitStates.HardwareStates;
import org.firstinspires.ftc.teamcode.PedroPathing.localization.Pose;

import java.util.HashMap;

public class Bot implements Robot{
    public Drivetrain drivetrain;
    public Mechanism motorMech, servoMech, slideMech;
    public Sensors sensors;

    public Bot(HashMap<String, HardwareStates> hardwareStates, HashMap<String, HardwareStates> sensorStates){
        /*
        Bot constructor creates all mechanisms in Mechanism objects if they are enabled
         */
        if(hardwareStates.get("motorMech").isEnabled){
            motorMech = new Mechanism("motorMech"); //todo, replace
        } else {
            motorMech = new Mechanism("motorMech");
        }
        if(hardwareStates.get("servoMech").isEnabled){
            servoMech = new Mechanism("servoMech"); //todo, replace
        } else {
            servoMech = new Mechanism("servoMech");
        }
        if(hardwareStates.get("servoMech").isEnabled){
            slideMech = new Mechanism("slideMech"); //todo, replace
        } else {
            slideMech = new Mechanism("slideMech");
        }
        drivetrain = new Drivetrain();
    }
    public void initDrivetrain(Pose pose){
        drivetrain.init(pose);
    }

    @Override
    public void init(){
        /*
        Initialize mechanisms here
        */
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        motorMech.init(0);
        servoMech.init(0);
        slideMech.init(0);
//        drivetrain.init();
    }

    @Override
    public void update(){
        /*
        Updates mechanisms
        */
        motorMech.update();
        servoMech.update();
        slideMech.update();
//        drivetrain.update();
    }

    @Override
    public void telemetry(){
        motorMech.telemetry();
        servoMech.telemetry();
        slideMech.telemetry();
        drivetrain.telemetry();
    }

    @Override
    public boolean isBusy(){
        return motorMech.isBusy() || servoMech.isBusy() || slideMech.isBusy();
    }

}
