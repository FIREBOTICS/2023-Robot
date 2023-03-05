package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.RelativeEncoder;


public class Drivetrain extends SubsystemBase {
    // set up each motor and combine into full drivetrain
    CANSparkMax m_frontLeft = new CANSparkMax(Constants.left_DT_CAN[0], MotorType.kBrushless);
    CANSparkMax m_middleLeft = new CANSparkMax(Constants.left_DT_CAN[1], MotorType.kBrushless);
    CANSparkMax m_backLeft = new CANSparkMax(Constants.left_DT_CAN[2], MotorType.kBrushless);
    RelativeEncoder m_leftEncoder = m_middleLeft.getEncoder();
    MotorControllerGroup m_left = new MotorControllerGroup(m_frontLeft, m_middleLeft, m_backLeft);

    CANSparkMax m_frontRight  = new CANSparkMax(Constants.rite_DT_CAN[0], MotorType.kBrushless);
    CANSparkMax m_middleRight = new CANSparkMax(Constants.rite_DT_CAN[1], MotorType.kBrushless);
    CANSparkMax m_backRight = new CANSparkMax(Constants.rite_DT_CAN[2], MotorType.kBrushless);
    RelativeEncoder m_rightEncoder = m_middleRight.getEncoder();
    MotorControllerGroup m_right = new MotorControllerGroup(m_frontRight, m_middleRight, m_backRight);

    DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

    public void sparksInit() {
        m_frontLeft.restoreFactoryDefaults();
            // m_frontLeft.setInverted(true);
        m_middleLeft.restoreFactoryDefaults();
        
        m_backLeft.restoreFactoryDefaults();
        
        m_frontRight.restoreFactoryDefaults();
        m_middleRight.restoreFactoryDefaults();
        m_backRight.restoreFactoryDefaults();

        m_frontLeft.setInverted(true);
        m_left.setInverted(true);
    }

    public void encodersInit() {
        m_leftEncoder.setPosition(0);
        m_rightEncoder.setPosition(0);
    }

    public void tankDrive(double y_left, double y_right){
        //square values while maintaining sign
        y_left  *= Math.abs(y_left)  * Constants.drivetrainPower;
        y_right *= Math.abs(y_right) * Constants.drivetrainPower;
        m_drive.tankDrive(y_left, y_right);
    }

    public void testMotorL1() {
        m_frontLeft.set(Constants.drivetrainPower);
    }
    public void testMotorL2() {
        m_middleLeft.set(Constants.drivetrainPower);
    }
    public void testMotorL3() {
        m_backLeft.set(Constants.drivetrainPower);
    }

    public void testMotorR1() {
        m_frontRight.set(Constants.drivetrainPower);
    }
    public void testMotorR2() {
        m_middleRight.set(Constants.drivetrainPower);
    }
    public void testMotorR3() {
        m_backRight.set(Constants.drivetrainPower);
    }

    //see the values in the SmartDashboard Application
    public void getLeftEncoder(){
         /**
         * Encoder position is read from a RelativeEncoder object by calling the
         * GetPosition() method.
         * 
         * GetPosition() returns the position of the encoder in units of revolutions
         */
        SmartDashboard.putNumber("Left Encoder Position", m_leftEncoder.getPosition());
        /**
         * Encoder velocity is read from a RelativeEncoder object by calling the
         * GetVelocity() method.
         * 
         * GetVelocity() returns the velocity of the encoder in units of RPM
         */
        SmartDashboard.putNumber("Left Encoder Velocity", m_leftEncoder.getVelocity());
    }

    public void getRightEncoder(){
        SmartDashboard.putNumber("Right Encoder Position", m_rightEncoder.getPosition());
        SmartDashboard.putNumber("Right Encoder Velocity", m_rightEncoder.getVelocity());

    }
}
