package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalGroups.GROUP10;
import static seedu.address.testutil.TypicalGroups.GROUP11;
import static seedu.address.testutil.TypicalGroups.GROUP12;
import static seedu.address.testutil.TypicalGroups.GROUP13;
import static seedu.address.testutil.TypicalGroups.GROUP14;
import static seedu.address.testutil.TypicalGroups.GROUP15;
import static seedu.address.testutil.TypicalGroups.GROUP4;
import static seedu.address.testutil.TypicalGroups.GROUP5;
import static seedu.address.testutil.TypicalGroups.GROUP6;
import static seedu.address.testutil.TypicalGroups.GROUP7;
import static seedu.address.testutil.TypicalGroups.GROUP8;
import static seedu.address.testutil.TypicalGroups.GROUP9;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.GroupContainsKeywordsPredicate;

public class CheckCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        GroupContainsKeywordsPredicate firstPredicate =
                new GroupContainsKeywordsPredicate(Collections.singletonList(String.valueOf(1)));
        GroupContainsKeywordsPredicate secondPredicate =
                new GroupContainsKeywordsPredicate(Collections.singletonList(String.valueOf(2)));

        CheckCommand firstCheckCommand = new CheckCommand(1, firstPredicate);
        CheckCommand secondCheckCommand = new CheckCommand(2, secondPredicate);

        // same object -> returns true
        assertTrue(firstCheckCommand.equals(firstCheckCommand));

        // same values -> returns true
        CheckCommand firstCheckCommandCopy = new CheckCommand(1, firstPredicate);
        assertTrue(firstCheckCommand.equals(firstCheckCommandCopy));

        // different types -> returns false
        assertFalse(firstCheckCommand.equals(1));

        // null -> returns false
        assertFalse(firstCheckCommand.equals(null));

        // different check -> returns false
        assertFalse(firstCheckCommand.equals(secondCheckCommand));
    }

    @Test
    public void execute_emptyGroup_showMessage() {
        String expectedMessage = String.format(CheckCommand.GROUP_NUM + CheckCommand.MESSAGE_CHECK_GROUP_SIZE_EMPTY
                + CheckCommand.MESSAGE_HELP, 5);
        GroupContainsKeywordsPredicate predicate = preparePredicate("5");
        CheckCommand command = new CheckCommand(5, predicate);
        expectedModel.updateFilteredGroupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, true);
        assertEquals(Arrays.asList(GROUP5), model.getFilteredGroupList());
    }

    @Test
    public void execute_oneMemberGroup_showMessage() {
        String expectedMessage = String.format(CheckCommand.GROUP_NUM + CheckCommand.MESSAGE_CHECK_GROUP_SIZE_ONE
                + CheckCommand.MESSAGE_HELP, 4);
        GroupContainsKeywordsPredicate predicate = preparePredicate("4");
        CheckCommand command = new CheckCommand(4, predicate);
        expectedModel.updateFilteredGroupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, true);
        assertEquals(Arrays.asList(GROUP4), model.getFilteredGroupList());
    }

    @Test
    public void execute_groupLessThanFiveSameNationalitySameGenderMismatchTutorial_showMessage() {
        String expectedMessage = String.format(CheckCommand.GROUP_NUM + CheckCommand.MESSAGE_CHECK_GROUP_SIZE_UNDER
                + CheckCommand.MESSAGE_CHECK_GROUP_NATIONALITY_WARNING + CheckCommand.MESSAGE_CHECK_GROUP_GENDER_WARNING
                + CheckCommand.MESSAGE_CHECK_GROUP_TUTORIAL_WARNING + CheckCommand.MESSAGE_HELP, 6);
        GroupContainsKeywordsPredicate predicate = preparePredicate("6");
        CheckCommand command = new CheckCommand(6, predicate);
        expectedModel.updateFilteredGroupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, true);
        assertEquals(Arrays.asList(GROUP6), model.getFilteredGroupList());
    }

    @Test
    public void execute_groupLessThanFiveSameGenderMismatchTutorial_showMessage() {
        String expectedMessage = String.format(CheckCommand.GROUP_NUM + CheckCommand.MESSAGE_CHECK_GROUP_SIZE_UNDER
                + CheckCommand.MESSAGE_CHECK_GROUP_GENDER_WARNING + CheckCommand.MESSAGE_CHECK_GROUP_TUTORIAL_WARNING
                + CheckCommand.MESSAGE_HELP, 7);
        GroupContainsKeywordsPredicate predicate = preparePredicate("7");
        CheckCommand command = new CheckCommand(7, predicate);
        expectedModel.updateFilteredGroupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, true);
        assertEquals(Arrays.asList(GROUP7), model.getFilteredGroupList());
    }

    @Test
    public void execute_groupLessThanFiveSameNationalityMismatchTutorial_showMessage() {
        String expectedMessage = String.format(CheckCommand.GROUP_NUM + CheckCommand.MESSAGE_CHECK_GROUP_SIZE_UNDER
                + CheckCommand.MESSAGE_CHECK_GROUP_NATIONALITY_WARNING
                + CheckCommand.MESSAGE_CHECK_GROUP_TUTORIAL_WARNING + CheckCommand.MESSAGE_HELP, 8);
        GroupContainsKeywordsPredicate predicate = preparePredicate("8");
        CheckCommand command = new CheckCommand(8, predicate);
        expectedModel.updateFilteredGroupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, true);
        assertEquals(Arrays.asList(GROUP8), model.getFilteredGroupList());
    }

    @Test
    public void execute_groupLessThanFiveSameNationalitySameGender_showMessage() {
        String expectedMessage = String.format(CheckCommand.GROUP_NUM + CheckCommand.MESSAGE_CHECK_GROUP_SIZE_UNDER
                + CheckCommand.MESSAGE_CHECK_GROUP_NATIONALITY_WARNING + CheckCommand.MESSAGE_CHECK_GROUP_GENDER_WARNING
                + CheckCommand.MESSAGE_HELP, 9);
        GroupContainsKeywordsPredicate predicate = preparePredicate("9");
        CheckCommand command = new CheckCommand(9, predicate);
        expectedModel.updateFilteredGroupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, true);
        assertEquals(Arrays.asList(GROUP9), model.getFilteredGroupList());
    }

    @Test
    public void execute_groupMoreThanFiveSameNationalitySameGenderMismatchTutorial_showMessage() {
        String expectedMessage = String.format(CheckCommand.GROUP_NUM + CheckCommand.MESSAGE_CHECK_GROUP_SIZE_OVER
                + CheckCommand.MESSAGE_CHECK_GROUP_NATIONALITY_WARNING + CheckCommand.MESSAGE_CHECK_GROUP_GENDER_WARNING
                + CheckCommand.MESSAGE_CHECK_GROUP_TUTORIAL_WARNING + CheckCommand.MESSAGE_HELP, 10);
        GroupContainsKeywordsPredicate predicate = preparePredicate("10");
        CheckCommand command = new CheckCommand(10, predicate);
        expectedModel.updateFilteredGroupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, true);
        assertEquals(Arrays.asList(GROUP10), model.getFilteredGroupList());
    }

    @Test
    public void execute_groupSameNationalitySameGenderMismatchTutorial_showMessage() {
        String expectedMessage = String.format(CheckCommand.GROUP_NUM
                + CheckCommand.MESSAGE_CHECK_GROUP_NATIONALITY_WARNING + CheckCommand.MESSAGE_CHECK_GROUP_GENDER_WARNING
                + CheckCommand.MESSAGE_CHECK_GROUP_TUTORIAL_WARNING + CheckCommand.MESSAGE_HELP, 11);
        GroupContainsKeywordsPredicate predicate = preparePredicate("11");
        CheckCommand command = new CheckCommand(11, predicate);
        expectedModel.updateFilteredGroupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, true);
        assertEquals(Arrays.asList(GROUP11), model.getFilteredGroupList());
    }

    @Test
    public void execute_groupSameNationalitySameGender_showMessage() {
        String expectedMessage = String.format(CheckCommand.GROUP_NUM
                + CheckCommand.MESSAGE_CHECK_GROUP_NATIONALITY_WARNING + CheckCommand.MESSAGE_CHECK_GROUP_GENDER_WARNING
                + CheckCommand.MESSAGE_HELP, 12);
        GroupContainsKeywordsPredicate predicate = preparePredicate("12");
        CheckCommand command = new CheckCommand(12, predicate);
        expectedModel.updateFilteredGroupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, true);
        assertEquals(Arrays.asList(GROUP12), model.getFilteredGroupList());
    }

    @Test
    public void execute_groupSameGender_showMessage() {
        String expectedMessage = String.format(CheckCommand.GROUP_NUM + CheckCommand.MESSAGE_CHECK_GROUP_GENDER_WARNING
                + CheckCommand.MESSAGE_HELP, 13);
        GroupContainsKeywordsPredicate predicate = preparePredicate("13");
        CheckCommand command = new CheckCommand(13, predicate);
        expectedModel.updateFilteredGroupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, true);
        assertEquals(Arrays.asList(GROUP13), model.getFilteredGroupList());
    }

    @Test
    public void execute_groupSameNationality_showMessage() {
        String expectedMessage = String.format(CheckCommand.GROUP_NUM
                + CheckCommand.MESSAGE_CHECK_GROUP_NATIONALITY_WARNING + CheckCommand.MESSAGE_HELP, 14);
        GroupContainsKeywordsPredicate predicate = preparePredicate("14");
        CheckCommand command = new CheckCommand(14, predicate);
        expectedModel.updateFilteredGroupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, true);
        assertEquals(Arrays.asList(GROUP14), model.getFilteredGroupList());
    }

    @Test
    public void execute_successGroup_showMessage() {
        String expectedMessage = String.format(CheckCommand.GROUP_NUM + CheckCommand.MESSAGE_CHECK_GROUP_SUCCESS, 15);
        GroupContainsKeywordsPredicate predicate = preparePredicate("15");
        CheckCommand command = new CheckCommand(15, predicate);
        expectedModel.updateFilteredGroupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, true);
        assertEquals(Arrays.asList(GROUP15), model.getFilteredGroupList());
    }

    @Test
    public void toStringMethod() {
        GroupContainsKeywordsPredicate predicate = new GroupContainsKeywordsPredicate(Arrays.asList(String.valueOf(1)));
        CheckCommand checkCommand = new CheckCommand(1, predicate);
        String expected = CheckCommand.class.getCanonicalName() + "{groupNumber=" + 1 + "}";
        assertEquals(expected, checkCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code GroupContainsKeywordsPredicate}.
     */
    private GroupContainsKeywordsPredicate preparePredicate(String userInput) {
        return new GroupContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
