// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FlyWheel extends SubsystemBase {
  private TalonFX m_leader = new TalonFX(20);
  private double targetSpeed = 0;

  TalonFXConfiguration configs = new TalonFXConfiguration();

  /** Creates a new FlyWheel. */
  public FlyWheel() {
    configs.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    m_leader.getConfigurator().apply(configs);
    m_leader.setControl(new Follower(19, false));
  }

  public void setFlyWheelSpeed(double speed) {
    targetSpeed = speed;
  }

  public double getFlyWheelSpeed() {
    return targetSpeed;
  }

  @Override
  public void periodic() {
    m_leader.set(targetSpeed);
    // This method will be called once per scheduler run
  }
}
