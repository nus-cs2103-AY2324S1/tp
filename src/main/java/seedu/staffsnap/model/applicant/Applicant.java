package seedu.staffsnap.model.applicant;

import static seedu.staffsnap.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import seedu.staffsnap.commons.util.ToStringBuilder;
import seedu.staffsnap.model.interview.Interview;

/**
 * Represents an Applicant in the applicant book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Applicant implements Comparable<Applicant> {

    // Field to compare by, default to Name
    private static Descriptor descriptor = Descriptor.NAME;
    // Order of comparision, default to ascending
    private static Boolean isDescendingOrder = false;


    // Identity fields
    private final Name name;
    private final Phone phone;
    // Data fields
    private final Email email;
    private final Position position;
    private final List<Interview> interviews = new ArrayList<>();
    private Status status;

    private final Score score;

    /**
     * Every field must be present and not null.
     */
    public Applicant(Name name, Phone phone, Email email, Position position, List<Interview> interviews,
                     Status status, Score score) {
        requireAllNonNull(name, phone, email, position, interviews);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
        this.interviews.addAll(interviews);
        Collections.sort(this.interviews);
        this.status = Objects.requireNonNullElse(status, Status.UNDECIDED);
        if (score == null) {
            this.score = new Score(0, 0, 0);
        } else {
            this.score = new Score(score);
        }
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Position getPosition() {
        return position;
    }

    public Score getScore() {
        return score;
    }

    /**
     * Returns an immutable interview list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Interview> getInterviews() {
        return Collections.unmodifiableList(interviews);
    }

    /**
     * Returns true if both applicants have the same email or phone number.
     * This defines a weaker notion of equality between two applicants.
     */
    public boolean isSameApplicant(Applicant otherApplicant) {
        if (otherApplicant == this) {
            return true;
        }

        return otherApplicant != null
                && (otherApplicant.getEmail().equals(getEmail())
                || otherApplicant.getPhone().equals(getPhone()));
    }

    /**
     * Returns true if both applicants have the same identity and data fields.
     * This defines a stronger notion of equality between two applicants.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Applicant)) {
            return false;
        }

        Applicant otherApplicant = (Applicant) other;
        return name.equals(otherApplicant.name)
                && phone.equals(otherApplicant.phone)
                && email.equals(otherApplicant.email)
                && position.equals(otherApplicant.position)
                && interviews.equals(otherApplicant.interviews)
                && status.equals(otherApplicant.status);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, position, interviews, status);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("position", position)
                .add("interviews", interviews)
                .add("status", status)
                .toString();
    }

    /**
     * Update the descriptor for all Applicants.
     * @param newDescriptor the new descriptor to sort Applicants by
     */
    public static void setDescriptor(Descriptor newDescriptor) {
        descriptor = newDescriptor;
    }

    public static Descriptor getDescriptor() {
        return descriptor;
    }

    /**
     * @param o the Applicant to be compared.
     * @return the value 0 if the argument Name is equal to this Name; a value less than 0 if this Name is
     *      lexicographically less than the Name argument; and a value greater than 0 if this string is
     *      lexicographically greater than the Name argument.
     */
    public int compareByName(Applicant o) {
        if (isDescendingOrder) {
            return -this.name.compareTo(o.name);
        }
        return this.name.compareTo(o.name);
    }

    /**
     * @param o the Applicant to be compared.
     * @return the value 0 if the argument Phone is equal to this Phone; a value less than 0 if this Phone is
     *      lexicographically less than the Phone argument; and a value greater than 0 if this Phone is
     *      lexicographically greater than the Phone argument.
     */
    public int compareByPhone(Applicant o) {
        if (isDescendingOrder) {
            return -this.phone.compareTo(o.phone);
        }
        return this.phone.compareTo(o.phone);
    }

    /**
     * @param o the Applicant to be compared.
     * @return the value 0 if the argument Score is equal to this Score; a value less than 0 if this Score is
     *      less than the Score argument; and a value greater than 0 if this Score is
     *      greater than the Score argument.
     */
    public int compareByScore(Applicant o) {
        if (isDescendingOrder) {
            return -this.getScore().getAverageScore().compareTo(o.getScore().getAverageScore());
        }
        return this.getScore().getAverageScore().compareTo(o.getScore().getAverageScore());
    }

    /**
     * @param o the Applicant to be compared.
     * @return the value 0 if the argument Email is equal to this Email; a value less than 0 if this Email is
     *      lexicographically less than the Email argument; and a value greater than 0 if this Email is
     *      lexicographically greater than the Email argument.
     */
    public int compareByEmail(Applicant o) {
        if (isDescendingOrder) {
            return -this.email.compareTo(o.email);
        }
        return this.email.compareTo(o.email);
    }

    /**
     * @param o the Applicant to be compared.
     * @return the value 0 if the argument Status is equal to this Status; a value less than 0 if this Status is
     *      less than the Status argument; and a value greater than 0 if this Status is
     *      greater than the Status argument. Order is (Undecided, Offered, Rejected)
     */
    public int compareByStatus(Applicant o) {
        if (isDescendingOrder) {
            return -this.status.compareTo(o.status);
        }
        return this.status.compareTo(o.status);
    }

    /**
     * @param o the Applicant to be compared.
     * @return the value 0 if the argument Position is equal to this Position; a value less than 0 if this Position is
     *      lexicographically less than the Position argument; and a value greater than 0 if this Position is
     *      lexicographically greater than the Position argument.
     */
    public int compareByPosition(Applicant o) {
        if (isDescendingOrder) {
            return -this.position.compareTo(o.position);
        }
        return this.position.compareTo(o.position);
    }

    /**
     * @param o the object to be compared.
     * @return the value 0 if the argument Applicant is equal to this Applicant;
     *      a value less than 0 if this Applicant is lexicographically less than the Applicant argument;
     *      and a value greater than 0 if this Applicant is lexicographically greater than the Applicant argument.
     */
    @Override
    public int compareTo(Applicant o) {
        switch (descriptor) {
        case NAME:
            return compareByName(o);
        case PHONE:
            return compareByPhone(o);
        case SCORE:
            return compareByScore(o);
        case EMAIL:
            return compareByEmail(o);
        case STATUS:
            return compareByStatus(o);
        case POSITION:
            return compareByPosition(o);
        default:
            return 0;
        }
    }

    /**
     * Add an interview to an Applicant.
     *
     * @param interviewToAdd the new interview to add to the Applicant
     */
    public void addInterview(Interview interviewToAdd) {
        interviews.add(interviewToAdd);
        Collections.sort(interviews);
    }

    /**
     * Deletes an interview of an Applicant.
     *
     * @param interviewToDelete the interview to delete to the Applicant
     */
    public void deleteInterview(Interview interviewToDelete) {
        interviews.remove(interviewToDelete);
        Collections.sort(interviews);
    }

    public int getInterviewIndexForApplicantCard(Interview interview) {
        return interviews.indexOf(interview) + 1;
    }

    /**
     * Get the status of an Applicant.
     * @return Status of applicant
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Set the status of an Applicant.
     * @param status Status to be set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    public static Boolean getIsDescendingOrder() {
        return isDescendingOrder;
    }

    public static void setIsDescendingOrder(Boolean newIsDescendingOrder) {
        isDescendingOrder = newIsDescendingOrder;
    }
}
