package dev.m4nd3l.loggerutil.files;

import dev.m4nd3l.loggerutil.LoggerUtils;
import dev.m4nd3l.loggerutil.configuration.FileLoggerConfiguration;
import dev.m4nd3l.loggerutil.logger.Logger;

import java.io.*;
import java.time.LocalDateTime;

public class FileLogging {
    private static FileLoggerConfiguration configuration;
    private static File LOG_FILE = null;
    private static PrintWriter writer = null;
    private static Logger logger = LoggerUtils.getLogger();

    public static void setup(FileLoggerConfiguration configuration) { FileLogging.configuration = configuration; initializeLogs(); }

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

    public static void append(String line) {
        writer.println(line);
        writer.flush();
    }

    public static void cleanOldLogs() {
        if (!configuration.getLogDirectory().exists() || !configuration.getLogDirectory().isDirectory()) return;

        LocalDateTime maxOldness = LocalDateTime.now().minusDays(configuration.getLogFileLifeDays());

        for (File file : configuration.getLogDirectory().listFiles()) {
            if (!file.isFile()) continue;

            String name = file.getName();
            if (!name.endsWith("." + configuration.getExtension())) continue;
            String timestampPart = name.substring(0, name.length() - 6);

            try {
                LocalDateTime fileTime = LocalDateTime.parse(timestampPart, configuration.getFileNameFormatter());
                if (fileTime.isBefore(maxOldness)) file.delete();
            } catch (Exception ignored) { }
        }
    }

    public static File getLogFile() {
        return LOG_FILE;
    }
}