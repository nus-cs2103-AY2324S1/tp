package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Contact;



/**
 * API of the Logic component.
 */
public interface Logic {
    public GuiSettings getGuiSettings();
    public void setGuiSettings(GuiSettings guiSettings);

    public ObservableList<Contact> getFilteredContactList();

    public Path getContactsPath();

    /**
     * Returns the {@link CommandResult} of executing the specified command
     * text.
     *
     * @throws ParseException If an error occurs during parsing.
     * @throws CommandException If an error occurs during command execution.
     */
    public CommandResult execute(
        String commandText
    ) throws ParseException, CommandException;
}
