// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.util.TunableNumber;

public class FlyWheelVelocityPID extends SubsystemBase {
  private CANSparkMax motor = new CANSparkMax(8, MotorType.kBrushless);
  SparkMaxPIDController pidController;
  private double targetSpeed = 0;

  TunableNumber kP = new TunableNumber("FlyWheel P Gain", 0.0);
  TunableNumber kI = new TunableNumber("FlyWheel I Gain", 0.0);
  TunableNumber kD = new TunableNumber("FlyWheel D Gain", 0.0);

  /** Creates a new SparkMaxClosedLoop. */
  public FlyWheelVelocityPID() {
    pidController = motor.getPIDController();
    pidController.setP(kP.get(), 0);
    pidController.setI(kI.get(), 0);
    pidController.setD(kD.get(), 0);
  }

  public double getTargetSpeed() {
    return targetSpeed;
  }

  public void setTargetSpeed(double speed) {
    targetSpeed = speed;
  }

  private void setPID() {
    if (Constants.tuningMode) {
      if (kP.hasChanged()) {
        pidController.setP(kP.get());
      }
      if (kI.hasChanged()) {
        pidController.setI(kI.get());
      }
      if (kI.hasChanged()) {
        pidController.setD(kD.get());
      }
    }
  }

  @Override
  public void periodic() {
    setPID();
    pidController.setReference(targetSpeed, ControlType.kVelocity, 0);
    // This method will be called once per scheduler run
  }
}
