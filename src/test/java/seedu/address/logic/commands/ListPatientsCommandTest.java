package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPatientWithId;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.patients.Patient;
import seedu.address.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListPatientsCommand.
 */
public class ListPatientsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // Creating sample patients
        Patient alice = new PatientBuilder().withName("Alice").build();
        Patient bob = new PatientBuilder().withName("Bob").build();

        // Adding patients to the model
        model.addPatient(alice);
        model.addPatient(bob);
        expectedModel.addPatient(alice);
        expectedModel.addPatient(bob);
    }

    @Test
    public void execute_patientListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListPatientCommand(), model, ListPatientCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_patientListIsFiltered_showsEverything() {
        showPatientWithId(model, 1);
        assertCommandSuccess(new ListPatientCommand(), model, ListPatientCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
