package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.PatientBuilder;

public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void execute_undoAdd_success() {
        Patient validPatient = new PatientBuilder().build();
        UndoCommand undoCommand = new UndoCommand();
        String expectedMessage = UndoCommand.MESSAGE_SUCCESS;
        Model expectedModel = model;
        model.addPerson(validPatient);
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_undoDelete_success() {
        UndoCommand undoCommand = new UndoCommand();
        String expectedMessage = UndoCommand.MESSAGE_SUCCESS;
        Person personToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        Model expectedModel = model;
        model.deletePerson(personToDelete);
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_undoEdit_success() {
        UndoCommand undoCommand = new UndoCommand();
        String expectedMessage = UndoCommand.MESSAGE_SUCCESS;
        Patient editedPatient = new PatientBuilder().build();
        Model expectedModel = model;
        Patient personToEdit = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.setPerson(personToEdit, editedPatient);
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void undo_nothingToUndo_failure() {
        UndoCommand undoCommand = new UndoCommand();
        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_EMPTY);
    }

    @Test
    public void execute_multipleUndos_success() {
        UndoCommand undoCommand = new UndoCommand();
        String expectedMessage = UndoCommand.MESSAGE_SUCCESS;
        Patient editedPatient = new PatientBuilder().build();
        Model expectedModelOne = model;

        /* edit command */
        Patient personToEdit = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.setPerson(personToEdit, editedPatient);

        /* delete command */
        Model expectedModelTwo = model;
        Patient personToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.deletePerson(personToDelete);

        /* add command */
        Model expectedModelThree = model;
        Doctor doctorToAdd = new DoctorBuilder().build();
        model.addPerson(doctorToAdd);

        /* undo 3 times */
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModelThree);
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModelTwo);
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModelOne);
    }

    @Test
    public void execute_exceedFiveUndos_failure() {
        UndoCommand undoCommand = new UndoCommand();
        String expectedMessage = UndoCommand.MESSAGE_SUCCESS;

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

        /* undo 6 times */
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModelFive);
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModelFour);
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModelThree);
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModelTwo);
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModelOne);
        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_EMPTY);
    }
}
