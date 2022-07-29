package fr.mrcubee.reflect;

import org.bukkit.Bukkit;

public enum McVersion {

    v1_7_R4(5),
    v1_8_R1(47),
    v1_8_R2(47),
    v1_8_R3(47),
    v1_9_R1(107),
    v1_9_R2(110),
    v1_10_R1(210),
    v1_11_R1(316),
    v1_12_R1(340),
    v1_15_R1(573),
    v1_16_R1(736),
    v1_17_R1(756),
    v1_18_R1(757),
    v1_19_R1(759);

    public final int protocolNumber;

    McVersion(int protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public boolean checkVersion() {
        final McVersion currentVersion = McVersion.getCurrent();
        return currentVersion != null && currentVersion.equals(this);
    }

    public boolean ifInVersion(final Runnable runnable) {
        if (checkVersion()) {
            if (runnable != null)
                runnable.run();
            return true;
        }
        return false;
    }

    public static McVersion fromString(String str) {
        if (str == null)
            return null;
        for (McVersion version : values()) {
            if (str.equalsIgnoreCase(version.name()))
                return version;
        }
        return null;
    }

    public static McVersion getCurrent() {
        String packageName = Bukkit.getServer().getClass().getPackage().getName();
        return fromString(packageName.substring(packageName.lastIndexOf(46) + 1));
    }

    public static boolean checkVersion(final McVersion version) {
        final McVersion currentVersion;

        if (version == null)
            return false;
        currentVersion = McVersion.getCurrent();
        if (currentVersion == null)
            return false;
        return version.equals(currentVersion);
    }

    public static boolean ifInVersion(final McVersion version, final Runnable runnable) {
        if (checkVersion(version)) {
            if (runnable != null)
                runnable.run();
            return true;
        }
        return false;
    }

}
