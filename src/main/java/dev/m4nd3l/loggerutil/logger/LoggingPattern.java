package dev.m4nd3l.loggerutil.logger;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public LoggingPattern(String pattern, DateTimeFormatter timeFormat, boolean levelUppercase) {
        this.levelUppercase = levelUppercase;
        this.pattern = pattern;
        this.timeFormat = timeFormat;
    }

    public String getPattern() { return pattern; }
    public String getPatternNoColor(LoggingLevels level, String className, String prefix, String message) {
        return getPatternIgnoreColor(level, className, prefix, message)
                .replace(levelColorCodeKeyword, "");
    }
    public String getPattern(LoggingLevels level, String className, String prefix, String message) {
        return getPatternIgnoreColor(level, className, prefix, message)
                .replace(levelColorCodeKeyword, level.getColorCode())
                + LoggingLevels.RESET.getColorCode();
    }
    private String getPatternIgnoreColor(LoggingLevels level, String className, String prefix, String message) {
        return pattern
                .replace(timestampKeyword, LocalDateTime.now().format(timeFormat))
                .replace(levelKeyword, levelUppercase ? level.getName().toUpperCase() : level.getName())
                .replace(prefixKeyword, prefix)
                .replace(classNameKeyword, className)
                .replace(messageKeyword, message);
    }

    public LoggingPattern setPattern(String pattern) { this.pattern = pattern; return this; }
    public LoggingPattern setTimeFormat(DateTimeFormatter timeFormat) { this.timeFormat = timeFormat; return this; }
    public LoggingPattern setLevelUppercase(boolean levelUppercase) { this.levelUppercase = levelUppercase; return this; }
}
