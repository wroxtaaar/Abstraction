package com.example.jackson.base_code.config;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import com.example.jackson.base_code.dto.MySqlConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigManager {
    public static final String CONFIG_FOLDER = "src/main/java/com/example/jackson/base_code/config_files/";
    public static final ObjectMapper objectMapper = new ObjectMapper();
    
    public static <T> T readConfig(String filename, Class<T> configClass) {
        T config = null;

        // try {
            File filePath = Paths.get(CONFIG_FOLDER + filename).toFile();

            ObjectMapper objectMapper = new ObjectMapper();

            try {
                config = objectMapper.readValue(filePath, configClass);
            } catch(IOException ex) {
                System.out.println("Error on reading data");
            }
            
            
        // } catch (URISyntaxException ex) {
        //     System.out.println("Invalid file path provided");
        // }
        
        return config;

    }    

    public static <T> void writeConfig(String filename, T configData) {
        // try {
            // File filePath = resolveFileFromResources(CONFIG_FOLDER + filename);
            File filePath = Paths.get(CONFIG_FOLDER + filename).toFile();
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                objectMapper.writeValue(filePath, configData);
            } catch(IOException ex) {
                System.out.println("Error on writing data");
            }

        // } catch (URISyntaxException ex) {
        //     System.out.println("Invalid file path provided");
        // }
    }    

    private static File resolveFileFromResources(String filename) throws URISyntaxException {
        return Paths.get(Thread.currentThread().getContextClassLoader().getResource(filename).toURI())
            .toFile();
      }
}