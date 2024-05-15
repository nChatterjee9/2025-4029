package org.firstinspires.ftc.teamcode.Bot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.List;

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

}
