/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Victor;
import frc.robot.commands.ManualWrist;

public class WristSubsystem extends Subsystem {

  private static WristSubsystem wristSubsystem = null;
	public static WristSubsystem getwristSubsystem() {
		wristSubsystem = wristSubsystem == null ? new WristSubsystem() : wristSubsystem;
		return wristSubsystem;
	}

  private Victor wrist = new Victor(0);



  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManualWrist());
  }


  public void setWrist(double power) {
      wrist.set(power);

  }

}