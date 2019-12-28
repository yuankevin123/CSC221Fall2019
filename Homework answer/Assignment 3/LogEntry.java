public class LogEntry {

    private String id;
    private String timestamp;
    private String source;
    private String destination;
    private String protocol;
    private String length;
    private String description;

    public LogEntry(String id, String timestamp, String source, String destination, String protocol, String length, String description) {

        if (!isValidInt(id) || !isValidStr(timestamp) || !isValidStr(source) || !isValidStr(destination) || !isValidStr(protocol) || !isValidInt(length) || !isValidStr(description))
            throw new IllegalArgumentException("One or more values are null");

        this.id = id;
        this.timestamp = timestamp;
        this.source = source;
        this.destination = destination;
        this.protocol = protocol;
        this.length = length;
        this.description = description;
    }

    public String toString() {

        return String.format("%s,%s,%s,%s,%s,%s,%s", id, timestamp, source, destination, protocol, length, description);
    }

    public String getId() {

        return id;
    }

    public String getTimestamp() {

        return timestamp;
    }

    public String getSource() {

        return source;
    }

    public String getDestination() {

        return destination;
    }

    public String getProtocol() {

        return protocol;
    }

    public String getLength() {

        return length;
    }

    public String getDescription() {

        return description;
    }

    private boolean isValidInt(String str) {

        return isValidStr(str) && Integer.parseInt(str) > 0;
    }

    private boolean isValidStr(String str) {

        return str != null && str.length() > 0 && !str.equals("-");
    }
}