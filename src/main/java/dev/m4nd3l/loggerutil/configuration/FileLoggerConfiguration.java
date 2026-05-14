package dev.m4nd3l.loggerutil.configuration;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Configuration settings specifically for file-based logging.
 * Manages directory locations, file naming conventions, and retention policies.
 */
public class FileLoggerConfiguration {
    private int logFileLifeDays;
    private File logDirectory;
    private DateTimeFormatter fileNameFormatter;
    private String extension;

    /**
     * Constructs a new FileLoggerConfiguration.
     *
     * @param logDirectory      The directory for logs.
     * @param logFileLifeDays   Retention period in days.
     * @param fileNameFormatter Format for the log filename.
     * @param extension         The file extension.
     */
    public FileLoggerConfiguration(File logDirectory, int logFileLifeDays, DateTimeFormatter fileNameFormatter, String extension) {
        this.extension = extension;
        this.fileNameFormatter = fileNameFormatter;
        this.logDirectory = logDirectory;
        this.logFileLifeDays = logFileLifeDays;
    }

    /**
     * Generates a new {@link File} object representing the log file for the current moment.
     * @return A file instance inside the log directory with a timestamped name.
     */
    public File getLogFile() {
        String name = LocalDateTime.now().format(fileNameFormatter) + "." + extension;
        return new File(logDirectory, name);
    }

    /** @return The file extension string. */
    public String getExtension() { return extension; }
    /** @return The formatter used for filenames. */
    public DateTimeFormatter getFileNameFormatter() { return fileNameFormatter; }
    /** @return The root directory for logs. */
    public File getLogDirectory() { return logDirectory; }
    /** @return The retention period in days. */
    public int getLogFileLifeDays() { return logFileLifeDays; }

    public FileLoggerConfiguration setExtension(String extension) { this.extension = extension; return this; }
    public FileLoggerConfiguration setFileNameFormatter(DateTimeFormatter fileNameFormatter) { this.fileNameFormatter = fileNameFormatter; return this; }
    public FileLoggerConfiguration setLogDirectory(File logDirectory) { this.logDirectory = logDirectory; return this; }
    public FileLoggerConfiguration setLogFileLifeDays(int logFileLifeDays) { this.logFileLifeDays = logFileLifeDays; return this; }
}