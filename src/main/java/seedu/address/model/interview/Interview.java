package seedu.address.model.interview;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;

import seedu.address.logic.parser.TimeParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.Applicant;

/**
 * Represents an Interview in the address book.
 */
public class Interview {
    private final Applicant applicant;
    private final String jobRole;
    /** TODO Change from 'String' to proper 'Date/Time' once natural DT is implemented*/
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final boolean isDone;

    /**
     * Default constructor for Interview object.
     */
    public Interview(Applicant app, String role, String startTimeString, String endTimeString)
            throws ParseException {
        requireAllNonNull(app, role, startTimeString, endTimeString);
        applicant = app;
        jobRole = role;
        startTime = TimeParser.parseDate(startTimeString);
        endTime = TimeParser.parseDate(endTimeString);
        isDone = false;
    }

    /**
     * Constructor for creating Interview object from storage
     *
     * @author Tan Kerway
     * @param app the applicant loaded from the db
     * @param role the applicant's role that they applied for
     * @param startTimeString the start time of the interview
     * @param endTimeString the end time of the interview
     * @throws ParseException if the start and/or end time string are invalid
     */
    public Interview(Applicant app, String role, String startTimeString, String endTimeString, boolean isDone)
            throws ParseException {
        requireAllNonNull(app, role, startTimeString, endTimeString);
        applicant = app;
        jobRole = role;
        startTime = TimeParser.parseDate(startTimeString);
        endTime = TimeParser.parseDate(endTimeString);
        this.isDone = isDone;
    }

    /**
     * Alternative constructor for creating Interview object from storage.
     */
    public Interview(Applicant app, String role, LocalDateTime startTime,
                     LocalDateTime endTime, boolean isDone) {
        requireAllNonNull(app, role, startTime, endTime, isDone);
        applicant = app;
        jobRole = role;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDone = isDone;
    }

    /**
     * Constructs a new instance of Interview.
     *
     * @author Tan Kerway
     * @param app the instance of the applicant class which represents the applicant
     * @param role the role of the applicant
     * @param startTime the start time of the interview
     * @param endTime the end time of the interview
     */
    public Interview(Applicant app, String role, LocalDateTime startTime,
                     LocalDateTime endTime) {
        requireAllNonNull(app, role, startTime, endTime);
        applicant = app;
        jobRole = role;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDone = false;
    }

    /**
     * Returns true if both Interviews have the same Applicant & Timing or if both Interviews are the same object
     * Adapted from AB3's Person.isSamePerson() method
     */
    public boolean isNotValidOrNewInterview(Interview otherInterview) {
        if (otherInterview == this) {
            return true;
        }

        return otherInterview != null
                && otherInterview.startTime.equals(startTime)
                && otherInterview.endTime.equals(endTime)
                && otherInterview.getInterviewApplicant().equals(getInterviewApplicant());
    }

    public Applicant getInterviewApplicant() {
        return applicant;
    }

    public String getJobRole() {
        return jobRole;
    }

    public String getInterviewStartTimeAsString() {
        return TimeParser.formatDate(startTime);
    }

    public LocalDateTime getInterviewStartTime() {
        return startTime.plusDays(0);
    }

    public String getInterviewEndTimeAsString() {
        return TimeParser.formatDate(endTime);
    }

    public LocalDateTime getInterviewEndTime() {
        return endTime.plusDays(0);
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
                && endTime.equals(otherInterview.endTime)
                && isDone == otherInterview.isDone;
    }
}
