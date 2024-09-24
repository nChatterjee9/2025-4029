package org.firstinspires.ftc.teamcode.Bot.OpModes;

import android.security.keystore.StrongBoxUnavailableException;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Bot.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Bot.Setup;
import org.firstinspires.ftc.teamcode.Bot.Bot;

import org.firstinspires.ftc.teamcode.PedroPathing.localization.Pose;
@TeleOp(name = "AAAAAAAAAAAAA", group = "1")
public class DriveTeleOp extends LinearOpMode {
    Drivetrain drivetrain;
    Setup setup;
    Bot bot;

    @Override
    public void runOpMode(){
        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.TELEOP, Setup.Team.Q1);
        bot = new Bot(Setup.mechStates, Setup.sensorStates);
        bot.drivetrain.init(new Pose());

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            drive();
            bot.drivetrain.update(true);
            bot.drivetrain.telemetry();
        }
    }
    double x, y, spin;
    void drive(){
        x = Math.abs(gamepad1.left_stick_x)>0.04 ? gamepad1.left_stick_x : 0;
        y = Math.abs(gamepad1.left_stick_y)>0.04 ? -gamepad1.left_stick_y : 0;
        spin = Math.abs(gamepad1.right_stick_x) > 0.04 ? gamepad1.right_stick_x : 0;

        bot.drivetrain.setTargetVectors(x, y, spin);
    }

    void driveUsingPeP(){
        bot.drivetrain.setTeleOpTargets(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
    }
}
