package org.firstinspires.ftc.teamcode.Bot.OpModes;

import android.security.keystore.StrongBoxUnavailableException;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Bot.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Bot.Setup;
import org.firstinspires.ftc.teamcode.PedroPathing.localization.Pose;

public class DriveTeleOp extends LinearOpMode {
    Drivetrain drivetrain;
    Setup setup;
    @Override
    public void runOpMode(){
        drivetrain = new Drivetrain();
        setup = new Setup(hardwareMap, telemetry, true, this, Setup.OpModeType.TELEOP, Setup.Team.Q1);
        drivetrain.init(new Pose());

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            drive();
            drivetrain.update(true);
            drivetrain.telemetry();
        }
    }
    double x, y, spin;
    void drive(){
        x = Math.abs(gamepad1.left_stick_x)>0.04 ? gamepad1.left_stick_x : 0;

        y = Math.abs(gamepad1.left_stick_y)>0.04 ? -gamepad1.left_stick_y : 0;
        spin = Math.abs(gamepad1.right_stick_x) > 0.04 ? gamepad1.right_stick_x : 0;

        drivetrain.setTargetVectors(x, y, spin);
    }

    void driveUsingPeP(){
        drivetrain.setTeleOpTargets(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
    }
}
