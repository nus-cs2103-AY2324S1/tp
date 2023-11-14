package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Task in the task list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Identity fields
    private final Title title;
    private final Note note;
    private Status status;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * A Task consists of a title, a note, a status and tags.
     * All fields must be present and not null.
     * Tasks will be default not done when created.
     */
    public Task(Title title, Note note, Set<Tag> tags) {
        requireAllNonNull(title, note, tags);
        this.title = title;
        this.note = note;
        this.status = Status.STATUS_NOT_DONE;
        this.tags.addAll(tags);
    }

    /**
     * Creates a new task with the given title, note, and status.
     *
     * @param title  The title of the task. Must not be null.
     * @param note   The note associated with the task. Must not be null.
     * @param status The status of the task. Must not be null.
     * @param tags The tags linked to the task. Must not be null.
     */
    public Task(Title title, Note note, Status status, Set<Tag> tags) {
        requireAllNonNull(title, note);
        this.title = title;
        this.note = note;
        this.status = status;
        this.tags.addAll(tags);
    }

    public Title getTitle() {
        return title;
    }

    public Note getNote() {
        return note;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Updates the Status of the Task as Done.
     */
    public Task markDone() {
        return new Task(title, note, Status.STATUS_DONE, tags);
    }

    /**
     * Updates the Status of the Task as not Done.
     */
    public Task unmarkDone() {
        return new Task(title, note, Status.STATUS_NOT_DONE, tags);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both tasks have the same title and note.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getTitle().equals(getTitle())
                && otherTask.getNote().equals(getNote());
    }

    /**
     * Returns true if both tasks have the same title, note, status and tags.
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
        return title.equals(otherTask.title)
                && note.equals(otherTask.note)
                && status.equals(otherTask.status)
                && tags.equals(otherTask.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, note, status, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("title", title)
                .add("note", note)
                .add("status", status)
                .add("tags", tags)
                .toString();
    }

}
