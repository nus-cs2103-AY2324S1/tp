package seedu.lovebook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.lovebook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.lovebook.testutil.TypicalDatePrefs.getTypicalDatePrefs;
import static seedu.lovebook.testutil.TypicalPersons.ALICE;
import static seedu.lovebook.testutil.TypicalPersons.BENSON;
import static seedu.lovebook.testutil.TypicalPersons.ELLE;
import static seedu.lovebook.testutil.TypicalPersons.FIONA;
import static seedu.lovebook.testutil.TypicalPersons.getTypicalLoveBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.lovebook.commons.util.PredicatesUtil;
import seedu.lovebook.logic.parser.Prefix;
import seedu.lovebook.model.Model;
import seedu.lovebook.model.ModelManager;
import seedu.lovebook.model.UserPrefs;
import seedu.lovebook.model.person.MetricContainsKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalLoveBook(), new UserPrefs(), getTypicalDatePrefs());
    private Model expectedModel = new ModelManager(getTypicalLoveBook(), new UserPrefs(), getTypicalDatePrefs());
    private final ArrayList<MetricContainsKeywordPredicate> predicates = new ArrayList<>();

    @Test
    public void equals() {
        ArrayList<MetricContainsKeywordPredicate> predicatesList1 = new ArrayList<>();
        ArrayList<MetricContainsKeywordPredicate> predicatesList2 = new ArrayList<>();
        MetricContainsKeywordPredicate predicate1 = new MetricContainsKeywordPredicate("John", PREFIX_NAME);
        MetricContainsKeywordPredicate predicate2 = new MetricContainsKeywordPredicate("Mary", PREFIX_NAME);
        predicatesList1.add(predicate1);
        predicatesList2.add(predicate2);

        FilterCommand filterFirstCommand = new FilterCommand(predicatesList1);
        FilterCommand filterSecondCommand = new FilterCommand(predicatesList2);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand findFirstCommandCopy = new FilterCommand(predicatesList1);
        assertTrue(filterFirstCommand.equals(findFirstCommandCopy));

        //  different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // different keyword -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_filterByName_noPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ArrayList<MetricContainsKeywordPredicate> predicate = preparePredicate("Johnny", PREFIX_NAME);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(new PredicatesUtil(predicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_filterByName_personsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        ArrayList<MetricContainsKeywordPredicate> predicateList = preparePredicate("Alice", PREFIX_NAME);
        FilterCommand command = new FilterCommand(predicateList);
        expectedModel.updateFilteredPersonList(new PredicatesUtil(predicateList));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_filterByHeight_noPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ArrayList<MetricContainsKeywordPredicate> predicateList = preparePredicate("190", PREFIX_HEIGHT);
        System.out.println("predicateList: " + predicateList);
        FilterCommand command = new FilterCommand(predicateList);
        expectedModel.updateFilteredPersonList(new PredicatesUtil(predicateList));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }
    @Test
    public void execute_filterByAge_noPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ArrayList<MetricContainsKeywordPredicate> predicateList = preparePredicate("100", PREFIX_AGE);
        System.out.println("predicateList: " + predicateList);
        FilterCommand command = new FilterCommand(predicateList);
        expectedModel.updateFilteredPersonList((person) -> {
            for (MetricContainsKeywordPredicate predicate : predicates) {
                if (!predicate.test(person)) {
                    return false;
                }
            }
            return true;
        });
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }
    @Test
    public void execute_filterByHeight_personsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        ArrayList<MetricContainsKeywordPredicate> predicateList = preparePredicate("123", PREFIX_HEIGHT);
        System.out.println("predicateList: " + predicateList);
        FilterCommand command = new FilterCommand(predicateList);
        expectedModel.updateFilteredPersonList((person) -> {
            for (MetricContainsKeywordPredicate predicate : predicates) {
                if (!predicate.test(person)) {
                    return false;
                }
            }
            return true;
        });
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_filterByAge_personsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        ArrayList<MetricContainsKeywordPredicate> predicateList = preparePredicate("22", PREFIX_AGE);
        System.out.println("predicateList: " + predicateList);
        FilterCommand command = new FilterCommand(predicateList);
        expectedModel.updateFilteredPersonList(new PredicatesUtil(predicateList));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }
    @Test
    public void execute_filterByGender_personsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ArrayList<MetricContainsKeywordPredicate> predicateList = preparePredicate("F", PREFIX_GENDER);
        System.out.println("predicateList: " + predicateList);
        FilterCommand command = new FilterCommand(predicateList);
        expectedModel.updateFilteredPersonList(new PredicatesUtil(predicateList));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        ArrayList<MetricContainsKeywordPredicate> predicateList = preparePredicate("F", PREFIX_GENDER);
        FilterCommand findCommand = new FilterCommand(predicateList);
        String expected = FilterCommand.class.getCanonicalName() + "{predicateList=" + predicateList + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code MetricContainsKeywordsPredicate}.
     */
    private ArrayList<MetricContainsKeywordPredicate> preparePredicate(String userInput, Prefix prefix) {
        MetricContainsKeywordPredicate predicate = new MetricContainsKeywordPredicate(userInput, prefix);
        predicates.add(predicate);
        return predicates;
    }
}
