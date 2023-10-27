package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TagBuilder;

public class DeleteTagCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_correctCommand_success() throws CommandException {
        int personId = 1;
        final Set<Tag> tagSet = new TagBuilder().inSet();

        assertCommandSuccessWithFeedback(() -> new DeleteTagCommand(personId, tagSet)
                .execute(model), DeleteTagCommand.MESSAGE_SUCCESS + new TagBuilder().build().toString());
    }

    @Test
    public void execute_personNotExist_fails() throws CommandException {
        int personId = 99999;
        final Set<Tag> tagSet = new TagBuilder().inSet();

        assertCommandFailWithFeedback(() -> new DeleteTagCommand(personId, tagSet)
                .execute(model), DeleteTagCommand.MESSAGE_PERSON_NOT_FOUND + personId);
    }

    private void assertCommandSuccessWithFeedback(ThrowingSupplier<CommandResult> function, String result) {
        try {
            assertEquals(function.get(), new CommandResult(result));
        } catch (Throwable e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }
    }

    private void assertCommandFailWithFeedback(ThrowingSupplier<CommandResult> function, String errResult) {
        try {
            function.get();
        } catch (Throwable e) {
            if (!(e instanceof CommandException)) {
                throw new AssertionError("Execution of command failed but not due to CommandException.");
            }
            assertEquals(e.getMessage(), errResult);
            return;
        }
        throw new AssertionError("Execution of command should fail.");
    }

    @Test
    public void equals() {
        final Set<Tag> tagSetA = new TagBuilder().withTag(VALID_TAG_HUSBAND).inSet();
        final Set<Tag> tagSetB = new TagBuilder().withTag(VALID_TAG_FRIEND).inSet();

        DeleteTagCommand commandA = new DeleteTagCommand(1, tagSetA);
        DeleteTagCommand commandB = new DeleteTagCommand(1, tagSetB);

        // same object -> returns true
        assertTrue(commandA.equals(commandA));

        // same values -> returns true
        DeleteTagCommand commandACopy = new DeleteTagCommand(1, tagSetA);
        assertTrue(commandA.equals(commandACopy));

        // different types -> returns false
        assertFalse(commandA.equals(1));

        // null -> returns false
        assertFalse(commandA.equals(null));

        // different person -> returns false
        assertFalse(commandA.equals(commandB));
    }
}
