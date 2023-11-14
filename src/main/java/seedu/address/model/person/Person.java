package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.attachment.Attachment;
import seedu.address.model.tag.Tag;

/**
 * Represents an applicant in the TA applicant list.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Person {

    // Identity fields
    private final StudentNumber studentNo;
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Gpa gpa;
    private final PreviousGrade previousGrade;
    private final Optional<InterviewScore> interviewScore;
    private final Optional<Comment> comment;
    private final Set<Tag> tags = new HashSet<>();
    private final List<Attachment> attachments = new ArrayList<>();

    // Flag fields
    private final IsHidden isHidden;
    private final IsBookmarked isBookmarked;

    /**
     * Every field must be present and not null.
     */
    public Person(
            StudentNumber studentNo,
            Name name,
            Phone phone,
            Email email,
            Gpa gpa,
            PreviousGrade previousGrade,
            Optional<InterviewScore> interviewScore,
            Optional<Comment> comment,
            Set<Tag> tags,
            List<Attachment> attachments,
            IsHidden isHidden,
            IsBookmarked isBookmarked) {
        requireAllNonNull(studentNo, name, phone, email, gpa, previousGrade, tags, isHidden);
        this.studentNo = studentNo;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gpa = gpa;
        this.previousGrade = previousGrade;
        this.interviewScore = interviewScore;
        this.comment = comment;
        this.tags.addAll(tags);
        this.attachments.addAll(attachments);
        this.isHidden = isHidden;
        this.isBookmarked = isBookmarked;
    }

    public StudentNumber getStudentNumber() {
        return studentNo;
    }

    public Name getName() {
        return this.name;
    }

    public Phone getPhone() {
        return this.phone;
    }

    public Email getEmail() {
        return this.email;
    }

    public Gpa getGpa() {
        return this.gpa;
    }

    public PreviousGrade getPreviousGrade() {
        return this.previousGrade;
    }

    public Optional<InterviewScore> getInterviewScore() {
        return this.interviewScore;
    }

    public Optional<Comment> getComment() {
        return this.comment;
    }

    /**
     * Returns an immutable tag set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable attachment list, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Attachment> getAttachments() {
        return Collections.unmodifiableList(attachments);
    }

    /**
     * Returns an immutable isHidden value, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public IsHidden getIsHidden() {
        return this.isHidden;
    }

    /**
     * Returns an immutable isBookmarked value, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public IsBookmarked getIsBookmarked() {
        return this.isBookmarked;
    }

    /**
     * Returns true if both persons have the same student number.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getStudentNumber().equals(getStudentNumber());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return studentNo.equals(otherPerson.studentNo)
                && name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && gpa.equals(otherPerson.gpa)
                && previousGrade.equals(otherPerson.previousGrade)
                && interviewScore.equals(otherPerson.interviewScore)
                && comment.equals(otherPerson.comment)
                && tags.equals(otherPerson.tags)
                && attachments.equals(otherPerson.attachments)
                && isHidden.equals(otherPerson.isHidden)
                && isBookmarked.equals(otherPerson.isBookmarked);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(studentNo, name, phone, email, gpa, previousGrade, interviewScore, comment, tags, isHidden,
                attachments);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("studentNo", studentNo)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("gpa", gpa)
                .add("previousGrade", previousGrade)
                .add("interviewScore", interviewScore)
                .add("comment", comment)
                .add("tags", tags)
                .add("attachments", attachments)
                .add("isHidden", isHidden)
                .add("isBookmarked", isBookmarked)
                .toString();
    }
}
