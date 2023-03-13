package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Drivetrain;

public class Vision extends SubsystemBase{
    
    private double[] grabTag(){
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tid").getDoubleArray(new double[6]);
    }
    public void movePath(){
        // PIDController pid = new PIDController(kP, kI, kD);

        double[] data = grabTag();
        // casting to an int
        switch ((int)(data[0])) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }
    }
}
