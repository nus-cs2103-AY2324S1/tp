package seedu.lovebook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private final List<JsonAdaptedDatePrefs> prefs = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableDatePrefs} with the given preferences.
     */
    @JsonCreator
    public JsonSerializableDatePrefs(@JsonProperty("prefs") List<JsonAdaptedDatePrefs> prefs) {
        this.prefs.addAll(prefs);
    }

    /**
     * Converts a given {@code ReadyOnlyDatePrefs} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableDatePrefs}.
     */
    public JsonSerializableDatePrefs(ReadOnlyDatePrefs source) {
        prefs.addAll(source.getPreferences().stream().map(JsonAdaptedDatePrefs::new).collect(Collectors.toList()));
    }

    /**
     * Converts these preferences into the model's {@code Preferences} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public DatePrefs toModelType() throws IllegalValueException {
        DatePrefs preferences = new DatePrefs();
        for (JsonAdaptedDatePrefs jsonAdaptedDatePrefs : prefs) {
            preferences = jsonAdaptedDatePrefs.toModelType();
        }
        return preferences;
    }
}
