package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteRemarkCommand;
import seedu.address.model.employee.Id;
import seedu.address.model.remark.Remark;

public class DeleteRemarkCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteRemarkCommand.MESSAGE_USAGE);
    private DeleteRemarkCommandParser parser = new DeleteRemarkCommandParser();

    @Test
    void parse_emptyArg_throwsParserException() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "     ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_missingParts_failure() {
        // missing employee id
        String userInput = " " + PREFIX_REMARK + "good worker";
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);

        // missing remark
        userInput = " " + PREFIX_ID + VALID_ID_BOB;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_invalidValue_failure() {
        // invalid employee id
        String userInput = " " + PREFIX_ID + "ID1234-5678 " + PREFIX_REMARK + "good worker";
        assertParseFailure(parser, userInput, Id.MESSAGE_CONSTRAINTS);

        // empty id
        userInput = " " + PREFIX_ID + " " + PREFIX_REMARK + "good worker";
        assertParseFailure(parser, userInput, Id.MESSAGE_CONSTRAINTS);
    }

    @Test
    void parse_allValidValue_success() {
        String userInput = " " + PREFIX_ID + VALID_ID_BOB + " " + PREFIX_REMARK + "good worker";
        DeleteRemarkCommand expectedCommand = new DeleteRemarkCommand(new Id(VALID_ID_BOB), new Remark("good worker"));
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
