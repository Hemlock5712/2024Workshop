// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FlyWheelVelocityPID;
import frc.robot.util.TunableNumber;

public class SpinFlyWheelPID extends Command {
  FlyWheelVelocityPID m_flyWheel;
  TunableNumber m_targetSpeed = new TunableNumber("FlyWheel Target Speed", 0);

  /** Creates a new FlyWheel. */
  public SpinFlyWheelPID(FlyWheelVelocityPID flyWheel) {
    m_flyWheel = flyWheel;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_flyWheel.setTargetSpeed(m_targetSpeed.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_flyWheel.setTargetSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
