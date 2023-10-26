package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.FINANCIAL_PLAN_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FINANCIAL_PLAN_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FINANCIAL_PLAN_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GatherCommand;
import seedu.address.model.financialplan.FinancialPlan;
import seedu.address.model.person.gatheremail.GatherEmailByFinancialPlan;
import seedu.address.model.person.gatheremail.GatherEmailByTag;
import seedu.address.model.tag.Tag;

public class GatherCommandParserTest {
    private GatherCommandParser parser = new GatherCommandParser();
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, GatherCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_returnsException() {
        // test for Financial Plan
        String fpExceptionMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FinancialPlan.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_FINANCIAL_PLAN_DESC, fpExceptionMessage);

        // test for Tag
        String tagExceptionMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_TAG_DESC, tagExceptionMessage);

        // both prompts
        String bothPrompts = FINANCIAL_PLAN_DESC_1 + TAG_DESC_HUSBAND;
        String exceptionMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, GatherCommand.MESSAGE_USAGE);
        assertParseFailure(parser, bothPrompts, exceptionMessage);
    }

    @Test
    public void parse_validArgs_returnsGatherCommand() {
        GatherEmailByFinancialPlan financialPlanPrompt = new GatherEmailByFinancialPlan(VALID_FINANCIAL_PLAN_1);
        GatherCommand fpExpectedGatherCommand = new GatherCommand(financialPlanPrompt);
        assertParseSuccess(parser, FINANCIAL_PLAN_DESC_1, fpExpectedGatherCommand);

        GatherEmailByTag tagPrompt = new GatherEmailByTag(VALID_TAG_HUSBAND);
        GatherCommand tagExpectedGatherCommand = new GatherCommand(tagPrompt);
        assertParseSuccess(parser, TAG_DESC_HUSBAND, tagExpectedGatherCommand);
    }
}
