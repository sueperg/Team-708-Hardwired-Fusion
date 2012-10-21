/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Represents a gamepad connected to the driver
 * station. Note: the drivers for the gamepad should
 * be installed on the driver station netbook.
 * @author Connor Willison
 */
public class Gamepad extends Joystick{

    //floating-point values:
    public static final int leftStick_X = 1;
    public static final int leftStick_Y = 2;
    public static final int shoulderAxis = 3;
    public static final int rightStick_X = 4;
    public static final int rightStick_Y = 5;
    public static final int dpadAxis = 6;
    
    //boolean buttons:
    public static final int button_A = 1;
    public static final int button_B = 2;
    public static final int button_X = 3;
    public static final int button_Y = 4;
    public static final int button_L_Shoulder = 5;
    public static final int button_R_Shoulder = 6;
    public static final int button_Back = 7;
    public static final int button_Start = 8;
    public static final int button_LeftStick = 9;
    public static final int button_RightStick = 10;	//only 12 buttons allowed - must find out which can be read    
    
    private static final double axis_deadzone = .1;
    
    private int port;
    /**
     * Creates a gamepad attached to
     * the specified port on the driver station.
     * @param port 
     */
    public Gamepad(int port){
	super(port);
        this.port = port;
    }
    
    /**
     * Get the value of a gamepad axis, adjusting
     * for a dead zone.
     * @param axis
     * @return 
     */
    public double getAxis(int axis){
	double val = getRawAxis(axis);
        if(Math.abs(val) <= axis_deadzone){
            return 0;
        }else{
            return val;
        }
    }
    
    /**
     * Get the value of a gamepad button.
     * @param button
     * @return 
     */
    public boolean getButton(int button){
	return getRawButton(button);
    }
    
    public int getPort()
    {
        return port;
    }
    
    /**
     * Outputs the gamepad's axes to the SmartDashboard.
     */
    public void sendAxesToDashboard()
    {
        SmartDashboard.putDouble("Gamepad " + port + "Right Stick X:",
                getAxis(Gamepad.rightStick_X));
        SmartDashboard.putDouble("Gamepad " + port + "Right Stick Y:",
                getAxis(Gamepad.rightStick_Y));
        SmartDashboard.putDouble("Gamepad " + port + "Left Stick X:",
                getAxis(Gamepad.leftStick_X));
        SmartDashboard.putDouble("Gamepad " + port + "Left Stick Y:",
                getAxis(Gamepad.leftStick_Y));
    }
}
