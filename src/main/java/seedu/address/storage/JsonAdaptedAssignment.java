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
import seedu.address.model.assignment.Assignment;
import seedu.address.model.fields.Date;
import seedu.address.model.fields.Description;
import seedu.address.model.fields.IsoDate;
import seedu.address.model.fields.Name;
import seedu.address.model.fields.NoDate;
import seedu.address.model.fields.Status;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Assignment}.
 */
class JsonAdaptedAssignment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Assignment's %s field is missing!";

    private final String name;
    private final String description;
    private final Boolean status;
    private final String endDate;
    private final String plannedFinishDate;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAssignment} with the given assignment details.
     */
    @JsonCreator
    public JsonAdaptedAssignment(@JsonProperty("name") String name, @JsonProperty("description") String description,
                                 @JsonProperty("status") boolean status, @JsonProperty("endDate") String endDate,
                                 @JsonProperty("plannedFinishDate") String plannedFinishDate,
                                 @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.endDate = endDate;
        this.plannedFinishDate = plannedFinishDate;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Assignment} into this class for Jackson use.
     */
    public JsonAdaptedAssignment(Assignment source) {
        this.name = source.getName().toString();
        this.description = source.getDescription().toString();
        this.status = source.getStatus().isCompleted();
        this.endDate = source.getEnd().toSaveData();
        this.plannedFinishDate = source.getPlannedFinishDate().toSaveData();
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted assignment object into the model's {@code Assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted assignment.
     */
    public Assignment toModelType() throws IllegalValueException {
        final List<Tag> assignmentTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            assignmentTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidText(name)) {
            throw new IllegalValueException("Name Not Valid " + Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        final Description modelDescription = new Description(description);


        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        final Status modelStatus = new Status(status);


        if (endDate == null) {
            throw new IllegalValueException("EndDate Null"
                    + String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }

        if (!IsoDate.isValidSavedDate(endDate)) {
            throw new IllegalValueException("EndDate Not Valid " + IsoDate.MESSAGE_CONSTRAINTS);
        }

        final IsoDate modelEndDate = new IsoDate(LocalDateTime.parse(endDate,
                DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT)));


        if (plannedFinishDate != null && !IsoDate.isValidSavedDate(plannedFinishDate)) {
            throw new IllegalValueException("PlannedFinishDate" + IsoDate.MESSAGE_CONSTRAINTS);
        }

        final Date modelPlannedFinishDate;

        if (plannedFinishDate == null) {
            modelPlannedFinishDate = new NoDate();
        } else {
            modelPlannedFinishDate = new IsoDate(LocalDateTime.parse(plannedFinishDate,
                    DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT)));
        }

        final Set<Tag> modelTags = new HashSet<>(assignmentTags);
        return new Assignment(modelName, modelEndDate, modelStatus,
                modelDescription, modelPlannedFinishDate, modelTags);
    }
}
