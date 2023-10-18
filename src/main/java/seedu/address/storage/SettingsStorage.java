package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlySettings;
import seedu.address.model.Settings;



/**
 * Represents a storage for {@link Settings}.
 */
public interface SettingsStorage {
    /**
     * Returns the {@link Path} of the settings storage file.
     */
    Path getSettingsPath();

    /**
     * Returns {@link Optional} {@link Settings} by reading the settings
     * storage file.
     *
     * If no file is found, returns {@link Optional.empty} instead.
     *
     * @throws DataLoadingException If loading data from the file fails.
     */
    Optional<Settings> readSettings() throws DataLoadingException;

    /**
     * Saves the specified {@link ReadOnlySettings} to the settings storage
     * file.
     *
     * @throws IOException If writing data to the file fails.
     */
    void saveSettings(ReadOnlySettings settings) throws IOException;
}
