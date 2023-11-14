package seedu.address.logic.parser.mapping;


import static seedu.address.logic.commands.CommandTestUtil.UNNECESSARY_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TestUtil.getSquareBracketWrappedArgument;
import static seedu.address.testutil.TypicalObjects.CS2040S_TO_NTU_S0402SC;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_PARTNER_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.INVALID_LOCAL_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_DESCRIPTION;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_UNIT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.mapping.MappingAddCommand;
import seedu.address.logic.commands.mapping.MappingCommand;
import seedu.address.messages.ConstraintMessage;
import seedu.address.messages.UsageMessage;
import seedu.address.model.mapping.Mapping;
import seedu.address.testutil.MappingBuilder;
import seedu.address.testutil.MappingUtil;

public class MappingAddCommandParserTest {
    private static final String commandActionWord = MappingCommand.COMMAND_WORD + " "
            + MappingAddCommand.ACTION_WORD;
    private MappingAddCommandParser parser = new MappingAddCommandParser();

    @Test
    public void parse_unnecessaryWhiteSpace_success() {
        Mapping expectedMapping = new MappingBuilder(CS2040S_TO_NTU_S0402SC).build();

        // add unnecessary whitespace
        assertParseSuccess(parser, UNNECESSARY_WHITESPACE
                        + MappingCommand.COMMAND_WORD
                        + UNNECESSARY_WHITESPACE.repeat(3)
                        + MappingAddCommand.ACTION_WORD
                        + UNNECESSARY_WHITESPACE.repeat(3)
                        + getSquareBracketWrappedArgument(expectedMapping.getLocalCode().toString())
                        + UNNECESSARY_WHITESPACE.repeat(3)
                        + getSquareBracketWrappedArgument(expectedMapping.getUniversityName().toString())
                        + UNNECESSARY_WHITESPACE.repeat(3)
                        + getSquareBracketWrappedArgument(expectedMapping.getPartnerCode().toString())
                        + UNNECESSARY_WHITESPACE.repeat(3)
                        + getSquareBracketWrappedArgument(expectedMapping.getMappingMiscInformation()
                        .toString()),
                new MappingAddCommand(expectedMapping));
    }

    @Test
    void parse_argumentNotClosedOrEmpty_failure() {
        String expectedMessage = UsageMessage.MAPPING_ADD.toString();

        // missing open bracket
        assertParseFailure(parser, commandActionWord
                + MappingUtil.getMappingArgumentsForAddCommand(CS2040S_TO_NTU_S0402SC)
                .substring(1), expectedMessage);

        String expectedMessageForEmptyArg = UsageMessage.MAPPING_ADD.getValueWithEmptyArgs();
        // empty argument
        assertParseFailure(parser, commandActionWord
                + getSquareBracketWrappedArgument(TYPICAL_LOCAL_COURSE_CODE) + " "
                + getSquareBracketWrappedArgument("") + " "
                + getSquareBracketWrappedArgument(EDGE_CASE_VALID_PARTNER_COURSE_CODE) + " "
                + getSquareBracketWrappedArgument("NIL"), expectedMessageForEmptyArg);
    }


    @Test
    public void parse_allArgumentMissing_failure() {
        String expectedMessage = UsageMessage.MAPPING_ADD.toString();

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
    }
}

