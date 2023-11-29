package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code FilterPatientCommand}.
 */
public class FilterPatientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_noMatchingPatients_emptyResult() {
        String attribute = "email";
        String keywords = "dummytest";
        FilterPatientCommand command = new FilterPatientCommand(attribute, keywords);
        CommandResult commandResult = command.execute(model);
        assertTrue(commandResult.getFeedbackToUser().contains("No patients found with the email: dummytest."));
    }

    @Test
    public void execute_emptyAttributeAndKeywords_emptyResult() {
        String attribute = "";
        String keywords = "";

        FilterPatientCommand command = new FilterPatientCommand(attribute, keywords);
        CommandResult commandResult = command.execute(model);
        System.out.println(commandResult);

        assertTrue(commandResult.getFeedbackToUser().contains("No patients found with the : ."));
    }

    @Test
    public void equals() {
        String attribute1 = "gender";
        String keywords1 = "Male";
        String attribute2 = "gender";
        String keywords2 = "Female";

        FilterPatientCommand command1 = new FilterPatientCommand(attribute1, keywords1);
        FilterPatientCommand command2 = new FilterPatientCommand(attribute2, keywords2);

        assertEquals(command1, command1);
        assertNotEquals(command1, command2);
    }
}
