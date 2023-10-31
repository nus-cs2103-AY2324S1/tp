package seedu.address.model.interview;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;

import seedu.address.logic.parser.TimeParser;
import seedu.address.logic.parser.exceptions.ParseException;
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
     * Default constructor for Interview object.
     */
    public Interview(Applicant app, String role, String startTimeString, String endTimeString)
            throws ParseException {
        requireAllNonNull(app, role, startTimeString, endTimeString);
        this.applicant = app;
        this.jobRole = role;
        this.rating = new Rating("0.0");
        this.startTime = new Time(TimeParser.parseDate(startTimeString));
        this.endTime = new Time(TimeParser.parseDate(endTimeString));
        this.isDone = false;
    }

    /**
     * Creates an Interview object from storage.
     *
     * @author Tan Kerway
     * @param app the applicant loaded from the db
     * @param role the applicant's role that they applied for
     * @param startTimeString the start time of the interview
     * @param endTimeString the end time of the interview
     * @param rating the rating of the interviewee
     * @throws ParseException if the start and/or end time string are invalid
     */
    public Interview(Applicant app, String role, String startTimeString, String endTimeString,
                     Rating rating, boolean isDone)
            throws ParseException {
        requireAllNonNull(app, role, startTimeString, endTimeString);
        this.applicant = app;
        this.jobRole = role;
        this.startTime = new Time(TimeParser.parseDate(startTimeString));
        this.endTime = new Time(TimeParser.parseDate(endTimeString));
        this.rating = rating;
        this.isDone = isDone;
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
        this.applicant = app;
        this.jobRole = role;
        this.startTime = new Time(TimeParser.parseDate(startTimeString));
        this.endTime = new Time(TimeParser.parseDate(endTimeString));
        this.rating = new Rating("0.0");
        this.isDone = isDone;
    }

    /**
     * Alternative constructor for creating Interview object from storage.
     */
    public Interview(Applicant app, String role, LocalDateTime startTime,
                     LocalDateTime endTime, boolean isDone) {
        requireAllNonNull(app, role, startTime, endTime, isDone);
        this.applicant = app;
        this.jobRole = role;
        this.startTime = new Time(startTime);
        this.endTime = new Time(endTime);
        this.rating = new Rating("0.0");
        this.isDone = isDone;
    }

    /**
     * Alternative constructor for creating Interview object from editing.
     */
    public Interview(Applicant app, String role, LocalDateTime startTime, LocalDateTime endTime,
                     Rating rate, boolean isDone) {
        requireAllNonNull(app, role, startTime, endTime, rate, isDone);
        this.applicant = app;
        this.jobRole = role;
        this.startTime = new Time(startTime);
        this.endTime = new Time(endTime);
        this.rating = rate;
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
        this.startTime = new Time(startTime);
        this.endTime = new Time(endTime);
        this.rating = new Rating("0.0");
        this.isDone = false;
    }

    /**
     * Returns true if both Interviews have the same Applicant & Timing or if both Interviews are the same object
     * Adapted from AB3's Person.isSamePerson() method
     */
    public boolean isSameInterview(Interview otherInterview) {
        if (otherInterview == this) {
            return true;
        }

        return otherInterview != null
                && otherInterview.startTime.equals(startTime)
                && otherInterview.endTime.equals(endTime)
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
                && startTime.getTime().equals(endTime.getTime());
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

    public LocalDateTime getInterviewStartTime() {
        return startTime.getDateAndTime();
    }

    public String getInterviewEndTimeAsString() {
        return TimeParser.formatDate(endTime.getDateAndTime());
    }

    public LocalDateTime getInterviewEndTime() {
        return endTime.getDateAndTime();
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
