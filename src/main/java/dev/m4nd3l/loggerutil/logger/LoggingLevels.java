package dev.m4nd3l.loggerutil.logger;

/**
 * Defines the severity levels and associated ANSI colors for logging.
 */
public enum LoggingLevels {
    /** Fine-grained informational events. */
    TRACE("Trace", "\u001B[35m", 1),
    /** Fine-grained events useful for debugging. */
    DEBUG("Debug", "\u001B[34m", 2),
    /** General informational messages. */
    INFO("Info", "\u001B[32m", 3),
    /** Potential issues or warnings. */
    WARN("Warn", "\u001B[33m", 4),
    /** Error events that might allow the application to continue. */
    ERROR("Error", "\u001B[31m", 5),
    /** Severe errors that will lead the application to abort. */
    FATAL("Fatal", "\u001B[1;31m", 6),
    /** Used to reset terminal colors to default. */
    RESET("Reset", "\u001B[0m", -1);

    private String name;
    private String colorCode;
    private int severity;

    LoggingLevels(String name, String colorCode, int severity) {
        this.colorCode = colorCode;
        this.name = name;
        this.severity = severity;
    }

    /** @return The display name of the level. */
    public String getName() { return name; }
    /** @return The ANSI color code string. */
    public String getColorCode() { return colorCode; }
    /** @return The integer severity value. */
    public int getSeverity() { return severity; }

    /**
     * Wraps a message in this level's color and appends a reset code.
     *
     * @param message The text to format.
     * @return Colored text string.
     */
    public String format(String message) {
        return getColorCode() + message + RESET.getColorCode();
    }
}