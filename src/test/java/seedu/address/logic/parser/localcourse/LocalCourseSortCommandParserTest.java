package seedu.address.logic.parser.localcourse;


import static seedu.address.logic.commands.CommandTestUtil.UNNECESSARY_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TestUtil.getSquareBracketWrappedArgument;
import static seedu.address.testutil.TypicalObjects.INVALID_LOCAL_COURSE_ATTRIBUTE;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.localcourse.LocalCourseCommand;
import seedu.address.logic.commands.localcourse.LocalCourseSortCommand;
import seedu.address.messages.ConstraintMessage;
import seedu.address.messages.UsageMessage;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.localcourse.LocalCourseAttribute;
import seedu.address.model.localcourse.comparator.LocalCourseComparatorByLocalCode;
import seedu.address.model.localcourse.comparator.LocalCourseComparatorByLocalName;
import seedu.address.testutil.LocalCourseUtil;

public class LocalCourseSortCommandParserTest {

    private static final String commandActionWord =
            LocalCourseCommand.COMMAND_WORD
                    + " "
                    + LocalCourseSortCommand.ACTION_WORD;

    private LocalCourseSortCommandParser parser = new LocalCourseSortCommandParser();

    @Test
    public void parse_unnecessaryWhiteSpace_success() {
        Comparator<LocalCourse> expectedComparator = new LocalCourseComparatorByLocalCode();

        // add unnecessary whitespace
        assertParseSuccess(parser, UNNECESSARY_WHITESPACE
                        + LocalCourseCommand.COMMAND_WORD
                        + UNNECESSARY_WHITESPACE
                        + LocalCourseSortCommand.ACTION_WORD
                        + UNNECESSARY_WHITESPACE
                        + getSquareBracketWrappedArgument("localcode")
                        + UNNECESSARY_WHITESPACE,
                new LocalCourseSortCommand(expectedComparator));
    }

    @Test
    public void parse_argumentNotClosedOrEmpty_failure() {
        String expectedMessage = UsageMessage.LOCALCOURSE_SORT.toString();
        // missing open bracket
        assertParseFailure(parser, commandActionWord
                + LocalCourseUtil.getLocalCourseArgumentsForSortCommand(new LocalCourseComparatorByLocalCode())
                .substring(1), expectedMessage);

        String expectedMessageWithEmptyArg = UsageMessage.LOCALCOURSE_SORT.getValueWithEmptyArgs();
        // empty argument
        assertParseFailure(parser, commandActionWord
                + getSquareBracketWrappedArgument(""), expectedMessageWithEmptyArg);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid attribute
        assertParseFailure(parser, commandActionWord
                        + getSquareBracketWrappedArgument(INVALID_LOCAL_COURSE_ATTRIBUTE),
                ConstraintMessage.LOCALCOURSE_ATTRIBUTE_SORT.toString());
    }

    @Test
    public void parse_validArguments_success() {
        assertParseSuccess(parser, commandActionWord
                + getSquareBracketWrappedArgument(LocalCourseAttribute.LOCALCODE.toString()),
                new LocalCourseSortCommand(new LocalCourseComparatorByLocalCode()));

        assertParseSuccess(parser, commandActionWord
                        + getSquareBracketWrappedArgument(LocalCourseAttribute.LOCALNAME.toString()),
                new LocalCourseSortCommand(new LocalCourseComparatorByLocalName()));

    }
}
