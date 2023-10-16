package seedu.lovebook.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.lovebook.commons.exceptions.IllegalValueException;

import seedu.lovebook.model.ReadOnlyDatePrefs;
import seedu.lovebook.model.DatePrefs;

@JsonRootName(value = "DatePrefs")
public class JsonSerializableDatePrefs {
    private JsonAdaptedDatePrefs jsonDatePrefs;
    private DatePrefs datePrefs;

    /**
     * Constructs a {@code JsonSerializableLoveBook} with the given dates.
     */
    @JsonCreator
    public JsonSerializableDatePrefs(@JsonProperty("prefs") JsonAdaptedDatePrefs prefs) {
        this.jsonDatePrefs = prefs;
    }

    /**
     * Converts a given {@code ReadOnlyLoveBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableLoveBook}.
     */
    public JsonSerializableDatePrefs(ReadOnlyDatePrefs source) {
        datePrefs = source.getPreferences();
    }

    /**
     * Converts this lovebook book into the model's {@code LoveBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public DatePrefs toModelType() throws IllegalValueException {
        return jsonDatePrefs.toModelType();
    }
}
