
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
//import edu.wpi.first.wpilibj.templates.commands.drivetrain.FollowBall;
import edu.wpi.first.wpilibj.templates.commands.testCatapult.ManualBackward;
import edu.wpi.first.wpilibj.templates.commands.testCatapult.ManualForward;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.ToggleDriveMode;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.ToggleSwagSpeed;
import utilclasses.Gamepad;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    //Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    
    /*
     * Define Button Constants
     */
    private static final int toggleDriveModeButtonNumber = Gamepad.button_A;
    private static final int holdToFollowBallButtonNumber = Gamepad.button_B;
    private static final int toggleSwagSpeedButtonNumber = Gamepad.button_R_Shoulder;
    
    //initialize Gamepads
    public static final Gamepad driverGamepad = new Gamepad(RobotMap.driverGamepad);
    public static final Gamepad operatorGamepad = new Gamepad(RobotMap.operatorGamepad);
    
    /*
     * Driver Buttons
     */
    
    //Initialize drivetrain switch button
    public static final Button toggleDriveMode = new JoystickButton(driverGamepad, toggleDriveModeButtonNumber);
    
    //Switch to swag speed
    public static final Button toggleSwagSpeed = new JoystickButton(driverGamepad, toggleSwagSpeedButtonNumber);
    
    //Follow the ball while this button is held
    public static final Button holdToFollowBallButton = new JoystickButton(driverGamepad,holdToFollowBallButtonNumber);
    
    /*
     * Operator Buttons
     */
    
    //Makes arm go forward or stop
    public static final Button manualForward = new JoystickButton(operatorGamepad, Gamepad.button_R_Shoulder);
    
    //Makes the arm go backward or stop
    public static final Button manualBackward = new JoystickButton(operatorGamepad, Gamepad.button_L_Shoulder);
    
    public OI() 
    {
        // Driver
        toggleDriveMode.whenPressed(new ToggleDriveMode());
        toggleSwagSpeed.whenPressed(new ToggleSwagSpeed());
//        holdToFollowBallButton.whenPressed(new FollowBall());
        
        // Operator
        //toggleCatapultState.whenPressed(new ToggleCatapultState());
        manualForward.whenPressed(new ManualForward());
        manualBackward.whenPressed(new ManualBackward());
    }
    /*
     * Is Button Pressed Query Methods
     */
    public static boolean isHoldToFollowButtonPressed()
    {
        return driverGamepad.getButton(holdToFollowBallButtonNumber);
    }
    
    /*
     * Nam add hold to swag button method
     */
}

