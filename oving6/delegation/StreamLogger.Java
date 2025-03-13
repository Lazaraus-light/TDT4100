package oving6.delegation;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Objects;

public class StreamLogger implements ILogger {

    private final PrintStream outputStream;
    private String formatString = "%s: %s (%s)"; // Default format string

    public StreamLogger(OutputStream stream) {
        if (stream == null) {
            throw new IllegalArgumentException("OutputStream cannot be null");
        }
        this.outputStream = new PrintStream(stream);
    }

    @Override
    public void log(String severity, String message, Exception exception) {
        Objects.requireNonNull(severity, "Severity cannot be null");
        Objects.requireNonNull(message, "Message cannot be null");

        String logMessage = String.format(
            formatString,
            severity,
            message,
            exception == null ? "null" : exception.toString()
        );

        outputStream.println(logMessage);
        outputStream.flush();
    }

    public void setFormatString(String formatString) {
        if (formatString == null) {
            throw new IllegalArgumentException("Format string cannot be null");
        }
        this.formatString = formatString;
    }
}
