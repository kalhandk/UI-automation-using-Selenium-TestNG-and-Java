package com.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFile {

    public static String readProperty(String property) {

        String result = "";
        String FileURL = "./LibraryFiles/Resources/config.properties";
        InputStream inputStream = null;
        try {
        	//Creates an empty property list with no default values.
            Properties prop = new Properties();
            
            //Setting the config property url 
            File initialFile = new File(FileURL);
            
            //opening a connection to the file
            inputStream = new FileInputStream(initialFile);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file not found in the classpath");
            }
            //Search for the property with the specified
            result = prop.getProperty(property);
            inputStream.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            // final action ---
        }
        return result;
    }
}
