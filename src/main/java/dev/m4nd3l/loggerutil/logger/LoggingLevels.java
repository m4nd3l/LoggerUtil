package dev.m4nd3l.loggerutil.logger;

public enum LoggingLevels {
    TRACE("Trace", "\u001B[35m", 1),
    DEBUG("Debug", "\u001B[34m", 2),
    INFO("Info", "\u001B[32m", 3),
    WARN("Warn", "\u001B[33m", 4),
    ERROR("Error", "\u001B[31m", 5),
    FATAL("Fatal", "\u001B[1;31m", 6),
    RESET("Reset", "\u001B[0m", -1);

    private String name;
    private String colorCode;
    private int severity;

    LoggingLevels(String name, String colorCode, int severity) {
        this.colorCode = colorCode;
        this.name = name;
        this.severity = severity;
    }

    public String getName() { return name; }
    public String getColorCode() { return colorCode; }
    public int getSeverity() { return severity; }

    public String format(String message) {
        return getColorCode() + message + RESET.getColorCode();
    }
}