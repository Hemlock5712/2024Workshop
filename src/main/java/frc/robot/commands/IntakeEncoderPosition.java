// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FalconEncoderPosition;
import frc.robot.util.TunableNumber;

public class IntakeEncoderPosition extends Command {
  /** Creates a new IntakeEncoderPosition. */
  FalconEncoderPosition m_falconIntake;
  double m_position;
  /** Creates a new Intake. */
  public IntakeEncoderPosition(FalconEncoderPosition falconIntake, double position) {
    m_falconIntake = falconIntake;
    m_position = position;
    addRequirements(m_falconIntake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  public IntakeEncoderPosition(FalconEncoderPosition falconIntake, TunableNumber speed){
    m_falconIntake = falconIntake;
    m_position = speed.get();
    addRequirements(m_falconIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_falconIntake.setPosition(m_position);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_position = 0;
    m_falconIntake.setPosition(m_position);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
