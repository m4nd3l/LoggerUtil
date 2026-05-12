package dev.m4nd3l.loggerutil.configuration;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLoggerConfiguration {
    private int logFileLifeDays;
    private File logDirectory;
    private DateTimeFormatter fileNameFormatter;
    private String extension;

    public FileLoggerConfiguration(File logDirectory, int logFileLifeDays, DateTimeFormatter fileNameFormatter, String extension) {
        this.extension = extension;
        this.fileNameFormatter = fileNameFormatter;
        this.logDirectory = logDirectory;
        this.logFileLifeDays = logFileLifeDays;
    }

    public File getLogFile() {
        String name = LocalDateTime.now().format(fileNameFormatter) + "." + extension;
        return new File(logDirectory, name);
    }

    public String getExtension() { return extension; }
    public DateTimeFormatter getFileNameFormatter() { return fileNameFormatter; }
    public File getLogDirectory() { return logDirectory; }
    public int getLogFileLifeDays() { return logFileLifeDays; }

    public FileLoggerConfiguration setExtension(String extension) { this.extension = extension; return this; }
    public FileLoggerConfiguration setFileNameFormatter(DateTimeFormatter fileNameFormatter) { this.fileNameFormatter = fileNameFormatter; return this; }
    public FileLoggerConfiguration setLogDirectory(File logDirectory) { this.logDirectory = logDirectory; return this; }
    public FileLoggerConfiguration setLogFileLifeDays(int logFileLifeDays) { this.logFileLifeDays = logFileLifeDays; return this; }
}