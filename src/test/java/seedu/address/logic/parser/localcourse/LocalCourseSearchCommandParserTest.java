package seedu.address.logic.parser.localcourse;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.localcourse.LocalCourseSearchCommand;
import seedu.address.messages.UsageMessage;
import seedu.address.model.localcourse.LocalCourseAttribute;
import seedu.address.model.localcourse.LocalCourseContainsKeywordsPredicate;


public class LocalCourseSearchCommandParserTest {
    private LocalCourseSearchCommandParser parser = new LocalCourseSearchCommandParser();


    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", UsageMessage.LOCALCOURSE_SEARCH.toString());
    }

    @Test
    public void parse_validLocalCodeArg_returnsLocalCourseSearchCommand() {
        // no leading and trailing whitespaces
        LocalCourseSearchCommand expectedLocalCourseSearchCommand =
                new LocalCourseSearchCommand(LocalCourseAttribute.LOCALCODE,
                        new LocalCourseContainsKeywordsPredicate(
                        "CS2042S", LocalCourseAttribute.LOCALCODE), "CS2042S");

        assertParseSuccess(parser,
                "localcourse search [localcode] [CS2042S]",
                expectedLocalCourseSearchCommand);

        // multiple whitespaces between keywords
        // assertParseSuccess(parser, " \n localcourse", expectedLocalCourseSearchCommand);
    }

    @Test
    public void parse_validLocalNameArg_returnsLocalCourseSearchCommand() {
        // no leading and trailing whitespaces
        LocalCourseSearchCommand expectedLocalCourseSearchCommand =
                new LocalCourseSearchCommand(LocalCourseAttribute.LOCALNAME,
                        new LocalCourseContainsKeywordsPredicate(
                        "Data Structures & Algorithms", LocalCourseAttribute.LOCALNAME),
                        "Data Structures & Algorithms");

        assertParseSuccess(parser, "localcourse search [localname] [Data Structures & Algorithms]",
                expectedLocalCourseSearchCommand);

        // multiple whitespaces between keywords
        // assertParseSuccess(parser, " \n localcourse", expectedLocalCourseSearchCommand);
    }

}
