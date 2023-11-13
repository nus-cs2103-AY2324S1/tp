package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.member.Member;

/**
 * Represents a Task in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Identity fields
    private final Description description;
    private final Status status;
    private final Note note;
    private final Deadline deadline;
    private final Priority priority;
    private final Set<Member> members = new HashSet<>();
    /**
     * Only description is required. All other fields are initialised to their default values.
     *
     * @param description Description of the task.
     */
    public Task(Description description) {
        requireAllNonNull(description);
        this.description = description;
        this.status = new Status();
        this.note = new Note("");
        this.deadline = Deadline.ofNull();
        this.priority = Priority.NONE;
        this.members.addAll(Collections.emptySet());
    }

    /**
     * Constructor for a task with a description, status, note, deadline and priority.
     *
     * @param description Description of the task.
     * @param status Status of the task.
     * @param note Note associated with the task.
     * @param deadline Deadline of the task.
     * @param priority Priority of the task.
     * @param members Members associated with the task.
     */
    public Task(Description description, Status status, Note note,
                Deadline deadline, Priority priority, Set<Member> members) {
        requireAllNonNull(description);
        this.description = description;
        this.status = status;
        this.note = note;
        this.deadline = deadline;
        this.priority = priority;
        this.members.addAll(members);
    }

    /**
     * Returns the description of the task.
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Returns an immutable member set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Member> getMembers() {
        return Collections.unmodifiableSet(members);
    }

    /**
     * Returns the status of the task.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Returns the note associated with the task.
     */
    public Note getNote() {
        return note;
    }

    /**
     * Returns the deadline of the task.
     */
    public Deadline getDeadline() {
        return deadline;
    }

    /**
     * Returns the priority level of the task.
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Returns true if both tasks have the same name.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getDescription().equals(getDescription());
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
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
        return description.equals(otherTask.description)
                && status.equals(otherTask.status)
                && priority.equals(otherTask.priority)
                && members.equals(otherTask.members);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, status, note, deadline, priority, members);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", description)
                .add("status", status)
                .add("note", note)
                .add("deadline", deadline)
                .add("priority", priority)
                .add("members", members)
                .toString();
    }
}
