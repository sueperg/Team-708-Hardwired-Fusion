/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import utilclasses.EncoderRotationSensor;
import utilclasses.RotationSensor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.Driving.ArcadeDrive;
import edu.wpi.first.wpilibj.templates.commands.Driving.TankDrive;

/**
 *
 * @author Vincent Gargiulo & Nam Tran
 */
public class Drivetrain extends PIDSubsystem {

    private static final double Kp = .0001; 
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;
    private static final double tolerance = .1; 
    
    private static final double wheelDiameterIn = 6.0;
    private static final double gearMultiplier = 7.5;
    private static final double encoderCountsPerRotation = 250.0;
    private static final double distancePerCount = wheelDiameterIn * Math.PI /
            (gearMultiplier * encoderCountsPerRotation);
    private static final double robotDiameterIn = 26.5;
    
    private static final boolean HIGH_GEAR = false;
    private static final boolean DEFAULT_GEAR = !HIGH_GEAR;
    
    private static final DoubleSolenoid.Value ROBOT_TIPPED = DoubleSolenoid.Value.kForward;
    private static final DoubleSolenoid.Value ROBOT_LEVEL = DoubleSolenoid.Value.kReverse;
    
    private double moveSpeed = 0.0;
    private double pidOut = 0.0;
    private static final double tankControlTolerance = .025;
    
    private RobotDrive robotDrive;
    private Encoder leftEnc,rightEnc;
    private RotationSensor rotationSensor;
    private Solenoid shifter;
    private DoubleSolenoid tipper;

    // Initialize your subsystem here
    public Drivetrain() {
        super("Drivetrain", Kp, Ki, Kd);
        robotDrive = new RobotDrive(RobotMap.leftJag, RobotMap.rightJag);
        
//        leftEnc = new AvgRateEncoder(RobotMap.leftEncoderA,RobotMap.leftEncoderB);
        leftEnc = new Encoder(RobotMap.leftEncoderA,RobotMap.leftEncoderB);
        leftEnc.setReverseDirection(true);
//        rightEnc = new AvgRateEncoder(RobotMap.rightEncoderA,RobotMap.rightEncoderB);
        rightEnc = new Encoder(RobotMap.rightEncoderA,RobotMap.rightEncoderB);
//        rightEnc.setReverseDirection(true);
        
        leftEnc.setDistancePerPulse(distancePerCount);
        rightEnc.setDistancePerPulse(distancePerCount);
        
        leftEnc.start();
        rightEnc.start();
        
        rotationSensor = new EncoderRotationSensor(leftEnc,rightEnc,robotDiameterIn);
        
        shifter = new Solenoid(RobotMap.shiftingSolenoid);
        shifter.set(DEFAULT_GEAR);
        
        tipper = new DoubleSolenoid(RobotMap.climberExtendSolenoidChannelA,RobotMap.climberExtendSolenoidChannelB);
        tipper.set(ROBOT_LEVEL);

        //set up PID Controller
        this.setAbsoluteTolerance(tolerance);
        this.setInputRange(-120.0,120.0);
        
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        setSetpoint(0.0);
        // enable() - Enables the PID controller.
    }
    
    /**
     * Returns the robot's current heading in degrees.
     * @return 
     */
    public double getAngle()
    {
        return rotationSensor.getAngle();
    }
    
    public void resetAngle()
    {
        rotationSensor.reset();
    }
    
    public void resetEncoders()
    {
        leftEnc.reset();
        rightEnc.reset();
    }
    
    public double getLeftEncDistance()
    {
        return leftEnc.getDistance();
    }
    
    public double getRightEncDistance()
    {
        return rightEnc.getDistance();
    }
    
    public void initDefaultCommand() {
        String driveMode = Preferences.getInstance().getString("DriveMode", "Halo");
        
        if(driveMode.equals("Halo"))
        {
            setDefaultCommand(new ArcadeDrive());
        }else if(driveMode.equals("Tank"))
        {
            setDefaultCommand(new TankDrive());
        }else
        {
            setDefaultCommand(new ArcadeDrive());
        }
    }
    
    public void shiftHigh()
    {
        shifter.set(HIGH_GEAR);
    }
    
    public void shiftLow()
    {
        shifter.set(!HIGH_GEAR);
    }
    
    public boolean getDefaultGear()
    {
        return DEFAULT_GEAR;
    }
    
    public boolean getGear()
    {
        return shifter.get();
    }
    
    public void changeGear()
    {
        if(shifter.get() == HIGH_GEAR)
        {
            shiftLow();
        }else
        {
            shiftHigh();
        }
//        shifter.set(!shifter.get());
    }
    
    public void tipRobot()
    {
        tipper.set(ROBOT_TIPPED);
    }
    
    public void levelRobot()
    {
        tipper.set(ROBOT_LEVEL);
    }
    
    public void toggleTipRobot()
    {
//        tipper.set(!tipper.get());
        
        if(tipper.get() == DoubleSolenoid.Value.kForward)
        {
            tipper.set(DoubleSolenoid.Value.kReverse);
        }else if(tipper.get() == DoubleSolenoid.Value.kReverse)
        {
            tipper.set(DoubleSolenoid.Value.kForward);
        }
    }
    
    public double getEncoderDifference()
    {
//        return rightEnc.getRate() - leftEnc.getRate();
        return leftEnc.getRate() - rightEnc.getRate();
    }
    
    protected double returnPIDInput() {
        return getEncoderDifference();
    }
    
    protected void usePIDOutput(double output) {
        pidOut = output;
        robotDrive.arcadeDrive(moveSpeed,output);
        
    }
    
    public void arcadeDrive(double move, double rotate)
    {
        //if trying to drive straight, use encoder feedback
        //to compensate for drivetrain bias
//        if(rotate == 0.0 && move != 0.0)
//        {
//            if(!this.getPIDController().isEnable())
//            {
//                this.enable();
//            }
//            
//            moveSpeed = move;
//        }else
//        {
//            if(this.getPIDController().isEnable()) this.disable();
            robotDrive.arcadeDrive(-move,rotate);
//        }
    }
    public void tankDrive(double leftStick, double rightStick)
    {
        //if trying to drive straight, use encoder feedback
        //to compensate for drivetrain bias
        if(Math.abs(leftStick - rightStick) < tankControlTolerance && leftStick != 0.0 && rightStick != 0.0)
        {
            if(!this.getPIDController().isEnable())
            {
                this.enable();
            }
            
            moveSpeed = (leftStick + rightStick)/2;
        }else{
            if(this.getPIDController().isEnable()) this.disable();
            robotDrive.tankDrive(leftStick, rightStick);
        }
    }
    
    public void sendToDash()
    {
        SmartDashboard.putNumber("LeftEncCounts",leftEnc.get());
        SmartDashboard.putNumber("RightEncCounts",rightEnc.get());
        SmartDashboard.putNumber("LeftEncDist",leftEnc.getDistance());
        SmartDashboard.putNumber("RightEncDist",rightEnc.getDistance());
        
        SmartDashboard.putNumber("RobotAngle",rotationSensor.getAngle());
        SmartDashboard.putNumber("EncDiff",getEncoderDifference());
        SmartDashboard.putNumber("RightEncRate",rightEnc.getRate());
        SmartDashboard.putNumber("LeftEncRate",leftEnc.getRate());
        SmartDashboard.putNumber("PIDOutput",pidOut);
        
        SmartDashboard.putString("Drive Gear",(shifter.get() == HIGH_GEAR)?"High":"Low");
    }
}
