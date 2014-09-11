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

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class GRITS2014 extends SimpleRobot {
    
    //Robot Drive
    RobotDrive drive;
    
    // Talons
    public Talon leftFront;
    public Talon leftBack;
    public Talon rightFront;
    public Talon rightBack;
    
    // Joystick
    public Joystick xbone;

    public GRITS2014() {
        leftFront = new Talon(1);
        leftBack = new Talon(2);
        rightFront = new Talon(3);
        rightBack = new Talon(4);
        
        drive = new RobotDrive(leftFront, leftBack, rightFront, rightBack);
        
        xbone = new Joystick(1);
    }
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while(isOperatorControl()){
            
        }
    }
}