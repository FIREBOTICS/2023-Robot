package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.REVLibError;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
    private final VictorSPX m_lifter;
    private final VictorSPX m_intake;
    private final Encoder m_encoder;
    private final PIDController m_pid;

    public Arm() {
        m_lifter = new VictorSPX(Constants.arm_CAN[0]);
        m_intake = new VictorSPX(Constants.arm_CAN[1]);
        m_encoder = new Encoder(0, 1, false, Encoder.EncodingType.k2X);
        m_pid = new PIDController(0, 0, 0);
    }

    public void reloadDash() {
        SmartDashboard.putNumber("Arm Encoder Distance", m_encoder.getDistance());
        SmartDashboard.putNumber("Arm Encoder Rate", m_encoder.getRate());
    }

    public void raise(double speed) {
        m_lifter.set(ControlMode.PercentOutput, speed * Constants.armSpeed);
    }

    public void intake(double speed) {
        m_intake.set(ControlMode.PercentOutput, speed * Constants.intakeSpeed);
    }

    public void resetEncoder() {
        m_encoder.reset();
    }

}
