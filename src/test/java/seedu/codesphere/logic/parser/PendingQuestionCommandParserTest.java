//@@author tiongjjyi
package seedu.codesphere.logic.parser;

import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_PENDING_QUESTION;
import static seedu.codesphere.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.codesphere.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.codesphere.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.codesphere.commons.core.index.Index;
import seedu.codesphere.logic.commands.PendingQuestionCommand;
import seedu.codesphere.model.student.PendingQuestion;

public class PendingQuestionCommandParserTest {
    private PendingQuestionCommandParser parser = new PendingQuestionCommandParser();
    private final String nonEmptyPendingQuestion = "Some question.";

    @Test
    public void parse_indexSpecified_success() {
        // have pending question
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_PENDING_QUESTION + nonEmptyPendingQuestion;
        PendingQuestionCommand expectedCommand = new PendingQuestionCommand(
                INDEX_FIRST_STUDENT, new PendingQuestion(nonEmptyPendingQuestion));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PendingQuestionCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, PendingQuestionCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser,
                PendingQuestionCommand.COMMAND_WORD + " " + nonEmptyPendingQuestion, expectedMessage);
    }
}
