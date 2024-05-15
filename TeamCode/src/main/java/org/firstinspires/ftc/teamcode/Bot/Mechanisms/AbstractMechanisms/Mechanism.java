package org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot.Setup;
public abstract class Mechanism {
    protected String name;
    public double targetPos = 0;
    protected double currentPos = 0;
    protected double velocity = 0;
    protected ElapsedTime timer;
    protected double zero = 0;
    protected double timeLimit;

    public Mechanism(String name) {
        this.name = name;
        timer = new ElapsedTime();
    }
    public void init(double target){
        setTarget(target);
    }
    public void setTarget(double target) {targetPos = target + zero;}
    public double getCurrentPosition() {
        return currentPos - zero;
    }
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
    public void startTimer(double time) {
        timeLimit = time;
        timer.reset();
    }
    public void reset(){
        zero = getCurrentPosition();
    }
    public void telemetry(){
        Setup.telemetry.addData(name + " currentPos",currentPos - zero);
        Setup.telemetry.addData(name + " targetPos",targetPos);
        Setup.telemetry.addData(name + " velocity",velocity);
        Setup.telemetry.addData(name + " timeLimit",timeLimit);
        Setup.telemetry.addData(name + " isBusy",isBusy());
    }
    public void update(){
        currentPos = targetPos;
    }
    public boolean isBusy(){
        return !(currentPos==targetPos);
    }
}
