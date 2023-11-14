//@@author tiongjjyi
package seedu.codesphere.logic.parser;

import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_PENDING_QUESTION;
import static seedu.codesphere.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.codesphere.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.codesphere.logic.commands.ListPendingQuestionsCommand;
import seedu.codesphere.logic.parser.exceptions.ParseException;
import seedu.codesphere.model.student.predicates.AllPendingQuestionPredicate;

public class ListPendingQuestionCommandParserTest {

    private AllPendingQuestionPredicate predicate = new AllPendingQuestionPredicate();
    private ListPendingQuestionsCommandParser parser = new ListPendingQuestionsCommandParser();

    @Test
    public void parse_nonEmptyArg_throwsParseException() {
        assertParseFailure(parser, "non-empty", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListPendingQuestionsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsListPendingQuestionCommand() throws ParseException {
        // no leading and trailing whitespaces
        String userInput = " " + PREFIX_PENDING_QUESTION;
        ListPendingQuestionsCommand expectedListPendingQuestionsCommand = new ListPendingQuestionsCommand(predicate);
        assertParseSuccess(parser, userInput, expectedListPendingQuestionsCommand);
    }
}
