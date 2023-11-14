package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.Meeting;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Contact> filteredContacts;
    private final FilteredList<Meeting> filteredMeetings;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredContacts = new FilteredList<>(this.addressBook.getContactList());
        filteredMeetings = new FilteredList<>(this.addressBook.getMeetingList());
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
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return addressBook.hasContact(contact);
    }

    @Override
    public void deleteContact(Contact target) {
        addressBook.removeContact(target);
    }

    @Override
    public void addContact(Contact contact) {
        addressBook.addContact(contact);
        updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
    }

    @Override
    public void setContact(Contact target, Contact editedContact) {
        requireAllNonNull(target, editedContact);

        addressBook.setContact(target, editedContact);
    }

    @Override
    public void deleteMeeting(Meeting target) {
        addressBook.removeMeeting(target);
    }

    @Override
    public void addMeeting(Meeting meeting) {
        addressBook.addMeeting(meeting);
        updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
    }

    @Override
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);

        addressBook.setMeeting(target, editedMeeting);
    }

    @Override
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return addressBook.hasMeeting(meeting);
    }

    public void update(Meeting meeting, Contact contact) {
        addressBook.update(meeting, contact);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Contact} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return filteredContacts;
    }

    @Override
    public ObservableList<Meeting> getFilteredMeetingList() {
        return filteredMeetings;
    }

    @Override
    public void updateFilteredContactList(Predicate<Contact> predicate) {
        requireNonNull(predicate);
        filteredContacts.setPredicate(predicate);
        addressBook.sortContacts();
    }

    @Override
    public void sortContacts() {
        addressBook.sortContacts();
    }

    @Override
    public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
        requireNonNull(predicate);
        filteredMeetings.setPredicate(predicate);
        addressBook.sortMeetings();
    }

    @Override
    public void sortMeetings() {
        addressBook.sortMeetings();
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
        boolean addressBookCheck = addressBook.equals(otherModelManager.addressBook);
        boolean userPrefsCheck = userPrefs.equals(otherModelManager.userPrefs);
        boolean filteredContactsCheck = filteredContacts.equals(otherModelManager.filteredContacts);
        boolean filteredMeetingsCheck = filteredMeetings.equals(otherModelManager.filteredMeetings);
        return addressBookCheck && userPrefsCheck && filteredContactsCheck && filteredMeetingsCheck;
    }
}
