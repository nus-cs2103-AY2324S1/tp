package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.treatment.Treatment;
import seedu.address.testutil.TreatmentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTreatmentCommand}.
 */
public class DeleteTreatmentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_nullModel_throwsNullPointerException() {
        DeleteTreatmentCommand deleteTreatmentCommand = new DeleteTreatmentCommand("");
        assertThrows(NullPointerException.class, () -> deleteTreatmentCommand.execute(null));
    }

    @Test
    void execute_validName_success() {
        Treatment treatment = new TreatmentBuilder()
                .withName("Root Canal")
                .withCost("1080")
                .withTime("03:00")
                .build();

        model.addTreatment(treatment);

        DeleteTreatmentCommand deleteTreatmentCommand =
                new DeleteTreatmentCommand(treatment.getName().getTreatmentName());

        assertDoesNotThrow(() -> deleteTreatmentCommand.execute(model));
    }

    @Test
    void execute_invalidName_throwsCommandException() {
        Treatment treatment = new TreatmentBuilder()
                .withName("Root Canal")
                .withCost("1080")
                .withTime("03:00")
                .build();

        model.addTreatment(treatment);

        DeleteTreatmentCommand deleteTreatmentCommand = new DeleteTreatmentCommand("");

        assertThrows(CommandException.class, () -> deleteTreatmentCommand.execute(model));
    }

    @Test
    void execute_nullTreatmentName_throwsCommandException() {
        DeleteTreatmentCommand deleteTreatmentCommand = new DeleteTreatmentCommand(null);
        assertThrows(CommandException.class, () -> deleteTreatmentCommand.execute(model));
    }

    @Test
    void execute_multipleDeletions_success() throws CommandException {
        Treatment treatmentOne = new TreatmentBuilder()
                .withName("Root Canal")
                .withCost("1080")
                .withTime("03:00")
                .build();

        Treatment treatmentTwo = new TreatmentBuilder()
                .withName("Cleaning")
                .withCost("250")
                .withTime("01:00")
                .build();

        model.addTreatment(treatmentOne);
        model.addTreatment(treatmentTwo);

        DeleteTreatmentCommand deleteFirstTreatmentCommand =
                new DeleteTreatmentCommand(treatmentOne.getName().getTreatmentName());
        deleteFirstTreatmentCommand.execute(model);

        assertNull(model.getTreatmentByName(treatmentOne.getName().getTreatmentName()));
        assertNotNull(model.getTreatmentByName(treatmentTwo.getName().getTreatmentName()));
    }

    @Test
    void equals() {
        String firstTreatmentName = "Cleaning";
        String secondTreatmentName = "Root Canal";

        DeleteTreatmentCommand deleteFirstTreatmentCommand = new DeleteTreatmentCommand(firstTreatmentName);
        DeleteTreatmentCommand deleteSecondTreatmentCommand = new DeleteTreatmentCommand(secondTreatmentName);

        // same object -> equals
        assertEquals(deleteFirstTreatmentCommand, deleteFirstTreatmentCommand);

        // same value -> equals
        DeleteTreatmentCommand deleteFirstTreatmentCommandCopy = new DeleteTreatmentCommand(firstTreatmentName);
        assertEquals(deleteFirstTreatmentCommand, deleteFirstTreatmentCommandCopy);

        // different values -> not equals
        assertNotEquals(deleteFirstTreatmentCommand, "cleaning");

        // null -> not equals
        assertNotEquals(deleteFirstTreatmentCommand, null);

        // different commands -> not equals
        assertNotEquals(deleteFirstTreatmentCommand, deleteSecondTreatmentCommand);
    }

}
