package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.logic.parser.TimeParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.Rating;

/**
 * A utility class to help with building Interview objects.
 */
public class InterviewBuilder {

    public static final Applicant DEFAULT_APPLICANT = TypicalApplicants.ALICE;
    public static final String DEFAULT_JOBROLE = "Software Engineer";
    public static final String DEFAULT_RATING = "3.7";
    public static final String DEFAULT_STARTTIME = "12-12-2023 1200";
    public static final String DEFAULT_ENDTIME = "12-12-2023 1300";
    public static final boolean DEFAULT_HAS_DONE = true;

    private Applicant applicant;
    private String jobRole;
    private Rating rating;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isDone;

    /**
     * Creates a {@code InterviewBuilder} with the default details.
     */
    public InterviewBuilder() throws ParseException {
        this.applicant = DEFAULT_APPLICANT;
        this.jobRole = DEFAULT_JOBROLE;
        this.rating = new Rating(DEFAULT_RATING);
        this.startTime = TimeParser.parseDate(DEFAULT_STARTTIME);
        this.endTime = TimeParser.parseDate(DEFAULT_ENDTIME);
        this.isDone = DEFAULT_HAS_DONE;
    }

    /**
     * Initializes the InterviewBuilder with the data of {@code interviewToCopy}.
     */
    public InterviewBuilder(Interview interviewToCopy) {
        this.applicant = interviewToCopy.getInterviewApplicant();
        this.jobRole = interviewToCopy.getJobRole();
        this.rating = interviewToCopy.getRating();
        this.startTime = interviewToCopy.getInterviewStartTime();
        this.endTime = interviewToCopy.getInterviewEndTime();
        this.isDone = interviewToCopy.isDone();
    }

    /**
     * Sets the {@code Applicant} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withApplicant(Applicant applicant) {
        this.applicant = applicant;
        return this;
    }

    /**
     * Sets the jobRole field of the {@code Interview} that we are building.
     */
    public InterviewBuilder withJobRole(String jobRole) {
        this.jobRole = jobRole;
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withRating(String rating) {
        this.rating = new Rating(rating);
        return this;
    }

    /**
     * Sets the {@code Start-Time} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withStartTime(String startTime) throws ParseException {
        this.startTime = TimeParser.parseDate(startTime);
        return this;
    }

    /**
     * Sets the {@code End-Time} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withEndTime(String endTime) throws ParseException {
        this.endTime = TimeParser.parseDate(endTime);
        return this;
    }

    /**
     * Sets the hasDone field of the {@code Interview} that we are building.
     */
    public InterviewBuilder withIsDone(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    public Interview build() {
        return new Interview(applicant, jobRole, startTime, endTime, rating, isDone);
    }

}

