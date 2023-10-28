package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.ShortcutSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {
    private GuiSettings guiSettings = new GuiSettings();
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");
    private ShortcutSettings shortcutSettings = new ShortcutSettings();

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        ShortcutSettings newShortcutSettings = newUserPrefs.getShortcutSettings()
                .removeBadMappings();
        setShortcutSettings(newShortcutSettings);
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
        setTheme(newUserPrefs.getTheme());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public ShortcutSettings getShortcutSettings() {
        return shortcutSettings;
    }

    public void setShortcutSettings(ShortcutSettings shortcutSettings) {
        requireNonNull(shortcutSettings);
        this.shortcutSettings = shortcutSettings;
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    public void setTheme(Theme theme) {
        ThemeProperty.getInstance().setValue(theme);
    }

    public void addThemeListener(ChangeListener<? super Theme> changeListener) {
        ThemeProperty.getInstance().addListener(changeListener);
    }

    public Theme getTheme() {
        return ThemeProperty.getInstance().getValue();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UserPrefs)) {
            return false;
        }

        UserPrefs otherUserPrefs = (UserPrefs) other;
        return guiSettings.equals(otherUserPrefs.guiSettings)
                && addressBookFilePath.equals(otherUserPrefs.addressBookFilePath)
                && shortcutSettings.equals(otherUserPrefs.shortcutSettings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nShortcutAlias Settings : " + shortcutSettings);
        sb.append("\nLocal data file location : " + addressBookFilePath);
        sb.append("\nTheme : " + ThemeProperty.getInstance().getValue());
        return sb.toString();
    }

    /**
     * ThemeProperty implements the Observable interface for a Theme enum.
     * ThemeProperty cannot be saved to user preferences due to limitations of the
     * JSON serialising library being used hence it is declared as private static as it
     * is not needed outside the UserPrefs class.
     */
    private static class ThemeProperty implements Property<Theme> {
        private static final ThemeProperty themeProperty = new ThemeProperty();
        private final SimpleObjectProperty<Theme> delegate = new SimpleObjectProperty<>(Theme.LIGHT);

        private ThemeProperty() {
        }

        public static ThemeProperty getInstance() {
            return themeProperty;
        }

        @Override
        public void bind(ObservableValue<? extends Theme> observable) {
            delegate.bind(observable);
        }

        @Override
        public void unbind() {
            delegate.unbind();
        }

        @Override
        public boolean isBound() {
            return delegate.isBound();
        }

        @Override
        public void bindBidirectional(Property<Theme> other) {
            delegate.bindBidirectional(other);
        }

        @Override
        public void unbindBidirectional(Property<Theme> other) {
            delegate.unbindBidirectional(other);
        }

        @Override
        public Object getBean() {
            return delegate.getBean();
        }

        @Override
        public String getName() {
            return delegate.getName();
        }

        @Override
        public void addListener(ChangeListener<? super Theme> listener) {
            delegate.addListener(listener);
        }

        @Override
        public void addListener(InvalidationListener listener) {
            delegate.addListener(listener);
        }
        @Override
        public void removeListener(ChangeListener<? super Theme> listener) {
            delegate.removeListener(listener);
        }

        @Override
        public void removeListener(InvalidationListener listener) {
            delegate.removeListener(listener);
        }

        @Override
        public Theme getValue() {
            return delegate.getValue();
        }

        @Override
        public void setValue(Theme value) {
            delegate.setValue(value);
        }
    }
}
