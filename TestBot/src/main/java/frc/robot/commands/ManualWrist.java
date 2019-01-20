/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.ConstantAccelerationCalculator;
import frc.robot.subsystems.WristSubsystem;
import frc.robot.Operator;

public class ManualWrist extends Command {

  private Operator operator = Operator.getOperator();
  private WristSubsystem wrist;
  private ConstantAccelerationCalculator wristAcceleration = new ConstantAccelerationCalculator(0.00005);


  public ManualWrist() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super("ManualWrist");

    requires(this.wrist = WristSubsystem.getwristSubsystem());


  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double power = wristAcceleration.getNextDataPoint(operator.controller.axisRightY.getAxisValue());

    this.wrist.setWrist(power);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    wrist.setWrist(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}