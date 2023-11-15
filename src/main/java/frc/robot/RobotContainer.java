// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.SpinFlyWheel;
import frc.robot.commands.SpinFlyWheelPID;
import frc.robot.commands.MultipleSubsystem;
import frc.robot.commands.SpinIntakeFast;
import frc.robot.commands.SpinIntakePID;
import frc.robot.commands.SpinIntakeSlow;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.FlyWheelVelocityPID;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeVelocityPID;
import frc.robot.util.TunableNumber;

public class RobotContainer {
  private final CommandXboxController controller = new CommandXboxController(0);
  // private final Intake intake = new Intake();
  // private final FlyWheel flyWheel = new FlyWheel();
  private final FlyWheelVelocityPID flyWheel = new FlyWheelVelocityPID();
  private final IntakeVelocityPID intakePID = new IntakeVelocityPID();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    // controller.a().whileTrue(new SpinIntakeFast(intake));
    // controller.b().whileTrue(new SpinIntakeSlow(intake));
    // controller.leftBumper().whileTrue(new ParallelCommandGroup(new
    // SpinIntakeFast(intake), new SpinFlyWheel(flyWheel, 1)));
    // controller.x().whileTrue(new MultipleSubsystem(intake, flyWheel, 0.5, -0.1));
    controller.b().whileTrue(new SpinIntakePID(intakePID));
    controller.a().whileTrue(new SpinFlyWheelPID(flyWheel));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
