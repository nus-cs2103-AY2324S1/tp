package wedlog.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import wedlog.address.commons.core.GuiSettings;
import wedlog.address.model.person.Guest;
import wedlog.address.model.person.Person;
import wedlog.address.model.person.Vendor;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Saves the current address book state.
     */
    void commitAddressBook();

    /**
     * Restores the address book to its previous state.
     */
    void undoAddressBook();

    /**
     * Restores the address book to its previously state before undo.
     */
    void redoAddressBook();

    /**
     * Returns true if the address book has previous states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if the address book has undone states to restore.
     */
    boolean canRedoAddressBook();

    /**
     * Returns true if a guest with the same identity as {@code guest} exists in the address book.
     */
    boolean hasGuest(Guest guest);

    /**
     * Deletes the given guest.
     * The guest must exist in the address book.
     */
    void deleteGuest(Guest target);

    /**
     * Adds the given guest.
     * {@code guest} must not already exist in the address book.
     */
    void addGuest(Guest guest);

    /**
     * Replaces the given guest {@code target} with {@code editedGuest}.
     * {@code target} must exist in the address book.
     * The guest identity of {@code editedGuest} must not be the same as another existing guest in the address book.
     */
    void setGuest(Guest target, Guest editedGuest);

    /**
     * Returns a {@code RsvpStatistics} with information of the RSVP statuses of guests in {@code AddressBook}.
     * @return {@code RsvpStatistics} object.
     */
    RsvpStatistics getRsvpStatistics();

    /**
     * Returns a {@code DietaryRequirementStatistics} with information of the dietary requirements of guests in
     * {@code AddressBook}.
     * @return {@code DietaryRequirementStatistics} object.
     */
    DietaryRequirementStatistics getDietaryRequirementStatistics();

    /**
     * Returns true if a vendor with the same identity as {@code vendor} exists in the address book.
     */
    boolean hasVendor(Vendor vendor);

    /**
     * Deletes the given vendor.
     * The vendor must exist in the address book.
     */
    void deleteVendor(Vendor target);

    /**
     * Adds the given vendor.
     * {@code vendor} must not already exist in the address book.
     */
    void addVendor(Vendor vendor);

    /**
     * Replaces the given vendor {@code target} with {@code editedVendor}.
     * {@code target} must exist in the address book.
     * The vendor identity of {@code editedVendor} must not be the same as another existing vendor in the address book.
     */
    void setVendor(Vendor target, Vendor editedVendor);

    /** Returns an unmodifiable view of the filtered guest list */
    ObservableList<Guest> getFilteredGuestList();

    /**
     * Updates the filter of the filtered guest list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredGuestList(Predicate<? super Guest> predicate);

    /** Returns an unmodifiable view of the filtered vendor list */
    ObservableList<Vendor> getFilteredVendorList();

    /**
     * Updates the filter of the filtered vendor list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredVendorList(Predicate<? super Vendor> predicate);
}
