package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookWithAttendance;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AttendanceCommandTest {

    @Test
    public void execute_indexTooBig_errorMessage() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandFailure(new AttendanceCommand(Index.fromZeroBased(999999),
                new Tag("S1")), model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexAndTag_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Tag toAdd = new Tag("S1");

        Person person = model.getFilteredPersonList().get(0);
        Person editedPerson = new PersonBuilder(person).withTags("friends", "S1").build();

        AttendanceCommand attendanceCommand = new AttendanceCommand(INDEX_FIRST_PERSON, toAdd);
        String expectedMessage = String.format(AttendanceCommand.MESSAGE_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(attendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAttendance_errorMessage() {
        Model model = new ModelManager(getTypicalAddressBookWithAttendance(), new UserPrefs());

        assertCommandFailure(new AttendanceCommand(INDEX_FIRST_PERSON,
                new Tag("S1")), model, AttendanceCommand.MESSAGE_DUPLICATE_ATTENDANCE);
    }

    @Test
    public void equals() {
        Tag tag = new Tag("S1");
        AttendanceCommand a1 = new AttendanceCommand(INDEX_FIRST_PERSON, tag);
        AttendanceCommand a2 = new AttendanceCommand(INDEX_FIRST_PERSON, tag);

        assertTrue(a1.equals(a1));
        assertTrue(a1.equals(a2));
        assertFalse(a1.equals(1));
    }
}
