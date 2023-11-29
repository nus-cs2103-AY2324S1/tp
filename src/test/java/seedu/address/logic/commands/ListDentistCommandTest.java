package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showDentistWithId;
import static seedu.address.testutil.TypicalDentists.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.testutil.DentistBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListDentistCommand.
 */
public class ListDentistCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // Creating sample patients
        Dentist alice = new DentistBuilder().withName("Alice").build();
        Dentist bob = new DentistBuilder().withName("Bob").build();

        // Adding dentists to the model
        model.addDentist(alice);
        model.addDentist(bob);
        expectedModel.addDentist(alice);
        expectedModel.addDentist(bob);
    }

    @Test
    public void execute_patientListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListDentistCommand(), model, ListDentistCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_patientListIsFiltered_showsEverything() {
        showDentistWithId(model, 1);
        assertCommandSuccess(new ListDentistCommand(), model, ListDentistCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
