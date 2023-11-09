// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SparkMaxEncoder extends SubsystemBase {
  private CANSparkMax motor = new CANSparkMax(8, MotorType.kBrushless);
  private CANcoder encoder = new CANcoder(1);
  private double kP = 0.0;
  private double kI = 0.0;
  private double kD = 0.0;
  private PIDController pidController = new PIDController(kP, kI, kD);
  private double targetSpeed = 0;
  
  /** Creates a new SparkMaxEncoder. */

  public SparkMaxEncoder() {
    motor.setIdleMode(CANSparkMax.IdleMode.kBrake);
  }

  public void setFlyWheelSpeed(double speed) {
    targetSpeed = speed;
  }

  public double getTargetFlyWheelSpeed(){
    return targetSpeed;
  }

  public double getEncoderValue() {
    return encoder.getVelocity().getValueAsDouble();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double error = pidController.calculate(getEncoderValue(), targetSpeed);
    motor.set(error);
  }
}
