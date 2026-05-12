package dev.m4nd3l.loggerutil.configuration;

import dev.m4nd3l.loggerutil.logger.LoggingPattern;

import java.time.format.DateTimeFormatter;

public class LoggerConfigurationBuilder {
    private LoggingPattern pattern =
            new LoggingPattern("{%level-color-code%}{%time%} - {%level%} -> {%prefix%} {%class-name%}.class: {%message%}",
                    DateTimeFormatter.ofPattern("HH:mm:ss:SS"), false);
    private boolean fileLog = true;

    public LoggerConfigurationBuilder() { }

    public boolean logInFile() { return fileLog; }
    public LoggingPattern getPattern() { return pattern; }

    public LoggerConfigurationBuilder disableFileLog() { this.fileLog = false; return this; }
    public LoggerConfigurationBuilder enableFileLog() { this.fileLog = true; return this; }

    public LoggerConfigurationBuilder setPattern(LoggingPattern pattern) { this.pattern = pattern; return this; }

    public LoggerConfigurationBuilder setPattern(String pattern) { this.pattern.setPattern(pattern); return this; }
    public LoggerConfigurationBuilder setDateTimeFormatter(String formatter) { return setDateTimeFormatter(DateTimeFormatter.ofPattern(formatter)); }
    public LoggerConfigurationBuilder setDateTimeFormatter(DateTimeFormatter formatter) { this.pattern.setTimeFormat(formatter); return this; }
    public LoggerConfigurationBuilder setLevelTextUpperCase() { this.pattern.setLevelUppercase(true); return this; }
    public LoggerConfigurationBuilder setLevelTextNormalCase() { this.pattern.setLevelUppercase(false); return this; }

    public LoggerConfiguration create() { return new LoggerConfiguration(fileLog, pattern); }
}
