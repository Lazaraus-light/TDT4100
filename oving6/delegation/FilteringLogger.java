package oving6.delegation;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FilteringLogger implements ILogger {

    private final ILogger logger;
    private final Map<String, Boolean> severityMap;

    public FilteringLogger(ILogger logger, String... severities) {
        Objects.requireNonNull(logger, "Logger cannot be null");
        this.logger = logger;
        this.severityMap = new HashMap<>();
        
        for (String severity : severities) {
            this.severityMap.put(severity, true);
        }
    }

    @Override
    public void log(String severity, String message, Exception exception) {
        if (isLogging(severity)) {
            logger.log(severity, message, exception);
        }
    }

    public boolean isLogging(String severity) {
        return severityMap.getOrDefault(severity, false);
    }

    public void setIsLogging(String severity, boolean value) {
        severityMap.put(severity, value);
    }
}
