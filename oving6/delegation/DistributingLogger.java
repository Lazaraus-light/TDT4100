package oving6.delegation;

import java.util.Objects;

public class DistributingLogger implements ILogger {

    private ILogger errorLogger;
    private ILogger warningLogger;
    private ILogger infoLogger;

    public DistributingLogger(ILogger errorLogger, ILogger warningLogger, ILogger infoLogger) {
        this.errorLogger = Objects.requireNonNull(errorLogger, "Error logger cannot be null");
        this.warningLogger = Objects.requireNonNull(warningLogger, "Warning logger cannot be null");
        this.infoLogger = Objects.requireNonNull(infoLogger, "Info logger cannot be null");
    }

    @Override
    public void log(String severity, String message, Exception exception) {
        switch (severity) {
            case ERROR:
                errorLogger.log(severity, message, exception);
                break;
            case WARNING:
                warningLogger.log(severity, message, exception);
                break;
            case INFO:
                infoLogger.log(severity, message, exception);
                break;
            default:
                throw new IllegalArgumentException("Unknown severity: " + severity);
        }
    }

    public void setLogger(String severity, ILogger logger) {
        Objects.requireNonNull(logger, "Logger cannot be null");

        switch (severity) {
            case ERROR:
                this.errorLogger = logger;
                break;
            case WARNING:
                this.warningLogger = logger;
                break;
            case INFO:
                this.infoLogger = logger;
                break;
            default:
                throw new IllegalArgumentException("Unknown severity: " + severity);
        }
    }
}
