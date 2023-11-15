package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_STALL_INDEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STALL_RATING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STALL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STALL_INDEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STALL_RATING;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_STALL_INDEX;
import static seedu.address.model.review.Rating.MESSAGE_CONSTRAINTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STALL;
import static seedu.address.testutil.TypicalStallReviews.STALL_REVIEW_3;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddStallReviewCommand;

public class AddStallReviewCommandParserTest {

    private AddStallReviewCommandParser parser = new AddStallReviewCommandParser();

    @Test
    public void parse_validInput_returnsAddStallReviewCommand() {
        assertParseSuccess(parser, VALID_STALL_INDEX_DESC + VALID_STALL_RATING + VALID_STALL_DESC,
                new AddStallReviewCommand(STALL_REVIEW_3, INDEX_FIRST_STALL));
    }

    // Invalid stall index
    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, INVALID_STALL_INDEX_DESC + VALID_STALL_RATING + VALID_STALL_DESC,
                MESSAGE_INVALID_STALL_INDEX);
    }

    // Invalid stall rating
    @Test
    public void parse_invalidArgs_throwsParseException2() {
        assertParseFailure(parser, VALID_STALL_INDEX_DESC + INVALID_STALL_RATING + VALID_STALL_DESC,
                MESSAGE_CONSTRAINTS);
    }
}
