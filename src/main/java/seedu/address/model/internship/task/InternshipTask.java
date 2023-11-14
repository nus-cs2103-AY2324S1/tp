package seedu.address.model.internship.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.fields.IsoDate;
import seedu.address.model.fields.Name;
import seedu.address.model.fields.Status;
import seedu.address.model.fields.TaskOutcome;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.tag.Tag;
import seedu.address.model.unique.UniqueModelWithDate;

/**
 * Represents a task needed for an intern application/role
 */
// PLS DO NOT REMOVE THE FINAL MODIFIER SUPPOSE TO BE IMMUTABLE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public final class InternshipTask implements Comparable<InternshipTask>, UniqueModelWithDate<InternshipTask> {
    private final InternshipRole role;
    private final Name taskName;
    private final IsoDate deadline;
    private final Status status;
    private final TaskOutcome outcome;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Creates an InternshipTask object
     * @param role that this task is related to
     * @param taskName of the task
     * @param deadline of the task
     * @param status of the task
     * @param outcome of the task
     * @param tags of the task
     */
    public InternshipTask(InternshipRole role, Name taskName, IsoDate deadline,
                          Status status, TaskOutcome outcome, Set<Tag> tags) {
        requireAllNonNull(role, taskName, deadline, status, outcome);
        this.role = role;
        this.taskName = taskName;
        this.deadline = deadline;
        this.status = status;
        this.outcome = outcome;
        this.tags.addAll(tags);
    }

    public Name getTaskName() {
        return taskName;
    }

    public InternshipRole getInternshipRole() {
        return role;
    }

    public IsoDate getDeadline() {
        return deadline;
    }

    public Status getStatus() {
        return status;
    }

    public TaskOutcome getOutcome() {
        return outcome;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public void hashDateToNameWith(HashMap<LocalDate, LinkedList<String>> map) {
        LocalDate d = deadline.getDate().map(LocalDateTime::toLocalDate).orElse(LocalDate.MIN);
        if (map.containsKey(d)) {
            map.get(d).add(taskName.toString());
        } else {
            LinkedList<String> l = new LinkedList<>();
            l.add(taskName.toString());
            map.put(d, l);
        }
    }

    /**
     * Mark an internship task as completed
     * @return a new InternshipTask that is marked as completed
     */
    public InternshipTask mark() {
        Status newStatus = new Status(true);
        return new InternshipTask(role, taskName, deadline, newStatus, outcome, tags);
    }

    /**
     * Mark an assignment as incomplete
     * @return a new Assignment that is marked as incomplete
     */
    public InternshipTask unMark() {
        Status newStatus = new Status(false);
        return new InternshipTask(role, taskName, deadline, newStatus, outcome, tags);
    }

    public InternshipTask getNewInternshipTaskWithOutcome(TaskOutcome newOutcome) {
        return new InternshipTask(role, taskName, deadline, status, newOutcome, tags);
    }

    public InternshipTask editInternshipRole(InternshipRole newInternshipRole) {
        return new InternshipTask(newInternshipRole, taskName, deadline, status, outcome, tags);
    }

    /**
     * @param otherTask to be verifed
     * @return true if otherTask has the same task name
     */
    public boolean isDuplicate(InternshipTask otherTask) {
        if (this == otherTask) {
            return true;
        }

        return otherTask != null && role.isDuplicate(otherTask.role) && taskName.equals(otherTask.taskName);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(taskName, role, deadline, status, outcome, tags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof InternshipTask)) {
            return false;
        }

        InternshipTask otherTask = (InternshipTask) other;

        return role.equals(otherTask.role)
                && taskName.equals(otherTask.taskName)
                && deadline.equals(otherTask.deadline)
                && status.equals(otherTask.status)
                && outcome.equals(otherTask.outcome)
                && tags.equals(otherTask.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("role", role.getMainDetails())
                .add("taskName", taskName)
                .add("deadline", deadline)
                .add("status", status)
                .add("outcome", outcome)
                .add("tags", tags)
                .toString();
    }

    @Override
    public int compareTo(InternshipTask o) {
        if (this.deadline.compareTo(o.deadline) == 0) {
            return 0;
        } else if (this.deadline.compareTo(o.deadline) > 0) {
            return 1;
        } else {
            return -1;
        }
    }
}
