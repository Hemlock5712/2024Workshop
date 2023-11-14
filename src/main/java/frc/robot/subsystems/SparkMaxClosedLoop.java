// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SparkMaxClosedLoop extends SubsystemBase {
  private CANSparkMax motor = new CANSparkMax(8, MotorType.kBrushless);
  SparkMaxPIDController pidController;
  private double targetSpeed = 0;

  private double kP = 0.0;
  private double kI = 0.0;
  private double kD = 0.0;

  /** Creates a new SparkMaxClosedLoop. */
  public SparkMaxClosedLoop() {
    this.pidController = motor.getPIDController();

    pidController.setP(kP, 0);
    pidController.setI(kI, 0);
    pidController.setD(kD, 0);
  }

  public double getTargetSpeed() {
    return targetSpeed;
  }

  public void setTargetSpeed(double targetSpeed) {
    this.targetSpeed = targetSpeed;
  }

  @Override
  public void periodic() {
    pidController.setReference(targetSpeed, ControlType.kVelocity, 0);
    // This method will be called once per scheduler run
  }
}
