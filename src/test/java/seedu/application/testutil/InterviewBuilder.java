package seedu.application.testutil;

import seedu.application.model.job.*;
import seedu.application.model.job.interview.Interview;
import seedu.application.model.job.interview.InterviewAddress;
import seedu.application.model.job.interview.InterviewDateTime;
import seedu.application.model.job.interview.InterviewType;

/**
 * A utility class to help with building Interview objects.
 */
public class InterviewBuilder {

    public static final String DEFAULT_TYPE = "Technical";
    public static final String DEFAULT_DATETIME = "Dec 31 2030 1200";
    public static final String DEFAULT_ADDRESS = "Home";

    private InterviewType interviewType;
    private InterviewDateTime interviewDateTime;
    private InterviewAddress interviewAddress;

    /**
     * Creates a {@code InterviewBuilder} with the default details.
     */
    public InterviewBuilder() {
        interviewType = new InterviewType(DEFAULT_TYPE);
        interviewDateTime = new InterviewDateTime(DEFAULT_DATETIME);
        interviewAddress = new InterviewAddress(DEFAULT_ADDRESS);
    }

    /**
     * Initializes the InterviewBuilder with the data of {@code interviewToCopy}.
     */
    public InterviewBuilder(Interview interviewToCopy) {
        interviewType = interviewToCopy.getInterviewType();
        interviewDateTime = interviewToCopy.getInterviewDateTime();
        interviewAddress = interviewToCopy.getInterviewAddress();
    }

    /**
     * Sets the {@code interviewType} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withType(String interviewType) {
        this.interviewType = new InterviewType(interviewType);
        return this;
    }

    /**
     * Sets the {@code interviewDateTime} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withDateTime(String interviewDateTime) {
        this.interviewDateTime = new InterviewDateTime(interviewDateTime);
        return this;
    }

    /**
     * Sets the {@code interviewAddress} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withAddress(String interviewAddress) {
        this.interviewAddress = new InterviewAddress(interviewAddress);
        return this;
    }

    public Interview build() {
        return new Interview(interviewType, interviewDateTime, interviewAddress);
    }

}
