package dev.m4nd3l.loggerutil;

import dev.m4nd3l.loggerutil.configuration.FileLoggerConfigurationBuilder;
import dev.m4nd3l.loggerutil.configuration.LoggerConfiguration;
import dev.m4nd3l.loggerutil.configuration.FileLoggerConfiguration;
import dev.m4nd3l.loggerutil.files.FileLogging;
import dev.m4nd3l.loggerutil.logger.Logger;

public class LoggerUtils {
    public static synchronized void enableColoredLogging() {
        try {
            new ProcessBuilder("reg", "add",
                    "HKCU\\Console", "/v", "VirtualTerminalLevel",
                    "/t", "REG_DWORD", "/d", "1", "/f")
                    .inheritIO().start().waitFor();
        } catch (Exception ignored) {}

        System.setProperty("org.jline.terminal.disable", "false");
    }
    public static void setupFileLogging(FileLoggerConfiguration configuration) { FileLogging.setup(configuration); }
    public static void setupFileLogging() { FileLogging.setup(new FileLoggerConfigurationBuilder().create()); }

    public static void setDefaultLoggerConfiguration(LoggerConfiguration configuration) { Logger.defaultConfiguration = configuration; }

    public static Logger getLogger() { return getLogger("", Logger.defaultConfiguration); }
    public static Logger getLogger(String prefix) { return getLogger(prefix, Logger.defaultConfiguration); }
    public static Logger getLogger(LoggerConfiguration configuration) { return getLogger("", configuration); }
    public static Logger getLogger(String prefix, LoggerConfiguration configuration) {
        Class<?> callerClass = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .walk(frames -> frames
                        .filter(f -> !f.getClassName().equals(Logger.class.getName()))
                        .findFirst()
                        .map(StackWalker.StackFrame::getDeclaringClass)
                        .orElseThrow());

        return Logger.getLogger(callerClass, prefix, configuration);
    }
}