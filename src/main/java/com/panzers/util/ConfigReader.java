package com.panzers.util;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.*;
import org.slf4j.Logger;

public class ConfigReader {
    public Configuration getConfig() {

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("fileName = {}", getFileName());
        }

        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                .setFileName(fileName));

        return readConfig(builder);
    }

    private Configuration readConfig(FileBasedConfigurationBuilder<FileBasedConfiguration> builder) {
        Configuration config;
        try {
            config = builder.getConfiguration();
        } catch (ConfigurationException cex) {
            throw new RuntimeException(cex);
        }
        return config;
    }

    public ConfigReader(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    String fileName;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigReader.class);
}
