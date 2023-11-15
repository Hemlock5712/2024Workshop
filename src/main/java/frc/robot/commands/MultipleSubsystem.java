// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.Intake;

public class MultipleSubsystem extends Command {
  Intake m_intake;
  FlyWheel m_flyWheel;
  double m_intakeSpeed;
  double m_flyWheelSpeed;


  /** Creates a new MultiSub. */
  public MultipleSubsystem(Intake intake, FlyWheel flyWheel, double intakeSpeed,
      double flyWheelSpeed) {
    m_intake = intake;
    m_flyWheel = flyWheel;
    m_intakeSpeed = intakeSpeed;
    m_flyWheelSpeed = flyWheelSpeed;
    addRequirements(m_intake, m_flyWheel);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_flyWheel.setFlyWheelSpeed(m_flyWheelSpeed);
    m_intake.setIntakeSpeed(m_intakeSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_flyWheel.setFlyWheelSpeed(0);
    m_intake.setIntakeSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
