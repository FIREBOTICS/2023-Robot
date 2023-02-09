package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
    CANSparkMax m_frontLeft  = new CANSparkMax(Constants.left_DT_CAN[0], MotorType.kBrushless);
    CANSparkMax m_middleLeft = new CANSparkMax(Constants.left_DT_CAN[1], MotorType.kBrushless);
    CANSparkMax m_backLeft = new CANSparkMax(Constants.left_DT_CAN[2], MotorType.kBrushless);
    MotorControllerGroup m_left = new MotorControllerGroup(m_frontLeft, m_middleLeft, m_backLeft);

    CANSparkMax m_frontRight  = new CANSparkMax(Constants.rite_DT_CAN[0], MotorType.kBrushless);
    CANSparkMax m_middleRight = new CANSparkMax(Constants.rite_DT_CAN[1], MotorType.kBrushless);
    CANSparkMax m_backRight = new CANSparkMax(Constants.rite_DT_CAN[2], MotorType.kBrushless);
    MotorControllerGroup m_right = new MotorControllerGroup(m_frontRight, m_middleRight, m_backRight);


    DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

}
