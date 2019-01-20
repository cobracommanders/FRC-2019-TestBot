/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Mappings;
import frc.robot.commands.ManualDrive;
import edu.wpi.first.wpilibj.Encoder;


public class Drivetrain extends Subsystem {

  //math for the encoder 
  private static final double WheelDiameter = 0.1524; //6 inch wheeels. This was coverted to meters
  private static final double PulsePerRevolution = 2048; //all switches on the encoder are off
  private static final double WheelCircumference = WheelDiameter *Math.PI;
  private static final double MetersPerPulse = WheelCircumference / PulsePerRevolution;


  private static Drivetrain drivetrain = null;

  public static Drivetrain getDrivetrain() {
    drivetrain = drivetrain == null ? new Drivetrain() : drivetrain;
    return drivetrain;
  }


  // TODO: may need to change the motor controllers and will need to change the motor channels.
  private Spark frontLeftDrive = new Spark(Mappings.frontLeftMotorChannel);
  private Spark frontRightDrive = new Spark(Mappings.frontRightMotorChannel);
  private Spark backLeftDrive = new Spark(Mappings.backLeftMotorChannel);
  private Spark backRightDrive = new Spark(Mappings.backRightMotorChannel);

  private SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontLeftDrive, backLeftDrive);
  private SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontRightDrive, backRightDrive);

  private DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);


  //TODO: get the digital sources for the encoders
  public Encoder leftEncoder = new Encoder(Mappings.leftEncoderDigitalSource1, Mappings.leftEncoderDigitalSource2); 
  public Encoder rightEncoder = new Encoder(Mappings.rightEncoderDigitalSource1, Mappings.rightEncoderDigitalSource2);

  public Drivetrain() {
    super("Drivetrain");
    leftEncoder.setDistancePerPulse(MetersPerPulse);
    rightEncoder.setDistancePerPulse(MetersPerPulse);

  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManualDrive());
  }

  public void drive(double move, double turn) {
    drive.arcadeDrive(move, turn);
  }

  public double getDistance() {
    //averages the encoders distance
    return(leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
  }

  public void tankDrive(double leftPower, double rightPower){
    frontLeftDrive.set(leftPower);
    frontRightDrive.set(rightPower);
    backLeftDrive.set(leftPower);
    backRightDrive.set(rightPower);
  }

}