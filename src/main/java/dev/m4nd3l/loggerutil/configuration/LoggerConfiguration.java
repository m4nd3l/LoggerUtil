package dev.m4nd3l.loggerutil.configuration;

import dev.m4nd3l.loggerutil.logger.LoggingPattern;

import java.time.format.DateTimeFormatter;

public class LoggerConfiguration {
    private LoggingPattern pattern;
    private boolean fileLog;

    public LoggerConfiguration(boolean fileLog, LoggingPattern pattern) {
        this.fileLog = fileLog;
        this.pattern = pattern;
    }

    public boolean logInFile() { return fileLog; }
    public LoggingPattern getPattern() { return pattern; }

    public LoggerConfiguration setFileLog(boolean fileLog) { this.fileLog = fileLog; return this; }
    public LoggerConfiguration setPattern(LoggingPattern pattern) { this.pattern = pattern; return this; }
    public LoggerConfiguration setPattern(String pattern, DateTimeFormatter timeFormat, boolean levelUppercase) {
        return setPattern(new LoggingPattern(pattern, timeFormat, levelUppercase));
    }
}
