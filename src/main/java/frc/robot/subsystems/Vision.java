package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
// import frc.robot.subsystems.Drivetrain;

public class Vision extends SubsystemBase{
    
    private static double getTag(){
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tid").getDouble(0);
    }

    // public void reloadDash() {
    //     SmartDashboard.putNumber("AprilTag seen", getTag());
    // }
    
    public static void movePath(Drivetrain m_drivetrain){
        
        PIDController pid = new PIDController(Constants.kP, Constants.kI, Constants.kD);
        double data = getTag();
        // System.out.println("hi");
        System.out.println(data);
        System.out.println((int)(data));
        // // casting to an int
        m_drivetrain.tankDrive((pid.calculate(m_drivetrain.getLeftEncoder(), 500)),(pid.calculate(m_drivetrain.getRightEncoder(), 500)));
        // switch ((int)(data)) {
        //     // things that need to be accounted for: turning, game element, auto-balancing, distance, other bots(?)
        //     case 1:
        //         m_drivetrain.tankDrive((pid.calculate(m_drivetrain.getLeftEncoder(), 5)),(pid.calculate(m_drivetrain.getRightEncoder(), 5)));
        //         break;
        //     case 2:
        //         break;
        //     case 3:
        //         break;
        //     case 4:
        //         break;
        //     case 5:
        //         break;
        //     case 6:
        //         break;
        //     case 7:
        //         break;
        //     case 8:
        //         break;
        // }
    }
}
