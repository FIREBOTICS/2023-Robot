// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Drivetrain
    public static int[] left_DT_CAN = {11,12,13};
    public static int[] rite_DT_CAN = {14,15,16};
    public static double drivetrainPower = 0.5;//.5
    public static double TELEOPPower = 1.25;

    public static void toggleTOP() {
        if (TELEOPPower == 1.15) TELEOPPower = 1.25;
        else if (TELEOPPower == 1.25) TELEOPPower = 1;
        else TELEOPPower = 1.15;
    }

    // Auto-Balancing
    public static final double angleOffThreshold = 10;
    public static final double angleOnThreshold = 5;
    public static boolean autoBalancing = false;

    // Arm
    public static int[] arm_CAN = {20,21};
    public static double armSpeed = 0.7;
    public static double intakeSpeed = 1;

    // PID
    public static double kP = 0.5;
    public static double kI = 0.5;
    public static double kD = 0.5;

    // Controller Ports
    public static int XboxController0 = 0;
    public static int XboxController1 = 1;

    // public static boolean ft = true;
}
