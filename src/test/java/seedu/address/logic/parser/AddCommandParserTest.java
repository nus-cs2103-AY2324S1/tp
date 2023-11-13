package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_DG;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_UG;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MEMBER_DESC_DG;
import static seedu.address.logic.commands.CommandTestUtil.MEMBER_DESC_UG;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_DG;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_UG;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DESC_DG;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DESC_TEST;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DESC_UG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_UG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMBER_DG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMBER_UG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTasks.TASK_DG;
import static seedu.address.testutil.TypicalTasks.TASK_UG;
import static seedu.address.testutil.TypicalTasks.TEST;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.member.Member;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    private AddCommand.AddTaskDescriptor desc = new AddCommand.AddTaskDescriptor();

    private void setUpDesc(Task validTask) {
        desc.setDescription(validTask.getDescription());
        desc.setDeadline(validTask.getDeadline());
        desc.setPriority(validTask.getPriority());
        desc.setMembers(validTask.getMembers());
    }

    @AfterEach
    public void reset() {
        desc = new AddCommand.AddTaskDescriptor();
    }

    @Test
    public void parse_allFieldsPresentSingleMember_success() {
        Task expectedTask = new TaskBuilder(TASK_DG).withMembers(VALID_MEMBER_DG).withNote("").build();

        setUpDesc(expectedTask);

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + TASK_DESC_DG + PRIORITY_DESC_DG + MEMBER_DESC_DG,
                new AddCommand(desc));
    }

    @Test
    public void parse_allFieldsPresentMultipleMember_success() {
        //multiple members - all accepted //TODO
        Task expectedTask = new TaskBuilder(TASK_UG).withMembers(VALID_MEMBER_DG, VALID_MEMBER_UG).withNote("")
                .build();

        setUpDesc(expectedTask);

        assertParseSuccess(parser,
                TASK_DESC_UG + MEMBER_DESC_DG + MEMBER_DESC_UG + PRIORITY_DESC_UG,
                new AddCommand(desc));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedTaskString = TASK_DESC_UG + DEADLINE_DESC_UG + PRIORITY_DESC_UG + MEMBER_DESC_UG;

        // multiple descriptions
        assertParseFailure(parser, TASK_DESC_DG + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));

        // multiple deadlines
        assertParseFailure(parser, DEADLINE_DESC_DG + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DEADLINE));

        // multiple priorities
        assertParseFailure(parser, PRIORITY_DESC_DG + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRIORITY));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedTaskString + TASK_DESC_DG + TASK_DESC_UG
                        + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION, PREFIX_DEADLINE, PREFIX_PRIORITY));
        // invalid value followed by valid value

        // invalid description
        assertParseFailure(parser, INVALID_TASK_DESC + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));

        // invalid deadline
        assertParseFailure(parser, INVALID_DEADLINE_DESC + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DEADLINE));

        // invalid priority
        assertParseFailure(parser, INVALID_PRIORITY_DESC + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRIORITY));

        // valid value followed by invalid value
        // invalid name
        assertParseFailure(parser, validExpectedTaskString + INVALID_TASK_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));

        // invalid deadline
        assertParseFailure(parser, validExpectedTaskString + INVALID_DEADLINE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DEADLINE));

        // invalid priority
        assertParseFailure(parser, validExpectedTaskString + INVALID_PRIORITY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRIORITY));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero members
        Task expectedTask = new TaskBuilder(TEST).withMembers().build();
        setUpDesc(expectedTask);
        assertParseSuccess(parser, TASK_DESC_TEST, new AddCommand(desc));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.COMMAND_WORD)
                + "\nUsage: " + AddCommand.MESSAGE_USAGE;

        // missing description prefix
        assertParseFailure(parser, VALID_DESC_UG + DEADLINE_DESC_UG + PRIORITY_DESC_UG,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid description
        assertParseFailure(parser, INVALID_TASK_DESC + DEADLINE_DESC_UG + PRIORITY_DESC_DG
                + MEMBER_DESC_DG + MEMBER_DESC_UG, Description.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, TASK_DESC_UG + INVALID_DEADLINE_DESC + PRIORITY_DESC_DG
                + MEMBER_DESC_DG + MEMBER_DESC_UG, Deadline.MESSAGE_CONSTRAINTS);

        // invalid priority
        assertParseFailure(parser, TASK_DESC_UG + DEADLINE_DESC_UG + INVALID_PRIORITY_DESC
                + MEMBER_DESC_DG + MEMBER_DESC_UG, Priority.MESSAGE_CONSTRAINTS);

        // invalid member
        assertParseFailure(parser, TASK_DESC_UG + DEADLINE_DESC_UG + PRIORITY_DESC_DG
                + INVALID_MEMBER_DESC, Member.MESSAGE_CONSTRAINTS);
        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TASK_DESC + DEADLINE_DESC_UG + INVALID_PRIORITY_DESC
                + MEMBER_DESC_DG + MEMBER_DESC_UG, Description.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TASK_DESC_UG + DEADLINE_DESC_UG + PRIORITY_DESC_DG
                + MEMBER_DESC_DG + MEMBER_DESC_UG,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.COMMAND_WORD)
                        + "\nUsage: " + AddCommand.MESSAGE_USAGE);
    }
}
