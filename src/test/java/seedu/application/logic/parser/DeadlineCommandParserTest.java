package seedu.application.logic.parser;
import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_JOB;

import org.junit.jupiter.api.Test;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.DeadlineCommand;
import seedu.application.model.job.Deadline;

class DeadlineCommandParserTest {
    private DeadlineCommandParser parser = new DeadlineCommandParser();
    private final String nonEmptyDeadline = "Dec 31 2030 1200";

    @Test
    public void parse_indexSpecified_success() {
        // have deadline
        Index targetIndex = INDEX_FIRST_JOB;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_DEADLINE + nonEmptyDeadline;
        DeadlineCommand expectedCommand = new DeadlineCommand(INDEX_FIRST_JOB, new Deadline(nonEmptyDeadline));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeadlineCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, DeadlineCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, DeadlineCommand.COMMAND_WORD + " " + nonEmptyDeadline, expectedMessage);
    }
}
