package br.com.scrapper;

import br.com.scrapper.core.ChromeBot;
import br.com.scrapper.utils.FileUtils;

public class Main {

    private static final String URL = "https://www.google.com";

    public static void main(String[] args) {
        
        System.out.println(FileUtils.getPathOfProject());
        
        ChromeBot.init(true);
        ChromeBot.goTo(URL);
        ChromeBot.wait(15);
    }

}
