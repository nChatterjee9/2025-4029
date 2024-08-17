package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Sensors {
    private static SensorColor[] colors;
    private static SensorDistance[] distances;
    private static SensorSwitch[] switches;
    private static boolean standardInvertSwitches = true;

    public Sensors(int colorSensorLength, int distanceSensorLength, int switchSensorLength){
        colors = new SensorColor[colorSensorLength];
        distances = new SensorDistance[distanceSensorLength];
        switches = new SensorSwitch[switchSensorLength];
    }
    public void addSensor(Class type, int position, String name){
        if(type == ColorSensor.class){
            colors[position] = new SensorColor(name);
        } else if(type == DistanceSensor.class){
            distances[position] = new SensorDistance(name);
        } else if(type == DigitalChannel.class){
            switches[position] = new SensorSwitch(name, standardInvertSwitches);
        }
    }
    public void invertSwitch(boolean invertSwitch, int position){
        switches[position].invertSwitch(invertSwitch);
    }
    public double[] getColor(int position){
        return colors[position].getColor();
    }
    public double getDistance(DistanceUnit unit, int position){
        return distances[position].getDistance(unit);
    }
    public boolean getSwitchStatus(int position){
        return switches[position].getStatus();
    }
    public void setNewI2cAdressColor(I2cAddr newAddress, int position){
        colors[position].setI2cAddress(newAddress);
    }
    public void toggleColorLight(boolean isOn, int position){
        colors[position].enableLed(isOn);
    }

}
