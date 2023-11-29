package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code SearchPatientCommand}.
 */
class SearchPatientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private NameContainsKeywordsPredicate preparePredicate(String searchString) {
        return new NameContainsKeywordsPredicate(Arrays.asList(searchString.split("\\s+")));
    }

    @Test
    public void execute_zeroKeywords_noPatientFound() {
        String expectedMessage = String.format(Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        SearchPatientCommand command = new SearchPatientCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        CommandResult commandResult = command.execute(model);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(Collections.emptyList(), model.getFilteredPatientList());
    }

    @Test
    public void execute_multipleKeywords_multiplePatientsFound() {
        model.addPatient(new PatientBuilder().withName("Billy").build());
        model.addPatient(new PatientBuilder().withName("Alexander").build());
        model.addPatient(new PatientBuilder().withName("Jayden").build());

        String expectedMessage = String.format(Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Billy Alexander Jayden");
        SearchPatientCommand command = new SearchPatientCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);

        CommandResult commandResult = command.execute(model);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(3, model.getFilteredPatientList().size());
    }

    @Test
    public void execute_noMatchingKeyword_noPatientFound() {
        NameContainsKeywordsPredicate predicate = preparePredicate("NonexistentKeyword");
        SearchPatientCommand command = new SearchPatientCommand(predicate);
        CommandResult commandResult = command.execute(model);
        assertEquals(String.format(Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW, 0), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_similarButNoExactMatch_keywords() {
        NameContainsKeywordsPredicate predicate = preparePredicate("Jonn");
        SearchPatientCommand command = new SearchPatientCommand(predicate);
        CommandResult commandResult = command.execute(model);
        assertEquals(String.format(Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW, 0), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_caseInsensitiveSearch_keywordFound() {
        model.addPatient(new PatientBuilder().withName("John").build());
        NameContainsKeywordsPredicate predicate = preparePredicate("john");
        SearchPatientCommand command = new SearchPatientCommand(predicate);
        CommandResult commandResult = command.execute(model);
        assertEquals(String.format(Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW, 1), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_nonAlphanumericCharactersSearch_noPatientFound() {
        NameContainsKeywordsPredicate predicate = preparePredicate("@#$%");
        SearchPatientCommand command = new SearchPatientCommand(predicate);
        CommandResult commandResult = command.execute(model);
        assertEquals(String.format(Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW, 0), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_mixtureOfValidAndInvalidKeywords() {
        NameContainsKeywordsPredicate predicate = preparePredicate("ValidName InvalidName");
        SearchPatientCommand command = new SearchPatientCommand(predicate);
        CommandResult commandResult = command.execute(model);
        assertEquals(String.format(Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW, 0), commandResult.getFeedbackToUser());
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        NameContainsKeywordsPredicate predicate = preparePredicate("John");
        SearchPatientCommand command = new SearchPatientCommand(predicate);
        assertTrue(command.equals(command));
    }

    @Test
    public void equals_differentType_returnsFalse() {
        NameContainsKeywordsPredicate predicate = preparePredicate("John");
        SearchPatientCommand command = new SearchPatientCommand(predicate);
        assertFalse(command.equals(1));
    }

    @Test
    public void equals_null_returnsFalse() {
        NameContainsKeywordsPredicate predicate = preparePredicate("John");
        SearchPatientCommand command = new SearchPatientCommand(predicate);
        assertFalse(command.equals(null));
    }

    @Test
    public void equals_searchByNameSameValues_returnsTrue() {
        NameContainsKeywordsPredicate predicate = preparePredicate("John");
        SearchPatientCommand command1 = new SearchPatientCommand(predicate);
        SearchPatientCommand command2 = new SearchPatientCommand(predicate);
        assertTrue(command1.equals(command2));
    }

    @Test
    public void equals_searchByNameDifferentValues_returnsFalse() {
        NameContainsKeywordsPredicate predicateFirst = preparePredicate("John");
        NameContainsKeywordsPredicate predicateSecond = preparePredicate("Mary");
        SearchPatientCommand command1 = new SearchPatientCommand(predicateFirst);
        SearchPatientCommand command2 = new SearchPatientCommand(predicateSecond);
        assertFalse(command1.equals(command2));
    }

    @Test
    public void equals_searchByIdSameValues_returnsTrue() {
        SearchPatientCommand command1 = new SearchPatientCommand(1);
        SearchPatientCommand command2 = new SearchPatientCommand(1);
        assertTrue(command1.equals(command2));
    }

    @Test
    public void equals_searchByIdDifferentValues_returnsFalse() {
        SearchPatientCommand command1 = new SearchPatientCommand(1);
        SearchPatientCommand command2 = new SearchPatientCommand(2);
        assertFalse(command1.equals(command2));
    }
}
