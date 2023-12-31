// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeVelocityPID;
import frc.robot.util.TunableNumber;

public class SpinIntakePID extends Command {
  IntakeVelocityPID m_sparkMaxEncoder;
  TunableNumber m_targetSpeed = new TunableNumber("Intake Wheel Target Speed", 0);

  /** Creates a new SpinSparkMaxEncoder. */
  public SpinIntakePID(IntakeVelocityPID sparkMaxEncoder) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_sparkMaxEncoder = sparkMaxEncoder;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_sparkMaxEncoder.setTargetSpeed(m_targetSpeed.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_sparkMaxEncoder.setTargetSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
