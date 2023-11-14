package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.displayable.DisplayableListViewItem;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.rsvp.Rsvp;
import seedu.address.model.vendor.Vendor;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Event> getFilteredEventList();

    /** Returns an unmodifiable view of the filtered list of event attendees */
    ObservableList<Person> getFilteredEventAttendeesList();

    /** Returns an unmodifiable view of the filtered list of event vendors */
    ObservableList<Vendor> getFilteredEventVendorsList();

    /** Returns an unmodifiable view of the filtered list of event attendees */
    ObservableList<DisplayableListViewItem> getFilteredDisplayableItemsList();

    /** Returns an unmodifiable view of the filtered list of rsvps */
    ObservableList<Rsvp> getFilteredRsvpList();

    ObservableList<Rsvp> getFilteredEventRsvpList();

    /** Returns the current event to be viewed */
    Event getEventToView();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
