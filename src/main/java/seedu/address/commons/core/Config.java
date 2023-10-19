package seedu.address.commons.core;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Level;

import seedu.address.commons.util.ToStringBuilder;



/**
 * Configuration for the app, read from a file.
 */
public class Config {
    public static final Path DEFAULT_PATH = Paths.get("config.json");

    private Path settingsPath = Paths.get("settings.json");
    private Level logLevel = Level.INFO;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("settingsPath", this.settingsPath)
                .add("logLevel", this.logLevel)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof also handles nulls
        if (!(other instanceof Config)) {
            return false;
        }
        Config otherConfig = (Config)other;

        return (
            Objects.equals(this.settingsPath, otherConfig.settingsPath)
            && Objects.equals(this.logLevel, otherConfig.logLevel)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.logLevel, this.settingsPath);
    }

    public Path getSettingsPath() {
        return this.settingsPath;
    }

    public void setSettingsPath(Path settingsPath) {
        this.settingsPath = settingsPath;
    }

    public Level getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(Level logLevel) {
        this.logLevel = logLevel;
    }
}
