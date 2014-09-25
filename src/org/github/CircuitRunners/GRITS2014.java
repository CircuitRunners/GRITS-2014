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

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class GRITS2014 extends SimpleRobot {

    // Talons
    public Talon left;
    public Talon right;

    // Intake
    public Talon intakeMotor;

    // Robot Drive
    public RobotDrive drive;

    // Joystick
    public Joystick xbone;

    public GRITS2014() {
        left = new Talon(1);
        right = new Talon(2);

        intakeMotor = new Talon(3);

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
        
        while(isOperatorControl() && isEnabled()){
            
            double moveValue = MathUtils.pow(xbone.getRawAxis(2), 3);
            double rotateValue = MathUtils.pow(xbone.getRawAxis(1), 3);
            
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
        }
    }
}
