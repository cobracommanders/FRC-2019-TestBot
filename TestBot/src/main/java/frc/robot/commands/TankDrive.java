/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.ConstantAccelerationCalculator;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Operator;

public class TankDrive extends Command {

  private Operator operator = Operator.getOperator();
  private Drivetrain drivetrain;
  private ConstantAccelerationCalculator moveAcceleration = new ConstantAccelerationCalculator(0.00005);
  private ConstantAccelerationCalculator turnAcceleration = new ConstantAccelerationCalculator(0.00005);


  public TankDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super("RampDrive");

    requires(this.drivetrain = Drivetrain.getDrivetrain());


  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double leftPower = moveAcceleration.getNextDataPoint(operator.controller.axisLeftY.getAxisValue());
    double rightPower = moveAcceleration.getNextDataPoint(operator.controller.axisRightY.getAxisValue());

    this.drivetrain.tankDrive(leftPower, rightPower);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    drivetrain.drive(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}