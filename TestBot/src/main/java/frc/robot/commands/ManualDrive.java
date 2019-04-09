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

public class ManualDrive extends Command {

  private Operator operator = Operator.getOperator();
  private Drivetrain drivetrain;
  private ConstantAccelerationCalculator moveAcceleration = new ConstantAccelerationCalculator(0.00005);
  private ConstantAccelerationCalculator turnAcceleration = new ConstantAccelerationCalculator(0.00005);

  public ManualDrive() {
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
    double move = Robot.driverController.axisLeftY.getAxisValue();
    double turn = Robot.driverController.axisRightX.getAxisValue();

    // slowmode
    if (Robot.driverController.axisLeftTrigger.getAxisValue() > .1) {
      move *= 0.8;
      turn *= 0.7;
    }

    // when right joypress is true turn will be at 100%
    if (!Robot.driverController.rightJoyPress.get()) {
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
    drivetrain.drive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}