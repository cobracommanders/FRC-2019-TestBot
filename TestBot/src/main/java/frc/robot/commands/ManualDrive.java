/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.ConstantAccelerationCalculator;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Operator;
import frc.robot.Robot;

public class ManualDrive extends Command {

  public ManualDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super("RampDrive");

    requires(Robot.drivetrain);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double move = Robot.controller.axisLeftY.getAxisValue();
    double turn = Robot.controller.axisRightX.getAxisValue();

    // slowmode
    if (Robot.controller.axisLeftTrigger.getAxisValue() > .1) {
      move *= 0.8;
      turn *= 0.7;
    }

    // when right joypress is true turn will be at 100%
    if (!Robot.controller.rightJoyPress.get()) {
      turn *= .85;
    }

    Robot.drivetrain.drive(move, turn);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.drive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}