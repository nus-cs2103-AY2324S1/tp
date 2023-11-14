package seedu.address.logic.parser;


import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAVELIST_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditLeaveCommand;
import seedu.address.model.employee.Id;

public class EditLeaveCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLeaveCommand.MESSAGE_USAGE);
    private EditLeaveCommandParser parser = new EditLeaveCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no employee id specified
        String userInput = " " + PREFIX_OLD + "2023-10-10 " + PREFIX_NEW + "2023-10-11";
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);

        // no old date specified
        userInput = " " + PREFIX_ID + VALID_ID_BOB + " " + PREFIX_NEW + "2023-10-11";
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);

        // no new date specified
        userInput = " " + PREFIX_ID + VALID_ID_BOB + " " + PREFIX_OLD + "2023-10-10";
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid employee id
        String userInput = INVALID_ID_DESC + " " + PREFIX_OLD + "2023-10-10 " + PREFIX_NEW + "2023-10-11";
        assertParseFailure(parser, userInput, Id.MESSAGE_CONSTRAINTS);

        // invalid old date
        userInput = " " + PREFIX_ID + VALID_ID_BOB + " " + PREFIX_OLD + "2023-13-11 " + PREFIX_NEW + "2023-10-11";
        assertParseFailure(parser, userInput, Messages.MESSAGE_INVALID_DATE);

        // invalid new date
        userInput = " " + PREFIX_ID + VALID_ID_BOB + " " + PREFIX_OLD + "2023-10-10 " + PREFIX_NEW + "11-10-2023";
        assertParseFailure(parser, userInput, Messages.MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_emptyValue_failure() {
        // empty employee id
        String userInput = " " + PREFIX_ID + " " + PREFIX_OLD + "2023-10-10 " + PREFIX_NEW + "2023-10-11";
        assertParseFailure(parser, userInput, Id.MESSAGE_CONSTRAINTS);

        // empty old date
        userInput = " " + PREFIX_ID + VALID_ID_BOB + " " + PREFIX_OLD + " " + PREFIX_NEW + "2023-10-11";
        assertParseFailure(parser, userInput, Messages.MESSAGE_MISSING_DATE);

        // empty new date
        userInput = " " + PREFIX_ID + VALID_ID_BOB + " " + PREFIX_OLD + "2023-10-10 " + PREFIX_NEW + " ";
        assertParseFailure(parser, userInput, Messages.MESSAGE_MISSING_DATE);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = " " + PREFIX_ID + VALID_ID_BOB + " " + PREFIX_OLD
                + VALID_LEAVELIST_BOB.get(0).toString() + " " + PREFIX_NEW + "2023-11-11";
        EditLeaveCommand expectedCommand = new EditLeaveCommand(new Id(VALID_ID_BOB),
                VALID_LEAVELIST_BOB.get(0).leaveDate, LocalDate.of(2023, 11, 11));
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
