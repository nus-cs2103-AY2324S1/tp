package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIENDS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SMART;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.HashSet;
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


/**
 * Contains integration tests (interaction with the Model) and unit tests for TagCommand.
 */
public class TagCommandTest {

    private Model model = new ModelManager(new AddressBook(getTypicalAddressBook()), new UserPrefs());

    @Test
    public void execute_replaceAllTag_success() {
        Student studentToTag = new StudentBuilder(TypicalStudents.ALICE)
            .withTags(VALID_TAG_SMART, VALID_TAG_FRIENDS).build();
        Set<Tag> replacedTags = SampleDataUtil.getTagSet(VALID_TAG_SMART, VALID_TAG_FRIENDS);
        TagCommand tagCommand = new TagCommand(
            studentToTag.getStudentNumber(),
            replacedTags);

        String expectedMessage = String.format(TagCommand.MESSAGE_REPLACE_ALL_TAG_SUCCESS,
                studentToTag.getName()) + replacedTags;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), studentToTag);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteAllTag_success() {
        Student studentToRemoveTag = new StudentBuilder(TypicalStudents.CARL).build();
        Set<Tag> emptyTag = new HashSet<>();
        TagCommand tagCommand = new TagCommand(
            studentToRemoveTag.getStudentNumber(),
            emptyTag);

        String expectedMessage = String.format(TagCommand.MESSAGE_DELETE_ALL_TAG_SUCCESS,
                studentToRemoveTag.getName()) + emptyTag;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(2), studentToRemoveTag);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noStudentWithStudentNumber_failure() {
        TagCommand tagCommand = new TagCommand(
            new StudentNumber(VALID_STUDENT_NUMBER_AMY),
            TypicalStudents.ALICE.getTags());

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_STUDENT_DOES_NOT_EXIST);
    }

    @Test
    public void equals() {
        final TagCommand standardCommand = new TagCommand(
            TypicalStudents.ALICE.getStudentNumber(),
            TypicalStudents.ALICE.getTags());

        TagCommand commandWithSameValue = new TagCommand(
            TypicalStudents.ALICE.getStudentNumber(),
            SampleDataUtil.getTagSet(VALID_TAG_FRIENDS));

        assertTrue(standardCommand.equals(commandWithSameValue));

        assertTrue(standardCommand.equals(standardCommand));

        assertFalse(standardCommand.equals(null));

        assertFalse(standardCommand.equals(new ClearCommand()));
    }

    @Test
    public void toStringMethod() {
        TagCommand tagCommand = new TagCommand(
            TypicalStudents.ALICE.getStudentNumber(),
            TypicalStudents.ALICE.getTags());
        String expected = TagCommand.class.getCanonicalName() + "{tags=" + TypicalStudents.ALICE.getTags() + "}";
        assertEquals(expected, tagCommand.toString());
    }
}
