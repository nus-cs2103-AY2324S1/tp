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

public class BookmarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_personBookmarked_bookmarkSuccessful() throws CommandException {
        Person bookmarkedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        CommandResult commandResult = new BookmarkCommand(INDEX_FIRST_PERSON).execute(model);

        String expectedMessage = String.format(BookmarkCommand.MESSAGE_BOOKMARK_APPLICANT_SUCCESS,
                INDEX_FIRST_PERSON.getOneBased());

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()).getIsBookmarked(),
                new IsBookmarked(true));
    }

    @Test
    public void execute_indexOutOfRange_throwsCommandException() {
        Index outOfRangeIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        BookmarkCommand bookmarkCommand = new BookmarkCommand(outOfRangeIndex);
        assertCommandFailure(bookmarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        BookmarkCommand bookmarkFirstCommand = new BookmarkCommand(INDEX_FIRST_PERSON);
        BookmarkCommand bookmarkSecondCommand = new BookmarkCommand(INDEX_SECOND_PERSON);

        assertTrue(bookmarkFirstCommand.equals(bookmarkFirstCommand));

        BookmarkCommand bookmarkFirstCommandCopy = new BookmarkCommand(INDEX_FIRST_PERSON);
        assertTrue(bookmarkFirstCommand.equals(bookmarkFirstCommandCopy));

        assertFalse(bookmarkFirstCommand.equals(1));

        assertFalse(bookmarkFirstCommand.equals(null));

        assertFalse(bookmarkFirstCommand.equals(bookmarkSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        BookmarkCommand bookmarkCommand = new BookmarkCommand(targetIndex);
        String expected = BookmarkCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, bookmarkCommand.toString());
    }

}
