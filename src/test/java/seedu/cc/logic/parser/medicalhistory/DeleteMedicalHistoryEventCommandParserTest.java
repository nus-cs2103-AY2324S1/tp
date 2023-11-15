package seedu.cc.logic.parser.medicalhistory;

import static seedu.cc.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.logic.commands.CommandTestUtil.PATIENT_INDEX_DESC;
import static seedu.cc.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.cc.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_MEDHISTEVENT;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import org.junit.jupiter.api.Test;

import seedu.cc.logic.commands.medhisteventcommands.DeleteMedicalHistoryEventCommand;

class DeleteMedicalHistoryEventCommandParserTest {

    private final DeleteMedicalHistoryEventCommandParser parser = new DeleteMedicalHistoryEventCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1" + PATIENT_INDEX_DESC,
                new DeleteMedicalHistoryEventCommand(INDEX_FIRST_MEDHISTEVENT, INDEX_FIRST_PATIENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteMedicalHistoryEventCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "a" + PATIENT_INDEX_DESC, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteMedicalHistoryEventCommand.MESSAGE_USAGE));
    }
}
