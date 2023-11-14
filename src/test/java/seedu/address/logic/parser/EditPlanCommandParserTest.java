package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PLAN_DATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PLAN_FRIEND_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PLAN_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PLAN_DATETIME_DESC_GAMING;
import static seedu.address.logic.commands.CommandTestUtil.PLAN_DATETIME_DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.PLAN_FRIEND_DESC_GAMING;
import static seedu.address.logic.commands.CommandTestUtil.PLAN_FRIEND_DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.PLAN_NAME_DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_DATETIME_GAMING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_DATETIME_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_FRIEND_GAMING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_FRIEND_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_NAME_MEETING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRIEND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PLAN;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PLAN;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PLAN;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditPlanCommand;
import seedu.address.logic.commands.EditPlanCommand.EditPlanDescriptor;
import seedu.address.model.person.Name;
import seedu.address.model.plan.PlanDateTime;
import seedu.address.model.plan.PlanName;
import seedu.address.testutil.EditPlanDescriptorBuilder;

public class EditPlanCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPlanCommand.MESSAGE_USAGE);

    private EditPlanCommandParser parser = new EditPlanCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_PLAN_NAME_MEETING, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditPlanCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + PLAN_NAME_DESC_MEETING, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + PLAN_NAME_DESC_MEETING, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_PLAN_NAME_DESC,
                PlanName.MESSAGE_CONSTRAINTS); // invalid planName
        assertParseFailure(parser, "1" + INVALID_PLAN_DATETIME_DESC,
                PlanDateTime.MESSAGE_CONSTRAINTS); // invalid dateTime
        assertParseFailure(parser, "1" + INVALID_PLAN_FRIEND_DESC,
                Name.MESSAGE_CONSTRAINTS); // invalid friend

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PLAN_DATETIME_DESC + PLAN_FRIEND_DESC_MEETING,
                PlanDateTime.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_PLAN_NAME_DESC + INVALID_PLAN_DATETIME_DESC
                        + VALID_PLAN_FRIEND_MEETING, PlanName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PLAN;
        String userInput = targetIndex.getOneBased() + PLAN_FRIEND_DESC_GAMING + PLAN_DATETIME_DESC_GAMING
                + PLAN_NAME_DESC_MEETING;

        EditPlanDescriptor descriptor = new EditPlanDescriptorBuilder().withPlanName(VALID_PLAN_NAME_MEETING)
                .withPlanDateTime(VALID_PLAN_DATETIME_GAMING).withPlanFriendName(VALID_PLAN_FRIEND_GAMING).build();
        EditPlanCommand expectedCommand = new EditPlanCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PLAN;
        String userInput = targetIndex.getOneBased() + PLAN_DATETIME_DESC_GAMING + PLAN_FRIEND_DESC_MEETING;

        EditPlanDescriptor descriptor = new EditPlanDescriptorBuilder().withPlanDateTime(VALID_PLAN_DATETIME_GAMING)
                .withPlanFriendName(VALID_PLAN_FRIEND_MEETING).build();
        EditPlanCommand expectedCommand = new EditPlanCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PLAN;
        String userInput = targetIndex.getOneBased() + PLAN_NAME_DESC_MEETING;
        EditPlanCommand.EditPlanDescriptor descriptor = new EditPlanDescriptorBuilder()
                .withPlanName(VALID_PLAN_NAME_MEETING).build();
        EditPlanCommand expectedCommand = new EditPlanCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PLAN_DATETIME_DESC_MEETING;
        descriptor = new EditPlanDescriptorBuilder().withPlanDateTime(VALID_PLAN_DATETIME_MEETING).build();
        expectedCommand = new EditPlanCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + PLAN_FRIEND_DESC_MEETING;
        descriptor = new EditPlanDescriptorBuilder().withPlanFriendName(VALID_PLAN_FRIEND_MEETING).build();
        expectedCommand = new EditPlanCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_PLAN;
        String userInput = targetIndex.getOneBased() + INVALID_PLAN_DATETIME_DESC + PLAN_DATETIME_DESC_GAMING;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATETIME));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + PLAN_DATETIME_DESC_GAMING + INVALID_PLAN_DATETIME_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATETIME));

        // mulltiple valid fields repeated
        userInput = targetIndex.getOneBased() + PLAN_DATETIME_DESC_MEETING + PLAN_FRIEND_DESC_MEETING
                + PLAN_DATETIME_DESC_MEETING + PLAN_FRIEND_DESC_MEETING + PLAN_DATETIME_DESC_GAMING
                + PLAN_DATETIME_DESC_GAMING + PLAN_FRIEND_DESC_GAMING;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATETIME, PREFIX_FRIEND));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_PLAN_DATETIME_DESC + INVALID_PLAN_FRIEND_DESC
                + INVALID_PLAN_DATETIME_DESC + INVALID_PLAN_FRIEND_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATETIME, PREFIX_FRIEND));
    }
}
