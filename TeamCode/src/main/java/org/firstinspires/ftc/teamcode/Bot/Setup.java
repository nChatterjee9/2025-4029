package org.firstinspires.ftc.teamcode.Bot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Bot.InitStates.HardwareStates;

import java.util.HashMap;
import java.util.List;

/*
class to set up:
- hardware
- telemetry
- dashboard
- team
- type of opmode
 */

public class Setup {
    public enum OpModeType {
        AUTO,
        TELEOP
    }

    public enum Team{ //TODO: Set Team (i.e. Red Right) here
        Q1,
        Q2,
        Q3,
        Q4
    }
    public static HardwareMap hardwareMap;
    public static Telemetry telemetry;
    public static LinearOpMode opMode;
    public static OpModeType opModeType;
    public static FtcDashboard dashboard;

    public static HashMap<String, HardwareStates> mechStates = new HashMap<>();
    public static HashMap<String, HardwareStates> sensorStates = new HashMap<>();

    public Setup(HardwareMap hmap, Telemetry tel, boolean enableDash, LinearOpMode op, OpModeType opType, Team team){
        hardwareMap = hmap;
        telemetry = tel;
        opMode = op;
        opModeType = opType;
        if (enableDash){
            configureDashboard();
        }else{
            dashboard = null;
        }
        configureTelemetry();
        configureBulkReads();
        addMechanisms();
        addSensors();
    }

    public void configureDashboard(){
        dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        dashboard.updateConfig();
    }
    public void configureTelemetry(){
        telemetry.setMsTransmissionInterval(50);
    }
    public void configureBulkReads() {
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }
    }
    public void addMechanisms(){
        mechStates.put("drivetrain", new HardwareStates(true));
        mechStates.put("intakeMotor", new HardwareStates(true));
        mechStates.put("intakeServo", new HardwareStates(true));
        mechStates.put("outtakeLatchLeft", new HardwareStates(true));
        mechStates.put("outtakeLatchRight", new HardwareStates(true));
        mechStates.put("outtakeSlides", new HardwareStates(true));
        mechStates.put("outtakeRotation", new HardwareStates(true));
        mechStates.put("outtakeTilt", new HardwareStates(true));
        mechStates.put("outtakeV4B", new HardwareStates(true));
        mechStates.put("airplaneLauncher", new HardwareStates(true));
        mechStates.put("winch", new HardwareStates(true));
        mechStates.put("autoPixelDropper", new HardwareStates(true));
    }

    public void addSensors(){
        sensorStates.put("webcam", new HardwareStates(true));
        sensorStates.put("outtakeSlidesSwitch", new HardwareStates(true));
    }

    public void disableMechanism(String mechanismName){
        mechStates.put(mechanismName, new HardwareStates(false));
    }

    public void disableSensor(String sensorName){
        sensorStates.put(sensorName, new HardwareStates(false));
    }
}
