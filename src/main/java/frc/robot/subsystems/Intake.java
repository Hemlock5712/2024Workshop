// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private TalonFX falconIntake = new TalonFX(0);
  private double targetSpeed = 0;

  /** Creates a new FalconIntake. */
  public Intake() {
  }

  public void setIntakeSpeed(double speed) {
    targetSpeed = speed;
  }

  public double getIntakeSpeed() {
    return targetSpeed;
  }

  @Override
  public void periodic() {
    falconIntake.set(targetSpeed);
    // This method will be called once per scheduler run
  }
}
