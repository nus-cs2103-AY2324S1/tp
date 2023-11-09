package seedu.address.logic.parser.personparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_SORT_ATTRIBUTE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.personcommands.SortPatientCommand;

public class SortPatientCommandParserTest {

    private static final String INVALID_PREAMBLE_1 = " ascending";
    private static final String INVALID_ATTRIBUTE_DESC = " by= friend";
    private static final String VALID_PREAMBLE_1 = " asc";
    private static final String VALID_ATTRIBUTE_DESC = " by= name";
    private static final String VALID_ATTRIBUTE_1 = "name";
    private SortPatientCommandParser parser = new SortPatientCommandParser();
    @Test
    public void parse_invalidPreamble_failure() {
        assertParseFailure(parser, INVALID_PREAMBLE_1 + VALID_ATTRIBUTE_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortPatientCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_invalidAttribute_failure() {
        assertParseFailure(parser, VALID_PREAMBLE_1 + INVALID_ATTRIBUTE_DESC,
                String.format(MESSAGE_INVALID_SORT_ATTRIBUTE, SortPatientCommandParser.printAttributes()));
    }

    @Test
    public void parse_validPreambleAndAttribute_success() {
        SortPatientCommand expectedSortPatientCommand = new SortPatientCommand(true, VALID_ATTRIBUTE_1);
        assertParseSuccess(parser, VALID_PREAMBLE_1 + VALID_ATTRIBUTE_DESC, expectedSortPatientCommand);
    }
}
