package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
// import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;




public class Drivetrain extends SubsystemBase {
    // set up each motor and combine into full drivetrain
    CANSparkMax m_frontLeft, m_middleLeft, m_backLeft;
    CANSparkMax m_frontRight, m_middleRight, m_backRight;
    RelativeEncoder m_leftEncoder, m_rightEncoder;

    MotorControllerGroup m_left;
    MotorControllerGroup m_right;

    private AHRS ahrs;

    DifferentialDrive m_drive;
    MecanumDrive mecanumDrive;
    // can use Xbox Controller as a joystick
    Joystick stick = new Joystick(Constants.XboxController0);

    public Drivetrain() {
        m_frontLeft = new CANSparkMax(Constants.left_DT_CAN[0], MotorType.kBrushless);
            m_frontLeft.restoreFactoryDefaults();
            m_frontLeft.setInverted(true);
        m_middleLeft = new CANSparkMax(Constants.left_DT_CAN[1], MotorType.kBrushless);
            m_middleLeft.restoreFactoryDefaults();
        m_backLeft = new CANSparkMax(Constants.left_DT_CAN[2], MotorType.kBrushless);
            m_backLeft.restoreFactoryDefaults();

        m_frontRight  = new CANSparkMax(Constants.rite_DT_CAN[0], MotorType.kBrushless);
            m_frontRight.restoreFactoryDefaults();
        m_middleRight = new CANSparkMax(Constants.rite_DT_CAN[1], MotorType.kBrushless);
            m_middleRight.restoreFactoryDefaults();
        m_backRight = new CANSparkMax(Constants.rite_DT_CAN[2], MotorType.kBrushless);
            m_backRight.restoreFactoryDefaults();


        m_left = new MotorControllerGroup(m_frontLeft, m_middleLeft, m_backLeft);
            // m_left.setInverted(true);
        m_right = new MotorControllerGroup(m_frontRight, m_middleRight, m_backRight);
        m_drive = new DifferentialDrive(m_left, m_right);
        

        m_leftEncoder = m_middleLeft.getEncoder();
            m_leftEncoder.setPosition(0);
        m_rightEncoder = m_middleRight.getEncoder();
            m_rightEncoder.setPosition(0);

        ahrs = new AHRS(SerialPort.Port.kMXP);
        ahrs.reset();

        mecanumDrive = new MecanumDrive(m_frontLeft, m_backLeft, m_frontRight, m_backRight);
    }

    public void tankDrive(double y_left, double y_right){
        //square values while maintaining sign
        y_left  *= Math.abs(y_left)  * Constants.drivetrainPower;
        y_right *= Math.abs(y_right) * Constants.drivetrainPower;
        m_drive.tankDrive(y_left, y_right);
    }

    public void testMotor(int motor) {
        switch (motor) {
            case 1:m_frontLeft.set(Constants.drivetrainPower);  break;
            case 2:m_middleLeft.set(Constants.drivetrainPower); break;
            case 3:m_backLeft.set(Constants.drivetrainPower);   break;
            case 4:m_frontRight.set(Constants.drivetrainPower); break;
            case 5:m_middleRight.set(Constants.drivetrainPower);break;
            case 6:m_backRight.set(Constants.drivetrainPower);  break;
        }
    }

    public void reloadDash(){
        SmartDashboard.putNumber("Left Encoder Position", m_leftEncoder.getPosition());
        SmartDashboard.putNumber("Left Encoder Velocity", m_leftEncoder.getVelocity());
        SmartDashboard.putNumber("Right Encoder Position", m_rightEncoder.getPosition());
        SmartDashboard.putNumber("Right Encoder Velocity", m_rightEncoder.getVelocity());
        SmartDashboard.putData(ahrs);
    }

    public void calibrateAHRS() {
        ahrs.calibrate();
    }
    public void closeAHRS() {
        ahrs.close();
    }

    public void balance(){
        //https://pdocs.kauailabs.com/navx-mxp/examples/automatic-balancing/
        double anglePitch = ahrs.getPitch();
        double angleRoll = ahrs.getRoll();
        double xAxis = 0;
        double yAxis = 0;
        // determine if modification to drive is necessary
        if(!Constants.autoBalancing && (Math.abs(anglePitch) >= Constants.angleOffThreshold)){
            Constants.autoBalancing = true;
        }
        else if(Constants.autoBalancing && (Math.abs(anglePitch) <= Constants.angleOnThreshold)){
            Constants.autoBalancing = false;
        }
        // correct by reverse driving
        if(Constants.autoBalancing){
            xAxis = Math.sin((anglePitch * (Math.PI/180))) * -1;
            yAxis = Math.sin((angleRoll * (Math.PI/180))) * -1;
        }
        mecanumDrive.driveCartesian(xAxis, yAxis, stick.getTwist());
        Timer.delay(0.005);
    }
}
