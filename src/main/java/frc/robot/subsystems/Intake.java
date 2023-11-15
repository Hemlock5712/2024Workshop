// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private CANSparkMax motor = new CANSparkMax(18, MotorType.kBrushless);
  private double targetSpeed = 0;

  public Intake() {
    motor.setIdleMode(CANSparkMax.IdleMode.kBrake);
  }

  public void setIntakeSpeed(double speed) {
    targetSpeed = speed;
  }

  public double getIntakeSpeed() {
    return targetSpeed;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    motor.set(targetSpeed);
  }
}