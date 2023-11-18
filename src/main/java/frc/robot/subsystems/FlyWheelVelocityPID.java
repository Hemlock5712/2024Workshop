// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.TunableNumber;

public class FlyWheelVelocityPID extends SubsystemBase {
  private TalonFX flyWheel = new TalonFX(20);
  private double targetSpeed = 0;
  private double targetFF = 0.0;
  private TunableNumber kFF = new TunableNumber("FlyWheel FF", 4.8);
  private final VelocityVoltage m_voltageVelocity = new VelocityVoltage(targetSpeed).withSlot(0);

  TalonFXConfiguration configs = new TalonFXConfiguration();

  /** Creates a new flyWheelEncoder. */
  public FlyWheelVelocityPID() {
    /*
     * Voltage-based velocity requires a feed forward to account for the back-emf of
     * the motor
     */
    configs.Slot0.kP = 0.0; // An error of 1 rotation per second results in 2V output
    configs.Slot0.kI = 0.0; // An error of 1 rotation per second increases output by 0.5V every second
    configs.Slot0.kD = 0.0; // A change of 1 rotation per second squared results in 0.01 volts output

    configs.Slot0.kS = 0.0; // Out put to overcome static friction
    configs.Slot0.kV = 0.0; // Falcon 500 is a 500kV motor, 500rpm per V = 8.333 rps per V, 1/8.33 = 0.12
                            // volts / Rotation per second


    configs.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

    /* Retry config apply up to 5 times, report if failure */
    StatusCode status = StatusCode.StatusCodeNotInitialized;
    for (int i = 0; i < 5; ++i) {
      status = flyWheel.getConfigurator().apply(configs);
      if (status.isOK())
        break;
    }
    if (!status.isOK()) {
      System.out.println("Could not apply configs, error code: " + status.toString());
    }
    flyWheel.setControl(new Follower(19, false));
  }

  public void setTargetSpeed(double speed) {
    targetSpeed = speed;
  }

  public double getTargetSpeed() {
    return targetSpeed;
  }

  public double getFlyWheelSpeed() {
    return flyWheel.getVelocity().getValueAsDouble();
  }

  private void setFF(){
    if(kFF.hasChanged()){
      targetFF = kFF.get();
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    setFF();
    flyWheel.setControl(m_voltageVelocity.withVelocity(targetSpeed).withFeedForward(targetFF));
    SmartDashboard.putNumber("FlyWheel Speed", flyWheel.getVelocity().getValueAsDouble());
    //SmartDashboard.putNumber("Test", );
  }
}
