package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEmployees.ALICE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddLeaveCommand;
import seedu.address.model.employee.Id;

public class AddLeaveCommandParserTest {

    private AddLeaveCommandParser parser = new AddLeaveCommandParser();

    @Test
    public void parse_validArgs_returnsAddLeaveCommand() {
        String userInput = " " + PREFIX_ID + ALICE.getId() + " " + PREFIX_FROM + "2023-12-26 "
                + PREFIX_TO + "2023-12-28";

        assertParseSuccess(parser, userInput,
                new AddLeaveCommand(ALICE.getId(), LocalDate.parse("2023-12-26", DateTimeFormatter.ISO_LOCAL_DATE),
                LocalDate.parse("2023-12-28", DateTimeFormatter.ISO_LOCAL_DATE)));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String invalidIdInput = " " + PREFIX_ID + "EID0732-11a0" + " " + PREFIX_FROM + "2023-12-26 "
                + PREFIX_TO + "2023-12-28";
        String invalidDateInput = " " + PREFIX_ID + ALICE.getId() + " " + PREFIX_FROM + "2023-02-26 "
                + PREFIX_TO + "2023-02-29";

        assertParseFailure(parser, invalidIdInput, Id.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, invalidDateInput, Messages.MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_invalidFormat_throwsParseException() {
        String datesInput = " " + PREFIX_FROM + "2023-12-26 " + PREFIX_TO + "2023-12-28";

        // missing id
        assertParseFailure(parser, datesInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " " + PREFIX_ID + " " + datesInput, Id.MESSAGE_CONSTRAINTS);

        String idInput = " " + PREFIX_ID + ALICE.getId();
        String startDateInput = " " + PREFIX_FROM + "2023-12-26";
        String endDateInput = " " + PREFIX_TO + "2023-12-28";

        // missing start date
        assertParseFailure(parser, idInput + endDateInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
        assertParseFailure(parser, idInput + " " + PREFIX_FROM + endDateInput,
                Messages.MESSAGE_MISSING_DATE);

        // missing end date
        assertParseFailure(parser, idInput + startDateInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
        assertParseFailure(parser, idInput + startDateInput + " " + PREFIX_TO,
                Messages.MESSAGE_MISSING_DATE);
    }
}
