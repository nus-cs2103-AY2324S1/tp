package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.fields.IsoDate;
import seedu.address.model.fields.Name;
import seedu.address.model.fields.Outcome;
import seedu.address.model.fields.Status;
import seedu.address.model.fields.TaskOutcome;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.internship.task.InternshipTask;
import seedu.address.model.tag.Tag;

/**
 * Json serialised InternshipTask
 */
public class JsonAdaptedInternshipTask {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "InternshipTask's %s field is missing!";

    private final String name;
    private final String deadline;
    private final Boolean status;
    private final Outcome outcome;
    private final JsonAdaptedInternshipRole internshipRole;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedInternshipRole} with the given internship role details.
     */
    @JsonCreator
    public JsonAdaptedInternshipTask(@JsonProperty("name") String name,
                                     @JsonProperty("deadline") String deadline,
                                     @JsonProperty("outcome") Outcome outcome,
                                     @JsonProperty("status") boolean status,
                                     @JsonProperty("internshipRole") JsonAdaptedInternshipRole internshipRole,
                                     @JsonProperty("tags") List<JsonAdaptedTag> tags) {

        this.name = name;
        this.deadline = deadline;
        this.outcome = outcome;
        this.status = status;
        this.internshipRole = internshipRole;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code InternshipRole} into this class for Jackson use.
     */
    public JsonAdaptedInternshipTask(InternshipTask source) {
        this.name = source.getTaskName().toString();
        this.deadline = source.getDeadline().toSaveData();
        this.status = source.getStatus().isCompleted();
        this.outcome = source.getOutcome().getTaskOutcome();
        this.internshipRole = new JsonAdaptedInternshipRole(source.getInternshipRole());
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted internship role object into the model's {@code InternshipTask} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted internship role.
     */
    public InternshipTask toModelType() throws IllegalValueException {
        final List<Tag> internshipTaskTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            internshipTaskTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidText(name)) {
            throw new IllegalValueException("Name Not Valid " + Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);


        if (deadline == null) {
            throw new IllegalValueException("EndDate Null"
                    + String.format(MISSING_FIELD_MESSAGE_FORMAT, IsoDate.class.getSimpleName()));
        }

        if (!IsoDate.isValidSavedDate(deadline)) {
            throw new IllegalValueException("EndDate Not Valid " + IsoDate.MESSAGE_CONSTRAINTS);
        }

        final IsoDate modelDeadline = new IsoDate(LocalDateTime.parse(deadline,
                DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT)));


        if (outcome == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Outcome.class.getSimpleName()));
        }
        final TaskOutcome modelOutcome = new TaskOutcome(outcome);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        final Status modelStatus = new Status(status);

        if (internshipRole == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, InternshipRole
                    .class.getSimpleName()));
        }
        final InternshipRole modelInternshipRole = internshipRole.toModelType();

        final Set<Tag> modelTags = new HashSet<>(internshipTaskTags);

        return new InternshipTask(modelInternshipRole, modelName, modelDeadline,
                modelStatus, modelOutcome, modelTags);
    }
}
