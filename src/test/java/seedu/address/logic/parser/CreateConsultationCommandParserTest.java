package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_OBJ;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_OBJ;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.CreateConsultCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.util.SampleDataUtil;

public class CreateConsultationCommandParserTest {
    private CreateConsultCommandParser parser = new CreateConsultCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_DATE_DESC + VALID_TIME_DESC + NAME_DESC_AMY,
                new CreateConsultCommand(VALID_DATE_OBJ, VALID_TIME_OBJ,
                        SampleDataUtil.getNamesSet(VALID_NAME_AMY)));
    }

    @Test
    public void parse_multipleNames_success() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                        + VALID_DATE_DESC + VALID_TIME_DESC + NAME_DESC_AMY + NAME_DESC_BOB,
                new CreateConsultCommand(VALID_DATE_OBJ, VALID_TIME_OBJ,
                        SampleDataUtil.getNamesSet(VALID_NAME_AMY, VALID_NAME_BOB)));
    }

    @Test
    public void parse_repeatedPrefix_failure() {
        String validExpectedConsultationString = VALID_DATE_DESC + VALID_TIME_DESC + NAME_DESC_AMY;

        // multiple dates
        assertParseFailure(parser, VALID_DATE_DESC + validExpectedConsultationString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE));

        // multiple time
        assertParseFailure(parser, VALID_TIME_DESC + validExpectedConsultationString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TIME));
    }

    @Test
    public void parse_invalidInput_failure() {
        // invalid date
        String invalidDateInput = INVALID_DATE_DESC + VALID_TIME_DESC + NAME_DESC_AMY;
        assertThrows(ParseException.class, () -> parser.parse(invalidDateInput));

        // invalid time
        String invalidTimeInput = VALID_DATE_DESC + INVALID_TIME_DESC + NAME_DESC_AMY;
        assertThrows(ParseException.class, () -> parser.parse(invalidTimeInput));


        // invalid date and time
        String invalidDateTimeInput = INVALID_DATE_DESC + INVALID_TIME_DESC + NAME_DESC_AMY;
        assertThrows(ParseException.class, () -> parser.parse(invalidDateTimeInput));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateConsultCommand.MESSAGE_USAGE);

        // missing time
        assertParseFailure(parser, VALID_DATE + VALID_NAME_AMY,
                expectedMessage);

        // missing date
        assertParseFailure(parser, VALID_TIME + VALID_NAME_AMY,
                expectedMessage);

        // missing name
        assertParseFailure(parser, VALID_DATE + VALID_TIME,
                expectedMessage);
    }
}
