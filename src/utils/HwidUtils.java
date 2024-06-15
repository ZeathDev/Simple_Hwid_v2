package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import utils.OSUtils.OS;

public class HwidUtils {

    public static String getHwid() {
        final OS os = OSUtils.getOS();

        if (os.equals(OSUtils.OS.WINDOWS)) {
            return getWindowsHwid();
        } else if (os.equals(OSUtils.OS.MACOS)) {
            return getMacHwid();
        } else if (os.equals(OSUtils.OS.LINUX)) {
            return getLinuxHwid();
        } else {
            return "Failed to get your HWID";
        }
    }

    private static String getWindowsHwid() {
        try {
            Process process = Runtime.getRuntime().exec("wmic csproduct get uuid");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder hwid = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    hwid.append(line.trim());
                }
            }

            return hwid.toString();
        } catch (IOException e) {
            return "Failed to get Windows HWID";
        }
    }

    private static String getMacHwid() {
        try {
            Process process = Runtime.getRuntime().exec("system_profiler SPHardwareDataType");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder hwid = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    hwid.append(line.trim());
                }
            }

            return hwid.toString();
        } catch (IOException e) {
            return "Failed to get MacOS HWID";
        }
    }

    private static String getLinuxHwid() {
        try {
            // Need root
            Process process = Runtime.getRuntime().exec("sudo dmidecode -s system-uuid");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder hwid = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    hwid.append(line.trim());
                }
            }

            return hwid.toString();
        } catch (IOException e) {
            return "Failed to get Linux HWID";
        }
    }

    @SuppressWarnings("all")
    public static void crash() {
        // Code your own crash source.
        try {
            int i = 114514 / 0;
        } catch (final Throwable t) {
            crash();
        }
    }

    public static void crash2() {
        try {
            crash2();
        } catch (Exception e) {
            crash2();
        }
    }

    @SuppressWarnings("null")
    public static void crash3() {
        try {
            Thread thread = new Thread(() -> {
                Object obj = null;
                obj.toString();
            });

            thread.start();
        } catch (Exception e) {
            crash3();
        }
    }

}
