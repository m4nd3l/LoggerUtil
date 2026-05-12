package dev.m4nd3l.loggerutil.logger;

import dev.m4nd3l.loggerutil.configuration.LoggerConfiguration;
import dev.m4nd3l.loggerutil.configuration.LoggerConfigurationBuilder;
import dev.m4nd3l.loggerutil.files.FileLogging;

public class Logger {
    public static LoggerConfiguration defaultConfiguration = new LoggerConfigurationBuilder().create();

    private String prefix;
    private String className;
    private LoggerConfiguration configuration;

    protected Logger() { this("Unknown", "", defaultConfiguration); }
    protected Logger(Class<?> loggerClass) { this(loggerClass, "", defaultConfiguration); }
    protected Logger(Class<?> loggerClass, String prefix) { this(loggerClass.getSimpleName(), prefix, defaultConfiguration); }
    protected Logger(String className) { this(className, "", defaultConfiguration); }
    protected Logger(String className, String prefix) { this(className, prefix, defaultConfiguration); }

    protected Logger(Class<?> loggerClass, LoggerConfiguration configuration) { this(loggerClass, "", configuration); }
    protected Logger(Class<?> loggerClass, String prefix, LoggerConfiguration configuration) { this(loggerClass.getSimpleName(), prefix, configuration); }
    protected Logger(String className, LoggerConfiguration configuration) { this(className, "", configuration); }
    protected Logger(String className, String prefix, LoggerConfiguration configuration) {
        this.className = className;
        this.configuration = configuration;
        this.prefix = prefix;
    }

    public Logger log(LoggingLevels level, String message) {
        String finalMessage = configuration.getPattern().getPattern(level, className, prefix, message);
        System.out.println(finalMessage);
        FileLogging.append(configuration.getPattern().getPatternNoColor(level, className, prefix, message));
        return this;
    }

    public Logger trace(String message) { log(LoggingLevels.TRACE, message); return this; }
    public Logger debug(String message) { log(LoggingLevels.DEBUG, message); return this; }
    public Logger info(String message)  { log(LoggingLevels.INFO,  message); return this; }
    public Logger warn(String message)  { log(LoggingLevels.WARN,  message); return this; }
    public Logger error(String message) { log(LoggingLevels.ERROR, message); return this; }
    public Logger fatal(String message) { log(LoggingLevels.FATAL, message); return this; }

    public Logger resetColors() { System.out.print(LoggingLevels.RESET.getColorCode()); return this; }


    public String getPrefix() { return prefix; }
    public String getClassName() { return className; }
    public LoggerConfiguration getConfiguration() { return configuration; }

    public Logger setPrefix(String prefix) { this.prefix = prefix; return this; }
    public Logger setClassName(String className) { this.className = className; return this; }
    public Logger setConfiguration(LoggerConfiguration configuration) { this.configuration = configuration; return this; }

    public static Logger getLogger() { return new Logger(); }
    public static Logger getLogger(Class<?> loggerClass, String prefix, LoggerConfiguration configuration) {
        return new Logger(loggerClass, prefix, configuration);
    }
}