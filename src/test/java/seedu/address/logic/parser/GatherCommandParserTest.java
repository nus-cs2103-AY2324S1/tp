package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.GatherCommandParser.FINANCIAL_PLAN_CONSTRAINTS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GatherCommand;
import seedu.address.model.person.GatherEmails;
import seedu.address.model.person.GatherEmailsByFinancialPlan;

public class GatherCommandParserTest {
    private GatherCommandParser parser = new GatherCommandParser();
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, GatherCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_invalidArgs_returnsException() {
        String prompt = "fp/***";
        String exceptionMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FINANCIAL_PLAN_CONSTRAINTS);
        assertParseFailure(parser, prompt, exceptionMessage);
    }

    @Test
    public void parse_validArgs_returnsGatherCommand() {
        String input = "fp/Sample Financial Plan";
        GatherEmails prompt = new GatherEmailsByFinancialPlan("Sample Financial Plan");
        GatherCommand expectedGatherCommand = new GatherCommand(prompt);
        assertParseSuccess(parser, input, expectedGatherCommand);
    }
}
