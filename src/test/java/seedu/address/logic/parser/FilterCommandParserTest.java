package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_EMPTY_PREFIX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.company.ApplicationStatus.MESSAGE_CONSTRAINTS_VALID_STATUS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.company.ApplicationStatus;
import seedu.address.model.company.predicates.ApplicationStatusPredicate;

public class FilterCommandParserTest {
    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_validArgs_success() {
        ApplicationStatus status = new ApplicationStatus("pa");
        String userInput = " " + PREFIX_STATUS + "pa";
        ApplicationStatusPredicate predicate = new ApplicationStatusPredicate(status);
        FilterCommand expectedFilterCommand = new FilterCommand(status, predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);
    }

    @Test
    public void parse_emptyArg_failure() {
        assertParseFailure(parser, "", String.format(MESSAGE_EMPTY_PREFIX, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingPrefix_failure() {
        assertParseFailure(parser, "pa", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid status
        String userInput = " " + PREFIX_STATUS + "pp";
        assertParseFailure(parser, userInput, MESSAGE_CONSTRAINTS_VALID_STATUS);

        // invalid prefix
        userInput = " " + "ss/ " + "pp";
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }
}
