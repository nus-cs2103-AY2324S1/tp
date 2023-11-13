package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.SHORTCUT_ALIAS_1;
import static seedu.address.logic.commands.CommandTestUtil.SHORTCUT_ALIAS_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class DeleteShortcutCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullParameters_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteShortcutCommand(null));
    }

    @Test
    public void execute_mappingExists_success() {
        ArrayList<ShortcutAlias> aliasList = new ArrayList<>();
        aliasList.add(SHORTCUT_ALIAS_1);
        DeleteShortcutCommand deleteShortcutCommand = new DeleteShortcutCommand(aliasList);

        String expectedMessage = String.format(DeleteShortcutCommand.MESSAGE_SUCCESS, "del --> delete");

        ModelManager beforeModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        beforeModel.getShortcutSettings().registerShortcut(new ShortcutAlias("del"),
                new CommandWord(DeleteCommand.COMMAND_WORD));

        ModelManager afterModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        afterModel.commit();

        assertCommandSuccess(deleteShortcutCommand, beforeModel, expectedMessage, afterModel);
    }

    @Test
    public void execute_onlySomeMappingsExist_success() {
        ArrayList<ShortcutAlias> aliasList = new ArrayList<>();
        aliasList.add(SHORTCUT_ALIAS_1);
        aliasList.add(SHORTCUT_ALIAS_2);
        DeleteShortcutCommand deleteShortcutCommand = new DeleteShortcutCommand(aliasList);

        String expectedMessage = String.format(DeleteShortcutCommand.MESSAGE_SUCCESS, "del --> delete")
                + String.format(DeleteShortcutCommand.MESSAGE_NONEXISTENT, SHORTCUT_ALIAS_2);

        ModelManager beforeModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        beforeModel.getShortcutSettings().registerShortcut(new ShortcutAlias("del"),
                new CommandWord(DeleteCommand.COMMAND_WORD));

        ModelManager afterModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        afterModel.commit();

        assertCommandSuccess(deleteShortcutCommand, beforeModel, expectedMessage, afterModel);
    }

    @Test
    public void execute_mappingsDoNotExist_success() {
        ArrayList<ShortcutAlias> aliasList = new ArrayList<>();
        aliasList.add(SHORTCUT_ALIAS_1);
        aliasList.add(SHORTCUT_ALIAS_2);
        DeleteShortcutCommand deleteShortcutCommand = new DeleteShortcutCommand(aliasList);

        String expectedMessage = String.format(DeleteShortcutCommand.MESSAGE_NONEXISTENT, SHORTCUT_ALIAS_1)
                + String.format(DeleteShortcutCommand.MESSAGE_NONEXISTENT, SHORTCUT_ALIAS_2);

        ModelManager beforeModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        ModelManager afterModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(deleteShortcutCommand, beforeModel, expectedMessage, afterModel);
    }
    @Test
    public void equals() {
        DeleteShortcutCommand deleteShortcutFirstCommand = new DeleteShortcutCommand(new ArrayList<>() {
            {
                add(SHORTCUT_ALIAS_1);
            }
        });

        DeleteShortcutCommand deleteShortcutSecondCommand = new DeleteShortcutCommand(new ArrayList<>() {
            {
                add(SHORTCUT_ALIAS_2);
            }
        });

        // same object -> returns true

        assertTrue(deleteShortcutFirstCommand.equals(deleteShortcutFirstCommand));

        // same values -> returns true
        assertTrue(deleteShortcutFirstCommand.equals(new DeleteShortcutCommand(new ArrayList<>() {
            {
                add(SHORTCUT_ALIAS_1);
            }
        })));

        // different types -> returns false
        assertFalse(deleteShortcutFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteShortcutFirstCommand.equals(null));

        // different aliases -> returns false
        assertFalse(deleteShortcutFirstCommand.equals(deleteShortcutSecondCommand));

    }
    @Test
    public void toStringMethod() {
        DeleteShortcutCommand deleteShortcutFirstCommand = new DeleteShortcutCommand(new ArrayList<>() {
            {
                add(SHORTCUT_ALIAS_1);
            }
        });
        String firstExpected = DeleteShortcutCommand.class.getCanonicalName()
                + "{shortcutAliasList=" + "[" + SHORTCUT_ALIAS_1 + "]}";
        assertEquals(firstExpected, deleteShortcutFirstCommand.toString());

        DeleteShortcutCommand deleteShortcutSecondCommand = new DeleteShortcutCommand(new ArrayList<>() {
            {
                add(SHORTCUT_ALIAS_1);
                add(SHORTCUT_ALIAS_2);
            }
        });
        String secondExpected = DeleteShortcutCommand.class.getCanonicalName()
                + "{shortcutAliasList=" + "["
                + SHORTCUT_ALIAS_1 + ", "
                + SHORTCUT_ALIAS_2 + "]}";
        assertEquals(secondExpected, deleteShortcutSecondCommand.toString());
    }
}
