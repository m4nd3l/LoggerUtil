package dev.m4nd3l.loggerutil.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Defines the visual structure of a log message using placeholders.
 * <p>
 * Supported placeholders:
 * <ul>
 *     <li>{@code {%time%}} - The current timestamp</li>
 *     <li>{@code {%level-color-code%}} - The ANSI color code for the log level</li>
 *     <li>{@code {%level%}} - The name of the log level</li>
 *     <li>{@code {%class-name%}} - The name of the class issuing the log</li>
 *     <li>{@code {%prefix%}} - A custom user-defined prefix</li>
 *     <li>{@code {%message%}} - The actual log content</li>
 * </ul>
 */
public class LoggingPattern {
    private static String timestampKeyword      = "{%time%}";
    private static String levelColorCodeKeyword = "{%level-color-code%}";
    private static String levelKeyword          = "{%level%}";
    private static String classNameKeyword      = "{%class-name%}";
    private static String prefixKeyword         = "{%prefix%}";
    private static String messageKeyword        = "{%message%}";

    private String pattern;
    private DateTimeFormatter timeFormat;
    private boolean levelUppercase;

    /**
     * Constructs a new LoggingPattern.
     *
     * @param pattern        The string pattern containing placeholders.
     * @param timeFormat     The format to use for the timestamp placeholder.
     * @param levelUppercase Whether the level name should be forced to uppercase.
     */
    public LoggingPattern(String pattern, DateTimeFormatter timeFormat, boolean levelUppercase) {
        this.levelUppercase = levelUppercase;
        this.pattern = pattern;
        this.timeFormat = timeFormat;
    }

    /**
     * @return The raw pattern string.
     */
    public String getPattern() { return pattern; }

    /**
     * Processes the pattern into a final string while stripping out color codes.
     * Useful for file logging where ANSI codes are not desired.
     *
     * @param level     The logging level.
     * @param className The name of the calling class.
     * @param prefix    The log prefix.
     * @param message   The message content.
     * @return A formatted string without ANSI colors.
     */
    public String getPatternNoColor(LoggingLevels level, String className, String prefix, String message) {
        return getPatternIgnoreColor(level, className, prefix, message)
                .replace(levelColorCodeKeyword, "");
    }

    /**
     * Processes the pattern into a final string including ANSI color codes and a reset sequence.
     *
     * @param level     The logging level.
     * @param className The name of the calling class.
     * @param prefix    The log prefix.
     * @param message   The message content.
     * @return A formatted string with ANSI colors.
     */
    public String getPattern(LoggingLevels level, String className, String prefix, String message) {
        return getPatternIgnoreColor(level, className, prefix, message)
                .replace(levelColorCodeKeyword, level.getColorCode())
                + LoggingLevels.RESET.getColorCode();
    }

    /**
     * Internal helper to replace all standard keywords except for the color code.
     */
    private String getPatternIgnoreColor(LoggingLevels level, String className, String prefix, String message) {
        return pattern
                .replace(timestampKeyword, LocalDateTime.now().format(timeFormat))
                .replace(levelKeyword, levelUppercase ? level.getName().toUpperCase() : level.getName())
                .replace(prefixKeyword, prefix)
                .replace(classNameKeyword, className)
                .replace(messageKeyword, message);
    }

    /**
     * @param pattern The new pattern string.
     * @return This instance for chaining.
     */
    public LoggingPattern setPattern(String pattern) { this.pattern = pattern; return this; }

    /**
     * @param timeFormat The new date-time formatter.
     * @return This instance for chaining.
     */
    public LoggingPattern setTimeFormat(DateTimeFormatter timeFormat) { this.timeFormat = timeFormat; return this; }

    /**
     * @param levelUppercase True if the level name should be uppercase.
     * @return This instance for chaining.
     */
    public LoggingPattern setLevelUppercase(boolean levelUppercase) { this.levelUppercase = levelUppercase; return this; }
}