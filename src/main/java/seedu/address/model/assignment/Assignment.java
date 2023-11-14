package seedu.address.model.assignment;

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
import seedu.address.model.fields.Date;
import seedu.address.model.fields.Description;
import seedu.address.model.fields.IsoDate;
import seedu.address.model.fields.Name;
import seedu.address.model.fields.Status;
import seedu.address.model.tag.Tag;
import seedu.address.model.unique.UniqueModelWithDate;

/**
 * Represents an Assignment in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public final class Assignment implements Comparable<Assignment>, UniqueModelWithDate<Assignment> {
    private final Name name;
    private final IsoDate enddate;
    private final Status status;
    private final Description description;
    private final Date plannedFinishDate;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Assignment(Name name, IsoDate endDate, Status status, Description description,
                      Date plannedFinishDate, Set<Tag> tags) {
        requireAllNonNull(name, endDate, status, tags);
        this.name = new Name(name.getText());
        this.enddate = endDate;
        this.status = status;
        this.tags.addAll(tags);
        this.description = description;
        this.plannedFinishDate = plannedFinishDate;
    }

    public Name getName() {
        return name;
    }

    public IsoDate getEnd() {
        return this.enddate;
    }

    public Status getStatus() {
        return status;
    }

    public Description getDescription() {
        return description;
    }

    public Date getPlannedFinishDate() {
        return plannedFinishDate;
    }

    @Override
    public void hashDateToNameWith(HashMap<LocalDate, LinkedList<String>> map) {
        LocalDate d = enddate.getDate().map(LocalDateTime::toLocalDate).orElse(LocalDate.MIN);
        if (map.containsKey(d)) {
            map.get(d).add(name.toString());
        } else {
            LinkedList<String> l = new LinkedList<>();
            l.add(name.toString());
            map.put(d, l);
        }
    }


    /**
     * Mark an assignment as completed
     * @return a new Assignment that is marked as completed
     */
    public Assignment mark() {
        Status newStatus = new Status(true);
        return new Assignment(name, enddate, newStatus, description, plannedFinishDate, tags);
    }

    /**
     * Mark an assignment as incomplete
     * @return a new Assignment that is marked as incomplete
     */
    public Assignment unMark() {
        Status newStatus = new Status(false);
        return new Assignment(name, enddate, newStatus, description, plannedFinishDate, tags);
    }

    /**
     * Returns a new Assignment with the given description
     * @return a new Assignment with the given description
     */
    public Assignment getNewAssignmentWithDescription(Description newDescription) {
        return new Assignment(name, enddate, status, newDescription, plannedFinishDate, tags);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both assignments have the same name.
     * This defines a weaker notion of equality between two assignments.
     */
    @Override
    public boolean isDuplicate(Assignment otherAssignment) {
        if (otherAssignment == this) {
            return true;
        }

        return otherAssignment != null
                && otherAssignment.getName().equals(getName());
    }

    /**
     * Returns true if both assignments have the same identity and data fields.
     * This defines a stronger notion of equality between two assignment.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Assignment)) {
            return false;
        }

        Assignment otherAssignment = (Assignment) other;
        return name.equals(otherAssignment.name)
                && this.enddate.equals(otherAssignment.enddate)
                && this.status.equals(otherAssignment.status)
                && this.tags.equals(otherAssignment.tags)
                && this.description.equals(otherAssignment.description)
                && this.plannedFinishDate.equals(otherAssignment.plannedFinishDate);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, enddate, status, tags, description, plannedFinishDate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("completeness", status)
                .add("description", description)
                .add("plannedFinishDate", plannedFinishDate)
                .add("end", enddate)
                .add("tags", tags)
                .toString();
    }

    @Override
    public int compareTo(Assignment o) {
        if (this.enddate.compareTo(o.enddate) == 0) {
            return 0;
        } else if (this.enddate.compareTo(o.enddate) > 0) {
            return 1;
        } else {
            return -1;
        }
    }
}
