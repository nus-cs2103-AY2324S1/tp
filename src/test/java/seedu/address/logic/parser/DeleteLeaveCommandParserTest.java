package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteLeaveCommand;
import seedu.address.model.employee.Id;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class DeleteLeaveCommandParserTest {

    private DeleteLeaveCommandParser parser = new DeleteLeaveCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLeaveCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_datesWrongOrder_throwsParseException() {
        String userInput = " " + PREFIX_ID + "EID1234-5678 " + PREFIX_FROM + "2023-12-28 " + PREFIX_TO + "2023-12-26";
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLeaveCommand.MESSAGE_INVALID_DATE_ORDER));
    }

    @Test
    public void parse_validArgs_returnsDeleteLeaveCommand(){

        String userInput = " " + PREFIX_ID + "EID1234-5678 " + PREFIX_FROM + "2023-12-26 " + PREFIX_TO + "2023-12-28";

        Id id = new Id("EID1234-5678");
        LocalDate startDate = LocalDate.parse("2023-12-26", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDate = LocalDate.parse("2023-12-28", DateTimeFormatter.ISO_LOCAL_DATE);

        DeleteLeaveCommand expectedDeleteLeaveCommand = new DeleteLeaveCommand(id, startDate, endDate);

        assertParseSuccess(parser, userInput, expectedDeleteLeaveCommand);
    }
}