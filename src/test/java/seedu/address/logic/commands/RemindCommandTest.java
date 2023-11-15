package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.predicates.RemindPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code RemindCommand}.
 */
public class RemindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        RemindPredicate firstRemindPredicate = new RemindPredicate(0);
        RemindPredicate secondRemindPredicate = new RemindPredicate(30);

        RemindCommand firstRemindCommand = new RemindCommand(firstRemindPredicate);
        RemindCommand secondRemindCommand = new RemindCommand(secondRemindPredicate);

        // same object -> returns true
        assertTrue(firstRemindCommand.equals(firstRemindCommand));

        // same values -> returns true
        RemindCommand firstRemindCommandCopy = new RemindCommand(firstRemindPredicate);
        assertTrue(firstRemindCommand.equals(firstRemindCommandCopy));

        // different types -> returns false
        assertFalse(firstRemindCommand.equals(1));

        // null -> returns false
        assertFalse(firstRemindCommand.equals(null));

        // different person -> returns false
        assertFalse(firstRemindCommand.equals(secondRemindCommand));
    }

    @Test
    public void execute_noPolicyExpiryIn30Days_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        RemindPredicate predicate = new RemindPredicate(30);

        RemindCommand command = new RemindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        RemindPredicate predicate = new RemindPredicate(30);
        RemindCommand remindCommand = new RemindCommand(predicate);

        String expected = RemindCommand.class.getCanonicalName() + "{remind predicate=" + predicate + "}";
        assertEquals(expected, remindCommand.toString());
    }
}
