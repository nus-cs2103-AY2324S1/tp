package seedu.address.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
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

    public static class TaskDeadlineComparator implements Comparator<Task> {
        @Override
        public int compare(Task task1, Task task2) {
            boolean oneHasDeadline = task1.getDeadline().isPresent();
            boolean twoHasDeadline = task2.getDeadline().isPresent();
            if (oneHasDeadline && twoHasDeadline) {
                return task1.getDeadline().get().compareTo(task2.getDeadline().get());
            } else if (oneHasDeadline) {
                return -1;
            } else if (twoHasDeadline) {
                return 1;
            } else {
                return new TaskDescriptorComparator().compare(task1, task2);
            }
        }
    }

    public static class TaskDescriptorComparator implements Comparator<Task> {
        public int compare(Task task1, Task task2) {
            return task1.getDescription().compareTo(task2.getDescription());
        }
    }
}
