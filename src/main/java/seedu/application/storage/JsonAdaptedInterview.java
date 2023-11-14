package seedu.application.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.application.commons.exceptions.IllegalValueException;
import seedu.application.model.job.*;
import seedu.application.model.job.interview.Interview;
import seedu.application.model.job.interview.InterviewAddress;
import seedu.application.model.job.interview.InterviewDateTime;
import seedu.application.model.job.interview.InterviewType;

/**
 * Jackson-friendly version of {@link Job}.
 */
class JsonAdaptedInterview {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Interview's %s field is missing!";

    private final String interviewType;
    private final String interviewDateTime;
    private final String interviewAddress;

    /**
     * Constructs a {@code JsonAdaptedInterview} with the given job details.
     */
    @JsonCreator
    public JsonAdaptedInterview(@JsonProperty("interviewType") String interviewType,
                                @JsonProperty("interviewDateTime") String interviewDateTime,
                                @JsonProperty("interviewAddress") String interviewAddress) {
        this.interviewType = interviewType;
        this.interviewDateTime = interviewDateTime;
        this.interviewAddress = interviewAddress;
    }

    /**
     * Converts a given {@code Interview} into this class for Jackson use.
     */
    public JsonAdaptedInterview(Interview source) {
        interviewType = source.getInterviewType().interviewType;
        interviewDateTime = source.getInterviewDateTime().interviewDateTime;
        interviewAddress = source.getInterviewAddress().interviewAddress;
    }

    /**
     * Converts this Jackson-friendly adapted job object into the model's {@code Interview} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Interview.
     */
    public Interview toModelType() throws IllegalValueException {
        if (interviewType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                InterviewType.class.getSimpleName()));
        }
        if (!InterviewType.isValidInterviewType(interviewType)) {
            throw new IllegalValueException(InterviewType.MESSAGE_CONSTRAINTS);
        }
        final InterviewType modelInterviewType = new InterviewType(interviewType);

        if (interviewDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                InterviewDateTime.class.getSimpleName()));
        }
        if (!InterviewDateTime.isValidInterviewDateTime(interviewDateTime)) {
            throw new IllegalValueException(InterviewDateTime.MESSAGE_CONSTRAINTS);
        }
        final InterviewDateTime modelInterviewDateTime = new InterviewDateTime(interviewDateTime);

        if (interviewAddress == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                InterviewAddress.class.getSimpleName()));
        }
        if (!InterviewAddress.isValidInterviewAddress(interviewAddress)) {
            throw new IllegalValueException(InterviewAddress.MESSAGE_CONSTRAINTS);
        }
        final InterviewAddress modelInterviewAddress = new InterviewAddress(interviewAddress);

        return new Interview(modelInterviewType, modelInterviewDateTime, modelInterviewAddress);
    }

}
