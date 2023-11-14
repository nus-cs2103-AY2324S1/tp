package seedu.address.logic.parser.partnercourse;


import static seedu.address.logic.commands.CommandTestUtil.UNNECESSARY_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TestUtil.getSquareBracketWrappedArgument;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_PARTNER_COURSE;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_PARTNER_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_PARTNER_COURSE_DESCRIPTION;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_PARTNER_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_PARTNER_COURSE_UNIT;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_UNIVERSITY_NAME;
import static seedu.address.testutil.TypicalObjects.INVALID_PARTNER_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.INVALID_PARTNER_COURSE_DESCRIPTION;
import static seedu.address.testutil.TypicalObjects.INVALID_PARTNER_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.INVALID_PARTNER_COURSE_UNIT;
import static seedu.address.testutil.TypicalObjects.INVALID_UNIVERSITY_NAME;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_DESCRIPTION;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_UNIT;
import static seedu.address.testutil.TypicalObjects.TYPICAL_UNIVERSITY_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.partnercourse.PartnerCourseAddCommand;
import seedu.address.logic.commands.partnercourse.PartnerCourseCommand;
import seedu.address.messages.ConstraintMessage;
import seedu.address.messages.UsageMessage;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.testutil.PartnerCourseBuilder;
import seedu.address.testutil.PartnerCourseUtil;
import seedu.address.testutil.TypicalObjects;

public class PartnerCourseAddCommandParserTest {
    private static final String commandActionWord =
            PartnerCourseCommand.COMMAND_WORD + " " + PartnerCourseAddCommand.ACTION_WORD + " ";

    private PartnerCourseAddCommandParser parser = new PartnerCourseAddCommandParser();

    @Test
    public void parse_unnecessaryWhiteSpace_success() {
        PartnerCourse expectedPartnerCourse = new PartnerCourseBuilder(TypicalObjects.COMP1000).build();

        // add unnecessary whitespace
        assertParseSuccess(parser,
                UNNECESSARY_WHITESPACE
                        + PartnerCourseCommand.COMMAND_WORD
                        + UNNECESSARY_WHITESPACE
                        + PartnerCourseAddCommand.ACTION_WORD
                        + UNNECESSARY_WHITESPACE
                        + getSquareBracketWrappedArgument(expectedPartnerCourse.getPartnerUniversity()
                        .getUniversityName()
                        .toString())
                        + UNNECESSARY_WHITESPACE
                        + getSquareBracketWrappedArgument(expectedPartnerCourse.getPartnerCode().toString())
                        + UNNECESSARY_WHITESPACE
                        + getSquareBracketWrappedArgument(expectedPartnerCourse.getPartnerName().toString())
                        + UNNECESSARY_WHITESPACE
                        + getSquareBracketWrappedArgument(expectedPartnerCourse.getPartnerUnit().toString())
                        + UNNECESSARY_WHITESPACE
                        + getSquareBracketWrappedArgument(expectedPartnerCourse.getPartnerDescription().toString()),
                new PartnerCourseAddCommand(expectedPartnerCourse));
    }

    @Test
    void parse_argumentNotClosedOrEmpty_failure() {
        String expectedMessage = UsageMessage.PARTNERCOURSE_ADD.toString();

        // missing open bracket
        assertParseFailure(
                parser,
                commandActionWord + PartnerCourseUtil.getPartnerCourseArgumentsForAddCommand(TypicalObjects.COMP1000)
                        .substring(1),
                expectedMessage);

        String expectedMessageForEmptyArg = UsageMessage.PARTNERCOURSE_ADD.getValueWithEmptyArgs();
        // empty argument
        assertParseFailure(
                parser,
                commandActionWord
                        + getSquareBracketWrappedArgument(TYPICAL_PARTNER_COURSE_CODE)
                        + " "
                        + getSquareBracketWrappedArgument(""),
                expectedMessageForEmptyArg);
    }


    @Test
    public void parse_argumentMissing_failure() {
        String expectedMessage = UsageMessage.PARTNERCOURSE_ADD.toString();

        // missing patrtnercode argument
        assertParseFailure(parser,
                commandActionWord + getSquareBracketWrappedArgument(TYPICAL_PARTNER_COURSE_NAME),
                expectedMessage);

        // missing partnername argument
        assertParseFailure(parser,
                commandActionWord + getSquareBracketWrappedArgument(TYPICAL_PARTNER_COURSE_CODE),
                expectedMessage);

        // all arguments missing
        assertParseFailure(parser, commandActionWord, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // For universityName, partnerName, and partnerDescription
        // Arguments are trimmed before checkArgument, as a standardisation
        // Therefore an argument starting with a whitespace will pass,
        // while a whitespace alone will not. A whitespace alone will trigger
        // ParseException due to ParserUtil#areValuesEncloseAndNonEmpty.
        String expectedMessage = UsageMessage.PARTNERCOURSE_ADD.toString();

        String expectedMessageForEmptyArg = UsageMessage.PARTNERCOURSE_ADD.getValueWithEmptyArgs();

        // invalid universityName
        assertParseFailure(
                parser,
                commandActionWord
                        + getSquareBracketWrappedArgument(INVALID_UNIVERSITY_NAME)
                        + getSquareBracketWrappedArgument(TYPICAL_PARTNER_COURSE_CODE)
                        + getSquareBracketWrappedArgument(TYPICAL_PARTNER_COURSE_NAME)
                        + getSquareBracketWrappedArgument(TYPICAL_PARTNER_COURSE_UNIT.toString())
                        + getSquareBracketWrappedArgument(TYPICAL_PARTNER_COURSE_DESCRIPTION),
                expectedMessageForEmptyArg);
        // invalid partnerCode
        assertParseFailure(
            parser,
                commandActionWord
                        + getSquareBracketWrappedArgument(TYPICAL_UNIVERSITY_NAME)
                        + getSquareBracketWrappedArgument(INVALID_PARTNER_COURSE_CODE)
                        + getSquareBracketWrappedArgument(TYPICAL_PARTNER_COURSE_NAME)
                        + getSquareBracketWrappedArgument(TYPICAL_PARTNER_COURSE_UNIT.toString())
                        + getSquareBracketWrappedArgument(TYPICAL_PARTNER_COURSE_DESCRIPTION),
            ConstraintMessage.PARTNERCOURSE_CODE.toString());

        // invalid partnerName
        assertParseFailure(parser,
                commandActionWord
                        + getSquareBracketWrappedArgument(TYPICAL_PARTNER_COURSE_CODE)
                        + getSquareBracketWrappedArgument(INVALID_PARTNER_COURSE_NAME)
                        + getSquareBracketWrappedArgument(TYPICAL_PARTNER_COURSE_UNIT.toString())
                        + getSquareBracketWrappedArgument(TYPICAL_PARTNER_COURSE_DESCRIPTION),
                expectedMessageForEmptyArg);

        // invalid partnerUnit
        assertParseFailure(
                parser,
                commandActionWord
                        + getSquareBracketWrappedArgument(TYPICAL_UNIVERSITY_NAME)
                        + getSquareBracketWrappedArgument(TYPICAL_PARTNER_COURSE_CODE)
                        + getSquareBracketWrappedArgument(TYPICAL_PARTNER_COURSE_NAME)
                        + getSquareBracketWrappedArgument(INVALID_PARTNER_COURSE_UNIT.toString())
                        + getSquareBracketWrappedArgument(TYPICAL_PARTNER_COURSE_DESCRIPTION),
                ConstraintMessage.PARTNERCOURSE_UNIT.toString());

        // invalid partnerDescription
        assertParseFailure(
                parser,
                commandActionWord
                        + getSquareBracketWrappedArgument(TYPICAL_UNIVERSITY_NAME)
                        + getSquareBracketWrappedArgument(TYPICAL_PARTNER_COURSE_CODE)
                        + getSquareBracketWrappedArgument(TYPICAL_PARTNER_COURSE_NAME)
                        + getSquareBracketWrappedArgument(TYPICAL_PARTNER_COURSE_UNIT.toString())
                        + getSquareBracketWrappedArgument(INVALID_PARTNER_COURSE_DESCRIPTION),
                expectedMessageForEmptyArg);
    }

    @Test
    public void parse_edgeCasePartnerNameValue_success() {
        assertParseSuccess(parser,
                commandActionWord
                        + getSquareBracketWrappedArgument(EDGE_CASE_VALID_UNIVERSITY_NAME)
                        + getSquareBracketWrappedArgument(EDGE_CASE_VALID_PARTNER_COURSE_CODE)
                        + getSquareBracketWrappedArgument(EDGE_CASE_VALID_PARTNER_COURSE_NAME)
                        + getSquareBracketWrappedArgument(EDGE_CASE_VALID_PARTNER_COURSE_UNIT.toString())
                        + getSquareBracketWrappedArgument(EDGE_CASE_VALID_PARTNER_COURSE_DESCRIPTION),
                new PartnerCourseAddCommand(EDGE_CASE_VALID_PARTNER_COURSE));
    }
}
