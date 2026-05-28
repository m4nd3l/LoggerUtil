package dev.m4nd3l.loggerutil;

import dev.m4nd3l.loggerutil.configuration.FileLoggerConfigurationBuilder;
import dev.m4nd3l.loggerutil.configuration.LoggerConfiguration;
import dev.m4nd3l.loggerutil.configuration.FileLoggerConfiguration;
import dev.m4nd3l.loggerutil.files.FileLogging;
import dev.m4nd3l.loggerutil.logger.Logger;
import dev.m4nd3l.loggerutil.logger.LoggingLevels;

/**
 * Utility class providing static methods to initialize and manage the logging system.
 * This class handles terminal color support, file logging setup, and logger instantiation.
 */
public class LoggerUtils {

    /**
     * Enables ANSI colored logging on Windows by modifying the Windows Registry.
     * This method sets the VirtualTerminalLevel to 1, allowing the console to interpret ANSI escape codes.
     */
    public static synchronized void enableColoredLogging() {
        try {
            new ProcessBuilder("reg", "add",
                    "HKCU\\Console", "/v", "VirtualTerminalLevel",
                    "/t", "REG_DWORD", "/d", "1", "/f")
                    .inheritIO().start().waitFor();
        } catch (Exception ignored) {}

        System.setProperty("org.jline.terminal.disable", "false");
    }

    /**
     * Configures the file logging system with a custom configuration.
     *
     * @param configuration The {@link FileLoggerConfiguration} to apply.
     */
    public static void setupFileLogging(FileLoggerConfiguration configuration) { FileLogging.setup(configuration); }

    /**
     * Configures the file logging system with default settings.
     */
    public static void setupFileLogging() { FileLogging.setup(new FileLoggerConfigurationBuilder().create()); }

    /**
     * Sets the default configuration that all newly created loggers will use.
     *
     * @param configuration The {@link LoggerConfiguration} to set as default.
     */
    public static void setDefaultLoggerConfiguration(LoggerConfiguration configuration) { Logger.defaultConfiguration = configuration; }

    /**
     * Obtains a Logger for the calling class with default settings.
     *
     * @return A configured {@link Logger} instance.
     */
    public static Logger getLogger() { return getLogger("", Logger.defaultConfiguration); }

    /**
     * Obtains a Logger for the calling class with a specific prefix.
     *
     * @param prefix The string prefix to prepend to log messages.
     * @return A configured {@link Logger} instance.
     */
    public static Logger getLogger(String prefix) { return getLogger(prefix, Logger.defaultConfiguration); }

    /**
     * Obtains a Logger for the calling class with a custom configuration.
     *
     * @param configuration The {@link LoggerConfiguration} to use for this specific logger.
     * @return A configured {@link Logger} instance.
     */
    public static Logger getLogger(LoggerConfiguration configuration) { return getLogger("", configuration); }

    /**
     * Obtains a Logger using the current stack trace to identify the calling class and showing it in logs.
     *
     * @param prefix        The string prefix to prepend to log messages.
     * @param configuration The {@link LoggerConfiguration} to use.
     * @return A configured {@link Logger} instance.
     * @throws java.util.NoSuchElementException if the calling class cannot be determined.
     */
    public static Logger getLogger(String prefix, LoggerConfiguration configuration) {
        Class<?> callerClass = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .walk(frames -> frames
                        .skip(2)
                        .findFirst()
                        .map(StackWalker.StackFrame::getDeclaringClass)
                        .orElseThrow());

        return Logger.getLogger(callerClass, prefix, configuration);
    }

    /**
     * Method to log quickly without having to use StackWalker.
     *
     * @param clazz         The class for the logger to show.
     * @param level         The {@link LoggingLevels} to use.
     * @param message       The message to log.
     */
    public static void quickLog(Class<?> clazz, LoggingLevels level, String message) {
        Logger.getLogger(clazz, "", Logger.defaultConfiguration).log(level, message);
    }

    /**
     * Method to log quickly without having to use StackWalker.
     *
     * @param clazz         The class for the logger to show.
     * @param prefix        The prefix of the message.
     * @param level         The {@link LoggingLevels} to use.
     * @param message       The message to log.
     */
    public static void quickLog(Class<?> clazz, String prefix, LoggingLevels level, String message) {
        Logger.getLogger(clazz, prefix, Logger.defaultConfiguration).log(level, message);
    }
}