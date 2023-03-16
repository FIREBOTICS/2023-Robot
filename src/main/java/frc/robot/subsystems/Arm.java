package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.fasterxml.jackson.databind.deser.impl.MergingSettableBeanProperty;
import com.revrobotics.REVLibError;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
    private final VictorSPX m_lifter;
    private final VictorSPX m_intake;
    private final DutyCycleEncoder m_DCencoder;
    // private final PIDController m_pid;

    public Arm() {
        m_lifter = new VictorSPX(Constants.arm_CAN[0]);
        m_intake = new VictorSPX(Constants.arm_CAN[1]);
        m_DCencoder = new DutyCycleEncoder(2);
        // m_pid = new PIDController(0, 0, 0);
    }

    //unimplemented and untested
    public void encoderArmMoveHalfway(){
        double maxHeightRotations = 21;
        double minHeightRotations = 19;
        double speed = 0.5;
        // if(m_Rencoder.getDistance() < maxHeightRotations){
        //     m_lifter.set(ControlMode.PercentOutput, -speed);
        // }
        // else if(m_Rencoder.getDistance() > minHeightRotations){
        //     m_lifter.set(ControlMode.PercentOutput, speed);
        // }
    }

    public double getEncoder() {
        // return -((double)(int)(m_DCencoder.getAbsolutePosition()*100)/100 - 0.75);
        return (double)(int)(m_DCencoder.getAbsolutePosition()*100)/100;
        //33,36,74, 52, 62
    }
    public void reloadDash() {
        SmartDashboard.putNumber("Arm [A] Encoder Position", getEncoder()*100);
    }

    public void raise(double speed) {
        if (getEncoder() < 0.34 && speed < 0) return;
        m_lifter.set(ControlMode.PercentOutput, speed);
    }

    public void intake(double speed) {
        m_intake.set(ControlMode.PercentOutput, speed);
    }

    public void resetEncoder() {
        m_DCencoder.reset();
    }

}
