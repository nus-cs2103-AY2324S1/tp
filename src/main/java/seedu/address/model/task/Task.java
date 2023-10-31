package seedu.address.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class Task {
    private final TaskDescription description;
    private final Optional<LocalDateTime> deadline;
    public static final DateTimeFormatter DATE_TIME_STRING_FORMATTER = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm");

    public Task(String description, LocalDateTime deadline) {
        requireNonNull(description);

        this.description = new TaskDescription(description);
        this.deadline = Optional.ofNullable(deadline);
    }

    public TaskDescription getDescription() {
        return description;
    }

    public String getDescriptionString() {
        return description.get();
    }

    public Optional<LocalDateTime> getDeadline() {
        return deadline;
    }

    public String getFormattedDeadline() {
        return deadline.orElseThrow().format(DATE_TIME_STRING_FORMATTER);
    }
}
