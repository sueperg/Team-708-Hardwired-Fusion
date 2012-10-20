/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class SwerveTest extends IterativeRobot {

    private Gamepad gamepad;
    private SwerveWheel wheel1, wheel2, wheel3, wheel4;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    
        wheel1 = new SwerveWheel(Constants.RelayChannels.kFrontRightSpike,
                Constants.PWMChannels.kFrontRightCIM,
                Constants.AnalogInputChannels.kFrontRightPot,
                Constants.DigitalIOChannels.kFrontRightEncoderA,
                Constants.DigitalIOChannels.kFrontRightEncoderB,
                false, Constants.Calibrations.kFrontRightPotLowVlts,
                Constants.Calibrations.kFrontRightPotHighVlts,
                Constants.Calibrations.kFrontRightHeadingLower,
                Constants.Calibrations.kFrontRightHeadingHigher);

        wheel2 = new SwerveWheel(Constants.RelayChannels.kFrontLeftSpike,
                Constants.PWMChannels.kFrontLeftCIM,
                Constants.AnalogInputChannels.kFrontLeftPot,
                Constants.DigitalIOChannels.kFrontLeftEncoderA,
                Constants.DigitalIOChannels.kFrontLeftEncoderB,
                false, Constants.Calibrations.kFrontLeftPotLowVlts,
                Constants.Calibrations.kFrontLeftPotHighVlts,
                Constants.Calibrations.kFrontLeftHeadingLower,
                Constants.Calibrations.kFrontLeftHeadingHigher);

        wheel3 = new SwerveWheel(Constants.RelayChannels.kRearLeftSpike,
                Constants.PWMChannels.kRearLeftCIM,
                Constants.AnalogInputChannels.kRearLeftPot,
                Constants.DigitalIOChannels.kRearLeftEncoderA,
                Constants.DigitalIOChannels.kRearLeftEncoderB,
                false, Constants.Calibrations.kRearLeftPotLowVlts,
                Constants.Calibrations.kRearLeftPotHighVlts,
                Constants.Calibrations.kRearLeftHeadingLower,
                Constants.Calibrations.kRearLeftHeadingHigher);

        wheel4 = new SwerveWheel(Constants.RelayChannels.kRearRightSpike,
                Constants.PWMChannels.kRearRightCIM,
                Constants.AnalogInputChannels.kRearRightPot,
                Constants.DigitalIOChannels.kRearRightEncoderA,
                Constants.DigitalIOChannels.kRearRightEncoderB,
                false, Constants.Calibrations.kRearRightPotLowVlts,
                Constants.Calibrations.kRearRightPotHighVlts,
                Constants.Calibrations.kRearRightHeadingLower,
                Constants.Calibrations.kRearRightHeadingHigher);

        gamepad = new Gamepad(1);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        double rotation = gamepad.getAxis(Gamepad.leftStick_X);
        double speed = gamepad.getAxis(Gamepad.rightStick_X);
        String selected = "";
        
        
        if (gamepad.getButton(Gamepad.button_A)) {
            setRotation(rotation, wheel1);
            wheel1.setPWM(speed);
            selected += "FR ";
        }

        if (gamepad.getButton(Gamepad.button_X)) {
            setRotation(rotation, wheel2);
            wheel2.setPWM(speed);
            selected += "FL ";
        }

        if (gamepad.getButton(Gamepad.button_Y)) {
            setRotation(rotation, wheel3);
            wheel3.setPWM(speed);
            selected += "RL ";
            
        }

        if (gamepad.getButton(Gamepad.button_B)) {
            setRotation(rotation, wheel4);
            wheel4.setPWM(speed);
            selected += "RR ";
        }
        
        
        
        SmartDashboard.putString("Selected Wheel",selected);
        SmartDashboard.putDouble("Speed", speed);
        SmartDashboard.putDouble("Rotation",rotation);
        SmartDashboard.putDouble("Angle fr",Math708.round(wheel1.getHeading(),2));
        SmartDashboard.putDouble("Vlts fr",Math708.round(wheel1.getPotVlts(),2));
    }

    private void setRotation(double rotation, SwerveWheel wheel) {
        if (rotation > 0.0) {
            wheel.increaseHeading();
        } else if (rotation < 0.0) {
            wheel.decreaseHeading();
        } else {
            wheel.stopRotation();
        }
    }
}
