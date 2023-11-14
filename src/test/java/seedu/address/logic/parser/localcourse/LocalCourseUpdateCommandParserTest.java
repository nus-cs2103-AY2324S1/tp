package seedu.address.logic.parser.localcourse;

import static seedu.address.logic.commands.CommandTestUtil.UNNECESSARY_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TestUtil.getSquareBracketWrappedArgument;
import static seedu.address.testutil.TypicalObjects.CS2042S;
import static seedu.address.testutil.TypicalObjects.INVALID_LOCAL_COURSE_ATTRIBUTE;
import static seedu.address.testutil.TypicalObjects.INVALID_LOCAL_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.INVALID_LOCAL_COURSE_UNIT;
import static seedu.address.testutil.TypicalObjects.INVALID_PARTNER_COURSE_CODE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.localcourse.LocalCourseCommand;
import seedu.address.logic.commands.localcourse.LocalCourseUpdateCommand;
import seedu.address.messages.ConstraintMessage;
import seedu.address.messages.UsageMessage;
import seedu.address.model.localcourse.LocalCourseAttribute;

public class LocalCourseUpdateCommandParserTest {

    private static final String commandActionWord = LocalCourseCommand.COMMAND_WORD
            + LocalCourseUpdateCommand.ACTION_WORD;

    private LocalCourseUpdateCommandParser parser = new LocalCourseUpdateCommandParser();

    @Test
    public void parse_unnecessaryWhiteSpace_success() {


        // add unnecessary whitespace
        assertParseSuccess(parser,
                UNNECESSARY_WHITESPACE
                        + LocalCourseCommand.COMMAND_WORD
                        + UNNECESSARY_WHITESPACE
                        + LocalCourseUpdateCommand.ACTION_WORD
                        + UNNECESSARY_WHITESPACE
                        + getSquareBracketWrappedArgument(CS2042S.getLocalCode().toString())
                        + UNNECESSARY_WHITESPACE
                        + getSquareBracketWrappedArgument(LocalCourseAttribute.LOCALUNIT.toString())
                        + UNNECESSARY_WHITESPACE
                        + getSquareBracketWrappedArgument("10.0"),
                new LocalCourseUpdateCommand(
                        CS2042S.getLocalCode(),
                        LocalCourseAttribute.LOCALUNIT,
                        "10.0"));
    }

    @Test
    public void parse_argumentNotClosedOrEmpty_failure() {
        // empty argument
        assertParseFailure(parser, commandActionWord
                + getSquareBracketWrappedArgument(""), UsageMessage.LOCALCOURSE_UPDATE.getValueWithEmptyArgs());
    }

    @Test
    public void parse_argumentMissing_failure() {
        String expectedMessage = UsageMessage.LOCALCOURSE_UPDATE.toString();

        // missing localCode argument
        assertParseFailure(
                parser,
                commandActionWord
                        + getSquareBracketWrappedArgument(LocalCourseAttribute.LOCALUNIT.toString())
                        + getSquareBracketWrappedArgument("10.0"),
                expectedMessage);

        // missing attribute argument
        assertParseFailure(
                parser,
                commandActionWord
                        + getSquareBracketWrappedArgument(CS2042S.getLocalCode().toString())
                        + getSquareBracketWrappedArgument("10.0"),
                expectedMessage);

        // missing updateValue argument
        assertParseFailure(
                parser,
                commandActionWord
                        + getSquareBracketWrappedArgument(CS2042S.getLocalCode().toString())
                        + getSquareBracketWrappedArgument(LocalCourseAttribute.LOCALUNIT.toString()),
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

        // invalid localCode
        assertParseFailure(
                parser,
                commandActionWord
                        + getSquareBracketWrappedArgument(INVALID_PARTNER_COURSE_CODE)
                        + getSquareBracketWrappedArgument(LocalCourseAttribute.LOCALUNIT.toString())
                        + getSquareBracketWrappedArgument("10.0"),
                ConstraintMessage.LOCALCOURSE_CODE.toString());

        // invalid localAttribute
        assertParseFailure(
                parser,
                commandActionWord
                        + getSquareBracketWrappedArgument(CS2042S.getLocalCode().toString())
                        + getSquareBracketWrappedArgument(INVALID_LOCAL_COURSE_ATTRIBUTE)
                        + getSquareBracketWrappedArgument("10.0"),
                ConstraintMessage.LOCALCOURSE_ATTRIBUTE_UPDATE.toString());

        // invalid updatedValue for partnerCode
        assertParseFailure(
                parser,
                commandActionWord
                        + getSquareBracketWrappedArgument(CS2042S.getLocalCode().toString())
                        + getSquareBracketWrappedArgument(LocalCourseAttribute.LOCALCODE.toString())
                        + getSquareBracketWrappedArgument(INVALID_LOCAL_COURSE_CODE),
                ConstraintMessage.LOCALCOURSE_CODE.toString());

        // invalid updatedValue for unit
        assertParseFailure(
                parser,
                commandActionWord
                        + getSquareBracketWrappedArgument(CS2042S.getLocalCode().toString())
                        + getSquareBracketWrappedArgument(LocalCourseAttribute.LOCALUNIT.toString())
                        + getSquareBracketWrappedArgument(INVALID_LOCAL_COURSE_UNIT.toString()),
                ConstraintMessage.LOCALCOURSE_UNIT.toString());
    }
}

