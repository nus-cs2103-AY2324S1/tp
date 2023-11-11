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

    public static final String TO_ADD_INTERVIEW_TYPE = "TO_ADD_INTERVIEW_TYPE";
    public static final InterviewType DEFAULT_INTERVIEW_TYPE = new InterviewType(TO_ADD_INTERVIEW_TYPE);
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
        if (isEmptyInterviewType(interviewType)) {
            this.interviewType = TO_ADD_INTERVIEW_TYPE;
        } else {
            this.interviewType = interviewType.toUpperCase();
        }
    }

    /**
     * Returns true if a given string is a valid interviewType.
     */
    public static boolean isValidInterviewType(String test) {
        if (isEmptyInterviewType(test)) {
            return true;
        }
        for (InterviewType.InterviewTypes t : InterviewType.InterviewTypes.values()) {
            if (test.equalsIgnoreCase(t.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if a given string is an empty interviewType.
     */
    private static boolean isEmptyInterviewType(String test) {
        return test.equals(TO_ADD_INTERVIEW_TYPE);
    }

    @Override
    public String toString() {
        if (isEmptyInterviewType(interviewType)) {
            return TO_ADD_INTERVIEW_TYPE;
        } else {
            return interviewType;
        }
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
