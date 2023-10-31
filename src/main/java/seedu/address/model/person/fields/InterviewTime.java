package seedu.address.model.person.fields;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InterviewTime {

    LocalDateTime interviewTime;

    public static final String MESSAGE_CONSTRAINTS =
            "Interview time should be in the format of DD/MM/YYYY HHmm";

    public static final String VALIDATION_REGEX = "^\\d{2}/\\d{2}/\\d{4} \\d{4}$";
    public InterviewTime(LocalDateTime interviewTime){

        this.interviewTime = interviewTime;
    }

    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
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

        return interviewTime.equals(otherInterviewTime.interviewTime);
    }

    @Override
    public String toString() {
        if (interviewTime == null) {
            return "Interview time has not been set";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy ha");
        String formattedTime = interviewTime.format(formatter);
        return formattedTime;
    }

    // Create an empty InterviewTime object
    public static InterviewTime createEmptyInterviewTime() {
        return new InterviewTime(null);
    }

    @Override
    public int hashCode() {
        return interviewTime.hashCode();
    }


}
