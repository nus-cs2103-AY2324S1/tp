package seedu.address.model.task;

public class TaskDescription implements Comparable<TaskDescription> {
    private final String description;

    TaskDescription(String description) {
        this.description = description;
    }

    public String get() {
        return description;
    }

    @Override
    public int compareTo(TaskDescription other) {
        return description.compareTo(other.description);
    }
}
