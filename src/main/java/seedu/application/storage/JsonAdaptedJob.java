package seedu.application.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.application.commons.exceptions.IllegalValueException;
import seedu.application.model.job.*;
import seedu.application.model.job.interview.Interview;

/**
 * Jackson-friendly version of {@link Job}.
 */
class JsonAdaptedJob {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Job's %s field is missing!";

    private final String role;
    private final String company;
    private final String status;
    private final String deadline;
    private final String jobType;
    private final String industry;
    private final List<JsonAdaptedInterview> interviews;

    /**
     * Constructs a {@code JsonAdaptedJob} with the given job details.
     */
    @JsonCreator
    public JsonAdaptedJob(@JsonProperty("role") String role, @JsonProperty("company") String company,
                          @JsonProperty("status") String status, @JsonProperty("deadline") String deadline,
                          @JsonProperty("jobType") String jobType, @JsonProperty("industry") String industry,
                          @JsonProperty("interviews") List<JsonAdaptedInterview> interviews) {
        this.role = role;
        this.company = company;
        this.status = status;
        this.deadline = deadline;
        this.jobType = jobType;
        this.industry = industry;
        this.interviews = interviews;
    }

    /**
     * Converts a given {@code Job} into this class for Jackson use.
     */
    public JsonAdaptedJob(Job source) {
        role = source.getRole().description;
        company = source.getCompany().name;
        status = source.getStatus().status;
        deadline = source.getDeadline().deadline;
        jobType = source.getJobType().jobType;
        industry = source.getIndustry().industry;
        interviews = source.getInterviews().stream()
            .map(JsonAdaptedInterview::new)
            .collect(Collectors.toList());
    }

    /**
     * Converts this Jackson-friendly adapted job object into the model's {@code Job} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted job.
     */
    public Job toModelType() throws IllegalValueException {
        if (role == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Role.class.getSimpleName()));
        }
        if (!Role.isValidRole(role)) {
            throw new IllegalValueException(Role.MESSAGE_CONSTRAINTS);
        }
        final Role modelRole = new Role(role);

        if (company == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Company.class.getSimpleName()));
        }
        if (!Company.isValidCompany(company)) {
            throw new IllegalValueException(Company.MESSAGE_CONSTRAINTS);
        }
        final Company modelCompany = new Company(company);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = new Status(status);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline);

        if (jobType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    JobType.class.getSimpleName()));
        }
        if (!JobType.isValidJobType(jobType)) {
            throw new IllegalValueException(JobType.MESSAGE_CONSTRAINTS);
        }
        final JobType modelJobType = new JobType(jobType);

        if (industry == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Industry.class.getSimpleName()));
        }
        if (!Industry.isValidIndustry(industry)) {
            throw new IllegalValueException(Industry.MESSAGE_CONSTRAINTS);
        }
        final Industry modelIndustry = new Industry(industry);

        List<Interview> modelInterviews = new ArrayList<>();

        if (interviews != null) {
            for (JsonAdaptedInterview interview : interviews) {
                Interview toModelType = interview.toModelType();
                modelInterviews.add(toModelType);
            }
        }

        return new Job(modelRole, modelCompany, modelDeadline, modelStatus,
                modelJobType, modelIndustry, modelInterviews);
    }

}
