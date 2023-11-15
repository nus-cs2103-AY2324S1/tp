package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPriorities.DEFAULT_PRIORITY;
import static seedu.address.testutil.TypicalPriorities.HIGH_PRIORITY;
import static seedu.address.testutil.TypicalPriorities.LOW_PRIORITY;
import static seedu.address.testutil.TypicalPriorities.MEDIUM_PRIORITY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandWarnings;
import seedu.address.logic.commands.SetSellerPriorityCommand;
import seedu.address.model.displayable.Priority;


public class SetSellerPriorityCommandParserTest {
    private final SetSellerPriorityCommandParser parser = new SetSellerPriorityCommandParser();

    @Test
    public void parse_invalidArg_exceptionThrown() {
        assertParseFailure(parser, "1high",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetSellerPriorityCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "high",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetSellerPriorityCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "high 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetSellerPriorityCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetSellerPriorityCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetSellerPriorityCommand.MESSAGE_USAGE));
        assertThrows(NullPointerException.class, () -> parser.parse(null, new CommandWarnings()));
    }

    @Test
    public void parse_zeroInput_exceptionThrown() {
        assertParseFailure(parser, "0 nil",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetSellerPriorityCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_negativeInput_exceptionThrown() {
        assertParseFailure(parser, "-1 nil",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetSellerPriorityCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonZeroUnsignedInteger_success() {
        assertParseSuccess(parser, "1 nil",
                new SetSellerPriorityCommand(Index.fromZeroBased(0), DEFAULT_PRIORITY));

        assertParseSuccess(parser, "2 nil",
                new SetSellerPriorityCommand(Index.fromZeroBased(1), DEFAULT_PRIORITY));

        assertParseSuccess(parser, "100 nil",
                new SetSellerPriorityCommand(Index.fromZeroBased(99), DEFAULT_PRIORITY));
    }

    @Test
    public void parse_invalidPriority_exceptionThrown() {
        assertParseFailure(parser, "1 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetSellerPriorityCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetSellerPriorityCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetSellerPriorityCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1  ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetSellerPriorityCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1         ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetSellerPriorityCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 jigh",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetSellerPriorityCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 aedfaf",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetSellerPriorityCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_unrecommendedPriority_warningGiven() {
        CommandWarnings warnings = new CommandWarnings();
        warnings.addWarning(Priority.MESSAGE_RECOMMENDATIONS);

        assertParseSuccess(parser, "1987 hkrhjfek",
                new SetSellerPriorityCommand(Index.fromZeroBased(1986), HIGH_PRIORITY, warnings));
        assertParseSuccess(parser, "1 highlow",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, HIGH_PRIORITY, warnings));
        assertParseSuccess(parser, "1 hlow",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, HIGH_PRIORITY, warnings));
        assertParseSuccess(parser, "1 highh",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, HIGH_PRIORITY, warnings));
        assertParseSuccess(parser, "1 hhhhh",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, HIGH_PRIORITY, warnings));
        assertParseSuccess(parser, "1 hmln",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, HIGH_PRIORITY, warnings));
        assertParseSuccess(parser, "1 mil",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, MEDIUM_PRIORITY, warnings));
        assertParseSuccess(parser, "1 mfdifdsjifads",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, MEDIUM_PRIORITY, warnings));
        assertParseSuccess(parser, "1 mediums",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, MEDIUM_PRIORITY, warnings));
        assertParseSuccess(parser, "1 mhln",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, MEDIUM_PRIORITY, warnings));
        assertParseSuccess(parser, "1 lpw",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, LOW_PRIORITY, warnings));
        assertParseSuccess(parser, "1 lpwfgadadfewf",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, LOW_PRIORITY, warnings));
        assertParseSuccess(parser, "1 lhmn",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, LOW_PRIORITY, warnings));
        assertParseSuccess(parser, "1 no",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, DEFAULT_PRIORITY, warnings));
        assertParseSuccess(parser, "1 nedium",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, DEFAULT_PRIORITY, warnings));
        assertParseSuccess(parser, "1 nhml",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, DEFAULT_PRIORITY, warnings));

        // PRIORITY PARSING ISSUES:
        // 1. inputs like 'hhh' or 'mmm' or 'lll' are supposed to give warnings, but are
        // accepted just fine --> but not allowed to change since we didn't advertise this in the UG,
        // so write down in DG under planned enhancements instead.
        // 2. Extra arguments at the end are supposed to give warnings too, like '1 high low', but
        // again not allowed to change so write in DG.
        // 3. Typos like 'hgih' or 'meduim' are also supposed to give typo warnings but they don't, so
        // write this in DG too.
    }

    @Test
    public void parse_validPriority_success() {
        assertParseSuccess(parser, "1 high",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, HIGH_PRIORITY));
        assertParseSuccess(parser, "1             high",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, HIGH_PRIORITY));
        assertParseSuccess(parser, "1 HIGH",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, HIGH_PRIORITY));
        assertParseSuccess(parser, "1 h",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, HIGH_PRIORITY));
        assertParseSuccess(parser, "1 H",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, HIGH_PRIORITY));
        assertParseSuccess(parser, "1 medium",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, MEDIUM_PRIORITY));
        assertParseSuccess(parser, "1 MEDIUM",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, MEDIUM_PRIORITY));
        assertParseSuccess(parser, "1 med",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, MEDIUM_PRIORITY));
        assertParseSuccess(parser, "1 M",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, MEDIUM_PRIORITY));
        assertParseSuccess(parser, "1 m",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, MEDIUM_PRIORITY));
        assertParseSuccess(parser, "1 low",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, LOW_PRIORITY));
        assertParseSuccess(parser, "1 LOW",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, LOW_PRIORITY));
        assertParseSuccess(parser, "1 L",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, LOW_PRIORITY));
        assertParseSuccess(parser, "1 l",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, LOW_PRIORITY));
        assertParseSuccess(parser, "1 nil",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, DEFAULT_PRIORITY));
        assertParseSuccess(parser, "1 NIL",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, DEFAULT_PRIORITY));
        assertParseSuccess(parser, "1 N",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, DEFAULT_PRIORITY));
        assertParseSuccess(parser, "1 n",
                new SetSellerPriorityCommand(INDEX_FIRST_PERSON, DEFAULT_PRIORITY));
    }
}
