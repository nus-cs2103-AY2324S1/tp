package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.IsBookmarked;
import seedu.address.model.person.Person;

public class UnbookmarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_personUnbookmarked_unbookmarkSuccessful() throws CommandException {
        Person unbookmarkedPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        CommandResult commandResult = new UnbookmarkCommand(INDEX_SECOND_PERSON).execute(model);

        String expectedMessage = String.format(UnbookmarkCommand.MESSAGE_UNBOOKMARK_APPLICANT_SUCCESS,
                INDEX_SECOND_PERSON.getOneBased());

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased()).getIsBookmarked(),
                new IsBookmarked(false));
    }

    @Test
    public void execute_indexOutOfRange_throwsCommandException() {
        Index outOfRangeIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UnbookmarkCommand unbookmarkCommand = new UnbookmarkCommand(outOfRangeIndex);
        assertCommandFailure(unbookmarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnbookmarkCommand unbookmarkFirstCommand = new UnbookmarkCommand(INDEX_FIRST_PERSON);
        UnbookmarkCommand unbookmarkSecondCommand = new UnbookmarkCommand(INDEX_SECOND_PERSON);

        assertTrue(unbookmarkFirstCommand.equals(unbookmarkFirstCommand));

        UnbookmarkCommand unbookmarkFirstCommandCopy = new UnbookmarkCommand(INDEX_FIRST_PERSON);
        assertTrue(unbookmarkFirstCommand.equals(unbookmarkFirstCommandCopy));

        assertFalse(unbookmarkFirstCommand.equals(1));

        assertFalse(unbookmarkFirstCommand.equals(null));

        assertFalse(unbookmarkFirstCommand.equals(unbookmarkSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        UnbookmarkCommand unbookmarkCommand = new UnbookmarkCommand(targetIndex);
        String expected = UnbookmarkCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, unbookmarkCommand.toString());
    }

}
