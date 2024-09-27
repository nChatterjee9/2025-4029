package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Bot.Mechanisms.AbstractMechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.MotorExample;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.ServoExample;
import org.firstinspires.ftc.teamcode.Bot.Setup;

import java.util.Set;

public class FlexibleTest extends LinearOpMode {
    Setup setup;

    Mechanism mechanism;
    Mechanism motor = new MotorExample(), servo = new ServoExample();

    double targetPos, futureVelPos, changeInPos, velocity;
    double SERVO_INCREMENT = 0.001, MOTOR_INCREMENT = 5;
    ElapsedTime timer = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {

    }
}
