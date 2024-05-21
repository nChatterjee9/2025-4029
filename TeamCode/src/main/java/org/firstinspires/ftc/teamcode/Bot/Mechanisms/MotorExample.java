package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.MotorMechanism;

public class MotorExample extends MotorMechanism {
    public static final double FORWARD_POW = 1.0;
    public static final double BACKWARD = 1.0;

    public static final double SLOW_POW = 0.5;

    public static final double STOP_POW = 0.0;

    public MotorExample(){
        super("motorExample");
    }

}
