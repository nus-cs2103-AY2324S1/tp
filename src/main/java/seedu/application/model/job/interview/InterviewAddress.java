package seedu.application.model.job.interview;

import static java.util.Objects.requireNonNull;

import seedu.application.commons.util.AppUtil;

/**
 * Represents an Interview's address in the application book.
 * Guarantees: immutable; is valid as declared in {@link #isValidInterviewAddress(String)}
 */
public class InterviewAddress {
    public static final String MESSAGE_CONSTRAINTS =
            "Address descriptions should adhere to the following constraints:\n"
                    + "1. The first character must not be a whitespace \n";

    public static final String TO_ADD_INTERVIEW_ADDRESS = "TO_ADD_INTERVIEW_ADDRESS";
    public static final InterviewAddress DEFAULT_INTERVIEW_ADDRESS =
            new InterviewAddress(TO_ADD_INTERVIEW_ADDRESS);

    /*
     * The first character of interview address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String interviewAddress;

    /**
     * Constructs a {@code InterviewAddress}.
     *
     * @param interviewAddress A valid interview address.
     */
    public InterviewAddress(String interviewAddress) {
        requireNonNull(interviewAddress);
        AppUtil.checkArgument(isValidInterviewAddress(interviewAddress), MESSAGE_CONSTRAINTS);
        this.interviewAddress = interviewAddress.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid interview address.
     */
    public static boolean isValidInterviewAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return interviewAddress;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InterviewAddress)) {
            return false;
        }

        InterviewAddress otherInterviewAddress = (InterviewAddress) other;
        return interviewAddress.equals(otherInterviewAddress.interviewAddress);
    }

    @Override
    public int hashCode() {
        return interviewAddress.hashCode();
    }
}

