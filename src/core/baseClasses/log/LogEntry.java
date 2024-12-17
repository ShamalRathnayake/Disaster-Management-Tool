package core.baseClasses.log;

import java.util.Date;

import core.enums.Enum.LogLevel;

public class LogEntry {
    private Date timestamp;
    private LogLevel level;
    private String message;
    private String details;

    // Constructor to initialize the log entry
    public LogEntry(LogLevel level, String message, String details) {
        this.timestamp = new Date();
        this.level = level;
        this.message = message;
        this.details = details;
    }

    // Getters for each field
    public Date getTimestamp() {
        return timestamp;
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s: %s", timestamp.toString(), level, message, details);
    }
}