package com.zych.configuration;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

public class LoggerConfiguration {

    public static void setLoggingLevel(Level level) {
        LoggerContext logContext = (LoggerContext) LogManager.getContext(false);
        Configuration config = logContext.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.setLevel(level);
        logContext.updateLoggers();
    }
}
