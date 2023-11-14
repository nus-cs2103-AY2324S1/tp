package seedu.address.logic.parser.localcourse;


import static seedu.address.logic.commands.CommandTestUtil.UNNECESSARY_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TestUtil.getSquareBracketWrappedArgument;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_LOCAL_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_LOCAL_COURSE_DESCRIPTION;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_LOCAL_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_LOCAL_COURSE_UNIT;
import static seedu.address.testutil.TypicalObjects.INVALID_LOCAL_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.INVALID_LOCAL_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_DESCRIPTION;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_UNIT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.localcourse.LocalCourseAddCommand;
import seedu.address.logic.commands.localcourse.LocalCourseCommand;
import seedu.address.messages.ConstraintMessage;
import seedu.address.messages.UsageMessage;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.testutil.LocalCourseBuilder;
import seedu.address.testutil.LocalCourseUtil;
import seedu.address.testutil.TypicalObjects;

public class LocalCourseAddCommandParserTest {
    private static final String commandActionWord = LocalCourseCommand.COMMAND_WORD + " "
            + LocalCourseAddCommand.ACTION_WORD;
    private LocalCourseAddCommandParser parser = new LocalCourseAddCommandParser();

    @Test
    public void parse_unnecessaryWhiteSpace_success() {
        LocalCourse expectedLocalCourse = new LocalCourseBuilder(TypicalObjects.CS2040S).build();

        // add unnecessary whitespace
        assertParseSuccess(parser, UNNECESSARY_WHITESPACE
                        + LocalCourseCommand.COMMAND_WORD
                        + UNNECESSARY_WHITESPACE
                        + LocalCourseAddCommand.ACTION_WORD
                        + UNNECESSARY_WHITESPACE
                        + getSquareBracketWrappedArgument(expectedLocalCourse.getLocalCode().toString())
                        + UNNECESSARY_WHITESPACE
                        + getSquareBracketWrappedArgument(expectedLocalCourse.getLocalName().toString())
                        + UNNECESSARY_WHITESPACE
                        + getSquareBracketWrappedArgument(expectedLocalCourse.getLocalUnit().toString())
                        + UNNECESSARY_WHITESPACE
                        + getSquareBracketWrappedArgument(expectedLocalCourse.getLocalDescription()
                        .toString()),
                new LocalCourseAddCommand(expectedLocalCourse));
    }

    @Test
    void parse_argumentNotClosedOrEmpty_failure() {
        String expectedMessage =
                UsageMessage.LOCALCOURSE_ADD.toString();

        // missing open bracket
        assertParseFailure(parser, commandActionWord
                + LocalCourseUtil.getLocalCourseArgumentsForAddCommand(TypicalObjects.CS3230)
                .substring(1), expectedMessage);

        String expectedMessageForEmptyArg =
                UsageMessage.LOCALCOURSE_ADD.getValueWithEmptyArgs();
        // empty argument
        assertParseFailure(parser, commandActionWord
                + getSquareBracketWrappedArgument(TYPICAL_LOCAL_COURSE_CODE)
                + getSquareBracketWrappedArgument(""), expectedMessageForEmptyArg);
    }


    @Test
    public void parse_allArgumentMissing_failure() {
        String expectedMessage = UsageMessage.LOCALCOURSE_ADD.toString();

        // missing localcode argument
        assertParseFailure(parser, commandActionWord
                + getSquareBracketWrappedArgument(TYPICAL_LOCAL_COURSE_NAME), expectedMessage);

        // missing localname prefix
        assertParseFailure(parser, commandActionWord
                + getSquareBracketWrappedArgument(TYPICAL_LOCAL_COURSE_CODE), expectedMessage);

        // all arguments missing
        assertParseFailure(parser, commandActionWord, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // Invalid localCode
        String invalidLocalCodeCommand = commandActionWord
                + " " + getSquareBracketWrappedArgument(INVALID_LOCAL_COURSE_CODE)
                + " " + getSquareBracketWrappedArgument(TYPICAL_LOCAL_COURSE_NAME)
                + " " + getSquareBracketWrappedArgument(TYPICAL_LOCAL_COURSE_UNIT.toString())
                + " " + getSquareBracketWrappedArgument(TYPICAL_LOCAL_COURSE_DESCRIPTION);


        assertParseFailure(parser, invalidLocalCodeCommand, ConstraintMessage.LOCALCOURSE_CODE.toString());

        // Invalid localName
        // This test also ensures that the input to checkArgument and parseLocalName matches.
        // localName is trimmed before checkArgument, as a standardization
        // Therefore, a localName argument starting with a whitespace will pass,
        // while a whitespace alone will not. A whitespace alone will trigger
        // ParseException due to ParserUtil#areValuesEncloseAndNonEmpty.
        String invalidLocalNameCommand = commandActionWord
                + " " + getSquareBracketWrappedArgument(TYPICAL_LOCAL_COURSE_CODE)
                + " " + getSquareBracketWrappedArgument(INVALID_LOCAL_COURSE_NAME)
                + " " + getSquareBracketWrappedArgument(TYPICAL_LOCAL_COURSE_DESCRIPTION);

        assertParseFailure(parser, invalidLocalNameCommand, UsageMessage.LOCALCOURSE_ADD.getValueWithEmptyArgs());
    }


    @Test
    public void parse_edgeCaseLocalNameValue_success() {
        String command = commandActionWord
                + getSquareBracketWrappedArgument(EDGE_CASE_VALID_LOCAL_COURSE_CODE)
                + getSquareBracketWrappedArgument(EDGE_CASE_VALID_LOCAL_COURSE_NAME)
                + getSquareBracketWrappedArgument(EDGE_CASE_VALID_LOCAL_COURSE_UNIT.toString())
                + getSquareBracketWrappedArgument(EDGE_CASE_VALID_LOCAL_COURSE_DESCRIPTION);

        LocalCourse localCourse =
                new LocalCourseBuilder().withLocalCode(EDGE_CASE_VALID_LOCAL_COURSE_CODE)
                        .withLocalName(EDGE_CASE_VALID_LOCAL_COURSE_NAME)
                        .withLocalUnit(EDGE_CASE_VALID_LOCAL_COURSE_UNIT)
                        .withLocalDescription(EDGE_CASE_VALID_LOCAL_COURSE_DESCRIPTION)
                        .build();
        assertParseSuccess(parser, command, new LocalCourseAddCommand(localCourse));
    }
}

