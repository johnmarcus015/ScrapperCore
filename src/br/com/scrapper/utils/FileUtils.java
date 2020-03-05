package br.com.scrapper.utils;

public class FileUtils {
    
    public static String getPathOfProject(){
        return System.getProperty("user.dir");
    }
    
    public static String getPathOfDependencies(){
        return getPathOfProject()+"/drivers";
    }
    
    public static String getPathOfDriverForMac(){
        return getPathOfDependencies()+"/chromedrivermac64";
    }
    
    public static String getPathOfDriverForLinux(){
        return getPathOfDependencies()+"/chromedriverlinux64";
    }
    
    public static String getPathOfDriverForWindows(){
        return getPathOfDependencies()+"/chromedriverwindows32.exe";
    }
    
}
