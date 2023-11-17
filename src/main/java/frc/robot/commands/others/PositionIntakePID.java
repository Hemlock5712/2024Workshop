// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.others;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.others.IntakePositionPID;

public class PositionIntakePID extends Command {
  /** Creates a new IntakeEncoderPosition. */
  IntakePositionPID m_intake;
  double m_position;

  /** Creates a new Intake. */
  public PositionIntakePID(IntakePositionPID intake, double position) {
    m_intake = intake;
    m_position = position;
    addRequirements(m_intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intake.setPosition(m_position);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.setPosition(m_intake.getPostion());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
