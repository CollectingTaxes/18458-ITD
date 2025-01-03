package org.firstinspires.ftc.teamcode.pedropathing.util;

public class ConstantsUser {
    public static Class<?> fConstants;
    public static Class<?> lConstants;

    public static void setConstants(Class<?> followerConstants, Class<?> localizerConstants) {
        fConstants = followerConstants;
        lConstants = localizerConstants;
        setup();
    }

    private static void setup() {
        try {
            fConstants.getDeclaredConstructor().newInstance();
            lConstants.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
