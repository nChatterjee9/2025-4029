package org.firstinspires.ftc.teamcode.Bot.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/*
practice motor test without using codebase
 */
@TeleOp
public class MotorTest extends LinearOpMode {
    DcMotor motor;
    private int targetPosition;
    private double velocity = 1;
    private final int INCREMENT = 100;
    private final double V_INCREMENT = 0.00001;

    @Override
    public void runOpMode () {
        motor = hardwareMap.get(DcMotor.class, "motor");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized");

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.left_stick_y < -0.4) {
                targetPosition += 1;
            }
            if (gamepad1.left_stick_y > 0.4) {
                targetPosition -= 1;
            }

            if (gamepad1.right_stick_y < -0.4) {
                velocity=Range.clip(velocity+V_INCREMENT, -1, 1);
            }
            if (gamepad1.right_stick_y > 0.4) {
                velocity=Range.clip(velocity-V_INCREMENT, -1, 1);
            }

            if(!gamepad1.right_bumper) {
                if (gamepad1.left_bumper) {
                    motor.setTargetPosition(targetPosition / INCREMENT);
                    motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motor.setPower(Range.clip(velocity, -1, 1));
                } else{
                    motor.setPower(0);
                }
            }
            else if (gamepad1.right_bumper) {
                motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                motor.setPower(Range.clip(velocity, 0, 1));
            }

            telemetry.addData("TargetPosition", targetPosition / INCREMENT);
            telemetry.addData("velocity", velocity);

            telemetry.update();
        }
    }
}
