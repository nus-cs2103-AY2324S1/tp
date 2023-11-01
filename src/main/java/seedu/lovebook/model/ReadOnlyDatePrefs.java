package seedu.lovebook.model;

import java.util.List;

/**
 * Unmodifiable view of date preferences.
 */
public interface ReadOnlyDatePrefs {
    List<DatePrefs> getPreferences();
}
