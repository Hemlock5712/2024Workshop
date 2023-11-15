// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FlyWheelVelocityPID;
import frc.robot.util.TunableNumber;

public class SpinFlyWheelEncoder extends Command {
  FlyWheelVelocityPID m_flyWheel;
  double m_speed;

  /** Creates a new FlyWheel. */
  public SpinFlyWheelEncoder(FlyWheelVelocityPID flyWheel, double speed) {
    m_flyWheel = flyWheel;
    m_speed = speed;
    addRequirements(m_flyWheel);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  public SpinFlyWheelEncoder(FlyWheelVelocityPID flyWheel, TunableNumber speed) {
    m_flyWheel = flyWheel;
    m_speed = speed.get();
    addRequirements(m_flyWheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_flyWheel.setFlyWheelSpeed(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_speed = 0;
    m_flyWheel.setFlyWheelSpeed(m_speed);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
