package seedu.address.logic.parser.partnercourse;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.partnercourse.PartnerCourseSearchCommand;
import seedu.address.messages.UsageMessage;
import seedu.address.model.partnercourse.PartnerCourseAttribute;
import seedu.address.model.partnercourse.PartnerCourseContainsKeywordsPredicate;


public class PartnerCourseSearchCommandParserTest {
    private PartnerCourseSearchCommandParser parser = new PartnerCourseSearchCommandParser();


    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", UsageMessage.PARTNERCOURSE_SEARCH.toString());
    }

    @Test
    public void parse_validPartnerCodeArg_returnsPartnerCourseSearchCommand() {
        // no leading and trailing whitespaces
        PartnerCourseSearchCommand expectedPartnerCourseSearchCommand =
                new PartnerCourseSearchCommand(PartnerCourseAttribute.PARTNERCODE,
                        new PartnerCourseContainsKeywordsPredicate(
                        "COMP3000", PartnerCourseAttribute.PARTNERCODE), "COMP3000");

        assertParseSuccess(parser, "partnercourse search [partnercode] [COMP3000]",
                expectedPartnerCourseSearchCommand);

        // multiple whitespaces between keywords
        // assertParseSuccess(parser, " \n partnercourse", expectedLocalCourseSearchCommand);
    }

    @Test
    public void parse_validPartnerNameArg_returnsPartnerCourseSearchCommand() {
        // no leading and trailing whitespaces
        PartnerCourseSearchCommand expectedPartnerCourseSearchCommand =
                new PartnerCourseSearchCommand(PartnerCourseAttribute.PARTNERNAME,
                        new PartnerCourseContainsKeywordsPredicate(
                        "Introduction to Networks", PartnerCourseAttribute.PARTNERNAME),
                        "Introduction to Networks");

        assertParseSuccess(parser, "partnercourse search [partnername] [Introduction to Networks]",
                expectedPartnerCourseSearchCommand);

        // multiple whitespaces between keywords
        // assertParseSuccess(parser, " \n partnercourse", expectedLocalCourseSearchCommand);
    }
}
