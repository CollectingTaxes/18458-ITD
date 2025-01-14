package org.firstinspires.ftc.teamcode.pedroPathing.constants;

import com.pedropathing.localization.Encoder;
import com.pedropathing.localization.constants.DriveEncoderConstants;
import com.pedropathing.localization.constants.TwoWheelConstants;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class LConstants {
    static {
        TwoWheelConstants.forwardTicksToInches = 0.0218;
        TwoWheelConstants.strafeTicksToInches = .001989436789;
        /*
        VALUES TO TEST
        3.149 OR 5.9 for back wheel
        3.66 for the wheels on the side
         */
        TwoWheelConstants.forwardY = 5;
        TwoWheelConstants.strafeX = 5;
        TwoWheelConstants.forwardEncoder_HardwareMapName = "rightFront";
        TwoWheelConstants.strafeEncoder_HardwareMapName = "rightRear";
        TwoWheelConstants.forwardEncoderDirection = Encoder.FORWARD;
        TwoWheelConstants.strafeEncoderDirection = Encoder.FORWARD;
        TwoWheelConstants.IMU_HardwareMapName = "imu";
        TwoWheelConstants.IMU_Orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.LEFT, RevHubOrientationOnRobot.UsbFacingDirection.UP);

    }
}




