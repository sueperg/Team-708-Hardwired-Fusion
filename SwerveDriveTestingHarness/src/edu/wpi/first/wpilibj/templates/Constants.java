/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Relay;

/**
 * This class will provide access to the channels of each
 * device on connected to the cRIO and the slots of cRIO modules.
 * @author 708
 */
public class Constants{
    
    public static class crioModules{
        public static final int kModule1 = 1;
	public static final int kModule2 = 2;
    }
    
    public static class PWMChannels{
        public static final int kFrontRightCIM = 1;
        public static final int kFrontLeftCIM = 2;
        public static final int kRearLeftCIM = 3;
        public static final int kRearRightCIM = 4;
    }
    
    public static class RelayChannels{
        public static final int kFrontRightSpike = 1;
        public static final int kFrontLeftSpike = 2;
        public static final int kRearLeftSpike = 3;
        public static final int kRearRightSpike = 4;
    }
    
    public static class DigitalIOChannels{
        public static final int kFrontRightEncoderA = 1;
        public static final int kFrontRightEncoderB = 2;
        public static final int kFrontLeftEncoderA = 3;
        public static final int kFrontLeftEncoderB = 4;
        public static final int kRearLeftEncoderA = 5;
        public static final int kRearLeftEncoderB = 6;
        public static final int kRearRightEncoderA = 7;
        public static final int kRearRightEncoderB = 8;
    }
    
    public static class AnalogInputChannels{
        public static final int kFrontRightPot = 1;
        public static final int kFrontLeftPot = 2;
        public static final int kRearLeftPot = 3;
        public static final int kRearRightPot = 4;
        public static final int kGyro = 5;
    }
    
    public static class SolenoidChannels{
	//add solenoids for tshirt cannon
    }
    
    public static class Calibrations
    {
        public static final double kFrontRightPotLowVlts = 0.0;
        public static final double kFrontRightPotHighVlts = 1.0;
        public static final double kFrontLeftPotLowVlts = 0.0;
        public static final double kFrontLeftPotHighVlts = 1.0;
        public static final double kRearLeftPotLowVlts = 0.0;
        public static final double kRearLeftPotHighVlts = 1.0;
        public static final double kRearRightPotLowVlts = 0.0;
        public static final double kRearRightPotHighVlts = 1.0; 
    
        public static final Relay.Value kFrontRightHeadingLower = Relay.Value.kForward;
        public static final Relay.Value kFrontRightHeadingHigher = Relay.Value.kReverse;
        public static final Relay.Value kFrontLeftHeadingLower = Relay.Value.kForward;
        public static final Relay.Value kFrontLeftHeadingHigher = Relay.Value.kReverse;
        public static final Relay.Value kRearLeftHeadingLower = Relay.Value.kForward;
        public static final Relay.Value kRearLeftHeadingHigher = Relay.Value.kReverse;
        public static final Relay.Value kRearRightHeadingLower = Relay.Value.kForward;
        public static final Relay.Value kRearRightHeadingHigher = Relay.Value.kReverse;
    
    }
}
