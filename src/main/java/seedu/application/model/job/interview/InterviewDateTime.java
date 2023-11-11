package seedu.application.model.job.interview;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.application.commons.util.AppUtil;

/**
 * Represents an Interview's date and time in the application book.
 * Guarantees: immutable; is valid as declared in {@link #isValidInterviewDateTime(String)}
 */
public class InterviewDateTime {

    public static final String MESSAGE_CONSTRAINTS =
        "Date and Time should be in valid DateTime format: "
            + "MMM dd yyyy HHmm\n"
            + "Eg. Dec 31 2030 1200";

    public static final String TO_ADD_INTERVIEW_DATE_TIME = "TO_ADD_INTERVIEW_DATE_TIME";
    public static final InterviewDateTime DEFAULT_INTERVIEW_DATE_TIME =
        new InterviewDateTime(TO_ADD_INTERVIEW_DATE_TIME);
    public static final String DEFAULT_DATETIME_FORMAT = "MMM dd yyyy HHmm";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT);

    public final String interviewDateTime;

    /**
     * Constructs a {@code InterviewDateTime}.
     *
     * @param dateTime A valid dateTime.
     */
    public InterviewDateTime(String dateTime) {
        requireNonNull(dateTime);
        AppUtil.checkArgument(isValidInterviewDateTime(dateTime), MESSAGE_CONSTRAINTS);

        if (isEmptyInterviewDateTime(dateTime)) {
            this.interviewDateTime = TO_ADD_INTERVIEW_DATE_TIME;
        } else {
            this.interviewDateTime = dateTime;
        }
    }

    /**
     * Returns true if a given string is a valid interview date time.
     */
    public static boolean isValidInterviewDateTime(String test) {
        if (test.equals(TO_ADD_INTERVIEW_DATE_TIME)) {
            return true;
        }
        try {
            LocalDateTime dateTime = LocalDateTime.parse(test, DATE_TIME_FORMATTER);
            return dateTime.format(DATE_TIME_FORMATTER).equals(test);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns true if a given string is an empty interview date time.
     */
    private static boolean isEmptyInterviewDateTime(String test) {
        return test.equals(TO_ADD_INTERVIEW_DATE_TIME);
    }

    /**
     * Checks if the date and time represented by this {@code InterviewDateTime} is earlier
     * than the other InterviewDateTime. Empty interviewDateTimes are considered to be earlier.
     *
     * @param other The other {@code InterviewDateTime} to be compared to.
     * @return -1 if this InterviewDateTime is earlier, 0 if they are equal, and 1 if it is later.
     */
    public int compareTo(InterviewDateTime other) {
        if (this.interviewDateTime.equals(TO_ADD_INTERVIEW_DATE_TIME)) {
            return other.interviewDateTime.equals(TO_ADD_INTERVIEW_DATE_TIME) ? 0 : -1;
        }

        if (other.interviewDateTime.equals(TO_ADD_INTERVIEW_DATE_TIME)) {
            return 1;
        }

        LocalDateTime thisDateTime = LocalDateTime.parse(this.interviewDateTime, DATE_TIME_FORMATTER);
        LocalDateTime otherDateTime = LocalDateTime.parse(other.interviewDateTime, DATE_TIME_FORMATTER);

        if (thisDateTime.equals(otherDateTime)) {
            return 0;
        } else if (thisDateTime.isAfter(otherDateTime)) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        if (isEmptyInterviewDateTime(interviewDateTime)) {
            return TO_ADD_INTERVIEW_DATE_TIME;
        } else {
            LocalDateTime parsedInterviewDateTime = LocalDateTime.parse(interviewDateTime, DATE_TIME_FORMATTER);
            return parsedInterviewDateTime.format(DATE_TIME_FORMATTER);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InterviewDateTime)) {
            return false;
        }

        InterviewDateTime otherInterviewDateTime = (InterviewDateTime) other;
        return interviewDateTime.equals(otherInterviewDateTime.interviewDateTime);
    }

    @Override
    public int hashCode() {
        return interviewDateTime.hashCode();
    }
}
