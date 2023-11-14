package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRecords.ALLERGIC_REACTION2;
import static seedu.address.testutil.TypicalRecords.FEVER0;
import static seedu.address.testutil.TypicalRecords.FEVER_AND_COLD0;
import static seedu.address.testutil.TypicalRecords.FEVER_AND_COLD2;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.record.UniqueRecordList;
import seedu.address.testutil.PersonBuilder;

public class AddRecordCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToAddRecord = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        UniqueRecordList records = new UniqueRecordList();
        records.setRecords(personToAddRecord.getRecords());
        records.add(FEVER_AND_COLD2);

        Person personWithAddedRecord = new PersonBuilder(personToAddRecord).withRecords(records).build();
        AddRecordCommand addRecordCommand = new AddRecordCommand(INDEX_THIRD_PERSON, FEVER_AND_COLD2);

        String expectedMessage = String.format(AddRecordCommand.MESSAGE_SUCCESS,
                Messages.format(FEVER_AND_COLD2, personWithAddedRecord));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(2), personWithAddedRecord);
        expectedModel.updateRecordList(personWithAddedRecord);
        assertCommandSuccess(addRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddRecordCommand addRecordCommand = new AddRecordCommand(outOfBoundIndex, ALLERGIC_REACTION2);

        assertCommandFailure(addRecordCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToAddRecord = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UniqueRecordList records = new UniqueRecordList();
        records.setRecords(personToAddRecord.getRecords());
        records.add(FEVER_AND_COLD0);

        Person personWithAddedRecord = new PersonBuilder(personToAddRecord).withRecords(records).build();
        AddRecordCommand addRecordCommand = new AddRecordCommand(INDEX_FIRST_PERSON, FEVER_AND_COLD0);

        String expectedMessage = String.format(AddRecordCommand.MESSAGE_SUCCESS,
                Messages.format(FEVER_AND_COLD0, personWithAddedRecord));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        expectedModel.setPerson(model.getFilteredPersonList().get(0), personWithAddedRecord);
        expectedModel.updateRecordList(personWithAddedRecord);
        assertCommandSuccess(addRecordCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AddRecordCommand addRecordCommand = new AddRecordCommand(outOfBoundIndex, FEVER0);

        assertCommandFailure(addRecordCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicateRecord_throwsCommandException() {
        Index validIndex = INDEX_FIRST_PERSON;
        AddRecordCommand addrecordCommand = new AddRecordCommand(validIndex, FEVER0);
        assertCommandFailure(addrecordCommand, model, AddRecordCommand.MESSAGE_DUPLICATE_RECORDS);
    }

    @Test
    public void equals() {
        AddRecordCommand addRecordCommand1 = new AddRecordCommand(INDEX_FIRST_PERSON, FEVER0);
        AddRecordCommand addRecordCommand2 = new AddRecordCommand(INDEX_FIRST_PERSON, FEVER0);
        AddRecordCommand addRecordCommand3 = new AddRecordCommand(INDEX_THIRD_PERSON, ALLERGIC_REACTION2);

        assertTrue(addRecordCommand1.equals(addRecordCommand1));
        assertTrue(addRecordCommand1.equals(addRecordCommand2));
        assertFalse(addRecordCommand1.equals(1));
        assertFalse(addRecordCommand1.equals(null));
        assertFalse(addRecordCommand1.equals(addRecordCommand3));

    }

    @Test
    public void toStringMethod() {
        AddRecordCommand addRecordCommand = new AddRecordCommand(INDEX_FIRST_PERSON, FEVER0);
        String expected = AddRecordCommand.class.getCanonicalName() + "{index=" + INDEX_FIRST_PERSON + ", "
                + "record=" + FEVER0 + "}";

        assertEquals(expected, addRecordCommand.toString());

    }
}
