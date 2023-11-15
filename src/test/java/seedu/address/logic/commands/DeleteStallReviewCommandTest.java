package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStalls.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.parser.DeleteStallReviewCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteStallReviewCommand}.
 */
public class DeleteStallReviewCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }


    @Test
    public void parse_invalidArgs_throwsParseException() {
        DeleteStallReviewCommandParser parser = new DeleteStallReviewCommandParser();
        assertThrows(ParseException.class, () -> parser.parse("delete-stall-review 1"));
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStallList().size() + 1);
        DeleteStallReviewCommand deleteStallReviewCommand = new DeleteStallReviewCommand(outOfBoundIndex);

        assertCommandFailure(deleteStallReviewCommand, model, Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
    }
}
