// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.TunableNumber;

public class IntakeVelocityPID extends SubsystemBase {
  private CANSparkMax motor = new CANSparkMax(18, MotorType.kBrushless);
  SparkMaxPIDController pidController;
  private double targetSpeed = 0;

  TunableNumber kP = new TunableNumber("Intake P Gain", 0.0); // .000008
  TunableNumber kI = new TunableNumber("Intake I Gain", 0.0);
  TunableNumber kD = new TunableNumber("Intake D Gain", 0.0);
  TunableNumber kFF = new TunableNumber("Intake FF Gain", 0.0); //.000107

  /** Creates a new SparkMaxClosedLoop. */
  public IntakeVelocityPID() {
    pidController = motor.getPIDController();
    pidController.setP(kP.get(), 0);
    pidController.setI(kI.get(), 0);
    pidController.setD(kD.get(), 0);
    pidController.setFF(kFF.get(), 0);
    pidController.setOutputRange(0, 1, 0);
  }

  public double getTargetSpeed() {
    return targetSpeed;
  }

  public void setTargetSpeed(double speed) {
    targetSpeed = speed;
  }

  private void setPID() {
    if (kP.hasChanged()) {
      pidController.setP(kP.get());
    }
    if (kI.hasChanged()) {
      pidController.setI(kI.get());
    }
    if (kD.hasChanged()) {
      pidController.setD(kD.get());
    }
    if (kFF.hasChanged()) {
      pidController.setFF(kFF.get());
    }
  }

  @Override
  public void periodic() {
    setPID();
    pidController.setReference(targetSpeed, ControlType.kVelocity, 0);
    SmartDashboard.putNumber("IntakeSpeed", motor.getEncoder().getVelocity());
    // This method will be called once per scheduler run
  }
}
