package seedu.address.logic.parser.partnercourse;


import static seedu.address.logic.commands.CommandTestUtil.UNNECESSARY_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TestUtil.getSquareBracketWrappedArgument;
import static seedu.address.testutil.TypicalObjects.COMP1000;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_PARTNER_COURSE;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_PARTNER_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_UNIVERSITY_NAME;
import static seedu.address.testutil.TypicalObjects.INVALID_PARTNER_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_UNIVERSITY_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.partnercourse.PartnerCourseAddCommand;
import seedu.address.logic.commands.partnercourse.PartnerCourseCommand;
import seedu.address.logic.commands.partnercourse.PartnerCourseDeleteCommand;
import seedu.address.messages.ConstraintMessage;
import seedu.address.messages.UsageMessage;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.university.UniversityName;
import seedu.address.testutil.PartnerCourseUtil;
import seedu.address.testutil.TypicalObjects;


public class PartnerCourseDeleteCommandParserTest {
    private static final String commandActionWord = PartnerCourseCommand.COMMAND_WORD
            + " " + PartnerCourseDeleteCommand.ACTION_WORD + " ";

    private PartnerCourseDeleteCommandParser parser = new PartnerCourseDeleteCommandParser();

    @Test
    public void parse_unnecessaryWhiteSpace_success() {
        PartnerCode expectedPartnerCode = COMP1000.getPartnerCode();
        UniversityName expectedUniversityName = COMP1000.getPartnerUniversity().getUniversityName();
        // add unnecessary whitespace
        assertParseSuccess(parser, UNNECESSARY_WHITESPACE
                        + PartnerCourseCommand.COMMAND_WORD
                        + UNNECESSARY_WHITESPACE
                        + PartnerCourseAddCommand.ACTION_WORD
                        + UNNECESSARY_WHITESPACE
                        + getSquareBracketWrappedArgument(expectedUniversityName.toString())
                        + UNNECESSARY_WHITESPACE
                        + getSquareBracketWrappedArgument(expectedPartnerCode.toString())
                        + UNNECESSARY_WHITESPACE,
                new PartnerCourseDeleteCommand(expectedUniversityName, expectedPartnerCode));
    }

    @Test
    void parse_argumentNotClosedOrEmpty_failure() {
        String expectedMessage = UsageMessage.PARTNERCOURSE_DELETE.toString();

        // missing open bracket
        assertParseFailure(parser, commandActionWord
                + PartnerCourseUtil.getPartnerCourseArgumentsForDeleteCommand(TypicalObjects.COMP1000).substring(
                1), expectedMessage);

        String expectedMessageForEmptyArg = UsageMessage.PARTNERCOURSE_DELETE.getValueWithEmptyArgs();
        // empty argument
        assertParseFailure(parser, commandActionWord
                + getSquareBracketWrappedArgument(""), expectedMessageForEmptyArg);
    }


    @Test
    public void parse_argumentMissing_failure() {
        String expectedMessage = UsageMessage.PARTNERCOURSE_DELETE.toString();

        // all arguments missing
        assertParseFailure(parser, commandActionWord, expectedMessage);

        // missing universityname argument
        assertParseFailure(parser, commandActionWord
                + getSquareBracketWrappedArgument(TYPICAL_PARTNER_COURSE_CODE), expectedMessage);

        // missing partnercode argument
        assertParseFailure(parser, commandActionWord
                + getSquareBracketWrappedArgument(TYPICAL_UNIVERSITY_NAME), expectedMessage);

    }


    @Test
    public void parse_invalidValue_failure() {
        // invalid partnerCode
        assertParseFailure(parser, commandActionWord
                        + getSquareBracketWrappedArgument(INVALID_PARTNER_COURSE_CODE)
                        + getSquareBracketWrappedArgument(TYPICAL_UNIVERSITY_NAME),
                           ConstraintMessage.PARTNERCOURSE_CODE.toString());
    }

    @Test
    public void parse_edgeCaseValue_success() {
        assertParseSuccess(parser, commandActionWord
                        + getSquareBracketWrappedArgument(EDGE_CASE_VALID_UNIVERSITY_NAME)
                        + getSquareBracketWrappedArgument(EDGE_CASE_VALID_PARTNER_COURSE_CODE),
                new PartnerCourseDeleteCommand(
                        EDGE_CASE_VALID_PARTNER_COURSE.getPartnerUniversity().getUniversityName(),
                        EDGE_CASE_VALID_PARTNER_COURSE.getPartnerCode()
                ));
    }

}
