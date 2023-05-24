package com.utils;

import java.io.*;
import java.util.Properties;

public class ConfigReader {

    public static Properties prop;
    public static String path="src/test/resources/config.properties";
    public static String env;
    public static String Url;

    //public static String
    /**
     * Responsible for reading property file
     */
     public static void readProperty(){

        try {
            File f = new File(path);
            FileReader fi = new FileReader(f);
            prop = new Properties();
            prop.load(fi);


        } catch (FileNotFoundException e) {
            System.out.println("Config File is not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error while loading data from config property File");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error while Reading configuration file");
            e.printStackTrace();
        }
    }

}
