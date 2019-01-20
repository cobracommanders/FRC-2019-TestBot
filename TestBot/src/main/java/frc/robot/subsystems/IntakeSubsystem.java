/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Victor;

public class IntakeSubsystem extends Subsystem {

  private static IntakeSubsystem intakeSubsystem = null;
	public static IntakeSubsystem getIntakeSubsystem() {
		intakeSubsystem = intakeSubsystem == null ? new IntakeSubsystem() : intakeSubsystem;
		return intakeSubsystem;
	}

  private Victor intakeLeft = new Victor(0);
  private Victor intakeRight = new Victor(1);

  private double lastLeft = 0;
  private double lastRight = 0;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }


  public void setIntake(double leftPower, double rightPower) {
      intakeLeft.set(leftPower);
      intakeRight.set(rightPower);
      lastLeft = leftPower; //For purposes of the toggleintake command
      lastRight = rightPower;

  }
  public double getLastLeft(){
    return lastLeft;
  }
  public double getLastRight(){
    return lastRight;
  }

}