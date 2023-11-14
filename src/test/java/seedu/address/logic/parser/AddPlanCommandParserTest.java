package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PLAN_DATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PLAN_FRIEND_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PLAN_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PLAN_DATETIME_DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.PLAN_FRIEND_DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.PLAN_NAME_DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_DATETIME_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_FRIEND_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_NAME_MEETING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRIEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPlans.MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddPlanCommand;
import seedu.address.model.person.Name;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.PlanDateTime;
import seedu.address.model.plan.PlanName;
import seedu.address.testutil.PlanBuilder;

public class AddPlanCommandParserTest {

    private AddPlanCommandParser parser = new AddPlanCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Plan expectedPlan = new PlanBuilder(MEETING).build();

        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + PLAN_NAME_DESC_MEETING + PLAN_DATETIME_DESC_MEETING
                        + PLAN_FRIEND_DESC_MEETING, new AddPlanCommand(
                                expectedPlan.getPlanName(), expectedPlan.getPlanDateTime(),
                        expectedPlan.getPlanFriend().getName())
        );
    }

    @Test
    public void parse_invalidFields_failure() {
        String validExpectedPlanString = PLAN_NAME_DESC_MEETING + PLAN_DATETIME_DESC_MEETING + PLAN_FRIEND_DESC_MEETING;

        // multiple plan names
        assertParseFailure(parser, PLAN_NAME_DESC_MEETING + validExpectedPlanString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
        // multiple date times
        assertParseFailure(parser, PLAN_DATETIME_DESC_MEETING + validExpectedPlanString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATETIME));
        // multiple friends
        assertParseFailure(parser, PLAN_FRIEND_DESC_MEETING + validExpectedPlanString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_FRIEND));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_MISSING_ARGUMENTS, AddPlanCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_PLAN_NAME_MEETING + PLAN_DATETIME_DESC_MEETING + PLAN_FRIEND_DESC_MEETING,
                expectedMessage);
        // missing datetime prefix
        assertParseFailure(parser, PLAN_DATETIME_DESC_MEETING + VALID_PLAN_DATETIME_MEETING + PLAN_FRIEND_DESC_MEETING,
                expectedMessage);
        // missing friend prefix
        assertParseFailure(parser, PLAN_DATETIME_DESC_MEETING + PLAN_DATETIME_DESC_MEETING + VALID_PLAN_FRIEND_MEETING,
                expectedMessage);
        // missing all prefixes
        assertParseFailure(parser, VALID_PLAN_NAME_MEETING + VALID_PLAN_DATETIME_MEETING + VALID_PLAN_FRIEND_MEETING,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_PLAN_NAME_DESC + PLAN_DATETIME_DESC_MEETING + PLAN_FRIEND_DESC_MEETING,
                PlanName.MESSAGE_CONSTRAINTS);
        // invalid datetime
        assertParseFailure(parser, PLAN_NAME_DESC_MEETING + INVALID_PLAN_DATETIME_DESC + PLAN_FRIEND_DESC_MEETING,
                PlanDateTime.MESSAGE_CONSTRAINTS);
        // invalid friend
        assertParseFailure(parser, PLAN_NAME_DESC_MEETING + PLAN_DATETIME_DESC_MEETING + INVALID_PLAN_FRIEND_DESC,
                Name.MESSAGE_CONSTRAINTS);
        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_PLAN_NAME_DESC + INVALID_PLAN_DATETIME_DESC + PLAN_FRIEND_DESC_MEETING,
                PlanName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + PLAN_NAME_DESC_MEETING
                + PLAN_DATETIME_DESC_MEETING + PLAN_FRIEND_DESC_MEETING,
                String.format(MESSAGE_MISSING_ARGUMENTS, AddPlanCommand.MESSAGE_USAGE));
    }
}
