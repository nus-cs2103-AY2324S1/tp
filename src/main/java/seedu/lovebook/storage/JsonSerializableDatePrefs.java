package seedu.lovebook.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.lovebook.commons.exceptions.IllegalValueException;
import seedu.lovebook.model.DatePrefs;
import seedu.lovebook.model.ReadOnlyDatePrefs;

/**
 * An Immutable DatePrefs that is serializable to JSON format.
 */
@JsonRootName(value = "DatePrefs")
public class JsonSerializableDatePrefs {
    private JsonAdaptedDatePrefs jsonDatePrefs;
    private DatePrefs datePrefs;

    /**
     * Constructs a {@code JsonSerializableDatePrefs} with the given preferences.
     */
    @JsonCreator
    public JsonSerializableDatePrefs(@JsonProperty("prefs") JsonAdaptedDatePrefs prefs) {
        this.jsonDatePrefs = prefs;
    }

    /**
     * Converts a given {@code ReadyOnlyDatePrefs} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableDatePrefs}.
     */
    public JsonSerializableDatePrefs(ReadOnlyDatePrefs source) {
        datePrefs = source.getPreferences();
    }

    /**
     * Converts these preferences into the model's {@code Preferences} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public DatePrefs toModelType() throws IllegalValueException {
        return jsonDatePrefs.toModelType();
    }
}
