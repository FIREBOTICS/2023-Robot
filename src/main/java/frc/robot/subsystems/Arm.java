package frc.robot.subsystems;

import java.lang.invoke.ConstantCallSite;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {

    private final VictorSPX m_lifter = new VictorSPX(Constants.arm_CAN[0]);
    private final VictorSPX m_intake = new VictorSPX(Constants.arm_CAN[1]);

    public void raise(double speed) {
        m_lifter.set(ControlMode.PercentOutput, speed * Constants.armSpeed);
    }

    public void intake(double speed) {
        m_intake.set(ControlMode.PercentOutput, speed * Constants.intakeSpeed);
    }

}
