// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FalconIntake;
import frc.robot.subsystems.SparkMaxFlyWheel;
import frc.robot.util.TunableNumber;

public class MultiSub extends Command {
  FalconIntake m_falconIntake;
  SparkMaxFlyWheel m_flyWheel;
  double m_falconIntakeSpeed;
  double m_flyWheelSpeed;

  /** Creates a new MultiSub. */
  public MultiSub(FalconIntake falconIntake, SparkMaxFlyWheel flyWheel, TunableNumber falconIntakeSpeed,
      TunableNumber flyWheelSpeed) {
    m_falconIntake = falconIntake;
    m_flyWheel = flyWheel;
    m_falconIntakeSpeed = falconIntakeSpeed.get();
    m_flyWheelSpeed = flyWheelSpeed.get();
    addRequirements(m_falconIntake, m_flyWheel);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_falconIntake.setIntakeSpeed(m_falconIntakeSpeed);
    m_flyWheel.setFlyWheelSpeed(m_flyWheelSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_falconIntake.setIntakeSpeed(0);
    m_flyWheel.setFlyWheelSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
