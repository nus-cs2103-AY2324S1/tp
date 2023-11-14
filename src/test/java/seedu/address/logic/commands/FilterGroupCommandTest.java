package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_GROUPS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalGroups.GROUP2;
import static seedu.address.testutil.TypicalGroups.GROUP3;
import static seedu.address.testutil.TypicalGroups.GROUP4;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.GroupBelongsTutorialPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterGroupCommand}.
 */
public class FilterGroupCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        GroupBelongsTutorialPredicate firstPredicate = new GroupBelongsTutorialPredicate("01");
        GroupBelongsTutorialPredicate secondPredicate = new GroupBelongsTutorialPredicate("02");

        FilterGroupCommand filterGroupFirstCommand = new FilterGroupCommand(firstPredicate);
        FilterGroupCommand filterGroupSecondCommand = new FilterGroupCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterGroupFirstCommand.equals(filterGroupFirstCommand));

        // same values -> returns true
        FilterGroupCommand filterGroupFirstCommandCopy = new FilterGroupCommand(firstPredicate);
        assertTrue(filterGroupFirstCommand.equals(filterGroupFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterGroupFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterGroupFirstCommand.equals(null));

        // different tutorial -> returns false
        assertFalse(filterGroupFirstCommand.equals(filterGroupSecondCommand));
    }

    @Test
    public void execute_oneSlot_oneGroupFound() {
        String expectedMessage = String.format(MESSAGE_GROUPS_LISTED_OVERVIEW, 1);
        GroupBelongsTutorialPredicate predicate = preparePredicate("03");
        FilterGroupCommand command = new FilterGroupCommand(predicate);
        expectedModel.updateFilteredGroupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, true);
        assertEquals(Arrays.asList(GROUP3), model.getFilteredGroupList());
    }

    @Test
    public void execute_oneSlot_multipleGroupsFound() {
        String expectedMessage = String.format(MESSAGE_GROUPS_LISTED_OVERVIEW, 2);
        GroupBelongsTutorialPredicate predicate = preparePredicate("02");
        FilterGroupCommand command = new FilterGroupCommand(predicate);
        expectedModel.updateFilteredGroupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, true);
        assertEquals(Arrays.asList(GROUP2, GROUP4), model.getFilteredGroupList());
    }

    @Test
    public void toStringMethod() {
        GroupBelongsTutorialPredicate predicate = new GroupBelongsTutorialPredicate("01");
        FilterGroupCommand filterGroupCommand = new FilterGroupCommand(predicate);
        String expected = FilterGroupCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, filterGroupCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code GroupBelongsTutorialPredicate}.
     */
    private GroupBelongsTutorialPredicate preparePredicate(String userInput) {
        return new GroupBelongsTutorialPredicate(userInput);
    }
}
