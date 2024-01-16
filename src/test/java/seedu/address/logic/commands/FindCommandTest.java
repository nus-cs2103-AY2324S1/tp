package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.EventBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.StatusContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new EventBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new EventBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("firstName"));
        StatusContainsKeywordsPredicate firstStatusPredicate =
                new StatusContainsKeywordsPredicate(Collections.singletonList("firstStatus"));
        TagContainsKeywordsPredicate firstTagPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("firstTag"));
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("secondName"));
        StatusContainsKeywordsPredicate secondStatusPredicate =
                new StatusContainsKeywordsPredicate(Collections.singletonList("secondStatus"));
        TagContainsKeywordsPredicate secondTagPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("secondTag"));

        List<Predicate<Person>> firstPredicatesList = Arrays.asList(firstNamePredicate,
                firstStatusPredicate, firstTagPredicate);
        List<Predicate<Person>> secondPredicatesList = Arrays.asList(secondNamePredicate,
                secondStatusPredicate, secondTagPredicate);

        FindCommand findFirstCommand = new FindCommand(firstPredicatesList);
        FindCommand findSecondCommand = new FindCommand(secondPredicatesList);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicatesList);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate(" ");
        StatusContainsKeywordsPredicate statusPredicate = prepareStatusPredicate("");
        TagContainsKeywordsPredicate tagPredicate = prepareTagPredicate("");
        FindCommand command = new FindCommand(Arrays.asList(namePredicate, statusPredicate, tagPredicate));
        expectedModel.updateFilteredPersonList(Arrays.asList(namePredicate, statusPredicate, tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindCommand command = new FindCommand(preparePredicateList("Kurz Elle Kunz", "", ""));
        expectedModel.updateFilteredPersonList(preparePredicateList("Kurz Elle Kunz", "", ""));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleStatusKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 6);
        FindCommand command = new FindCommand(preparePredicateList("", "preliminary rejected", ""));
        expectedModel.updateFilteredPersonList(preparePredicateList("", "preliminary rejected", ""));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleTagKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        FindCommand command = new FindCommand(preparePredicateList("", "", "developer software"));
        expectedModel.updateFilteredPersonList(preparePredicateList("", "", "developer software"));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameStatusKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FindCommand command = new FindCommand(preparePredicateList("Alice Benson", "rejected", ""));
        expectedModel.updateFilteredPersonList(preparePredicateList("Alice Benson", "rejected", ""));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameTagKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FindCommand command = new FindCommand(preparePredicateList("Alice Benson", "", "intern"));
        expectedModel.updateFilteredPersonList(preparePredicateList("Alice Benson", "", "intern"));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleStatusTagKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FindCommand command = new FindCommand(preparePredicateList("", "interviewed", "intern"));
        expectedModel.updateFilteredPersonList(preparePredicateList("", "interviewed", "intern"));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameStatusTagKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FindCommand command = new FindCommand(preparePredicateList("Alice Benson Carl",
                "interviewed", "intern"));
        expectedModel.updateFilteredPersonList(preparePredicateList("Alice Benson Carl",
                "interviewed", "intern"));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }


    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        StatusContainsKeywordsPredicate statusPredicate = new StatusContainsKeywordsPredicate(Arrays.asList("keyword"));
        TagContainsKeywordsPredicate tagPredicate = new TagContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(Arrays.asList(namePredicate, statusPredicate, tagPredicate));
        String expected = FindCommand.class.getCanonicalName() + "{predicates list=[" + namePredicate + ", "
                + statusPredicate + ", " + tagPredicate + "]}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code StatusContainsKeywordsPredicate}.
     */
    private StatusContainsKeywordsPredicate prepareStatusPredicate(String userInput) {
        return new StatusContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    private TagContainsKeywordsPredicate prepareTagPredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    private List<Predicate<Person>> preparePredicateList(String nameKeywords, String statusKeywords,
                                                         String tagKeywords) {
        List<Predicate<Person>> predicatesList = new ArrayList<>() {{
                if (!nameKeywords.isEmpty()) {
                    add(prepareNamePredicate(nameKeywords));
                }
                if (!statusKeywords.isEmpty()) {
                    add(prepareStatusPredicate(statusKeywords));
                }
                if (!tagKeywords.isEmpty()) {
                    add(prepareTagPredicate(tagKeywords));
                }
            }};

        return predicatesList;
    }
}
