package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.TimeParser;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.Rating;

/**
 * Jackson-friendly version of {@link seedu.address.model.interview.Interview}.
 * Adapted from AB3's JsonAdaptedPerson
 */
class JsonAdaptedInterview {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Interview's %s field is missing!";
    public static final String APPLICANT_MISSING = "Applicant";
    public static final String MESSAGE_INVALID_APPLICANT =
            "Applicant's hasInterview field must be true if the applicant has an interview!";
    public static final String MESSAGE_INVALID_RATING =
            "Rating cannot be more than 0.0 if the interview is not done!";
    public static final String JOB_ROLE_MISSING = "Job role";
    public static final String START_TIME_MISSING = "Start time";
    public static final String END_TIME_MISSING = "End time";


    private final JsonAdaptedApplicant applicant;
    private final String jobRole;
    private final String rating;
    private final String interviewStartTime;
    private final String interviewEndTime;
    private final boolean isDone;

    /**
     * Constructs a {@code JsonAdaptedInterview} with the given interview details.
     */
    @JsonCreator
    public JsonAdaptedInterview(@JsonProperty("applicant") JsonAdaptedApplicant applicant,
                                @JsonProperty("jobRole") String jobRole,
                                @JsonProperty("rating") String rating,
                                @JsonProperty("interviewStartTime") String interviewStartTime,
                                @JsonProperty("interviewEndTime") String interviewEndTime,
                                @JsonProperty("isDone") boolean isDone) {
        this.applicant = applicant;
        this.jobRole = jobRole;
        this.rating = rating;
        this.interviewStartTime = interviewStartTime;
        this.interviewEndTime = interviewEndTime;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code Interview} into this class for Jackson use.
     */
    public JsonAdaptedInterview(Interview source) {
        applicant = new JsonAdaptedApplicant(source.getInterviewApplicant());
        jobRole = source.getJobRole();
        rating = source.getRating().rating;
        interviewStartTime = source.getInterviewStartTimeAsString();
        interviewEndTime = source.getInterviewEndTimeAsString();
        isDone = source.isDone();
    }

    /**
     * Converts this Jackson-friendly adapted applicant object into the model's {@code Interview} object.
     *
     * @throws seedu.address.commons.exceptions.IllegalValueException if there were any data constraints
     *     violated in the adapted applicant.
     */
    public Interview toModelType() throws IllegalValueException {

        if (applicant == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, APPLICANT_MISSING));
        }

        Applicant modelApplicant = applicant.toModelType();

        if (!modelApplicant.hasInterview()) {
            throw new IllegalValueException(MESSAGE_INVALID_APPLICANT);
        }

        if (jobRole == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, JOB_ROLE_MISSING));
        }

        if (interviewStartTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, START_TIME_MISSING));
        }

        if (rating == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Rating.class.getSimpleName()));
        }

        if (!Rating.isValidRating(rating)) {
            throw new IllegalValueException(Rating.MESSAGE_CONSTRAINTS);
        }

        if (interviewEndTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, END_TIME_MISSING));
        }

        final Rating modelRating = new Rating(rating);

        if (!isDone && !modelRating.equals(new Rating("0.0"))) {
            throw new IllegalValueException(String.format(MESSAGE_INVALID_RATING));
        }

        return new Interview(modelApplicant, jobRole, modelRating,
                TimeParser.parseDate(interviewStartTime, false),
                TimeParser.parseDate(interviewEndTime, false),
                isDone);
    }

}
