/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Mappings;
import frc.robot.commands.ManualDrive;
import edu.wpi.first.wpilibj.Encoder;


public class Drivetrain extends Subsystem {


  // TODO: may need to change the motor controllers and will need to change the motor channels.
  private Talon frontLeftDrive = new Talon(Mappings.frontLeftMotorChannel);
  private Talon frontRightDrive = new Talon(Mappings.frontRightMotorChannel);
  private Talon backLeftDrive = new Talon(Mappings.backLeftMotorChannel);
  private Talon backRightDrive = new Talon(Mappings.backRightMotorChannel);

  private SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontLeftDrive, backLeftDrive);
  private SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontRightDrive, backRightDrive);

  private DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);

  public Drivetrain() {
    super("Drivetrain");
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManualDrive());
  }

  public void drive(double move, double turn) {
    drive.arcadeDrive(move, turn);
  }

  public void tankDrive(double leftPower, double rightPower){
    frontLeftDrive.set(leftPower);
    frontRightDrive.set(rightPower);
    backLeftDrive.set(leftPower);
    backRightDrive.set(rightPower);
  }

}