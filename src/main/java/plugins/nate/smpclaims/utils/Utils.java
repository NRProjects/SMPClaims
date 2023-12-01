package plugins.nate.smpclaims.utils;

import plugins.nate.smpclaims.SMPClaims;

public class Utils {
    public static void log(String log) {
        SMPClaims.getPlugin().getLogger().info(log);
    }
    public static void warn(String log) {
        SMPClaims.getPlugin().getLogger().warning(log);
    }
    public static void severe(String log) {
        SMPClaims.getPlugin().getLogger().severe(log);
    }
}
