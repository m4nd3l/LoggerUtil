package dev.m4nd3l.loggerutil;

import dev.m4nd3l.loggerutil.logger.*;

/**
 * A base class or wrapper that allows objects to have built-in logging capabilities.
 * Supports a fluent API (method chaining) for ease of use.
 */
public class Loggable {
    private Logger logger;

    /**
     * Assigns a {@link Logger} instance to this object.
     *
     * @param logger The logger to be used for all subsequent log calls.
     * @return This {@code Loggable} instance for method chaining.
     */
    public Loggable setLogger(Logger logger) { this.logger = logger; return this; }

    /**
     * Logs a message with a specific custom logging level.
     *
     * @param message The message to log.
     * @param level   The {@link LoggingLevels} severity to use.
     * @return This {@code Loggable} instance.
     */
    public Loggable log(String message, LoggingLevels level) {  if (logger == null) return this; logger.log(level, message); return this; }

    /**
     * Logs a message at the TRACE level.
     *
     * @param message The message to log.
     * @return This {@code Loggable} instance.
     */
    public Loggable trace(String message) {  if (logger == null) return this; logger.trace(message); return this; }

    /**
     * Logs a message at the DEBUG level.
     *
     * @param message The message to log.
     * @return This {@code Loggable} instance.
     */
    public Loggable debug(String message) {  if (logger == null) return this; logger.debug(message); return this; }

    /**
     * Logs a message at the INFO level.
     *
     * @param message The message to log.
     * @return This {@code Loggable} instance.
     */
    public Loggable info(String message)  {  if (logger == null) return this; logger.info(message); return this; }

    /**
     * Logs a message at the WARN level.
     *
     * @param message The message to log.
     * @return This {@code Loggable} instance.
     */
    public Loggable warn(String message)  {  if (logger == null) return this; logger.warn(message); return this; }

    /**
     * Logs a message at the ERROR level.
     *
     * @param message The message to log.
     * @return This {@code Loggable} instance.
     */
    public Loggable error(String message) {  if (logger == null) return this; logger.error(message); return this; }

    /**
     * Logs a message at the FATAL level.
     *
     * @param message The message to log.
     * @return This {@code Loggable} instance.
     */
    public Loggable fatal(String message) {  if (logger == null) return this; logger.fatal(message); return this; }

    /**
     * Resets the terminal color codes to default.
     *
     * @return This {@code Loggable} instance.
     */
    public Loggable resetColors() {  if (logger == null) return this; logger.resetColors(); return this; }
}