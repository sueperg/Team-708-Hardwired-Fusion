
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.testCatapult.ManualBackward;
import edu.wpi.first.wpilibj.templates.commands.testCatapult.ManualForward;
import edu.wpi.first.wpilibj.templates.commands.testCatapult.ManualStop;
import edu.wpi.first.wpilibj.templates.commands.testCatapult.ToggleCatapultState;
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
    
    //initialize Gamepads
    public static final Gamepad driverGamepad = new Gamepad(RobotMap.driverGamepad);
    public static final Gamepad operatorGamepad = new Gamepad(RobotMap.operatorGamepad);
    
    /*
     * Driver Buttons
     */
    
    //Initialize drivetrain switch button
    public static final Button toggleDriveMode = new JoystickButton(driverGamepad, Gamepad.button_A);
    
    //Switch to swag speed
    public static final Button toggleSwagSpeed = new JoystickButton(driverGamepad, Gamepad.button_R_Shoulder);
    
    /*
     * Operator Buttons
     */
    
    //Switches what the catapult should be doing
    public static final Button toggleCatapultState = new JoystickButton(operatorGamepad, Gamepad.button_R_Shoulder);
    
    //Makes arm go forward
    public static final Button manualForward = new JoystickButton(operatorGamepad, Gamepad.button_Y);
    
    //Makes the arm go backward
    public static final Button manualBackward = new JoystickButton(operatorGamepad, Gamepad.button_A);
    
    //Makes the arm stop
    public static final Button manualStop = new JoystickButton(operatorGamepad, Gamepad.button_B);
    
    public OI() 
    {
        // Driver
        toggleDriveMode.whenPressed(new ToggleDriveMode());
        toggleSwagSpeed.whenPressed(new ToggleSwagSpeed());
        
        // Operator
        toggleCatapultState.whenPressed(new ToggleCatapultState());
        manualForward.whenPressed(new ManualForward());
        manualBackward.whenPressed(new ManualBackward());
        manualStop.whenPressed(new ManualStop());
    }
}

