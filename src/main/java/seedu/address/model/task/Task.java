package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a task in the task list.
 */
public class Task {
    public static final DateTimeFormatter DATE_TIME_STRING_FORMATTER = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm");
    private final TaskDescription description;
    private final Deadline deadline;

    /**
     * Constructs a Task object with the given description and deadline
     */
    public Task(String description, LocalDateTime deadline) {
        requireNonNull(description);

        this.description = new TaskDescription(description);
        this.deadline = new Deadline(deadline);
    }

    /**
     * Constructs a Task object with the given {@code TaskDescription} and {@code Deadline}
     */
    public Task(TaskDescription description, Deadline deadline) {
        requireAllNonNull(description, deadline);

        this.description = description;
        this.deadline = deadline;
    }

    public TaskDescription getDescription() {
        return description;
    }

    public String getDescriptionString() {
        return description.get();
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public String getDeadlineString() {
        return deadline.getFormattedDeadline();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        boolean descriptionMatches = description.equals(otherTask.description);
        boolean deadlineMatches = deadline.equals(otherTask.deadline);
        return descriptionMatches && deadlineMatches;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("description", description)
                .add("deadline", deadline)
                .toString();
    }

    /**
     * A comparator to sort the task list by deadline.
     */
    public static class TaskDeadlineComparator implements Comparator<Task> {
        @Override
        public int compare(Task task1, Task task2) {
            int firstResult = task1.deadline.compareTo(task2.deadline);
            if (firstResult == 0) {
                return new TaskDescriptorComparator().compare(task1, task2);
            }
            return firstResult;
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof TaskDeadlineComparator;
        }
    }

    /**
     * A comparator to sort the task list by description, alphabetically.
     */
    public static class TaskDescriptorComparator implements Comparator<Task> {
        public int compare(Task task1, Task task2) {
            return task1.getDescription().compareTo(task2.getDescription());
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof TaskDescriptorComparator;
        }
    }
}
