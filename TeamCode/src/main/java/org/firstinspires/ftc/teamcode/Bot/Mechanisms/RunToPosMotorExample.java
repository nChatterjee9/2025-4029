package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.RunToPosMotorMechanism;

public class RunToPosMotorExample extends RunToPosMotorMechanism {
    public static double POS_INIT = 0;
    public static double POS_1 = 100;
    public static double POS_2 = 200;
    public static double POS_3 = 300;

    public static double POS_MAX = 500;

    public RunToPosMotorExample(){
        super("runToPosMotorExample");
        velocity = 1;
    }
}
