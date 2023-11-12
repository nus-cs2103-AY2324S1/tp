package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CompleteCommand.MESSAGE_INVALID_DATE;
import static seedu.address.logic.commands.CompleteCommand.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CompleteByDate;
import seedu.address.logic.commands.CompleteByIndex;
import seedu.address.logic.commands.CompleteCommand;

class CompleteCommandParserTest {
    public static final String DATE_DESC = " " + PREFIX_APPOINTMENT_DATE;
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompleteCommand.MESSAGE_USAGE);
    private CompleteCommandParser parser = new CompleteCommandParser();

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // multiple index
        assertParseFailure(parser, "1 1" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);

        //max int
        assertParseFailure(parser, "2147483648", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidDate_failure() {
        //Invalid date format
        assertParseFailure(parser, DATE_DESC + "01 May 2023", MESSAGE_INVALID_DATE_FORMAT);
        assertParseFailure(parser, DATE_DESC + "01-05", MESSAGE_INVALID_DATE_FORMAT);
        assertParseFailure(parser, DATE_DESC + "05-2023", MESSAGE_INVALID_DATE_FORMAT);
        assertParseFailure(parser, DATE_DESC + "01-05-2023 20:00", MESSAGE_INVALID_DATE_FORMAT);

        //invalid date
        assertParseFailure(parser, DATE_DESC + "01-13-2023", MESSAGE_INVALID_DATE);
        assertParseFailure(parser, DATE_DESC + "29-02-2023", MESSAGE_INVALID_DATE);
        assertParseFailure(parser, DATE_DESC + "31-04-2023", MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_invalidArgs_failure() {
        //no index or date specified
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);

        //both index and date specified
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + DATE_DESC + "01-05-2023",
                MESSAGE_INVALID_FORMAT);
    }
    @Test
    public void parse_duplicateFields_failure() {
        String userInput = DATE_DESC + "01-05-2023" + DATE_DESC + "02-10-2023";
        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPOINTMENT_DATE));
    }

    @Test
    public void parse_indexSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + "";

        CompleteByIndex expectedCommand = new CompleteByIndex(targetIndex);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_dateSpecified_success() {
        String userInput = DATE_DESC + "01-05-2023";
        CompleteByDate expectedCommand1 = new CompleteByDate(LocalDate.of(2023, 5, 1));

        assertParseSuccess(parser, userInput, expectedCommand1);

        //leap year
        String leapYear = DATE_DESC + "29-02-2024";
        CompleteByDate expectedCommand2 = new CompleteByDate(LocalDate.of(2024, 2, 29));

        assertParseSuccess(parser, leapYear, expectedCommand2);
    }

    @Test
    public void isValidArguments() {
        String nonEmptyIndex = "1";
        Optional<String> nonEmptyDate = Optional.of("01-01-2022");
        String emptyIndex = "";
        Optional<String> emptyDate = Optional.empty();

        assertFalse(CompleteCommandParser.isValidArguments(nonEmptyIndex, nonEmptyDate));
        assertFalse(CompleteCommandParser.isValidArguments(emptyIndex, emptyDate));

        assertTrue(CompleteCommandParser.isValidArguments(nonEmptyIndex, emptyDate));
        assertTrue(CompleteCommandParser.isValidArguments(emptyIndex, nonEmptyDate));
    }
}
