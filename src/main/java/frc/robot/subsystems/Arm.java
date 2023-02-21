package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {

    private final VictorSPX m_elbow = new VictorSPX(Constants.arm_CAN[0]);
    private final VictorSPX m_wrist = new VictorSPX(Constants.arm_CAN[1]);
    private final VictorSPX m_extend = new VictorSPX(Constants.arm_CAN[2]);

    public void raise(double speed) {
        m_elbow.set(ControlMode.PercentOutput, speed);
    }

    public void extend(double speed) {
        m_extend.set(ControlMode.PercentOutput, speed);
    }

    public void retract(double speed) {
        m_extend.set(ControlMode.PercentOutput, speed * -1);
    }
}
