package seedu.letsgethired.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.letsgethired.commons.exceptions.IllegalValueException;
import seedu.letsgethired.model.InternTracker;
import seedu.letsgethired.model.ReadOnlyInternTracker;
import seedu.letsgethired.model.application.InternApplication;

/**
 * An Immutable InternTracker that is serializable to JSON format.
 */
@JsonRootName(value = "internTracker")
class JsonSerializableInternTracker {

    public static final String MESSAGE_DUPLICATE_INTERN_APPLICATION =
            "Intern Application list contains duplicate intern application(s).";

    private final List<JsonAdaptedInternApplication> applications = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableInternTracker} with the given intern applications.
     */
    @JsonCreator
    public JsonSerializableInternTracker(
            @JsonProperty("applications") List<JsonAdaptedInternApplication> applications) {
        this.applications.addAll(applications);
    }

    /**
     * Converts a given {@code ReadOnlyInternTracker} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableInternTracker}.
     */
    public JsonSerializableInternTracker(ReadOnlyInternTracker source) {
        applications.addAll(source.getApplicationList().stream().map(JsonAdaptedInternApplication::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this intern tracker into the model's {@code InternTracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public InternTracker toModelType() throws IllegalValueException {
        InternTracker internTracker = new InternTracker();
        for (JsonAdaptedInternApplication jsonAdaptedInternApplication : applications) {
            InternApplication internApplication = jsonAdaptedInternApplication.toModelType();
            if (internTracker.hasApplication(internApplication)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_INTERN_APPLICATION);
            }
            internTracker.addApplication(internApplication);
        }
        return internTracker;
    }
}
