/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.github.CircuitRunners;


import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class GRITS2014 extends SimpleRobot {
    
    public static final double DEADBAND_LOW = -0.2;
    public static final double DEADBAND_HIGH = 0.2;
    
    // Talons
    public Talon left;
    public Talon right;

    // Intake
    public Talon intakeMotor;

    // Kicker
    public Victor kickerMotor;

    // Robot Drive
    public RobotDrive drive;

    // Joystick
    public Joystick xbone;

    public GRITS2014() {
        left = new Talon(1);
        right = new Talon(2);

        intakeMotor = new Talon(3);

        kickerMotor = new Victor(4);

        drive = new RobotDrive(left, right);

        xbone = new Joystick(1);
    }
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        drive.setSafetyEnabled(false);

        // Drive forward for 2 seconds
        drive.drive(0.5, 0.0);
        Timer.delay(2.0);

        // Stop
        drive.drive(0.0, 0.0);
    }
    
    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        
        drive.setSafetyEnabled(true);
        
        double c;
        
        while(isOperatorControl() && isEnabled()){
            
            if (xbone.getRawButton(4)) {
                c = 1;
            } else {
                c = 0.4;
            }
            
            double moveValue = c * MathUtils.pow(deadband(xbone.getRawAxis(2)), 3);
            double rotateValue = c * MathUtils.pow(deadband(xbone.getRawAxis(1)), 3);
            
            // Arcade drive
            drive.arcadeDrive(moveValue, rotateValue);

            // Intake control
            if(xbone.getRawButton(3)) {
                intakeMotor.set(-0.4);
            } else if (xbone.getRawButton(1)) {
                intakeMotor.set(1);
            } else {
                intakeMotor.set(0.0);
            }
            
            // Kicker control
            if (xbone.getRawButton(6)) {
                kickerMotor.set(1);
            } else {
                kickerMotor.set(-0.4);
            }
        }
    }
    
    //Deadband
    public double deadband(double d){
        if(d > DEADBAND_LOW && d < DEADBAND_HIGH){
            d = 0;
        }
        return d;
    }
}
