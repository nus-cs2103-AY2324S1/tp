package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_FIRST_REC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONDITION_DIARRHEA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONDITION_HEAT_STROKE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_SLEEP_STUDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_THYROID_CHECK;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RECORD;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditRecordCommand.EditRecordDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.record.Record;
import seedu.address.model.record.UniqueRecordList;
import seedu.address.testutil.EditRecordDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.RecordBuilder;

public class EditRecordCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UniqueRecordList oldList = personToEdit.getRecords();
        Record recordToEdit = oldList.asUnmodifiableObservableList().get(INDEX_FIRST_RECORD.getZeroBased());
        Record editedRecord = new RecordBuilder(recordToEdit)
                .withDateTime(VALID_DATETIME_THYROID_CHECK)
                .withConditions(VALID_CONDITION_DIARRHEA).withPersonIndex(0).build();
        UniqueRecordList newList = new UniqueRecordList();
        newList.setRecords(oldList);
        newList.setRecord(recordToEdit, editedRecord);

        Person editedPerson = new PersonBuilder(personToEdit).withRecords(newList).build();
        EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder(recordToEdit)
                .withDateTime(VALID_DATETIME_THYROID_CHECK)
                .withConditions(VALID_CONDITION_DIARRHEA).withPatientIndex(0).build();
        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON, INDEX_FIRST_RECORD,
                descriptor);

        String expectedMessage = String.format(EditRecordCommand.MESSAGE_EDIT_RECORD_SUCCESS,
                Messages.format(editedRecord, editedPerson));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        expectedModel.updateRecordList(editedPerson);

        assertCommandSuccess(editRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UniqueRecordList oldList = firstPerson.getRecords();
        Record recordToEdit = oldList.asUnmodifiableObservableList().get(INDEX_FIRST_RECORD.getZeroBased());
        Record editedRecord = new RecordBuilder(recordToEdit)
                .withDateTime(VALID_DATETIME_SLEEP_STUDY).build();
        UniqueRecordList newList = new UniqueRecordList();
        newList.setRecords(oldList);
        newList.setRecord(recordToEdit, editedRecord);

        PersonBuilder personInList = new PersonBuilder(firstPerson);
        Person editedPerson = personInList.withRecords(newList).build();
        EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder()
                .withDateTime(VALID_DATETIME_SLEEP_STUDY).build();
        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON, INDEX_FIRST_RECORD,
                descriptor);

        String expectedMessage = String.format(EditRecordCommand.MESSAGE_EDIT_RECORD_SUCCESS, Messages.format(
                editedRecord, editedPerson));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        expectedModel.updateRecordList(editedPerson);

        assertCommandSuccess(editRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON, INDEX_FIRST_RECORD,
                new EditRecordCommand.EditRecordDescriptor());

        assertCommandFailure(editRecordCommand, model, EditRecordCommand.MESSAGE_DUPLICATE_RECORD);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UniqueRecordList oldList = personInFilteredList.getRecords();
        Record recordToEdit = oldList.asUnmodifiableObservableList().get(INDEX_FIRST_RECORD.getZeroBased());
        Record editedRecord = new RecordBuilder(recordToEdit).withConditions(VALID_CONDITION_HEAT_STROKE)
                .withDateTime(VALID_DATETIME_THYROID_CHECK)
                .withPersonIndex(0).build();
        UniqueRecordList newList = new UniqueRecordList();
        newList.setRecords(oldList);
        newList.setRecord(recordToEdit, editedRecord);

        Person editedPerson = new PersonBuilder(personInFilteredList).withRecords(newList).build();
        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON, INDEX_FIRST_RECORD,
                new EditRecordDescriptorBuilder().withConditions(VALID_CONDITION_HEAT_STROKE)
                        .withDateTime(VALID_DATETIME_THYROID_CHECK)
                        .withPatientIndex(0).build());
        String expectedMessage = String.format(EditRecordCommand.MESSAGE_EDIT_RECORD_SUCCESS,
                Messages.format(editedRecord, editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        expectedModel.updateRecordList(editedPerson);

        assertCommandSuccess(editRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateRecordUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UniqueRecordList uniqueRecordList = firstPerson.getRecords();
        Record firstRecord = uniqueRecordList.asUnmodifiableObservableList()
                .get(INDEX_FIRST_RECORD.getZeroBased());
        EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder(firstRecord)
                .build();
        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_RECORD, descriptor);
        assertCommandFailure(editRecordCommand, model, EditRecordCommand.MESSAGE_DUPLICATE_RECORD);
    }

    @Test
    public void execute_duplicateRecordFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInList = model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UniqueRecordList uniqueRecordList = personInList.getRecords();
        Record firstRecord = uniqueRecordList.asUnmodifiableObservableList()
                .get(INDEX_FIRST_RECORD.getZeroBased());
        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON, INDEX_FIRST_RECORD,
                new EditRecordDescriptorBuilder(firstRecord).build());

        assertCommandFailure(editRecordCommand, model, EditRecordCommand.MESSAGE_DUPLICATE_RECORD);
    }

    @Test
    public void execute_invalidRecordIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromZeroBased(model.getFilteredPersonList().get(
                INDEX_FIRST_PERSON.getZeroBased()).getRecords().asUnmodifiableObservableList().size());
        EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder()
                .withDateTime(VALID_DATETIME_THYROID_CHECK).build();
        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON, outOfBoundIndex,
                descriptor);

        assertCommandFailure(editRecordCommand, model, Messages.MESSAGE_INVALID_RECORD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder()
                .withDateTime(VALID_DATETIME_THYROID_CHECK).build();
        EditRecordCommand editRecordCommand = new EditRecordCommand(outOfBoundIndex, INDEX_FIRST_RECORD,
                descriptor);

        assertCommandFailure(editRecordCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void toStringTest() {
        EditRecordDescriptor editedFirstRecord = new EditRecordDescriptorBuilder(
                DESC_FIRST_REC)
                        .withDateTime(VALID_DATETIME_THYROID_CHECK).build();
        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_RECORD, editedFirstRecord);
        String expected = EditRecordCommand.class.getCanonicalName() + "{patientIndex=" + INDEX_FIRST_PERSON
                + ", "
                + "recordIndex=" + INDEX_FIRST_RECORD + ", "
                + "editRecordDescriptor=" + editedFirstRecord + "}";

        assertEquals(expected, editRecordCommand.toString());
    }

}
