// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FlyWheel extends SubsystemBase {
  private TalonFX m_flywheel = new TalonFX(20);
  private double targetSpeed = 0;

  /** Creates a new FlyWheel. */
  public FlyWheel() {
  }

  public void setFlyWheelSpeed(double speed) {
    targetSpeed = speed;
  }

  public double getFlyWheelSpeed() {
    return targetSpeed;
  }

  @Override
  public void periodic() {
    m_flywheel.set(targetSpeed);
    // This method will be called once per scheduler run
  }
}
