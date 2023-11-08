package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_DATE;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteLeaveCommand;

public class DeleteLeaveCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DeleteLeaveCommand.MESSAGE_USAGE);
    private final DeleteLeaveCommandParser parser = new DeleteLeaveCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + " /on 12/12/2024", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, "0" + " /on 12/12/2024", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);

        // empty prefix being parsed as preamble
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // empty space prefix being parsed as preamble
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);

        // invalid index being parsed as preamble
        assertParseFailure(parser, "-1", MESSAGE_INVALID_FORMAT);

        // invalid index being parsed as preamble
        assertParseFailure(parser, "-3", MESSAGE_INVALID_FORMAT);


    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + " 01/01/2024", MESSAGE_INVALID_FORMAT); // invalid format
        assertParseFailure(parser, "1" + " /on /from", MESSAGE_INVALID_FORMAT); // invalid format
        assertParseFailure(parser, "1" + " /on /to", MESSAGE_INVALID_FORMAT); // invalid format
        assertParseFailure(parser, "1" + " /on", MESSAGE_INVALID_DATE); // invalid date
        assertParseFailure(parser, "1" + " /on 1/01/2024", MESSAGE_INVALID_DATE); // invalid
        // date
        assertParseFailure(parser, "1" + " /on 2024/12/12", MESSAGE_INVALID_DATE); // invalid
        // date
    }

    @Test
    public void parse_allFieldsSpecifiedForDeletingSingleLeave_success() {
        LocalDate currentDate = LocalDate.now();
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " /on " + ParserUtil.dateToString(currentDate.plusDays(1));

        DeleteLeaveCommand deleteLeaveCommand = new DeleteLeaveCommand(targetIndex, currentDate.plusDays(1));

        assertParseSuccess(parser, userInput, deleteLeaveCommand);
    }

    @Test
    public void parse_allFieldsSpecifiedForDeletingMultipleLeave_success() {
        LocalDate currentDate = LocalDate.now();
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " /from "
                + ParserUtil.dateToString(currentDate.plusDays(1))
                + " /to "
                + ParserUtil.dateToString(currentDate.plusDays(2));

        DeleteLeaveCommand deleteLeaveCommand = new DeleteLeaveCommand(targetIndex, currentDate.plusDays(1),
                currentDate.plusDays(2));

        assertParseSuccess(parser, userInput, deleteLeaveCommand);
    }

    @Test
    public void parse_invalidStartDateFieldsSpecifiedForDeletingMultipleLeave_failure() {
        LocalDate currentDate = LocalDate.now();
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput1 = targetIndex.getOneBased() + " /from "
                + " /to "
                + ParserUtil.dateToString(currentDate.plusDays(1));
        String userInput2 = targetIndex.getOneBased() + " /from 32/02/2023"
                + " /to "
                + ParserUtil.dateToString(currentDate.plusDays(1));

        assertParseFailure(parser, userInput1, MESSAGE_INVALID_DATE);
        assertParseFailure(parser, userInput2, MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_invalidEndDateFieldsSpecifiedForDeletingMultipleLeave_failure() {
        LocalDate currentDate = LocalDate.now();
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput1 = targetIndex.getOneBased() + " /from "
                + ParserUtil.dateToString(currentDate.plusDays(1))
                + " /to ";
        String userInput2 = targetIndex.getOneBased() + " /from "
                + ParserUtil.dateToString(currentDate.plusDays(1))
                + " /to 32/02/2023";

        assertParseFailure(parser, userInput1, MESSAGE_INVALID_DATE);
        assertParseFailure(parser, userInput2, MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_invalidStartDateFieldsSpecifiedForDeletingSingleLeave_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput1 = targetIndex.getOneBased() + " /on";
        String userInput2 = targetIndex.getOneBased() + " /on 32/02/2023";
        String userInput3 = targetIndex.getOneBased() + " /on 29/02/2023";

        assertParseFailure(parser, userInput1, MESSAGE_INVALID_DATE);
        assertParseFailure(parser, userInput2, MESSAGE_INVALID_DATE);
        assertParseFailure(parser, userInput3, MESSAGE_INVALID_DATE);
    }


}
