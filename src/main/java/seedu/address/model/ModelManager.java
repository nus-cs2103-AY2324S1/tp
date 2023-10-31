package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.ShortcutSettings;
import seedu.address.commons.core.ThemeProperty;
import seedu.address.logic.commands.CommandWord;
import seedu.address.logic.commands.ShortcutAlias;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonType;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final TrackedAddressBook trackedAddressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private Person selectedPerson;
    private final CommandStringStash commandStringStash;
    /**
     * Ideally theme property should be under UserPrefs, but due to limitations of the
     * JSON Serialising library, it causes errors when put under there, and so the
     * theme preference cannot be saved from session to session.
     */
    private ThemeProperty themeProperty;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.trackedAddressBook = new TrackedAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.trackedAddressBook.getPersonList());
        this.commandStringStash = new CommandStringStash();
        this.themeProperty = new ThemeProperty();

        // DoConnek Pro shows all patients on startup by default.
        updateFilteredPersonList(PersonType.PATIENT.getSearchPredicate());

        this.selectedPerson = filteredPersons.size() == 0 ? null : filteredPersons.get(0);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public ShortcutSettings getShortcutSettings() {
        return userPrefs.getShortcutSettings();
    }

    @Override
    public void setShortcutSettings(ShortcutSettings shortcutSettings) {
        requireAllNonNull(shortcutSettings);
        userPrefs.setShortcutSettings(shortcutSettings);
    }

    @Override
    public String registerShortcut(ShortcutAlias shortcutAlias, CommandWord commandWord) {
        return getShortcutSettings().registerShortcut(shortcutAlias, commandWord);
    }

    @Override
    public String removeShortcut(ShortcutAlias shortcutAlias) {
        return getShortcutSettings().removeShortcut(shortcutAlias);
    }

    @Override
    public String getShortcut(String alias) {
        return getShortcutSettings().getShortcut(alias);
    }
    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.trackedAddressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return trackedAddressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return trackedAddressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        trackedAddressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        trackedAddressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        trackedAddressBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Selected Person Accessors ==================================================================

    @Override
    public Person getSelectedPerson() {
        return selectedPerson;
    }

    @Override
    public void updateSelectedPerson(Person person) {
        requireNonNull(person);
        selectedPerson = person;
    }

    @Override
    public boolean isSelectedEmpty() {
        return selectedPerson == null;
    }

    //=========== Command String Stash =============================================================

    @Override
    public String getPrevCommandString(String currentCommandString) {
        return commandStringStash.getPrevCommandString(currentCommandString);
    }

    @Override
    public String getPassedCommandString(String currentCommandString) {
        return commandStringStash.getPassedCommandString(currentCommandString);
    }

    @Override
    public void addCommandString(String commandString) {
        commandStringStash.addCommandString(commandString);
    }

    //=========== Theme =============================================================
    @Override
    public void setTheme(Theme theme) {
        themeProperty.setValue(theme);
    }

    @Override
    public void addThemeListener(ChangeListener<? super Theme> changeListener) {
        themeProperty.addListener(changeListener);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return trackedAddressBook.equals(otherModelManager.trackedAddressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons)
                && ((this.isSelectedEmpty() && otherModelManager.isSelectedEmpty())
                || (this.isSelectedEmpty() == otherModelManager.isSelectedEmpty()
                && selectedPerson.equals(otherModelManager.selectedPerson)));
    }

    //=========== Undo-Redo =============================================================
    @Override
    public boolean hasHistory() {
        return trackedAddressBook.hasHistory();
    }

    @Override
    public boolean canRedo() {
        return trackedAddressBook.canRedo();
    }

    @Override
    public void undoAddressBook() {
        trackedAddressBook.undo();
    }

    @Override
    public void commitAddressBook() {
        trackedAddressBook.commit();
    }

    @Override
    public void redoAddressBook() {
        trackedAddressBook.redo();
    }
}
