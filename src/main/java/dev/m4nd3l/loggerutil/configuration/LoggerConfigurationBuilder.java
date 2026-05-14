package dev.m4nd3l.loggerutil.configuration;

import dev.m4nd3l.loggerutil.logger.LoggingPattern;
import java.time.format.DateTimeFormatter;

/**
 * A builder class for creating instances of {@link LoggerConfiguration}.
 * Provides a fluent interface for customizing patterns and behavior.
 */
public class LoggerConfigurationBuilder {
    private LoggingPattern pattern =
            new LoggingPattern("{%level-color-code%}{%time%} - {%level%} -> {%prefix%} {%class-name%}.class: {%message%}",
                    DateTimeFormatter.ofPattern("HH:mm:ss:SS"), false);
    private boolean fileLog = true;

    /** Default constructor. */
    public LoggerConfigurationBuilder() { }

    /** @return current file logging status. */
    public boolean logInFile() { return fileLog; }
    /** @return current logging pattern. */
    public LoggingPattern getPattern() { return pattern; }

    /** Disables logging to external files. @return this builder instance. */
    public LoggerConfigurationBuilder disableFileLog() { this.fileLog = false; return this; }
    /** Enables logging to external files. @return this builder instance. */
    public LoggerConfigurationBuilder enableFileLog() { this.fileLog = true; return this; }

    /** @param pattern The {@link LoggingPattern} to use. @return this builder instance. */
    public LoggerConfigurationBuilder setPattern(LoggingPattern pattern) { this.pattern = pattern; return this; }
    /** @param pattern The raw pattern string. @return this builder instance. */
    public LoggerConfigurationBuilder setPattern(String pattern) { this.pattern.setPattern(pattern); return this; }
    /** @param formatter Pattern for date/time. @return this builder instance. */
    public LoggerConfigurationBuilder setDateTimeFormatter(String formatter) { return setDateTimeFormatter(DateTimeFormatter.ofPattern(formatter)); }
    /** @param formatter The {@link DateTimeFormatter} instance. @return this builder instance. */
    public LoggerConfigurationBuilder setDateTimeFormatter(DateTimeFormatter formatter) { this.pattern.setTimeFormat(formatter); return this; }
    /** Forces log levels to display in UPPERCASE. @return this builder instance. */
    public LoggerConfigurationBuilder setLevelTextUpperCase() { this.pattern.setLevelUppercase(true); return this; }
    /** Keeps log level casing as defined in the name. @return this builder instance. */
    public LoggerConfigurationBuilder setLevelTextNormalCase() { this.pattern.setLevelUppercase(false); return this; }

    /**
     * Constructs the final {@link LoggerConfiguration} object.
     * @return A new configuration instance.
     */
    public LoggerConfiguration create() { return new LoggerConfiguration(fileLog, pattern); }
}