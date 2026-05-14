package dev.m4nd3l.loggerutil.configuration;

import dev.m4nd3l.loggerutil.logger.LoggingPattern;
import java.time.format.DateTimeFormatter;

/**
 * Holds the settings for a Logger instance, including its output pattern
 * and whether it should persist logs to a file.
 */
public class LoggerConfiguration {
    private LoggingPattern pattern;
    private boolean fileLog;

    /**
     * @param fileLog Whether file logging is enabled.
     * @param pattern The visual pattern for log messages.
     */
    public LoggerConfiguration(boolean fileLog, LoggingPattern pattern) {
        this.fileLog = fileLog;
        this.pattern = pattern;
    }

    /** @return true if file logging is enabled. */
    public boolean logInFile() { return fileLog; }
    /** @return the current {@link LoggingPattern}. */
    public LoggingPattern getPattern() { return pattern; }

    public LoggerConfiguration setFileLog(boolean fileLog) { this.fileLog = fileLog; return this; }
    public LoggerConfiguration setPattern(LoggingPattern pattern) { this.pattern = pattern; return this; }

    /**
     * Convenience method to update the pattern with raw values.
     *
     * @param pattern        The pattern string.
     * @param timeFormat     The time format.
     * @param levelUppercase Whether level is uppercase.
     * @return current instance.
     */
    public LoggerConfiguration setPattern(String pattern, DateTimeFormatter timeFormat, boolean levelUppercase) {
        return setPattern(new LoggingPattern(pattern, timeFormat, levelUppercase));
    }
}