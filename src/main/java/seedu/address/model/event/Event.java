package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {
    private LocalDateTime start;
    private LocalDateTime end;
    private String name;

    public Event(String name, String start, String end) {
        // Temporary, need update
        this.name = name;
        this.start = LocalDateTime.now();
        this.end = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public String getStartString() {
        // Temporary, can use Util class instead
        return start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getEndString() {
        // Temporary, can use Util class instead
        return end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
