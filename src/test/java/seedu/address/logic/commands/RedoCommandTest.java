package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.PatientBuilder;

public class RedoCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_redoAddPatient_success() throws CommandException {
        Patient validPatient = new PatientBuilder().build();
        RedoCommand redoCommand = new RedoCommand();
        String expectedMessage = RedoCommand.MESSAGE_SUCCESS;
        Model expectedModel = model;

        model.addPerson(validPatient);
        model.undo();
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_redoDeletePatient_success() throws CommandException {
        RedoCommand redoCommand = new RedoCommand();
        String expectedMessage = RedoCommand.MESSAGE_SUCCESS;
        Person personToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        Model expectedModel = model;

        model.deletePerson(personToDelete);
        model.undo();
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_redoEditPatient_success() throws CommandException {
        RedoCommand redoCommand = new RedoCommand();
        String expectedMessage = RedoCommand.MESSAGE_SUCCESS;
        Patient editedPatient = new PatientBuilder().build();
        Model expectedModel = model;
        Patient personToEdit = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());

        model.setPerson(personToEdit, editedPatient);
        model.undo();
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void redo_nothingToRedo_failure() {
        RedoCommand redoCommand = new RedoCommand();
        assertCommandFailure(redoCommand, model, RedoCommand.MESSAGE_EMPTY);
    }

    @Test
    public void execute_multipleRedos_success() throws CommandException {
        RedoCommand redoCommand = new RedoCommand();
        String expectedMessage = RedoCommand.MESSAGE_SUCCESS;
        Patient editedPatient = new PatientBuilder().build();

        /* edit command */
        Patient personToEdit = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.setPerson(personToEdit, editedPatient);
        Model expectedModelOne = model;

        /* delete command */
        Patient personToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.deletePerson(personToDelete);
        Model expectedModelTwo = model;

        /* add command */
        Doctor doctorToAdd = new DoctorBuilder().build();
        model.addPerson(doctorToAdd);
        Model expectedModelThree = model;

        /* undo 3 times */
        model.undo();
        model.undo();
        model.undo();

        /* redo 3 times */
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModelThree);
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModelTwo);
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModelOne);
    }

    @Test
    public void execute_exceedFiveRedos_failure() throws CommandException {
        RedoCommand redoCommand = new RedoCommand();
        String expectedMessage = RedoCommand.MESSAGE_SUCCESS;

        /* add and delete patients */
        Patient patientToAdd = new PatientBuilder().build();
        model.addPerson(patientToAdd);
        Model expectedModelOne = model;
        Patient patientToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.deletePerson(patientToDelete);
        Model expectedModelTwo = model;
        Patient secondPatientToDelete = model.getFilteredPatientList().get(INDEX_SECOND_PERSON.getZeroBased());
        model.deletePerson(secondPatientToDelete);
        Model expectedModelThree = model;

        /* add and delete doctors */
        Doctor doctorToAdd = new DoctorBuilder().build();
        model.addPerson(doctorToAdd);
        Model expectedModelFour = model;
        Doctor doctorToDelete = model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.deletePerson(doctorToDelete);
        Model expectedModelFive = model;
        Doctor secondDoctorToDelete = model.getFilteredDoctorList().get(INDEX_SECOND_PERSON.getZeroBased());
        model.deletePerson(secondDoctorToDelete);

        /* undo 5 times */
        for (int i = 0; i < 5; i++) {
            model.undo();
        }

        /* redo 6 times */
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModelFive);
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModelFour);
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModelThree);
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModelTwo);
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModelOne);
        assertCommandFailure(redoCommand, model, RedoCommand.MESSAGE_EMPTY);
    }
}
