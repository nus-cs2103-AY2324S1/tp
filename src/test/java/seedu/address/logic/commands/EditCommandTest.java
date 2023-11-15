package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODTYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONDITION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CARDIOLOGIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LOW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_patientAllFieldsSpecifiedUnfilteredList_success() {
        Ic nricOfFirstPatient = model.getFilteredPatientList().get(0).getIc();
        Person editedPatient = new PatientBuilder(nricOfFirstPatient).build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(nricOfFirstPatient, editedPatient).build();
        EditCommand editCommand = new EditCommand(nricOfFirstPatient, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPatient));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPatientList().get(0), editedPatient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_doctorAllFieldsSpecifiedUnfilteredList_success() {
        Ic nricOfFirstDoctor = model.getFilteredDoctorList().get(0).getIc();
        Doctor editedDoctor = new DoctorBuilder(nricOfFirstDoctor).build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(nricOfFirstDoctor, editedDoctor).build();
        EditCommand editCommand = new EditCommand(nricOfFirstDoctor, descriptor);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedDoctor));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredDoctorList().get(0), editedDoctor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPatientList().size());
        Patient lastPatient = model.getFilteredPatientList().get(indexLastPerson.getZeroBased());

        PatientBuilder patientInList = new PatientBuilder(lastPatient);
        Patient editedPatient = patientInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_LOW).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_LOW).build();
        Ic nricLastPerson = lastPatient.getIc();
        EditCommand editCommand = new EditCommand(nricLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPatient));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPatient, editedPatient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        Patient firstPatient = model.getFilteredPatientList().get(0);
        Ic nricFirstPerson = firstPatient.getIc();
        EditCommand editCommand = new EditCommand(nricFirstPerson, new EditPersonDescriptor());
        Person editedPerson = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Ic nricOfFirstPerson = model.getFilteredPatientList().get(0).getIc();

        Patient personInFilteredList = model.getFilteredPatientList().stream().filter(p ->
                p.getIc().equals(nricOfFirstPerson)).findFirst().orElse(null);
        Patient editedPerson = new PatientBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(nricOfFirstPerson,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personInFilteredList, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Ic nricOfFirstPerson = model.getFilteredPatientList().get(0).getIc();
        Person firstPerson = model.getFilteredPatientList().stream().filter(p ->
                p.getIc().equals(nricOfFirstPerson)).findFirst().orElse(null);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(nricOfFirstPerson, firstPerson).build();
        Ic nricOfSecondPerson = model.getFilteredPatientList().get(1).getIc();
        EditCommand editCommand = new EditCommand(nricOfSecondPerson, descriptor);
        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_IC_CHANGED);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Patient patientInList = model.getAddressBook().getPatientList().get(INDEX_SECOND_PERSON.getZeroBased());
        Ic nricOfFirstPerson = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased()).getIc();
        EditCommand editCommand = new EditCommand(nricOfFirstPerson,
                new EditPersonDescriptorBuilder(patientInList.getIc(), patientInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_IC_CHANGED);
    }

    @Test
    public void execute_invalidPersonNricUnfilteredList_failure() {
        Ic invalidNric = new Ic("S9999999A"); // doesn't exist
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(invalidNric, descriptor);

        assertCommandFailure(editCommand, model, "This person hasn't been saved");
    }

    @Test
    public void editDoctor_editBloodType_throwsException() {
        Ic nricOfFirstDoctor = model.getFilteredDoctorList().get(0).getIc();
        Doctor editedDoctor = new DoctorBuilder(nricOfFirstDoctor).build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(nricOfFirstDoctor,
                editedDoctor).withBloodType(VALID_BLOODTYPE_AMY).build();
        EditCommand editCommand = new EditCommand(nricOfFirstDoctor, descriptor);
        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_EDIT_WRONG_FIELDS);
    }

    @Test
    public void editDoctor_editCondition_throwsException() {
        Ic nricOfFirstDoctor = model.getFilteredDoctorList().get(0).getIc();
        Doctor editedDoctor = new DoctorBuilder(nricOfFirstDoctor).build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(nricOfFirstDoctor,
                editedDoctor).withCondition(VALID_CONDITION_AMY).build();
        EditCommand editCommand = new EditCommand(nricOfFirstDoctor, descriptor);
        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_EDIT_WRONG_FIELDS);
    }

    @Test
    public void editDoctor_invalidTags_throwsException() {
        Ic nricOfFirstDoctor = model.getFilteredDoctorList().get(0).getIc();
        Doctor editedDoctor = new DoctorBuilder(nricOfFirstDoctor).build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(nricOfFirstDoctor,
                editedDoctor).withTags(VALID_TAG_LOW).build();
        EditCommand editCommand = new EditCommand(nricOfFirstDoctor, descriptor);
        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_INVALID_DOCTOR_TAGS);
    }
    @Test
    public void editPatient_invalidTags_throwsException() {
        Ic nricOfFirstPatient = model.getFilteredPatientList().get(0).getIc();
        Doctor editedDoctor = new DoctorBuilder(nricOfFirstPatient).build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(nricOfFirstPatient,
                editedDoctor).withTags(VALID_TAG_CARDIOLOGIST).build();
        EditCommand editCommand = new EditCommand(nricOfFirstPatient, descriptor);
        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_INVALID_PATIENT_TAGS);
    }
    @Test
    public void equals() {
        final Ic nricOfFirstPerson = model.getFilteredPatientList().get(0).getIc();
        final EditCommand standardCommand = new EditCommand(nricOfFirstPerson, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(nricOfFirstPerson, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        final Ic nricOfSecondPerson = model.getFilteredPatientList().get(1).getIc();
        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(nricOfSecondPerson, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(nricOfFirstPerson, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Ic nric = model.getFilteredPatientList().get(0).getIc();
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        EditCommand editCommand = new EditCommand(nric, editPersonDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{nric=" + nric + ", editPersonDescriptor="
                + editPersonDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
