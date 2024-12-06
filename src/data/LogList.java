package data;

class LogEntry {
    String timestamp;
    String message;

    public LogEntry(String timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + message;
    }
}

public class LogList {
    private CustomLinkedList<LogEntry> logs;

    public LogList() {
        logs = new CustomLinkedList<>();
    }

    public void addLog(String message) {
        logs.insertEnd(new LogEntry(
                java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                message
        ));
    }

    public void displayLogs() {
        logs.printAll();
    }
}
