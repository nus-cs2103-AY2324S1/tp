package seedu.application.model.job.interview;

import static java.util.Objects.requireNonNull;

import seedu.application.commons.util.AppUtil;

/**
 * Represents an Interview's type in the application book.
 * Guarantees: immutable; is valid as declared in {@link #isValidInterviewType(String)}
 */
public class InterviewType {

    public static final String MESSAGE_CONSTRAINTS =
            "Type is not case sensitive and should only be in the form: \n"
                    + "BEHAVIOURAL, TECHNICAL, CASE, GROUP, PHONE, VIDEO, ONLINE, ONSITE, OTHER";

    public final String interviewType;
    /**
     * Enum for interview types
     */
    public enum InterviewTypes {
        BEHAVIOURAL, TECHNICAL, CASE, GROUP, PHONE, VIDEO, ONLINE, ONSITE, OTHER
    }

    /**
     * Constructs a {@code InterviewType}.
     *
     * @param interviewType A valid interviewType.
     */
    public InterviewType(String interviewType) {
        requireNonNull(interviewType);
        AppUtil.checkArgument(isValidInterviewType(interviewType), MESSAGE_CONSTRAINTS);
        this.interviewType = interviewType.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid interviewType.
     */
    public static boolean isValidInterviewType(String test) {
        for (InterviewType.InterviewTypes t : InterviewType.InterviewTypes.values()) {
            if (test.equalsIgnoreCase(t.toString())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return interviewType;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InterviewType)) {
            return false;
        }

        InterviewType otherInterviewType = (InterviewType) other;
        return interviewType.equalsIgnoreCase(otherInterviewType.interviewType);
    }

    @Override
    public int hashCode() {
        return interviewType.hashCode();
    }
}
