package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlySchedule;
import seedu.address.model.person.Schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link seedu.address.model.person.Schedule}
 */
/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "schedule")
public class JsonSerializableSchedule {
    private final List<JsonAdaptedLesson> lessons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableSchedule} with the given schedule details.
     */
    @JsonCreator
    public JsonSerializableSchedule(@JsonProperty("lessons") List<JsonAdaptedLesson> lessons) {
        this.lessons.addAll(lessons);

    }

    /**
     * Converts a given {@code ReadOnlySchedule} into this class for Jackson use.
     */
    public JsonSerializableSchedule(ReadOnlySchedule source) {

        lessons.addAll(source.getLessonList()
                .stream().map(JsonAdaptedLesson::new)
                .collect(Collectors.toList()));

    }

    public Schedule toModelType () throws IllegalValueException {
        // TODO implementation

        return new Schedule();
    }
}
