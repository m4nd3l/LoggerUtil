# LoggerUtil

**LoggerUtil** is a lightweight, zero-dependency Java logging framework designed for developers who want beautiful console colors and automated file logging without the complexity of Log4j or SLF4J.

## ✨ Features

* 🚀 **Zero Configuration:** Start logging with a single line of code.
* 🎨 **ANSI Colors:** Built-in support for colored console output (Trace, Debug, Info, Warn, Error, Fatal).
* 📂 **Auto-File Logging:** Automatically persists logs to files with a "Dual-Stream" setup for `System.err`.
* 🧹 **Log Cleanup:** Automatically deletes old log files based on a configurable retention period.
* 🛠️ **Fluent API:** Clean, chainable methods for configuration and logging.
* 🕵️ **Smart Detection:** Uses `StackWalker` to automatically detect the calling class.

---

## 📦 Installation

Add the following to your `build.gradle`:

```gradle
dependencies {
    implementation 'io.github.m4nd3l:LoggerUtil:1.1.2'
}

```

Or for Maven `pom.xml`:

```xml
<dependency>
    <groupId>io.github.m4nd3l</groupId>
    <artifactId>LoggerUtil</artifactId>
    <version>1.1.2</version>
</dependency>

```

---

## 🚀 Quick Start

### 1. Basic Usage

LoggerUtil can automatically detect the calling class name:

```java
import dev.m4nd3l.loggerutil.LoggerUtils;
import dev.m4nd3l.loggerutil.logger.Logger;

public class Main {
    private static final Logger logger = LoggerUtils.getLogger();

    public static void main(String[] args) {
        logger.info("Application started successfully!");
        logger.warn("This is a warning message.");
        logger.error("Something went wrong!");
    }
}

```

### 2. Advanced Configuration

Customize the look and behavior of your logger using the fluent Builder:

```java
LoggerConfiguration config = new LoggerConfigurationBuilder()
    .setPattern("{%time%} [%level%] {%class-name%}: {%message%}")
    .setDateTimeFormatter("yyyy-MM-dd HH:mm:ss")
    .setLevelTextUpperCase()
    .create();

Logger logger = Logger.getLogger(Main.class, "APP-PREFIX", config);

```

### 3. Setting Up File Logging

Persist your logs to a directory and automatically clean up old files:

```java
FileLoggerConfiguration fileConfig = new FileLoggerConfigurationBuilder()
    .setLogDirectory(new File("logs"))
    .setLogFileLifeDays(30) // Keep logs for 30 days
    .setExtension("txt")
    .create();

FileLogging.setup(fileConfig);
FileLogging.cleanOldLogs(); // Clean up expired files

```

---

## ⚙️ Logging Levels

| Level | Color | Severity | Description |
| --- | --- | --- | --- |
| `TRACE` | Purple | 1 | Fine-grained informational events. |
| `DEBUG` | Blue | 2 | Useful for debugging applications. |
| `INFO` | Green | 3 | General highlights of application progress. |
| `WARN` | Yellow | 4 | Potentially harmful situations. |
| `ERROR` | Red | 5 | Error events that might still allow the app to run. |
| `FATAL` | Bold Red | 6 | Severe errors that will lead the application to abort. |

---

## 🖥️ Windows Support

If you are using Windows CMD and colors aren't appearing, call this utility at the start of your program to enable Virtual Terminal support in the registry:

```java
LoggerUtils.enableColoredLogging();

```

---

## 📄 License

Distributed under the Apache License 2.0. See `LICENSE` for more information.

## 👤 Author

**m4nd3l** - [GitHub](https://m4nd3l.github.io)
