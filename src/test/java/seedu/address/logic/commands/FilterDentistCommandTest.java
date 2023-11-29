package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.testutil.DentistBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code FilterDentistCommand}.
 */
public class FilterDentistCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validAttributeAndKeywords_success() {
        String attribute = "phone";
        String keywords = "98765432";

        Dentist dentist = new DentistBuilder()
            .withName("John Doe")
            .withPhone("98765432")
            .withEmail("johndoe@gmail.com")
            .withAddress("456, Orchard Road, #12-345")
            .withSpecialization("Orthodontics")
            .withYoe("5")
            .withTags("friendly", "experienced")
            .build();

        model.addDentist(dentist);

        FilterDentistCommand command = new FilterDentistCommand(attribute, keywords);
        CommandResult commandResult = command.execute(model);
        assertTrue(commandResult.getFeedbackToUser().contains("Filtered dentists by phone with keywords: 98765432."));
    }

    @Test
    public void execute_noMatchingDentists_emptyResult() {
        String attribute = "specialization";
        String keywords = "dummytest";
        FilterDentistCommand command = new FilterDentistCommand(attribute, keywords);
        CommandResult commandResult = command.execute(model);
        assertTrue(commandResult.getFeedbackToUser().contains("No dentists found with the specialization: dummytest."));
    }

    @Test
    public void execute_emptyAttributeAndKeywords_emptyResult() {
        String attribute = "";
        String keywords = "";

        FilterDentistCommand command = new FilterDentistCommand(attribute, keywords);
        CommandResult commandResult = command.execute(model);
        System.out.println(commandResult);

        assertTrue(commandResult.getFeedbackToUser().contains("No dentists found with the : ."));
    }

    @Test
    public void equals() {
        String attribute1 = "gender";
        String keywords1 = "Male";
        String attribute2 = "gender";
        String keywords2 = "Female";

        FilterDentistCommand command1 = new FilterDentistCommand(attribute1, keywords1);
        FilterDentistCommand command2 = new FilterDentistCommand(attribute2, keywords2);

        assertEquals(command1, command1);
        assertNotEquals(command1, command2);
    }
}
