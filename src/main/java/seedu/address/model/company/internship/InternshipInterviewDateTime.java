package seedu.address.model.company.internship;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.DateTimeParserUtil.DATE_TIME_CONSTRAINTS;
import static seedu.address.commons.util.DateTimeParserUtil.isValidDateTimeString;
import static seedu.address.commons.util.DateTimeParserUtil.parseDateTimeToString;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents an Internship's interview's date and time in the address book.
 */
public class InternshipInterviewDateTime implements Comparable<InternshipInterviewDateTime> {
    public static final String MESSAGE_CONSTRAINTS = DATE_TIME_CONSTRAINTS;
    private final LocalDateTime interviewDateTime;

    /**
     * Constructs a {@code InternshipInterviewDateTime} with an interview date.
     *
     * @param dateTime The date and time of the interview.
     */
    public InternshipInterviewDateTime(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        this.interviewDateTime = dateTime;
    }

    /**
     * Returns the date and time of the interview, in LocalDateTime format.
     *
     * @return The date and time of the interview.
     */
    public LocalDateTime getInternshipDateTime() {
        return this.interviewDateTime;
    }

    /**
     * Checks if a given string is in the correct date and time format for parsing.
     *
     * @return true if valid, false if not.
     */
    public static boolean isValidInterviewDateTime(String test) {
        if (isNull(test) || Objects.equals(test.strip(), "")) {
            return false;
        }

        return isValidDateTimeString(test);
    }

    @Override
    public int hashCode() {
        return interviewDateTime.hashCode();
    }

    @Override
    public String toString() {
        return parseDateTimeToString(interviewDateTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipInterviewDateTime)) {
            return false;
        }

        InternshipInterviewDateTime otherInternship = (InternshipInterviewDateTime) other;
        return interviewDateTime.equals(otherInternship.interviewDateTime);
    }

    @Override
    public int compareTo(InternshipInterviewDateTime otherInterviewDateTime) {
        return this.interviewDateTime.compareTo(otherInterviewDateTime.interviewDateTime);
    }

    public boolean isAfter(InternshipInterviewDateTime internshipInterviewDateTime) {
        return this.interviewDateTime.isAfter(internshipInterviewDateTime.interviewDateTime);
    }

    public boolean isBefore(InternshipInterviewDateTime internshipInterviewDateTime) {
        return this.interviewDateTime.isBefore(internshipInterviewDateTime.interviewDateTime);
    }
}
