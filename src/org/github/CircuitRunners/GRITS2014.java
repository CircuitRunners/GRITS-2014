/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.github.CircuitRunners;


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
    public Talon leftFront;
    public Talon leftBack;
    public Talon rightFront;
    public Talon rightBack;
    
    public Talon intakeMotor;
    // Robot Drive
    public RobotDrive drive;

    // Joystick
    public Joystick xbone;

    public GRITS2014() {
        leftFront = new Talon(1);
        leftBack = new Talon(2);
        rightFront = new Talon(3);
        rightBack = new Talon(4);
        
        intakeMotor = new Talon(5);

        drive = new RobotDrive(leftFront, leftBack, rightFront, rightBack);

        xbone = new Joystick(1);
    }
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        drive.setSafetyEnabled(false);
        drive.drive(0.5, 0.0);
        Timer.delay(2.0);
        drive.drive(0.0, 0.0);
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        drive.setSafetyEnabled(true);
        while(isOperatorControl() && isEnabled()){
            // Arcade drive
            drive.arcadeDrive(xbone, 1, xbone, 2, true);
            
            // Intake control
            if(xbone.getRawButton(3)) {
                intakeMotor.set(0.4);
            } else if (xbone.getRawButton(1)) {
                intakeMotor.set(1);
            } else {
                intakeMotor.set(0.0);
            }
        }
    }
}
