package br.com.scrapper.utils;

public class DriverSelector {

    public static String selectDriverToPlatform() {
        OSDetector.OSType ostype = OSDetector.getOperatingSystemType();
        switch (ostype) {
            case Windows:
                return FileUtils.getPathOfDriverForWindows();
            case MacOS:
                return FileUtils.getPathOfDriverForMac();
            case Linux:
                return FileUtils.getPathOfDriverForLinux();
            case Other:
                return "";
        }
        return "";
    }
}
