package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalDoctor.ALLEN;
import static seedu.address.testutil.TypicalDoctor.BOYD;
import static seedu.address.testutil.TypicalDoctor.CARLOS;
import static seedu.address.testutil.TypicalDoctor.DAVID;
import static seedu.address.testutil.TypicalDoctor.GREG;
import static seedu.address.testutil.TypicalPatient.ALICE;
import static seedu.address.testutil.TypicalPatient.BENSON;
import static seedu.address.testutil.TypicalPatient.CARL;
import static seedu.address.testutil.TypicalPatient.DANIEL;
import static seedu.address.testutil.TypicalPatient.ELLE;
import static seedu.address.testutil.TypicalPatient.FIONA;
import static seedu.address.testutil.TypicalPatient.GEORGE;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.KeywordParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.GenderPredicate;
import seedu.address.model.person.IcContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullField_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FindCommand(null));
    }

    @Test
    public void equals() {
        GenderPredicate firstPredicate =
                new GenderPredicate("female");
        IcContainsKeywordsPredicate secondPredicate =
                new IcContainsKeywordsPredicate("T1234567J");

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        Predicate<Person> predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPatientList());
    }

    @Test
    public void execute_findNric_personFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        Predicate<Person> predicate = preparePredicate("T0131267K");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPatientList());
    }

    @Test
    public void execute_findGender_personFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 9);
        Predicate<Person> predicate = preparePredicate("M");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, CARL, DANIEL, GEORGE),
                model.getFilteredPatientList());
        assertEquals(Arrays.asList(BOYD, CARLOS, DAVID, GREG, ALLEN), model.getFilteredDoctorList());
    }

    @Test
    public void toStringMethod() {
        IcContainsKeywordsPredicate predicate = new IcContainsKeywordsPredicate("keyword");
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code Predicate<Person>}.
     */
    private Predicate<Person> preparePredicate(String userInput) throws ParseException {
        return KeywordParser.parseInput(userInput.split("\\s"));
    }
}
