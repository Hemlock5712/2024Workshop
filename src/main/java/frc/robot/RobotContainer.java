// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.SpinIntake;
import frc.robot.commands.MultipleSubsystem;
import frc.robot.commands.SpinFlyWheelFast;
import frc.robot.commands.SpinFlyWheelPID;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.FlyWheelVelocityPID;

public class RobotContainer {
  private final CommandXboxController controller = new CommandXboxController(0);
  private final FlyWheel flyWheel = new FlyWheel();
  private final Intake intake = new Intake();
  private final FlyWheelVelocityPID flyWheelPID = new FlyWheelVelocityPID();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    controller.a().whileTrue(new SpinFlyWheelFast(flyWheel));
    controller.leftBumper()
        .whileTrue(new ParallelCommandGroup(new SpinFlyWheelFast(flyWheel), new SpinIntake(intake, 1)));
    controller.x().whileTrue(new MultipleSubsystem(intake, flyWheel, 0.5, 0.1));
    controller.b().whileTrue(new SpinFlyWheelPID(flyWheelPID));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
