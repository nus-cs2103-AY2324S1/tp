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
import seedu.address.model.person.dentist.Dentist;
import seedu.address.testutil.DentistBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteDentistCommand}.
 */
public class DeleteDentistCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_nullModel_throwsNullPointerException() {
        DeleteDentistCommand deleteDentistCommand = new DeleteDentistCommand(1L);
        assertThrows(NullPointerException.class, () -> deleteDentistCommand.execute(null));
    }

    @Test
    void execute_validId_success() {
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

        DeleteDentistCommand deleteDentistCommand = new DeleteDentistCommand(dentist.getId());

        assertDoesNotThrow(() -> deleteDentistCommand.execute(model));
    }

    @Test
    void execute_invalidId_throwsCommandException() {
        Model model = new ModelManager();
        long invalidId = 99999L;
        DeleteDentistCommand deleteDentistCommand = new DeleteDentistCommand(invalidId);
        assertThrows(CommandException.class, () -> deleteDentistCommand.execute(model));
    }

    @Test
    void execute_negativeId_throwsCommandException() {
        DeleteDentistCommand deleteDentistCommand = new DeleteDentistCommand(-1L);
        assertThrows(CommandException.class, () -> deleteDentistCommand.execute(new ModelManager()));
    }

    @Test
    void execute_zeroId_throwsCommandException() {
        DeleteDentistCommand deleteDentistCommand = new DeleteDentistCommand(0L);
        assertThrows(CommandException.class, () -> deleteDentistCommand.execute(new ModelManager()));
    }

    @Test
    void execute_multipleDeletions_success() throws CommandException {
        Dentist dentistOne = new DentistBuilder()
            .withName("John Doe")
            .withPhone("98765432")
            .withEmail("johndoe@gmail.com")
            .withAddress("456, Orchard Road, #12-345")
            .withSpecialization("ORTHODONTICS")
            .withYoe("5")
            .withTags("friendly", "experienced")
            .build();

        Dentist dentistTwo = new DentistBuilder()
            .withName("Mike Tan")
            .withPhone("90776655")
            .withEmail("aloha@gmail.com")
            .withAddress("90 Sophia Road")
            .withSpecialization("ENDODONTICS")
            .withYoe("2")
            .withTags("patient", "gentle")
            .build();

        model.addDentist(dentistOne);
        model.addDentist(dentistTwo);

        DeleteDentistCommand deleteFirstDentist = new DeleteDentistCommand(dentistOne.getId());
        deleteFirstDentist.execute(model);

        assertNull(model.getDentistById(dentistOne.getId()));
        assertNotNull(model.getDentistById(dentistTwo.getId()));
    }

    @Test
    void equals() {
        long firstId = 1L;
        long secondId = 2L;
        DeleteDentistCommand deleteFirstCommand = new DeleteDentistCommand(firstId);
        DeleteDentistCommand deleteSecondCommand = new DeleteDentistCommand(secondId);

        assertEquals(deleteFirstCommand, deleteFirstCommand);

        DeleteDentistCommand deleteFirstCommandCopy = new DeleteDentistCommand(firstId);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        assertNotEquals(deleteFirstCommand, 1);

        assertNotEquals(deleteFirstCommand, null);

        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

}
