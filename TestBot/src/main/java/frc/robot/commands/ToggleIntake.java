package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.IntakeSubsystem;


/**
 *
 */

public class ToggleIntake extends Command {

	private IntakeSubsystem ballIntake;
    private double leftPower;
    private double rightPower;
  

    public ToggleIntake(double leftPower, double rightPower) {
		  super("ManualIntake");
          this.leftPower = leftPower;
          this.rightPower = rightPower;
		  requires(this.ballIntake = IntakeSubsystem.getIntakeSubsystem());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (ballIntake.getLastLeft() == leftPower) { //A toggle making it so if the driver presses the same button twice, the intake turns off.
    		ballIntake.setIntake(0, 0);
    	} else {
    		ballIntake.setIntake(leftPower, -rightPower);
    	}
    	
    	
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {

    }
}