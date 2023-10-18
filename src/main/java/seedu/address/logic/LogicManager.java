package seedu.address.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.InputParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.storage.Storage;



/**
 * Implementation of the Logic component.
 */
public class LogicManager implements Logic {
    private Model model;
    private Storage storage;

    //TODO can the entire parser be static?
    private InputParser inputParser = new InputParser();

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return this.model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        this.model.setGuiSettings(guiSettings);
    }

    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return this.model.getFilteredContactList();
    }

    @Override
    public Path getContactsPath() {
        return this.storage.getContactsPath();
    }

    @Override
    public CommandResult execute(
        String commandText
    ) throws ParseException, CommandException {
        Command command = inputParser.parseCommand(commandText);
        CommandResult result = command.execute(model);

        try {
            storage.saveContacts(model.getContacts());
        } catch (AccessDeniedException e) {
            throw new CommandException(
                String.format(
                    Messages.FILE_OPS_PERMISSION_ERROR_FORMAT,
                    e.getMessage()
                ),
                e
            );
        } catch (IOException e) {
            throw new CommandException(
                String.format(
                    Messages.FILE_OPS_ERROR_FORMAT,
                    e.getMessage()
                ),
                e
            );
        }

        return result;
    }
}
