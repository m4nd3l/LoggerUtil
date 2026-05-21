package dev.m4nd3l.loggerutil.logger;

import dev.m4nd3l.loggerutil.configuration.LoggerConfiguration;
import dev.m4nd3l.loggerutil.configuration.LoggerConfigurationBuilder;
import dev.m4nd3l.loggerutil.files.FileLogging;

/**
 * The main entry point for the logging system.
 * Handles message formatting, console output, and file persistence.
 */
public class Logger {
    /** The configuration used by loggers when no specific configuration is provided. */
    public static LoggerConfiguration defaultConfiguration = new LoggerConfigurationBuilder().create();

    private String prefix;
    private String className;
    private LoggerConfiguration configuration;

    /** Default constructor using "Unknown" as class name. */
    protected Logger() { this("Unknown", "", defaultConfiguration); }

    /** @param loggerClass The class being logged. */
    protected Logger(Class<?> loggerClass) { this(loggerClass, "", defaultConfiguration); }

    /**
     * @param loggerClass The class being logged.
     * @param prefix      A custom string prefix.
     */
    protected Logger(Class<?> loggerClass, String prefix) { this(loggerClass.getSimpleName(), prefix, defaultConfiguration); }

    /** @param className Manual override for the class name string. */
    protected Logger(String className) { this(className, "", defaultConfiguration); }

    /**
     * @param className Manual override for the class name string.
     * @param prefix    A custom string prefix.
     */
    protected Logger(String className, String prefix) { this(className, prefix, defaultConfiguration); }

    protected Logger(Class<?> loggerClass, LoggerConfiguration configuration) { this(loggerClass, "", configuration); }
    protected Logger(Class<?> loggerClass, String prefix, LoggerConfiguration configuration) { this(loggerClass.getSimpleName(), prefix, configuration); }
    protected Logger(String className, LoggerConfiguration configuration) { this(className, "", configuration); }

    /**
     * Fully parameterized constructor.
     *
     * @param className     The class name to display.
     * @param prefix        The prefix string.
     * @param configuration The configuration settings to use.
     */
    protected Logger(String className, String prefix, LoggerConfiguration configuration) {
        this.className = className;
        this.configuration = configuration;
        this.prefix = prefix;
    }

    /**
     * Primary log method that processes formatting and writes to output streams.
     *
     * @param level   The severity level of the log.
     * @param message The content to be logged.
     * @return This logger instance for method chaining.
     */
    public Logger log(LoggingLevels level, String message) {
        String finalMessage = configuration.getPattern().getPattern(level, className, prefix, message);
        System.out.println(finalMessage);
        FileLogging.append(configuration.getPattern().getPatternNoColor(level, className, prefix, message));
        return this;
    }

    /** @param message The message to log at TRACE level.
     *  @return This instance. */
    public Logger trace(String message) { log(LoggingLevels.TRACE, message); return this; }
    /** @param message The message to log at DEBUG level.
     *  @return This instance. */
    public Logger debug(String message) { log(LoggingLevels.DEBUG, message); return this; }
    /** @param message The message to log at INFO level.
     *  @return This instance. */
    public Logger info(String message)  { log(LoggingLevels.INFO,  message); return this; }
    /** @param message The message to log at WARN level.
     *  @return This instance. */
    public Logger warn(String message)  { log(LoggingLevels.WARN,  message); return this; }
    /** @param message The message to log at ERROR level.
     *  @return This instance. */
    public Logger error(String message) { log(LoggingLevels.ERROR, message); return this; }
    /** @param message The message to log at FATAL level.
     *  @return This instance. */
    public Logger fatal(String message) { log(LoggingLevels.FATAL, message); return this; }

    /** @param message The message to log at TRACE level.
     *  @return This instance. */
    public Logger trace(Object message) { trace(message.toString()); return this; }
    /** @param message The message to log at DEBUG level.
     *  @return This instance. */
    public Logger debug(Object message) { debug(message.toString()); return this; }
    /** @param message The message to log at INFO level.
     *  @return This instance. */
    public Logger info(Object message)  { info(message.toString()); return this; }
    /** @param message The message to log at WARN level.
     *  @return This instance. */
    public Logger warn(Object message)  { warn(message.toString()); return this; }
    /** @param message The message to log at ERROR level.
     *  @return This instance. */
    public Logger error(Object message) { error(message.toString()); return this; }
    /** @param message The message to log at FATAL level.
     *  @return This instance. */
    public Logger fatal(Object message) { fatal(message.toString()); return this; }

    /** @param message The message to log at ERROR level.
     *  @return This instance. */
    public Logger error(Exception message) { error(message.toString()); return this; }

    /** @param message    The message to log at ERROR level.
     *  @param exception  The exception to show more information.
     *  @return This instance. */
    public Logger error(String message, Exception exception) { error(message + "\nException: " + exception); return this; }

    /** Resets console colors.
     *  @return This instance. */
    public Logger resetColors() { System.out.print(LoggingLevels.RESET.getColorCode()); return this; }

    /** @return The current prefix. */
    public String getPrefix() { return prefix; }
    /** @return The current class name. */
    public String getClassName() { return className; }
    /** @return The current configuration. */
    public LoggerConfiguration getConfiguration() { return configuration; }

    public Logger setPrefix(String prefix) { this.prefix = prefix; return this; }
    public Logger setClassName(String className) { this.className = className; return this; }
    public Logger setConfiguration(LoggerConfiguration configuration) { this.configuration = configuration; return this; }

    /** Factory method for a new default logger. @return A new Logger instance. */
    public static Logger getLogger() { return new Logger(); }

    /**
     * Factory method for a customized logger.
     *
     * @param loggerClass   The calling class.
     * @param prefix        The prefix.
     * @param configuration Custom config.
     * @return A new Logger instance.
     */
    public static Logger getLogger(Class<?> loggerClass, String prefix, LoggerConfiguration configuration) {
        return new Logger(loggerClass, prefix, configuration);
    }
}