package seedu.address.logic.commands;

import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandResultExecutedFromLogicManager;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertExceptionExecutedFromLogicManager;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.logic.LogicManager;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.storage.Storage;


public class UndoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private LogicManager getNewLogicManager() {
        return new LogicManager(model, new Storage() {
            @Override
            public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
                return Optional.empty();
            }

            @Override
            public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {

            }
            @Override
            public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
            }

            @Override
            public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
                return Optional.empty();
            }
            @Override
            public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
                return Optional.empty();
            }

            @Override
            public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {

            }

            @Override
            public Path getAddressBookFilePath() {
                return null;
            }

            @Override
            public Path getUserPrefsFilePath() {
                return null;
            }
        });
    }

    /**
     * Deletes the first person in {@code model}'s filtered list from {@code model}'s address book.
     */
    public static void deleteFirstPerson(Model model) {
        Person firstPerson = model.getFilteredPersonList().get(0);
        model.deletePerson(firstPerson);
        model.commit();
    }

    @BeforeEach
    public void setUp() {
        // set up of models' undo/redo history
        deleteFirstPerson(model);
        deleteFirstPerson(model);

        deleteFirstPerson(expectedModel);
        deleteFirstPerson(expectedModel);
    }

    @Test
    public void execute() {
        // multiple undoable states in model
        expectedModel.undo();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // single undoable state in model
        expectedModel.undo();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // no undoable states in model
        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_FAILURE);
    }

    @Test
    public void undoAddShortcutCommandSuccess() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        LogicManager logicManager = getNewLogicManager();

        //multiple successions of addsc and undo
        model.getShortcutSettings().registerShortcut(new ShortcutAlias("del"),
                new CommandWord(DeleteCommand.COMMAND_WORD));
        model.commit();

        model.getShortcutSettings().registerShortcut(new ShortcutAlias("del2"),
                new CommandWord(DeleteCommand.COMMAND_WORD));
        model.commit();

        model.getShortcutSettings().registerShortcut(new ShortcutAlias("del3"),
                new CommandWord(DeleteCommand.COMMAND_WORD));
        model.commit();
        model.undo();
        model.undo();
        model.undo();

        //all previously added and then undone should throw unknown command error when called
        assertExceptionExecutedFromLogicManager(logicManager, "del 1",
                new ParseException(MESSAGE_UNKNOWN_COMMAND));
        assertExceptionExecutedFromLogicManager(logicManager, "del2 1",
                new ParseException(MESSAGE_UNKNOWN_COMMAND));
        assertExceptionExecutedFromLogicManager(logicManager, "del3 1",
                new ParseException(MESSAGE_UNKNOWN_COMMAND));

        //final addsc to make sure previous unknown command errors are not flukes
        model.getShortcutSettings().registerShortcut(new ShortcutAlias("del4"),
                new CommandWord(DeleteCommand.COMMAND_WORD));
        model.commit();

        String commandResultString = "Deleted 1 Person(s):\n"
                + "1. Patient: Alice Pauline; Phone: 94351253";
        assertCommandResultExecutedFromLogicManager(logicManager, "del4 1",
                new CommandResult(commandResultString));
    }

    @Test
    public void undoDeleteShortcutCommandSuccess() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        LogicManager logicManager = getNewLogicManager();

        //add and test shortcut works
        model.getShortcutSettings().registerShortcut(new ShortcutAlias("del2"),
                new CommandWord(DeleteCommand.COMMAND_WORD));
        model.commit();
        String commandResultString = "Deleted 1 Person(s):\n"
                + "1. Patient: Alice Pauline; Phone: 94351253";
        assertCommandResultExecutedFromLogicManager(logicManager, "del2 1",
                new CommandResult(commandResultString));

        //remove and test shortcut doesnt work
        model.getShortcutSettings().removeShortcut(new ShortcutAlias("del2"));
        model.commit();
        assertExceptionExecutedFromLogicManager(logicManager, "del2 1",
                new ParseException(MESSAGE_UNKNOWN_COMMAND));

        //undo and test shortcut works again
        model.undo();
        commandResultString = "Deleted 1 Person(s):\n"
                + "1. Patient: Benson Meier; Phone: 98765432";
        assertCommandResultExecutedFromLogicManager(logicManager, "del2 1",
                new CommandResult(commandResultString));
    }
}
