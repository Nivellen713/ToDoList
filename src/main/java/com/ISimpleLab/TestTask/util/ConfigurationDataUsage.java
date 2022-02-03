package com.ISimpleLab.TestTask.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Конфигурация путей к рабочим файлам
 */

public class ConfigurationDataUsage {

    /**
     * Возвращает путь к xml файлу списка задач
     * @return путь к xml файлу
     */
    public String getXMLToDoListPath() {
        String propertiesFilePath = "src/main/resources/paths.properties";
        try (FileInputStream propertiesFile = new FileInputStream(propertiesFilePath)) {
            Properties properties = new Properties();
            properties.load(propertiesFile);
            return properties.getProperty("PATH_TO_TODOLIST_XML_FILE");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "FileNotFound";
    }

}
