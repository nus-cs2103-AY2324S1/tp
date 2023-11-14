package seedu.address.logic.parser.mapping;

import static seedu.address.logic.commands.CommandTestUtil.UNNECESSARY_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TestUtil.getSquareBracketWrappedArgument;
import static seedu.address.testutil.TypicalObjects.INVALID_MAPPING_ATTRIBUTE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.mapping.MappingCommand;
import seedu.address.logic.commands.mapping.MappingSortCommand;
import seedu.address.messages.ConstraintMessage;
import seedu.address.messages.UsageMessage;
import seedu.address.model.mapping.MappingAttribute;
import seedu.address.model.mapping.MappingComparatorByAttribute;
import seedu.address.testutil.MappingUtil;

public class MappingSortCommandParserTest {

    private static final String commandActionWord =
            MappingCommand.COMMAND_WORD
                    + " "
                    + MappingSortCommand.ACTION_WORD;

    private MappingSortCommandParser parser = new MappingSortCommandParser();

    @Test
    public void parse_unnecessaryWhiteSpace_success() {
        // compare by localcode
        MappingComparatorByAttribute expectedComparatorByLocalCode =
                new MappingComparatorByAttribute(MappingAttribute.LOCALCODE);
        MappingComparatorByAttribute expectedComparatorByPartnerCode =
                new MappingComparatorByAttribute(MappingAttribute.PARTNERCODE);

        // add unnecessary whitespace
        assertParseSuccess(parser, UNNECESSARY_WHITESPACE.repeat(3)
                        + MappingCommand.COMMAND_WORD
                        + UNNECESSARY_WHITESPACE.repeat(3)
                        + MappingSortCommand.ACTION_WORD
                        + UNNECESSARY_WHITESPACE.repeat(3)
                        + getSquareBracketWrappedArgument("localcode")
                        + UNNECESSARY_WHITESPACE.repeat(3),
                new MappingSortCommand(expectedComparatorByLocalCode));

        assertParseSuccess(parser, UNNECESSARY_WHITESPACE.repeat(3)
                        + MappingCommand.COMMAND_WORD
                        + UNNECESSARY_WHITESPACE.repeat(3)
                        + MappingSortCommand.ACTION_WORD
                        + UNNECESSARY_WHITESPACE.repeat(3)
                        + getSquareBracketWrappedArgument("partnercode")
                        + UNNECESSARY_WHITESPACE.repeat(3),
                new MappingSortCommand(expectedComparatorByPartnerCode));
    }

    @Test
    public void parse_argumentNotClosedOrEmpty_failure() {
        String expectedMessage = UsageMessage.MAPPING_SORT.toString();
        // missing open bracket
        assertParseFailure(parser, commandActionWord
                + MappingUtil.getMappingArgumentsForSortCommand(
                        new MappingComparatorByAttribute(MappingAttribute.LOCALCODE))
                .substring(1), expectedMessage);

        String expectedMessageWithEmptyArg = UsageMessage.MAPPING_SORT.getValueWithEmptyArgs();
        // empty argument
        assertParseFailure(parser, commandActionWord
                + getSquareBracketWrappedArgument(""), expectedMessageWithEmptyArg);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid attribute
        assertParseFailure(parser, commandActionWord
                        + getSquareBracketWrappedArgument(INVALID_MAPPING_ATTRIBUTE),
                ConstraintMessage.MAPPING_ATTRIBUTE_SORT.toString());
    }

    @Test
    public void parse_validArguments_success() {
        assertParseSuccess(parser, commandActionWord
                        + " " + getSquareBracketWrappedArgument(MappingAttribute.LOCALCODE.toString()),
                new MappingSortCommand(new MappingComparatorByAttribute(MappingAttribute.LOCALCODE)));

        assertParseSuccess(parser, commandActionWord
                        + " " + getSquareBracketWrappedArgument(MappingAttribute.LOCALNAME.toString()),
                new MappingSortCommand(new MappingComparatorByAttribute(MappingAttribute.LOCALNAME)));

        assertParseSuccess(parser, commandActionWord
                        + " " + getSquareBracketWrappedArgument(MappingAttribute.PARTNERCODE.toString()),
                new MappingSortCommand(new MappingComparatorByAttribute(MappingAttribute.PARTNERCODE)));

        assertParseSuccess(parser, commandActionWord
                        + " " + getSquareBracketWrappedArgument(MappingAttribute.PARTNERNAME.toString()),
                new MappingSortCommand(new MappingComparatorByAttribute(MappingAttribute.PARTNERNAME)));

        assertParseSuccess(parser, commandActionWord
                        + " " + getSquareBracketWrappedArgument(MappingAttribute.UNIVERSITY.toString()),
                new MappingSortCommand(new MappingComparatorByAttribute(MappingAttribute.UNIVERSITY)));

        assertParseSuccess(parser, commandActionWord
                        + " " + getSquareBracketWrappedArgument(MappingAttribute.INFORMATION.toString()),
                new MappingSortCommand(new MappingComparatorByAttribute(MappingAttribute.INFORMATION)));
    }
}
