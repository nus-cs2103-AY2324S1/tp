package seedu.address.model.interview;

import seedu.address.logic.parser.TimeParser;
import seedu.address.model.Time;
import seedu.address.model.applicant.Applicant;

/**
 * Represents an Interview in the address book.
 */
public class Interview {
    private final Applicant applicant;
    private final String jobRole;
    private final Rating rating;
    private final Time startTime;
    private final Time endTime;
    private final boolean isDone;

    /**
     * Constructs an instance of an Interview loaded from the json file.
     *
     * @author Tan Kerway
     * @param applicant the applicant loaded from the json file
     * @param jobRole the job role of the applicant
     * @param rating the rating of the applicant
     * @param startTime the start time of the interview
     * @param endTime the end time of the interview
     * @param isDone whether the interview loaded is done or not
     */
    public Interview(Applicant applicant,
                     String jobRole, Rating rating,
                     Time startTime, Time endTime, boolean isDone) {
        this.applicant = applicant;
        this.jobRole = jobRole;
        this.rating = rating;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDone = isDone;
    }

    /**
     * Constructs an instance of the Interview class when add-i
     * command is executed.
     *
     * @author Tan Kerway
     * @param applicant the Applicant object instance containing the
     *                  details of the applicant
     * @param jobRole the job role of the applicant is applying for
     * @param startTime the start time of the interview to be added
     * @param endTime the end time of the interview to be added
     */
    public Interview(Applicant applicant, String jobRole, Time startTime, Time endTime) {
        this.applicant = applicant;
        this.jobRole = jobRole;
        this.rating = new Rating("0.0");
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDone = false;
    }

    /**
     * Returns true if both Interviews have the same Applicant or if both Interviews are the same object
     * Adapted from AB3's Person.isSamePerson() method
     */
    public boolean isSameInterview(Interview otherInterview) {
        if (otherInterview == this) {
            return true;
        }

        return otherInterview != null
                && otherInterview.getInterviewApplicant().equals(getInterviewApplicant());
    }

    /**
     * Returns true if startTime is before endTime, both times are
     * within working hours, and startTime and endTime are on the same day.
     */
    public boolean isValid() {
        return startTime.isBefore(endTime)
                && startTime.isWithinWorkingHours()
                && endTime.isWithinWorkingHours()
                && startTime.getDate().equals(endTime.getDate());
    }

    /**
     * Returns true if this interview instance clashes with
     * the given otherInterview in the argument.
     */
    public boolean isClashingWith(Interview otherInterview) {
        // Interviews do not clash with themselves
        if (isSameInterview(otherInterview)) {
            return false;
        }

        Time otherStartTime = otherInterview.getInterviewStartTime();
        Time otherEndTime = otherInterview.getInterviewEndTime();

        return (startTime.isBetween(otherStartTime, otherEndTime) || startTime.equals(otherStartTime))
                || (endTime.isBetween(otherStartTime, otherEndTime) || endTime.equals(otherEndTime))
                || otherStartTime.isBetween(startTime, endTime);
    }

    public Applicant getInterviewApplicant() {
        return applicant;
    }

    public String getJobRole() {
        return jobRole;
    }

    public String getInterviewStartTimeAsString() {
        return TimeParser.formatDate(startTime.getDateAndTime());
    }

    public Time getInterviewStartTime() {
        return startTime;
    }

    public String getInterviewEndTimeAsString() {
        return TimeParser.formatDate(endTime.getDateAndTime());
    }

    public Time getInterviewEndTime() {
        return endTime;
    }

    public Rating getRating() {
        return rating;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns true if both interviews have the same identity and data fields.
     * This defines a stronger notion of equality between two interviews
     * Mostly used for testing purposes
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Interview)) {
            return false;
        }

        Interview otherInterview = (Interview) other;
        return applicant.equals(otherInterview.applicant)
                && jobRole.equals(otherInterview.jobRole)
                && startTime.equals(otherInterview.startTime)
                && endTime.equals(otherInterview.endTime);
    }

    @Override
    public String toString() {
        return "applicant: "
                + applicant
                + "\n"
                + "job role: "
                + jobRole
                + "\n"
                + "start time: "
                + startTime
                + "end time: "
                + endTime;
    }
}
