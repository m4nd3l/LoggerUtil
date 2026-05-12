import dev.m4nd3l.loggerutil.LoggerUtils;
import dev.m4nd3l.loggerutil.configuration.FileLoggerConfigurationBuilder;
import dev.m4nd3l.loggerutil.logger.Logger;

import java.io.File;

import static dev.m4nd3l.loggerutil.LoggerUtils.enableColoredLogging;

public class test {
    public static void main(String[] args) {
        enableColoredLogging();
        LoggerUtils.setupFileLogging(new FileLoggerConfigurationBuilder().setLogDirectory(new File("C:\\Users\\m4nd3l\\Downloads")).create());
        Logger logger = LoggerUtils.getLogger();
        logger.trace("Hello :3");
        logger.debug("Hello :3");
        logger.info("Hello :3");
        logger.warn("Hello :3");
        logger.error("Hello :3");
        logger.fatal("Hello :3");
    }
}
