package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlySettings;
import seedu.address.model.Settings;



/**
 * Handles reading and saving {@link Settings} to and from the settings storage
 * JSON file.
 *
 * Contains an immutable {@link Path}.
 */
public class JsonSettingsStorage implements SettingsStorage {
    private final Path path;

    public JsonSettingsStorage(Path path) {
        this.path = path;
    }

    @Override
    public Path getSettingsPath() {
        return this.path;
    }

    @Override
    public Optional<Settings> readSettings() throws DataLoadingException {
        return JsonUtil.readJsonFile(
            this.path,
            Settings.class
        );
    }

    @Override
    public void saveSettings(ReadOnlySettings settings) throws IOException {
        JsonUtil.saveJsonFile(settings, this.path);
    }
}
