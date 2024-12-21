package data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.baseClasses.log.LogEntry;
import core.enums.Enum.LogLevel;

public class Log {
    // List to store all logged events
    private CustomLinkedList<LogEntry> logEntries;

    // Constructor to initialize the log entries list
    public Log() {
        this.logEntries = new CustomLinkedList<>();
    }

    // Enum to define the severity of the log (Info, Warning, Error)

    // Inner class to represent a single log entry

    // Method to add an entry to the log
    public void addLogEntry(LogLevel level, String message, String details) {
        LogEntry entry = new LogEntry(level, message, details);
        logEntries.insertEnd(entry);
    }

    // Method to generate a full report of all logs
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        for (LogEntry entry : logEntries.toList()) {
            report.append(entry.toString()).append("\n");
        }
        return report.toString();
    }

    // Method to generate a summary of logs by log level
    public String generateLogSummary() {
        Map<LogLevel, Integer> logSummary = new HashMap<>();
        for (LogEntry entry : logEntries.toList()) {
            logSummary.put(entry.getLevel(), logSummary.getOrDefault(entry.getLevel(), 0)
                    + 1);
        }
        StringBuilder summary = new StringBuilder();
        for (LogLevel level : LogLevel.values()) {
            summary.append(level).append(": ").append(logSummary.getOrDefault(level,
                    0)).append("\n");
        }
        return summary.toString();
    }

    // Method to get the list of all log entries
    public List<LogEntry> getLogEntries() {
        return logEntries.toList();
    }
}