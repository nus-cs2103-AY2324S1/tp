package seedu.address.logic.parser.partnercourse;


import static seedu.address.logic.commands.CommandTestUtil.UNNECESSARY_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TestUtil.getSquareBracketWrappedArgument;
import static seedu.address.testutil.TypicalObjects.INVALID_PARTNER_COURSE_ATTRIBUTE;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.partnercourse.PartnerCourseCommand;
import seedu.address.logic.commands.partnercourse.PartnerCourseSortCommand;
import seedu.address.messages.ConstraintMessage;
import seedu.address.messages.UsageMessage;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.partnercourse.PartnerCourseAttribute;
import seedu.address.model.partnercourse.comparator.PartnerCourseComparatorByPartnerCode;
import seedu.address.model.partnercourse.comparator.PartnerCourseComparatorByPartnerName;
import seedu.address.model.partnercourse.comparator.PartnerCourseComparatorByUniversity;
import seedu.address.testutil.PartnerCourseUtil;

public class PartnerCourseSortCommandParserTest {

    private static final String commandActionWord =
        PartnerCourseCommand.COMMAND_WORD
        + " "
        + PartnerCourseSortCommand.ACTION_WORD;

    private PartnerCourseSortCommandParser parser = new PartnerCourseSortCommandParser();

    @Test
    public void parse_unnecessaryWhiteSpace_success() {
        Comparator<PartnerCourse> expectedComparator = new PartnerCourseComparatorByPartnerCode();

        // add unnecessary whitespace
        assertParseSuccess(parser, UNNECESSARY_WHITESPACE
                        + PartnerCourseCommand.COMMAND_WORD
                        + UNNECESSARY_WHITESPACE
                        + PartnerCourseSortCommand.ACTION_WORD
                        + UNNECESSARY_WHITESPACE
                        + getSquareBracketWrappedArgument("partnercode")
                        + UNNECESSARY_WHITESPACE,
                new PartnerCourseSortCommand(expectedComparator));
    }

    @Test
    public void parse_argumentNotClosedOrEmpty_failure() {
        String expectedMessage = UsageMessage.PARTNERCOURSE_SORT.toString();

        // missing open bracket
        assertParseFailure(parser, commandActionWord
                + PartnerCourseUtil.getPartnerCourseArgumentsForSortCommand(new PartnerCourseComparatorByPartnerCode())
                .substring(1), expectedMessage);

        String expectedMessageForEmptyArg = UsageMessage.PARTNERCOURSE_SORT.getValueWithEmptyArgs();
        // empty argument
        assertParseFailure(parser, commandActionWord
                + getSquareBracketWrappedArgument(""), expectedMessageForEmptyArg);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid attribute
        assertParseFailure(parser, commandActionWord
                        + getSquareBracketWrappedArgument(INVALID_PARTNER_COURSE_ATTRIBUTE),
                           ConstraintMessage.PARTNERCOURSE_ATTRIBUTE_SORT.toString());
    }

    @Test
    public void parse_validArguments_success() {
        assertParseSuccess(parser, commandActionWord
                + getSquareBracketWrappedArgument(PartnerCourseAttribute.PARTNERCODE.toString()),
                new PartnerCourseSortCommand(new PartnerCourseComparatorByPartnerCode()));

        assertParseSuccess(parser, commandActionWord
                        + getSquareBracketWrappedArgument(PartnerCourseAttribute.PARTNERNAME.toString()),
                new PartnerCourseSortCommand(new PartnerCourseComparatorByPartnerName()));

        assertParseSuccess(parser, commandActionWord
                        + getSquareBracketWrappedArgument(PartnerCourseAttribute.UNIVERSITY.toString()),
                new PartnerCourseSortCommand(new PartnerCourseComparatorByUniversity()));
    }
}
