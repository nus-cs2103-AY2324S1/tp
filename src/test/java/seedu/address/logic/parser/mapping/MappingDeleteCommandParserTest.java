package seedu.address.logic.parser.mapping;


import static seedu.address.logic.commands.CommandTestUtil.UNNECESSARY_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TestUtil.getSquareBracketWrappedArgument;
import static seedu.address.testutil.TypicalObjects.CS2040S_TO_NTU_S0402SC;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_LOCAL_COURSE;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_LOCAL_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_PARTNER_COURSE;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_PARTNER_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.EDGE_CASE_VALID_UNIVERSITY_NAME;
import static seedu.address.testutil.TypicalObjects.INVALID_LOCAL_COURSE_CODE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.mapping.MappingCommand;
import seedu.address.logic.commands.mapping.MappingDeleteCommand;
import seedu.address.messages.ConstraintMessage;
import seedu.address.messages.UsageMessage;
import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.university.UniversityName;
import seedu.address.testutil.MappingUtil;


public class MappingDeleteCommandParserTest {

    private static final String commandActionWord = MappingCommand.COMMAND_WORD
            + " " + MappingDeleteCommand.ACTION_WORD + " ";

    private MappingDeleteCommandParser parser = new MappingDeleteCommandParser();

    @Test
    public void parse_unnecessaryWhiteSpace_success() {
        LocalCode expectedLocalCode = CS2040S_TO_NTU_S0402SC.getLocalCode();
        PartnerCode expectedPartnerCode = CS2040S_TO_NTU_S0402SC.getPartnerCode();
        UniversityName expectedUniversityName = CS2040S_TO_NTU_S0402SC.getUniversityName();

        // add unnecessary whitespace
        assertParseSuccess(parser, UNNECESSARY_WHITESPACE
                        + MappingCommand.COMMAND_WORD
                        + UNNECESSARY_WHITESPACE.repeat(4)
                        + MappingDeleteCommand.ACTION_WORD
                        + UNNECESSARY_WHITESPACE.repeat(3)
                        + getSquareBracketWrappedArgument(expectedLocalCode.toString())
                        + UNNECESSARY_WHITESPACE.repeat(3)
                        + getSquareBracketWrappedArgument(expectedUniversityName.toString())
                        + UNNECESSARY_WHITESPACE.repeat(3)
                        + getSquareBracketWrappedArgument(expectedPartnerCode.toString())
                        + UNNECESSARY_WHITESPACE.repeat(2),
                new MappingDeleteCommand(expectedLocalCode, expectedUniversityName, expectedPartnerCode));
    }

    @Test
    void parse_argumentNotClosedOrEmpty_failure() {
        String expectedMessage = UsageMessage.MAPPING_DELETE.toString();

        // missing open bracket
        assertParseFailure(parser, commandActionWord
                + MappingUtil.getMappingArgumentsForDeleteCommand(CS2040S_TO_NTU_S0402SC).substring(
                1), expectedMessage);

        String expectedMessageForEmptyArg = UsageMessage.MAPPING_DELETE.getValueWithEmptyArgs();
        // empty argument
        assertParseFailure(parser, commandActionWord
                + getSquareBracketWrappedArgument("") + " "
                + getSquareBracketWrappedArgument("") + " "
                + getSquareBracketWrappedArgument(""), expectedMessageForEmptyArg);
    }


    @Test
    public void parse_allArgumentMissing_failure() {
        String expectedMessage = UsageMessage.MAPPING_DELETE.toString();

        // all arguments missing
        assertParseFailure(parser, commandActionWord, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid localCode
        assertParseFailure(parser, commandActionWord
                        + getSquareBracketWrappedArgument(INVALID_LOCAL_COURSE_CODE) + " "
                        + getSquareBracketWrappedArgument(EDGE_CASE_VALID_UNIVERSITY_NAME) + " "
                        + getSquareBracketWrappedArgument(EDGE_CASE_VALID_PARTNER_COURSE_CODE),
                ConstraintMessage.LOCALCOURSE_CODE.toString());
    }

    @Test
    public void parse_edgeCaseValues_success() {
        assertParseSuccess(parser, commandActionWord
                        + getSquareBracketWrappedArgument(EDGE_CASE_VALID_LOCAL_COURSE_CODE) + " "
                        + getSquareBracketWrappedArgument(EDGE_CASE_VALID_UNIVERSITY_NAME) + " "
                        + getSquareBracketWrappedArgument(EDGE_CASE_VALID_PARTNER_COURSE_CODE),
                new MappingDeleteCommand(EDGE_CASE_VALID_LOCAL_COURSE.getLocalCode(),
                        EDGE_CASE_VALID_PARTNER_COURSE.getPartnerUniversity().getUniversityName(),
                        EDGE_CASE_VALID_PARTNER_COURSE.getPartnerCode()));
    }
}
