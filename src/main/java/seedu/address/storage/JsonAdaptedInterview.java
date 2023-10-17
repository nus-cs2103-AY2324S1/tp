package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.interview.Interview;

/**
 * Jackson-friendly version of {@link seedu.address.model.interview.Interview}.
 * Adapted from AB3's JsonAdaptedPerson
 */
class JsonAdaptedInterview {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Interview's %s field is missing!";
    public static final String APPLICANT_MISSING = "Applicant";
    public static final String JOB_ROLE_MISSING = "Job role";
    public static final String TIMING_MISSING = "Timing";

    private final String applicant;
    private final String jobRole;
    private final String interviewTiming;

    /**
     * Constructs a {@code JsonAdaptedInterview} with the given interview details.
     */
    @JsonCreator
    public JsonAdaptedInterview(@JsonProperty("applicant") String applicant, @JsonProperty("jobRole") String jobRole,
                                @JsonProperty("interviewTiming") String interviewTiming) {
        this.applicant = applicant;
        this.jobRole = jobRole;
        this.interviewTiming = interviewTiming;
    }

    /**
     * Converts a given {@code Interview} into this class for Jackson use.
     */
    public JsonAdaptedInterview(Interview source) {
        applicant = source.getInterviewApplicant();
        jobRole = source.getJobRole();
        interviewTiming = source.getInterviewTiming();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Interview} object.
     *
     * @throws seedu.address.commons.exceptions.IllegalValueException if there were any data constraints
     *     violated in the adapted person.
     */
    public Interview toModelType() throws IllegalValueException {

        if (applicant == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, APPLICANT_MISSING));
        }

        if (jobRole == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, JOB_ROLE_MISSING));
        }

        if (interviewTiming == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, TIMING_MISSING));
        }

        return new Interview(applicant, jobRole, interviewTiming);
    }

}
