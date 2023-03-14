// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;


  private Drivetrain m_drivetrain;
  private Arm m_arm;

  private XboxController XboxController0;
  private XboxController XboxController1;


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code. 
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    m_drivetrain = new Drivetrain();
    m_arm = new Arm();
    XboxController0 = new XboxController(Constants.XboxController0);
    XboxController1 = new XboxController(Constants.XboxController1);

    m_drivetrain.calibrateAHRS();
    m_arm.resetEncoder();

    UsbCamera camera = CameraServer.startAutomaticCapture();
    camera.setResolution(240, 190);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    m_drivetrain.reloadDash();
    m_arm.reloadDash();

  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    // m_drivetrain.closeAHRS();
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }

    m_drivetrain.calibrateAHRS();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    Vision.movePath(m_drivetrain);
    // m_drivetrain.balance();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    XboxController1 = new XboxController(Constants.XboxController1);
    m_drivetrain.calibrateAHRS();
    

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    double get0LeftY = XboxController0.getLeftY();
    double get0RightY = XboxController0.getRightY();
    double get1LeftY = XboxController1.getLeftY();
    double get1RightY = XboxController1.getRightY();
    double armSpeed = Constants.armSpeed;
    double intakeSpeed = Constants.intakeSpeed;

    if (XboxController0.getLeftBumper()) {
      get0LeftY = 1;
      get0RightY = 1;
    }
    if (XboxController0.getRightBumper()) {
      get0LeftY = -1;
      get0RightY = -1;
    }

    m_drivetrain.tankDrive(get0LeftY, get0RightY);

    // if bumpers, read bumpers, else read leftStick
    if(XboxController1.getLeftBumper()) {
      m_arm.raise(armSpeed);
    } else
    if(XboxController1.getRightBumper()) {
      m_arm.raise(-armSpeed);
    } else {
      m_arm.raise(-get1LeftY * armSpeed);
    }

    // UNTESTED
    // if triggers, read triggers, else read rightStick
    if(XboxController1.getLeftTriggerAxis() > 0) {
      m_arm.intake(XboxController1.getLeftTriggerAxis() * intakeSpeed);
    } else
    if(XboxController1.getRightTriggerAxis() > 0) {
      m_arm.intake(-XboxController1.getRightTriggerAxis() * intakeSpeed);
    } else {
      m_arm.intake(get1RightY * intakeSpeed);
    }
  }




  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
    // CameraServer.startAutomaticCapture();

    m_drivetrain.calibrateAHRS();

    }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    double getLeftY = XboxController0.getLeftY();
    double getRightY = XboxController0.getRightY();

    m_drivetrain.tankDrive(getLeftY, getRightY);

    if(XboxController0.getXButton()) { //11
      m_drivetrain.testMotor(1);
    }
    if(XboxController0.getYButton()) { //12
      m_drivetrain.testMotor(2);
    }
    if(XboxController0.getAButton()) { //13
      m_drivetrain.testMotor(3);
    }
    /* ============================= */
    switch (XboxController0.getPOV()) {
      case   0:m_drivetrain.testMotor(4);break;
      case  90:m_drivetrain.testMotor(5);break;
      case 180:m_drivetrain.testMotor(6);break;
    
      default:
        break;
    }

    if(XboxController0.getLeftBumper()) {
      m_arm.raise(Constants.armSpeed);
    } else
    if(XboxController0.getRightBumper()) {
      m_arm.raise(-Constants.armSpeed);
    } else {
      m_arm.raise(0);
    }
    
    if(XboxController0.getLeftTriggerAxis() > 0) {
      m_arm.intake(XboxController0.getLeftTriggerAxis() * Constants.intakeSpeed);
    } else {
      m_arm.intake(-XboxController0.getRightTriggerAxis() * Constants.intakeSpeed);
    }

  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
