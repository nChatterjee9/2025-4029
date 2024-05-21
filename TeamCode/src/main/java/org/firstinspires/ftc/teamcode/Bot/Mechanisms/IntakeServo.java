package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.ServoMechanism;

public class IntakeServo extends ServoMechanism {

    public static final double POS_INIT = 0.10189;//0.0369 then 0.1439
    public static final double POS_TELE_OP = 0.4559;//was 0.5139
    public static final double POS_EJECT = 0.15;//was 0.5139

    public static final double POS_AUTO = 0.5129;
    public static final double POS_STACK_6 = 0.28;
    public static final double POS_STACK_5 = 0.3179;
    public static final double POS_STACK_4 = 0.35;
    public static final double POS_STACK_3 = 0.385; //formerly 0.4
    public static final double POS_STACK_2 = 0.41; //formerly 0.45
    public static final double POS_STACK_1 = 0.45; //formerly 0.5139
    public static final double POS_UP= 0.176;
    public static final double INTAKE_SERVO_INCREMENT = 0.001;//0.0369
    public static final double INTAKE_SERVO_INCREMENT_AUTO = 0.0000000003;//0.0369




    public static final double MAX = 1;
    public static final double MIN = 0;
    public IntakeServo() {
        super("IntakeServo");
    }
}
