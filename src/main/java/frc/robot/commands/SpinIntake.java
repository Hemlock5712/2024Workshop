// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class SpinIntake extends Command {
  Intake m_falconIntake;
  double m_speed;

  /** Creates a new Intake. */
  public SpinIntake(Intake falconIntake, double speed) {
    m_falconIntake = falconIntake;
    m_speed = speed;
    addRequirements(m_falconIntake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_falconIntake.setIntakeSpeed(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_speed = 0;
    m_falconIntake.setIntakeSpeed(m_speed);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
