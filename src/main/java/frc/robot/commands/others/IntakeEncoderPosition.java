// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.others;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.others.FalconPosition;
import frc.robot.util.TunableNumber;

public class IntakeEncoderPosition extends Command {
  /** Creates a new IntakeEncoderPosition. */
  FalconPosition m_falconIntake;
  double m_position;

  /** Creates a new Intake. */
  public IntakeEncoderPosition(FalconPosition falconIntake, double position) {
    m_falconIntake = falconIntake;
    m_position = position;
    addRequirements(m_falconIntake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  public IntakeEncoderPosition(FalconPosition falconIntake, TunableNumber speed) {
    m_falconIntake = falconIntake;
    m_position = speed.get();
    addRequirements(m_falconIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_falconIntake.setPosition(m_position);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
