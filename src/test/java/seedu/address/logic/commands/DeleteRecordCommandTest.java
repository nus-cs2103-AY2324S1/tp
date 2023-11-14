package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.index.Index.fromOneBased;
import static seedu.address.commons.core.index.Index.fromZeroBased;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRecords.FEVER_AND_COLD0;

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

public class DeleteRecordCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndices_success() {
        Person personToDeleteRecord = model.getFilteredPersonList().get(3);
        UniqueRecordList records = new UniqueRecordList();
        records.setRecords(personToDeleteRecord.getRecords());
        records.remove(FEVER_AND_COLD0);

        Person personWithDeletedRecord = new PersonBuilder(personToDeleteRecord).withRecords(records).build();
        DeleteRecordCommand deleteRecordCommand = new DeleteRecordCommand(fromZeroBased(3), fromZeroBased(2));

        String expectedMessage = String.format(DeleteRecordCommand.MESSAGE_DELETE_RECORD_SUCCESS,
                Messages.format(FEVER_AND_COLD0, personWithDeletedRecord));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(3), personWithDeletedRecord);
        expectedModel.updateRecordList(personWithDeletedRecord);
        assertCommandSuccess(deleteRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPatientIndex_throwsCommandException() {
        Index outOfBoundPatientIndex = fromOneBased(model.getFilteredPersonList().size() + 1);
        Index validRecordIndex = fromOneBased(3);
        DeleteRecordCommand deleteRecordCommand = new DeleteRecordCommand(outOfBoundPatientIndex, validRecordIndex);

        assertCommandFailure(deleteRecordCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidRecordIndex_throwsCommandException() {
        Index outOfBoundPatientIndex = fromOneBased(4);
        Index validRecordIndex = fromOneBased(model.getFilteredPersonList().get(3).getRecords().size() + 1);
        DeleteRecordCommand deleteRecordCommand = new DeleteRecordCommand(outOfBoundPatientIndex, validRecordIndex);

        assertCommandFailure(deleteRecordCommand, model, Messages.MESSAGE_INVALID_RECORD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteRecordCommand deleteRecordCommand1 = new DeleteRecordCommand(fromOneBased(4), fromOneBased(3));
        DeleteRecordCommand deleteRecordCommand2 = new DeleteRecordCommand(fromOneBased(1), fromOneBased(1));
        DeleteRecordCommand deleteRecordCommand1Copy = new DeleteRecordCommand(fromOneBased(4), fromOneBased(3));

        assertTrue(deleteRecordCommand1.equals(deleteRecordCommand1));
        assertTrue(deleteRecordCommand1.equals(deleteRecordCommand1Copy));
        assertFalse(deleteRecordCommand1.equals(deleteRecordCommand2));
        assertFalse(deleteRecordCommand1.equals(1));
    }

    @Test
    public void toStringMethod() {
        Index patientIndex = fromOneBased(4);
        Index recordIndex = fromOneBased(3);
        DeleteRecordCommand deleteRecordCommand = new DeleteRecordCommand(patientIndex, recordIndex);

        String expected = DeleteRecordCommand.class.getCanonicalName()
                + "{targetPatientIndex=" + patientIndex + ", "
                + "targetRecordIndex=" + recordIndex + "}";

        assertEquals(expected, deleteRecordCommand.toString());
    }

}
