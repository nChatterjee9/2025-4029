package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/*
practice motor test without using codebase
 */
@TeleOp
public class MotorTest extends LinearOpMode {
    DcMotor motor;
    private int targetPosition;
    @Override
    public void runOpMode () {
        motor = hardwareMap.get(DcMotor.class, "motor");
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        int updateCounter = 0;
        int updateThreshold = 6;  // Change targetPosition every 6 iterations (slows it to 10 times per second)

        while (opModeIsActive()) {
            // Increment counter each loop iteration
            updateCounter++;

            // Only update the target position every updateThreshold iterations
            if (updateCounter >= updateThreshold) {
                if (gamepad1.left_stick_y < -0.04) {
                    targetPosition += 1;
                }
                if (gamepad1.left_stick_y > 0.04) {
                    targetPosition -= 1;
                }
            }

            updateCounter = 0;

            telemetry.addData("TargetPosition", targetPosition);
            telemetry.update();

            if (gamepad1.left_bumper) {
                motor.setTargetPosition(targetPosition);
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor.setPower(1);
            }
        }

    }
}
