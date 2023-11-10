package seedu.address.logic.commands.appointmentcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.PatientContainsKeywordsPredicate;
import seedu.address.testutil.TypicalAppointments;


/**
 * JUnit test class for {@code FindAppointmentCommand}.
 */
public class FindAppointmentCommandTest {
    private Model model = new ModelManager(TypicalAppointments.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalAppointments.getTypicalAddressBook(), new UserPrefs());

    /**
     * Test equality of {@code FindAppointmentCommand}.
     */
    @Test
    public void equals() {
        PatientContainsKeywordsPredicate firstPatientPredicate =
                new PatientContainsKeywordsPredicate(Collections.singletonList("John"));
        PatientContainsKeywordsPredicate secondPatientPredicate =
                new PatientContainsKeywordsPredicate(Collections.singletonList("Doe"));

        FindAppointmentCommand firstPatientFindAppointmentCommand =
                new FindAppointmentCommand(firstPatientPredicate);
        FindAppointmentCommand secondPatientFindAppointmentCommand =
                new FindAppointmentCommand(secondPatientPredicate);

        // same object -> returns true
        assertTrue(firstPatientFindAppointmentCommand.equals(firstPatientFindAppointmentCommand));

        // contains the same values -> returns true
        FindAppointmentCommand copy = new FindAppointmentCommand(firstPatientPredicate);
        assertTrue(firstPatientFindAppointmentCommand.equals(copy));

        // different types -> returns false
        assertFalse(firstPatientFindAppointmentCommand.equals(1));

        // null -> returns false
        assertFalse(firstPatientFindAppointmentCommand.equals(null));

        // command for different patients -> returns false
        assertFalse(firstPatientFindAppointmentCommand.equals(secondPatientFindAppointmentCommand));
    }

    /**
     * Tests the execution of {@code FindAppointmentCommand} when there are no matching keywords.
     */
    @Test
    public void execute_noMatchingKeyword_noAppointmentFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        PatientContainsKeywordsPredicate predicate = preparePredicate("noMatchingKeyword");
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    /**
     * Tests the execution of {@code FindAppointmentCommand} when there are multiple matching keywords.
     */
    @Test
    public void execute_multipleKeywords_multiplePatientsFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 2);
        PatientContainsKeywordsPredicate predicate = preparePredicate("alice hoon");
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    /**
     * Tests the execution of {@code FindAppointmentCommand} when the keyword is empty.
     */
    @Test
    public void execute_emptyKeyword_noAppointmentFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        PatientContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    /**
     * Parses {@code userInput} into a {@code PatientContainsKeywordsPredicate}.
     */
    private PatientContainsKeywordsPredicate preparePredicate(String userInput) {
        return new PatientContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
