package dev.m4nd3l.loggerutil;

import dev.m4nd3l.loggerutil.logger.*;

public class Loggable {
    private Logger logger;
    public Loggable setLogger(Logger logger) { this.logger = logger; return this; }

    public Loggable log(String message, LoggingLevels level) {  if (logger == null) return this; logger.log(level, message); return this; }

    public Loggable trace(String message) {  if (logger == null) return this; logger.trace(message); return this; }
    public Loggable debug(String message) {  if (logger == null) return this; logger.debug(message); return this; }
    public Loggable info(String message)  {  if (logger == null) return this; logger.info(message); return this; }
    public Loggable warn(String message)  {  if (logger == null) return this; logger.warn(message); return this; }
    public Loggable error(String message) {  if (logger == null) return this; logger.error(message); return this; }
    public Loggable fatal(String message) {  if (logger == null) return this; logger.fatal(message); return this; }

    public Loggable resetColors() {  if (logger == null) return this; logger.resetColors(); return this; }
}
