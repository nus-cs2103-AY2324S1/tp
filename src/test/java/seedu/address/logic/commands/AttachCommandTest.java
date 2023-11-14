package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.attachment.Attachment;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code AttachCommand}.
 */
public class AttachCommandTest {
    private static Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private static Person firstPerson = model.getFilteredPersonList().get(0);

    private static final String ATTACHMENT_SAMPLE_PATH = Paths
            .get("src", "test", "resources", "attachments", "sample.txt")
            .toString();
    private static final String ATTACHMENT_ANOTHER_SAMPLE_PATH = Paths
            .get("src", "test", "resources", "attachments", "another-sample.txt")
            .toString();

    private static final String ATTACHMENT_SAMPLE_EXPECTED_PATH = Paths
            .get("data", "attachments", firstPerson.getStudentNumber().value, "sample.txt")
            .toString();
    private static final String ATTACHMENT_ANOTHER_SAMPLE_EXPECTED_PATH = Paths
            .get("data", "attachments", firstPerson.getStudentNumber().value, "another-sample.txt")
            .toString();

    @Test
    public void equals() {
        List<Attachment> attachments = List.of(new Attachment(ATTACHMENT_SAMPLE_PATH));
        List<Attachment> otherAttachments = List.of(new Attachment(ATTACHMENT_ANOTHER_SAMPLE_PATH));

        AttachCommand findFirstCommand = new AttachCommand(Index.fromZeroBased(3), attachments);
        AttachCommand findSecondCommand = new AttachCommand(Index.fromZeroBased(4), attachments);
        AttachCommand findThirdCommand = new AttachCommand(Index.fromZeroBased(3), otherAttachments);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        AttachCommand findFirstCommandCopy = new AttachCommand(Index.fromZeroBased(3), attachments);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different index -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));

        // different attachments -> returns false
        assertFalse(findFirstCommand.equals(findThirdCommand));
    }

    @Test
    public void execute_manyAttachments_success() {
        List<Attachment> attachments = List.of(
                new Attachment(ATTACHMENT_SAMPLE_PATH),
                new Attachment(ATTACHMENT_ANOTHER_SAMPLE_PATH));

        Person person = model.getFilteredPersonList().get(0);

        String expectedMessage = String.format(AttachCommand.MESSAGE_ATTACH_SUCCESS, 2,
                person.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(person,
                new PersonBuilder(person)
                        .withAttachments(ATTACHMENT_SAMPLE_EXPECTED_PATH, ATTACHMENT_ANOTHER_SAMPLE_EXPECTED_PATH)
                        .build());

        AttachCommand command = new AttachCommand(Index.fromZeroBased(0), attachments);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
