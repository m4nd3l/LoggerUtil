package dev.m4nd3l.loggerutil.files;

import dev.m4nd3l.loggerutil.LoggerUtils;
import dev.m4nd3l.loggerutil.configuration.FileLoggerConfiguration;
import dev.m4nd3l.loggerutil.logger.Logger;

import java.io.*;
import java.time.LocalDateTime;

/**
 * Handles the physical writing of log data to the file system.
 * This class manages file creation, appending log lines, and the cleanup of expired log files.
 */
public class FileLogging {
    private static FileLoggerConfiguration configuration;
    private static File LOG_FILE = null;
    private static PrintWriter writer = null;
    private static Logger logger = LoggerUtils.getLogger();

    /**
     * Initializes the file logging system with a specific configuration.
     *
     * @param configuration The {@link FileLoggerConfiguration} defining paths and behavior.
     */
    public static void setup(FileLoggerConfiguration configuration) { FileLogging.configuration = configuration; initializeLogs(); }

    /**
     * Creates the log directory and file, and sets up a dual-stream for System.err
     * so that errors are printed to both the console and the log file.
     *
     * @throws RuntimeException if the log file cannot be created or accessed.
     */
    public static void initializeLogs() {
        configuration.getLogDirectory().mkdirs();
        LOG_FILE = configuration.getLogFile();
        try { LOG_FILE.createNewFile(); writer = new PrintWriter(new FileWriter(LOG_FILE, true)); }
        catch (Exception e) { throw new RuntimeException(e); }
        try {
            PrintStream console = System.err;
            PrintStream fileOut = new PrintStream(new FileOutputStream(LOG_FILE, true));
            PrintStream dual = new PrintStream(new OutputStream() {
                @Override public void write(int b) { console.write(b); fileOut.write(b); }
            });
            System.setErr(dual);
        } catch (Exception e) { LoggerUtils.getLogger().error(e.toString()); }
    }

    /**
     * Appends a single line of text to the current log file.
     *
     * @param line The formatted log string (typically without ANSI colors).
     */
    public static void append(String line) {
        if (writer != null) {
            writer.println(line);
            writer.flush();
        }
    }

    /**
     * Scans the log directory and deletes files that exceed the configured retention period.
     * Files are identified by their timestamped names and extension.
     */
    public static void cleanOldLogs() {
        if (!configuration.getLogDirectory().exists() || !configuration.getLogDirectory().isDirectory()) return;

        LocalDateTime maxOldness = LocalDateTime.now().minusDays(configuration.getLogFileLifeDays());

        for (File file : configuration.getLogDirectory().listFiles()) {
            if (!file.isFile()) continue;

            String name = file.getName();
            if (!name.endsWith("." + configuration.getExtension())) continue;
            // Assuming extension length is fixed based on your substring logic
            String timestampPart = name.substring(0, name.length() - (configuration.getExtension().length() + 1));

            try {
                LocalDateTime fileTime = LocalDateTime.parse(timestampPart, configuration.getFileNameFormatter());
                if (fileTime.isBefore(maxOldness)) file.delete();
            } catch (Exception ignored) { }
        }
    }

    /**
     * @return The currently active {@link File} being used for logs.
     */
    public static File getLogFile() {
        return LOG_FILE;
    }
}