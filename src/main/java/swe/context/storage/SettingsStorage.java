package swe.context.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import swe.context.commons.exceptions.DataLoadingException;
import swe.context.model.ReadOnlySettings;
import swe.context.model.Settings;



/**
 * Represents a storage for {@link Settings}.
 */
public interface SettingsStorage {
    /**
     * Returns the {@link Path} of the settings storage file.
     */
    public Path getSettingsPath();

    /**
     * Returns {@link Optional} {@link Settings} by reading the settings
     * storage file.
     *
     * If no file is found, returns {@link Optional.empty} instead.
     *
     * @throws DataLoadingException If loading data from the file fails.
     */
    public Optional<Settings> readSettings() throws DataLoadingException;

    /**
     * Saves the specified {@link ReadOnlySettings} to the settings storage
     * file.
     *
     * @throws IOException If writing data to the file fails.
     */
    public void saveSettings(ReadOnlySettings settings) throws IOException;
}
