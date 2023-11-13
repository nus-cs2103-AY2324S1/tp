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

public class DeleteAttendanceCommandTest {

    @Test
    public void execute_indexTooBig_errorMessage() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandFailure(new DeleteAttendanceCommand(Index.fromZeroBased(999999),
                new Tag("S1")), model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_noTagFound_errorMessage() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Tag toDelete = new Tag("S1");


        DeleteAttendanceCommand deleteAttendanceCommand = new DeleteAttendanceCommand(INDEX_FIRST_PERSON, toDelete);
        assertCommandFailure(deleteAttendanceCommand, model, DeleteAttendanceCommand.MESSAGE_NO_LESSON_FOUND);
    }

    @Test
    public void execute_validIndexAndTag_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Tag toDelete = new Tag("S1");
        model.addAttendanceTag(new Tag("A0203220JS1"));

        Person person = model.getFilteredPersonList().get(0);
        Person editedPerson = new PersonBuilder(person).withTags("friends", "S1").build();

        model.setPerson(person, editedPerson);

        DeleteAttendanceCommand deleteAttendanceCommand = new DeleteAttendanceCommand(INDEX_FIRST_PERSON, toDelete);
        String expectedMessage = String.format(DeleteAttendanceCommand.MESSAGE_SUCCESS, Messages.format(person));

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(deleteAttendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_notAttendanceTag_errorMessage() {
        Model model = new ModelManager(getTypicalAddressBookWithAttendance(), new UserPrefs());

        assertCommandFailure(new DeleteAttendanceCommand(INDEX_FIRST_PERSON,
                new Tag("friends")), model, DeleteAttendanceCommand.MESSAGE_NOT_ATTENDANCE_TAG);
    }

    @Test
    public void equals() {
        Tag tag = new Tag("S1");
        DeleteAttendanceCommand a1 = new DeleteAttendanceCommand(INDEX_FIRST_PERSON, tag);
        DeleteAttendanceCommand a2 = new DeleteAttendanceCommand(INDEX_FIRST_PERSON, tag);

        assertTrue(a1.equals(a1));
        assertTrue(a1.equals(a2));
        assertFalse(a1.equals(1));
    }
}
