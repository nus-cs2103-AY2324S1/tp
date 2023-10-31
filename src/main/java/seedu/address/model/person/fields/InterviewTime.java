package seedu.address.model.person.fields;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Person's interview time in the address book.
 */

public class InterviewTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Interview time should be in the format of DD/MM/YYYY HHmm. To cancel the interview, enter "
                    + "'Interview time has not been set'(case sensitive)";

    public static final String VALIDATION_REGEX = "^\\d{2}/\\d{2}/\\d{4} \\d{4}$";

    public String time;

    /**
     * Constructs a {@code InterviewTime}.
     *
     * @param interviewTime A valid interview time.
     */
    public InterviewTime(String interviewTime) {
        this.time = interviewTime;
    }

    /**
     * Returns true if a given string is a valid interview time.
     */
    public static boolean isValidTime(String test) {

        return test.matches(VALIDATION_REGEX) || test.equals("Interview time has not been set");
    }
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        InterviewTime otherInterviewTime = (InterviewTime) other;

        return time.equals(otherInterviewTime.time);
    }

    @Override
    public String toString() {
        if (time.equals("Interview time has not been set")) {
            return "Interview time has not been set";
        }
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(this.time, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy h:mma");
        String formattedDateTime = dateTime.format(outputFormatter);
        return "Interview scheduled at: " + formattedDateTime;
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
}
