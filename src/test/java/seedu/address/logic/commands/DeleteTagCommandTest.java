package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIENDS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentNumber;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TypicalStudents;

public class DeleteTagCommandTest {

    private Model model = new ModelManager(new AddressBook(getTypicalAddressBook()), new UserPrefs());

    @Test
    public void execute_deleteTag_success() {
        Student taggedStudent = new StudentBuilder(TypicalStudents.BENSON)
            .withTags(VALID_TAG_FRIENDS).build();
        Set<Tag> tagToDelete = SampleDataUtil.getTagSet("owesMoney");
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(
            TypicalStudents.BENSON.getStudentNumber(),
            tagToDelete);

        String expectedMessage = String.format(TagCommand.MESSAGE_DELETE_TAG_SUCCESS,
            taggedStudent.getName()) + tagToDelete;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(1), taggedStudent);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
        assertEquals(taggedStudent.getTags(), model.getFilteredStudentList().get(1).getTags());
    }

    @Test
    public void execute_noStudentWithStudentNumber_failure() {
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(
            new StudentNumber(VALID_STUDENT_NUMBER_AMY),
            TypicalStudents.ALICE.getTags());

        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_STUDENT_DOES_NOT_EXIST);
    }

    @Test
    public void equals() {
        final DeleteTagCommand standardCommand = new DeleteTagCommand(
            TypicalStudents.ALICE.getStudentNumber(),
            TypicalStudents.ALICE.getTags());

        DeleteTagCommand commandWithSameValue = new DeleteTagCommand(
            TypicalStudents.ALICE.getStudentNumber(),
            SampleDataUtil.getTagSet(VALID_TAG_FRIENDS));

        assertTrue(standardCommand.equals(commandWithSameValue));

        assertTrue(standardCommand.equals(standardCommand));

        assertFalse(standardCommand.equals(null));

        assertFalse(standardCommand.equals(new ClearCommand()));
    }

    @Test
    public void toStringMethod() {
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(
            TypicalStudents.ALICE.getStudentNumber(),
            TypicalStudents.ALICE.getTags());
        String expected = DeleteTagCommand.class.getCanonicalName() + "{tags=" + TypicalStudents.ALICE.getTags() + "}";
        assertEquals(expected, deleteTagCommand.toString());
    }
}
