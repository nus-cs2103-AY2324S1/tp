package seedu.address.logic.parser.university;

import static seedu.address.logic.commands.CommandTestUtil.UNNECESSARY_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TestUtil.getSquareBracketWrappedArgument;
import static seedu.address.testutil.TypicalObjects.INVALID_UNIVERSITY;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.university.UniversityCommand;
import seedu.address.logic.commands.university.UniversitySortCommand;
import seedu.address.messages.UsageMessage;
import seedu.address.model.university.University;
import seedu.address.model.university.comparator.UniversityComparatorByUniversityName;
import seedu.address.testutil.UniversityUtil;


public class UniversitySortCommandParserTest {
    private static final String commandActionWord =
            UniversityCommand.COMMAND_WORD
                    + " "
                    + UniversitySortCommand.ACTION_WORD;

    private UniversitySortCommandParser parser = new UniversitySortCommandParser();

    //sort by "university"
    @Test
    public void parse_unnecessaryWhiteSpace_success() {
        Comparator<University> expectedComparator = new UniversityComparatorByUniversityName();

        // add unnecessary whitespace
        assertParseSuccess(parser, UNNECESSARY_WHITESPACE
                        + UniversityCommand.COMMAND_WORD
                        + UNNECESSARY_WHITESPACE
                        + UniversitySortCommand.ACTION_WORD
                        + UNNECESSARY_WHITESPACE
                        + getSquareBracketWrappedArgument("university")
                        + UNNECESSARY_WHITESPACE,
                new UniversitySortCommand(expectedComparator));
    }

    @Test
    public void parse_argumentNotClosedOrEmpty_failure() {
        String expectedMessage = UsageMessage.UNIVERSITY_SORT.toString();

        // missing open bracket
        assertParseFailure(parser, commandActionWord
                + UniversityUtil.getUniversityArgumentsForSortCommand(new UniversityComparatorByUniversityName())
                .substring(1), expectedMessage);

        String expectedMessageForEmptyArg = UsageMessage.UNIVERSITY_SORT.getValueWithEmptyArgs();

        // empty argument
        assertParseFailure(parser, commandActionWord
                + getSquareBracketWrappedArgument(""), expectedMessageForEmptyArg);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid attribute
        assertParseFailure(parser, commandActionWord
                        + getSquareBracketWrappedArgument(INVALID_UNIVERSITY),
                "There is only 1 available for university sort:\n"
                        + "university\n");
    }

    @Test
    public void parse_validArguments_success() {

        assertParseSuccess(parser, commandActionWord
                        + getSquareBracketWrappedArgument("university"),
                new UniversitySortCommand(new UniversityComparatorByUniversityName()));
    }
}
