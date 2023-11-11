package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.GroupBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindGroupCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FindGroupCommand findCS2105Command = new FindGroupCommand("CS2105");
        FindGroupCommand findCS2100Command = new FindGroupCommand("CS2100");

        // same object -> returns true
        assertTrue(findCS2105Command.equals(findCS2105Command));

        // same values -> returns true
        FindGroupCommand findCS2105CommandCopy = new FindGroupCommand("CS2105");
        assertTrue(findCS2105Command.equals(findCS2105CommandCopy));

        // different types -> returns false
        assertFalse(findCS2105Command.equals(1));

        // null -> returns false
        assertFalse(findCS2105Command.equals(null));

        // different person -> returns false
        assertFalse(findCS2105Command.equals(findCS2100Command));
    }

    @Test
    public void execute_invalidGroup_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_NO_GROUP_WITH_NAME_FOUND);

        // empty input
        String emptyInput = "";
        assertThrows(CommandException.class, expectedMessage,
                () -> new FindGroupCommand(emptyInput).execute(model));

        // nonexistent group
        Group nonexistentGroup = new GroupBuilder().build();
        FindGroupCommand findGroupCommand =
                new FindGroupCommand(nonexistentGroup.getGroupName());

        assertThrows(CommandException.class, expectedMessage,
                () -> findGroupCommand.execute(model));

        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_existingGroup_multiplePersonsFound() throws CommandException {
        Group groupToFind = expectedModel.findGroup("CS2105");
        Predicate<Person> predicate = p -> p.containsGroup(groupToFind);
        expectedModel.updateFilteredPersonList(predicate);
        String expectedMessage = String.format(FindGroupCommand.MESSAGE_GROUP_FOUND,
                "CS2105", "CS2105 remark");

        CommandResult commandResult = new FindGroupCommand(groupToFind.getGroupName()).execute(model);

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        FindGroupCommand findCS2100Command = new FindGroupCommand("CS2100");
        String expected = FindGroupCommand.class.getCanonicalName() + "{group name=" + "CS2100" + "}";
        assertEquals(expected, findCS2100Command.toString());
    }

}