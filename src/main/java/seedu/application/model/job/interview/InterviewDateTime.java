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
        this.interviewDateTime = dateTime;
    }

    /**
     * Returns true if a given string is a valid interview date time.
     */
    public static boolean isValidInterviewDateTime(String test) {
        if (test == null) {
            throw new NullPointerException();
        }
        try {
            LocalDateTime dateTime = LocalDateTime.parse(test, DATE_TIME_FORMATTER);
            return dateTime.format(DATE_TIME_FORMATTER).equals(test);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        LocalDateTime parsedInterviewDateTime = LocalDateTime.parse(interviewDateTime, DATE_TIME_FORMATTER);
        return parsedInterviewDateTime.format(DATE_TIME_FORMATTER);
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
