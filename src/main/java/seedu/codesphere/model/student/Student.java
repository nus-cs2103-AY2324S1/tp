package seedu.codesphere.model.student;

import static seedu.codesphere.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.codesphere.commons.util.ToStringBuilder;
import seedu.codesphere.model.tag.Tag;

/**
 * Represents a Student in the student list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Email email;

    // Data fields
    private final Tag tag;
    private final Remark remark;
    private final PendingQuestion pendingQuestion;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Email email, Remark remark, PendingQuestion pendingQuestion, Tag tag) {
        requireAllNonNull(name, email, remark, tag);
        this.name = name;
        this.email = email;
        this.remark = remark;
        this.pendingQuestion = pendingQuestion;
        this.tag = tag;
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Remark getRemark() {
        return remark;
    }

    public PendingQuestion getPendingQuestion() {
        return pendingQuestion;
    }

    public Tag getTag() {
        return tag;
    }

    /**
     * Returns true if both students have the same email.
     * Email is used as an identifier as each student's email should be unique.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;

        return otherStudent.getName().equals(getName())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getRemark().equals(getRemark())
                && otherStudent.getPendingQuestion().equals(getPendingQuestion())
                && otherStudent.getTag().equals(getTag());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, email, remark, pendingQuestion, tag);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("email", email)
                .add("remark", remark)
                .add("pending question", pendingQuestion)
                .add("tag", tag)
                .toString();
    }

}
