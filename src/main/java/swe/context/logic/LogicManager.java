package swe.context.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import javafx.collections.ObservableList;
import swe.context.commons.core.GuiSettings;
import swe.context.logic.commands.Command;
import swe.context.logic.commands.CommandResult;
import swe.context.logic.commands.exceptions.CommandException;
import swe.context.logic.parser.InputParser;
import swe.context.logic.parser.exceptions.ParseException;
import swe.context.model.Model;
import swe.context.model.contact.Contact;
import swe.context.storage.Storage;



/**
 * Implementation of the Logic component.
 */
public class LogicManager implements Logic {
    private Model model;
    private Storage storage;

    /**
     * Constructs with the specified values.
     */
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
        Command command = InputParser.parseCommand(commandText);
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
