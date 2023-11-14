package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_GROUPS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalGroups.GROUP1;
import static seedu.address.testutil.TypicalGroups.GROUP2;
import static seedu.address.testutil.TypicalGroups.GROUP3;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.GroupContainsKeywordsPredicate;

public class FindGroupCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        GroupContainsKeywordsPredicate firstPredicate =
                new GroupContainsKeywordsPredicate(Collections.singletonList(String.valueOf(1)));
        GroupContainsKeywordsPredicate secondPredicate =
                new GroupContainsKeywordsPredicate(Collections.singletonList(String.valueOf(2)));

        FindGroupCommand firstFindGroupCommand = new FindGroupCommand(firstPredicate);
        FindGroupCommand secondFindGroupCommand = new FindGroupCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstFindGroupCommand.equals(firstFindGroupCommand));

        // same values -> returns true
        FindGroupCommand firstFindGroupCommandCopy = new FindGroupCommand(firstPredicate);
        assertTrue(firstFindGroupCommand.equals(firstFindGroupCommandCopy));

        // different types -> returns false
        assertFalse(firstFindGroupCommand.equals(1));

        // null -> returns false
        assertFalse(firstFindGroupCommand.equals(null));

        // different group -> returns false
        assertFalse(firstFindGroupCommand.equals(secondFindGroupCommand));
    }

    @Test
    public void execute_zeroKeywords_noGroupFound() {
        String expectedMessage = String.format(MESSAGE_GROUPS_LISTED_OVERVIEW, 0);
        GroupContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindGroupCommand command = new FindGroupCommand(predicate);
        expectedModel.updateFilteredGroupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, true);
        assertEquals(Collections.emptyList(), model.getFilteredGroupList());
    }

    @Test
    public void execute_multipleKeywords_multipleGroupsFound() {
        String expectedMessage = String.format(MESSAGE_GROUPS_LISTED_OVERVIEW, 3);
        GroupContainsKeywordsPredicate predicate = preparePredicate("1 2 3");
        FindGroupCommand command = new FindGroupCommand(predicate);
        expectedModel.updateFilteredGroupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, true);
        assertEquals(Arrays.asList(GROUP1, GROUP2, GROUP3), model.getFilteredGroupList());
    }

    @Test
    public void toStringMethod() {
        GroupContainsKeywordsPredicate predicate = new GroupContainsKeywordsPredicate(Arrays.asList(String.valueOf(1)));
        FindGroupCommand findGroupCommand = new FindGroupCommand(predicate);
        String expected = FindGroupCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findGroupCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code GroupContainsKeywordsPredicate}.
     */
    private GroupContainsKeywordsPredicate preparePredicate(String userInput) {
        return new GroupContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
