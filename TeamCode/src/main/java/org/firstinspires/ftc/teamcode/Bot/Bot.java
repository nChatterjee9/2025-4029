package org.firstinspires.ftc.teamcode.Bot;

import static org.firstinspires.ftc.teamcode.Bot.Setup.telemetry;

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
        telemetry.addLine("BOTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
//        if(hardwareStates.get("intakeMotor").isEnabled){
//            motorMech = new Mechanism("intakeMotor"); //todo, replace
//        } else {
//            motorMech = new Mechanism("intakeMotor");
//        }
        drivetrain = new Drivetrain();
        init();

    }
    public void initDrivetrain(Pose pose){
        if(drivetrain != null) {
            drivetrain.init(pose);
        } else {
//            telemetry.addLine("");
        }
    }
    public void drivetrainUpdate(boolean usePeP){
        drivetrain.update(usePeP);
    }
    @Override
    public void init(){
        /*
        Initialize mechanisms here
        */
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
//        motorMech.init(0);
        initDrivetrain(new Pose());
//        servoMech.init(0);
//        slideMech.init(0);
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
    public void setTargetVectors(double x, double y, double theta){
        drivetrain.setTargetVectors(x,y,theta);
    }
    public void setTeleOpTargets(double left_stick_x, double left_stick_y, double right_stick_x){
        drivetrain.setTeleOpTargets(left_stick_x, left_stick_y, right_stick_x);
    }

}
