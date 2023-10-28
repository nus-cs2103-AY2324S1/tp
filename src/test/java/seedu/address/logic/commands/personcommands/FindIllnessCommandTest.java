package seedu.address.logic.commands.personcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookWithIllness;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.IllnessContainsKeywordsPredicate;

public class FindIllnessCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookWithIllness(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookWithIllness(), new UserPrefs());

    @Test
    public void equals() {
        IllnessContainsKeywordsPredicate firstPredicate =
                new IllnessContainsKeywordsPredicate(Collections.singletonList("fever"));
        IllnessContainsKeywordsPredicate secondPredicate =
                new IllnessContainsKeywordsPredicate(Collections.singletonList("headache"));

        FindIllnessCommand findIllnessFirstCommand = new FindIllnessCommand(firstPredicate);
        FindIllnessCommand findIllnessSecondCommand = new FindIllnessCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findIllnessFirstCommand.equals(findIllnessFirstCommand));

        // same values -> returns true
        FindIllnessCommand findFirstCommandCopy = new FindIllnessCommand(firstPredicate);
        assertTrue(findIllnessFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findIllnessFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findIllnessFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findIllnessFirstCommand.equals(findIllnessSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noIllnessFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        IllnessContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindIllnessCommand command = new FindIllnessCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multipleIllnessFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        IllnessContainsKeywordsPredicate predicate = preparePredicate("FEVER HEADACHE APPENDICITIS");
        FindIllnessCommand command = new FindIllnessCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }


    private IllnessContainsKeywordsPredicate preparePredicate(String userInput) {
        return new IllnessContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
