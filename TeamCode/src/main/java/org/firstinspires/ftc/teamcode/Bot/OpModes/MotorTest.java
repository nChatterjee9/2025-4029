package org.firstinspires.ftc.teamcode.Bot.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class MotorTest extends LinearOpMode {
    DcMotor motor;
    private int targetPosition;
    @Override
    public void runOpMode () {
        motor = hardwareMap.get(DcMotor.class, "motor");
        telemetry.addData("Status", "Initilized");
        telemetry.update();

        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.left_stick_y > 0.04) {
                targetPosition += 10;
            }
            telemetry.addData("TargetPosition", targetPosition);
            telemetry.update();

            if (gamepad1.left_bumper) {
                motor.setTargetPosition(targetPosition);
            }
        }
    }
}
