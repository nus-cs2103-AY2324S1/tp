package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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
import seedu.address.model.person.UniquePersonList;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final List<ReadOnlyModelManager> modelManagerStateList;
    private int currentStatePointer;
    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private Person selectedPerson;
    private final CommandStringStash commandStringStash;
    /**
     * Ideally theme property should be under UserPrefs, but due to limitations of the
     * JSON Serialising library, it causes errors when put under there, and so the
     * theme preference cannot be saved from session to session.
     */
    private final ThemeProperty themeProperty;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.commandStringStash = new CommandStringStash();
        this.themeProperty = new ThemeProperty();
        this.modelManagerStateList = new ArrayList<>();
        this.currentStatePointer = -1;

        // DoConnek Pro shows all patients on startup by default.
        updateFilteredPersonList(PersonType.PATIENT.getSearchPredicate());

        this.selectedPerson = filteredPersons.size() == 0 ? null : filteredPersons.get(0);
        commit();
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    /**
     * Resets this ModelManager's data to copy another ModelManager's
     * @param toCopyFrom ModelManager to copy from
     */
    public void resetData(ReadOnlyModelManager toCopyFrom) {
        setAddressBook(new AddressBook(toCopyFrom.addressBook));
        setUserPrefs(toCopyFrom.userPrefs.getCopy());
        Person personCopy = toCopyFrom.selectedPerson == null
                ? null
                : toCopyFrom.selectedPerson.getCopy();
        updateSelectedPerson(personCopy);
        Predicate<Person> predicateCopy = toCopyFrom.filteredPersons.getPredicate()::test;
        updateFilteredPersonList(predicateCopy);
        setTheme(toCopyFrom.themeProperty.getValue());
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
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
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
        if (null == selectedPerson || hasPerson(selectedPerson)) {
            return selectedPerson;
        }
        this.selectedPerson = filteredPersons.size() == 0 ? null : filteredPersons.get(0);
        return selectedPerson;
    }

    @Override
    public void updateSelectedPerson(Person person) {
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
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons)
                && ((this.isSelectedEmpty() && otherModelManager.isSelectedEmpty())
                || (this.isSelectedEmpty() == otherModelManager.isSelectedEmpty()
                && selectedPerson.equals(otherModelManager.selectedPerson)));
    }

    //=========== Undo-Redo =============================================================
    @Override
    public boolean hasHistory() {
        return currentStatePointer > 0;
    }

    @Override
    public boolean canRedo() {
        return currentStatePointer < modelManagerStateList.size() - 1;
    }

    @Override
    public void redo() {
        if (!canRedo()) {
            throw new ModelManager.NoRedoableStateException();
        }
        currentStatePointer++;
        resetData(modelManagerStateList.get(currentStatePointer));
    }

    /**
     * Saves a copy of the current {@code ModelManager} state at the end of the state list.
     * Undone states are removed from the state list.
     */
    @Override
    public void commit() {
        removeStatesAfterCurrentPointer();
        Person personCopy =
                this.selectedPerson == null
                ? null
                : this.selectedPerson.getCopy();
        //making a copy of current filteredPersons
        UniquePersonList obListCopy = new UniquePersonList();
        obListCopy.setPersons(this.addressBook.getPersonList());
        FilteredList<Person> filteredListCopy = new FilteredList<>(obListCopy.asUnmodifiableObservableList());
        filteredListCopy.setPredicate(this.filteredPersons.getPredicate());

        modelManagerStateList.add(new ReadOnlyModelManager(
                new AddressBook(this.addressBook),
                filteredListCopy,
                this.userPrefs.getCopy(),
                personCopy,
                this.themeProperty.getCopy()
        ));
        currentStatePointer++;
    }

    private void removeStatesAfterCurrentPointer() {
        modelManagerStateList.subList(currentStatePointer + 1, modelManagerStateList.size()).clear();
    }

    /**
     * Restores the ModelManager to its previous state.
     */
    @Override
    public void undo() {
        if (!hasHistory()) {
            throw new seedu.address.model.ModelManager.NoUndoableStateException();
        }
        currentStatePointer--;
        resetData(modelManagerStateList.get(currentStatePointer));
    }
    /**
     * Thrown when trying to {@code undo()} but can't.
     */
    public static class NoUndoableStateException extends RuntimeException {
        private NoUndoableStateException() {
            super("Current state pointer at start of addressBookState list, unable to undo.");
        }
    }

    /**
     * Thrown when trying to {@code redo()} but can't.
     */
    public static class NoRedoableStateException extends RuntimeException {
        private NoRedoableStateException() {
            super("Current state pointer at end of addressBookState list, unable to redo.");
        }
    }
}
