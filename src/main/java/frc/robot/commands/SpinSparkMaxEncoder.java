// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SparkMaxEncoder;
import frc.robot.util.TunableNumber;

public class SpinSparkMaxEncoder extends Command {
  SparkMaxEncoder m_sparkMaxEncoder;
  double m_targetSpeed = 0;

  /** Creates a new SpinSparkMaxEncoder. */
  public SpinSparkMaxEncoder(SparkMaxEncoder sparkMaxEncoder, TunableNumber targetSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_sparkMaxEncoder = sparkMaxEncoder;
    m_targetSpeed = targetSpeed.get();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_sparkMaxEncoder.setFlyWheelSpeed(m_targetSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_sparkMaxEncoder.setFlyWheelSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
