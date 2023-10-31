package seedu.address.model.task;

public class TaskDescription {
    private final String description;

    TaskDescription(String description) {
        this.description = description;
    }

    public String get() {
        return description;
    }
}
