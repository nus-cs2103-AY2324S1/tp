package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Theme;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class ThemeCommandTest {
    private CommandHistory commandHistory = new CommandHistory();

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validTheme_success() {
        ThemeCommand themeCommand = new ThemeCommand(Theme.LIGHT);

        String expectedMessage = String.format(ThemeCommand.MESSAGE_SUCCESS,
                Theme.LIGHT);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(themeCommand, model, expectedMessage, expectedModel, commandHistory);
    }

    @Test
    public void execute_nullTheme_throwsCommandException() {
        assertThrows(AssertionError.class, () -> new ThemeCommand(null));
    }

    @Test
    public void equals() {
        ThemeCommand themeFirstCommand = new ThemeCommand(Theme.LIGHT);
        ThemeCommand themeSecondCommand = new ThemeCommand(Theme.DARK);

        // same object -> returns true
        assertTrue(themeFirstCommand.equals(themeFirstCommand));

        // same values -> returns true
        ThemeCommand themeFirstCommandCopy = new ThemeCommand(Theme.LIGHT);
        assertTrue(themeFirstCommand.equals(themeFirstCommandCopy));

        // different types -> returns false
        assertFalse(themeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(themeFirstCommand.equals(null));

        // different theme -> returns false
        assertFalse(themeFirstCommand.equals(themeSecondCommand));
    }

    @Test
    public void toStringMethod() {
        ThemeCommand themeCommand = new ThemeCommand(Theme.DARK);
        String expected = ThemeCommand.class.getCanonicalName() + "{theme=" + Theme.DARK
                + "}";
        assertEquals(expected, themeCommand.toString());
    }
}
