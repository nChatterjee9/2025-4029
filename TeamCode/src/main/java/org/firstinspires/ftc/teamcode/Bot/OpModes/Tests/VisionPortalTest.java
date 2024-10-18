package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Bot.Bot;
import org.firstinspires.ftc.teamcode.Bot.Setup;

public class VisionPortalTest extends LinearOpMode {

    Setup setup;
    Bot bot;
    @Override
    public void runOpMode(){
        setup = new Setup(hardwareMap,telemetry,true, this, Setup.OpModeType.AUTO, Setup.Team.Q1);
        bot = new Bot(Setup.mechStates, Setup.sensorStates);
        setup.disableMechanism("drivetrain");
        setup.disableMechanism("intakeMotor");
        setup.disableSensor("outtakeSlidesSwitch");

        bot.sensors.init();


    }
}
