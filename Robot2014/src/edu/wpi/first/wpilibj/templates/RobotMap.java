package edu.wpi.first.wpilibj.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    /*
    * Motor/PWM Ports:
    */
    
    // Drivetrain
    public static final int leftMotor1 = 1;    //normal drive
    public static final int leftMotor2 = 2;    //swag drive enabled
    public static final int rightMotor1 = 3;   //normal drive
    public static final int rightMotor2 = 4;   //swag drive enabled
    
    
    // Catapult
    public static final int catapultMotor = 5;
    
    /*
     * Sensors
     */
    
    // Catapult
    public static final int potentiometer = 1;
    
    /*
     * Joystick Ports:
    */
    public static final int driverGamepad = 1;
    public static final int operatorGamepad = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
}
