package seedu.address.logic.commands.personcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookWithIllness;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        List<String> firstPredicateKeywordListUpperCase = Collections.singletonList("FIRST");
        List<String> firstPredicateKeywordListMixedCase = Collections.singletonList("FiRsT");

        IllnessContainsKeywordsPredicate firstPredicate =
                new IllnessContainsKeywordsPredicate(firstPredicateKeywordList);
        IllnessContainsKeywordsPredicate secondPredicate =
                new IllnessContainsKeywordsPredicate(secondPredicateKeywordList);
        IllnessContainsKeywordsPredicate firstPredicateUpperCase =
                new IllnessContainsKeywordsPredicate(firstPredicateKeywordListUpperCase);
        IllnessContainsKeywordsPredicate firstPredicateMixedCase =
                new IllnessContainsKeywordsPredicate(firstPredicateKeywordListMixedCase);

        FindIllnessCommand findIllnessFirstCommand = new FindIllnessCommand(firstPredicate);
        FindIllnessCommand findIllnessSecondCommand = new FindIllnessCommand(secondPredicate);
        FindIllnessCommand findIllnessFirstCommandUpperCasePredicate = new FindIllnessCommand(firstPredicateUpperCase);
        FindIllnessCommand findIllnessFirstCommandMixedCasePredicate = new FindIllnessCommand(firstPredicateMixedCase);

        // same object -> returns true
        assertTrue(findIllnessFirstCommand.equals(findIllnessFirstCommand));

        // same values -> returns true
        FindIllnessCommand findFirstCommandCopy = new FindIllnessCommand(firstPredicate);
        assertTrue(findIllnessFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findIllnessFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findIllnessFirstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(findIllnessFirstCommand.equals(findIllnessSecondCommand));

        // predicate with same keywords but one upper case and another lower case -> returns true
        assertTrue(findIllnessFirstCommand.equals(findIllnessFirstCommandUpperCasePredicate));

        // predicate with same keywords but one mixed case and another lower case -> returns true
        assertTrue(findIllnessFirstCommand.equals(findIllnessFirstCommandMixedCasePredicate));
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
    public void execute_singleKeyword_singleIllnessFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        IllnessContainsKeywordsPredicate predicate = preparePredicate("FEVER");
        FindIllnessCommand command = new FindIllnessCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleKeywords_multipleIllnessFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        IllnessContainsKeywordsPredicate predicate = preparePredicate("FEVER HEADACHE APPENDICITIS");
        FindIllnessCommand command = new FindIllnessCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_partialKeyword_singleIllnessFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        // Note: the keyword 'fev' should still identify someone with 'fever'
        IllnessContainsKeywordsPredicate predicate = preparePredicate("fev");
        FindIllnessCommand command = new FindIllnessCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_wrongKeyword_noIllnessFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        // Note: typing the keyword 'FEVAR' instead of 'FEVER' will yield no result
        IllnessContainsKeywordsPredicate predicate = preparePredicate("FEVAR");
        FindIllnessCommand command = new FindIllnessCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    private IllnessContainsKeywordsPredicate preparePredicate(String userInput) {
        return new IllnessContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
