package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyContacts;
import seedu.address.model.contact.Contact;



/**
 * API of the Logic component.
 */
public interface Logic {
    /**
     * Returns the {@link CommandResult} of executing the specified command
     * text.
     *
     * @throws ParseException If an error occurs during parsing.
     * @throws CommandException If an error occurs during command execution.
     */
    CommandResult execute(String commandText) throws ParseException, CommandException;

    /**
     * Returns the ContactList.
     *
     * @see seedu.address.model.Model#getContacts()
     */
    // ReadOnlyContacts getContactList();

    /** Returns an unmodifiable view of the filtered list of contacts */
    // ObservableList<Contact> getFilteredContactList();

    /**
     * Returns the user prefs' address book file path.
     */
    // Path getConTextFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    // GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    // void setGuiSettings(GuiSettings guiSettings);
}
