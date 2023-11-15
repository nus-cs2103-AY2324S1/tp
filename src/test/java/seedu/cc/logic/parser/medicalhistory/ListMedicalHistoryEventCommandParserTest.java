package seedu.cc.logic.parser.medicalhistory;

import static seedu.cc.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.cc.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import org.junit.jupiter.api.Test;

import seedu.cc.logic.commands.medhisteventcommands.ListMedicalHistoryEventCommand;

class ListMedicalHistoryEventCommandParserTest {
    private final ListMedicalHistoryEventCommandParser parser = new ListMedicalHistoryEventCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new ListMedicalHistoryEventCommand(
                INDEX_FIRST_PATIENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListMedicalHistoryEventCommand.MESSAGE_USAGE));
    }
}
