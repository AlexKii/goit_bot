package ua.dpw.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertiesService {

    private static final Logger LOG = LogManager.getLogger(PropertiesService.class);

    public static String getApplicationProperties(String fileName, String propertyName) {
        String propertyValue = "";
        File file = new File(fileName);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            propertyValue = properties.getProperty(propertyName);
        } catch (IOException e) {
            LOG.warn("Error read properties file {}", fileName);
        }
        return propertyValue;
    }
}
