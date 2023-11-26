package swe.context.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import swe.context.commons.core.GuiSettings;
import swe.context.logic.commands.CommandResult;
import swe.context.logic.commands.exceptions.CommandException;
import swe.context.logic.parser.exceptions.ParseException;
import swe.context.model.contact.Contact;



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
