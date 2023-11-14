// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.Intake;
import frc.robot.commands.MultiSub;
import frc.robot.commands.SpinSparkMaxFast;
import frc.robot.subsystems.FalconIntake;
import frc.robot.subsystems.SparkMaxFlyWheel;
import frc.robot.util.TunableNumber;

public class RobotContainer {
  private final CommandXboxController controller = new CommandXboxController(0);
  private final SparkMaxFlyWheel flyWheel = new SparkMaxFlyWheel();
  private final FalconIntake intake = new FalconIntake();
  private TunableNumber intakeSpeed = new TunableNumber("Intake Speed", 0);
  private TunableNumber flyWheelSpeed = new TunableNumber("FlyWheel Speed", 0);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    controller.a().whileTrue(new SpinSparkMaxFast(flyWheel));
    controller.b().whileTrue(new Intake(intake, intakeSpeed));
    controller.x().whileTrue(new MultiSub(intake, flyWheel, intakeSpeed, flyWheelSpeed));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
