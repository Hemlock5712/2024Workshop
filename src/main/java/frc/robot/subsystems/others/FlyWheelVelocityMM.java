// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.others;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.TunableNumber;

public class FlyWheelVelocityMM extends SubsystemBase {
  private TalonFX flyWheel = new TalonFX(20);
  /* Be able to switch which control request to use based on a button press */
  /* Start at velocity 0, enable FOC, no feed forward, use slot 0 */
  private double targetSpeed = 0;
  private final VelocityVoltage m_voltageVelocity = new VelocityVoltage(targetSpeed, 0, false, 0, 0, false);

  double kP = 0.0;
  double kI = 0.0;
  double kD = 0.0;
  double kS = 0.0;
  double kV = 0.0;

  TunableNumber mmV = new TunableNumber("FlyWheel mmV Gain", 0.0);
  TunableNumber mmA = new TunableNumber("FlyWheel mmA Gain", 0.0);
  TunableNumber mmJ = new TunableNumber("FlyWheel mmJ Gain", 0.0);
  TunableNumber kFF = new TunableNumber("FlyWheel FF Gain", 0.0);

  TalonFXConfiguration configs = new TalonFXConfiguration();
  MotionMagicConfigs mm_configs;

  /** Creates a new flyWheelEncoder. */
  public FlyWheelVelocityMM() {
    /*
     * Voltage-based velocity requires a feed forward to account for the back-emf of
     * the motor
     */
    configs.Slot0.kP = kP; // An error of 1 rotation per second results in 2V output
    configs.Slot0.kI = kI; // An error of 1 rotation per second increases output by 0.5V every second
    configs.Slot0.kD = kD; // A change of 1 rotation per second squared results in 0.01 volts output

    configs.Slot0.kS = kS; // Out put to overcome static friction
    configs.Slot0.kV = kV; // Falcon 500 is a 500kV motor, 500rpm per V = 8.333 rps per V, 1/8.33 = 0.12
                           // volts / Rotation per second

    mm_configs = configs.MotionMagic;
    mm_configs.MotionMagicCruiseVelocity = 80;
    mm_configs.MotionMagicAcceleration = 160;
    mm_configs.MotionMagicJerk = 1600;

    /* Retry config apply up to 5 times, report if failure */
    StatusCode status = StatusCode.StatusCodeNotInitialized;
    for (int i = 0; i < 5; ++i) {
      status = flyWheel.getConfigurator().apply(mm_configs);
      if (status.isOK())
        break;
    }
    if (!status.isOK()) {
      System.out.println("Could not apply configs, error code: " + status.toString());
    }

    flyWheel.setControl(m_voltageVelocity.withVelocity(targetSpeed).withFeedForward(kFF.get()));
  }

  public void setFlyWheelSpeed(double speed) {
    targetSpeed = speed;
  }

  public double getTargetFlyWheelSpeed() {
    return targetSpeed;
  }

  public double getFlyWheelSpeed() {
    return flyWheel.getVelocity().getValueAsDouble();
  }

  private void setPID() {
    if (mmV.hasChanged()) {
      mm_configs.MotionMagicCruiseVelocity = mmV.get();
      flyWheel.getConfigurator().apply(mm_configs);
    }
    if (mmA.hasChanged()) {
      mm_configs.MotionMagicAcceleration = mmA.get();
      flyWheel.getConfigurator().apply(mm_configs);
    }
    if (mmJ.hasChanged()) {
      mm_configs.MotionMagicJerk = mmJ.get();
      flyWheel.getConfigurator().apply(mm_configs);
    }
    if (kFF.hasChanged()) {
      flyWheel.setControl(m_voltageVelocity.withFeedForward(kFF.get()));
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    setPID();
    flyWheel.setControl(m_voltageVelocity.withVelocity(targetSpeed));

  }
}
