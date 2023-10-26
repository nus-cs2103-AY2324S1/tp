package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ReadCommand;
import seedu.address.logic.parser.exceptions.ParseException;



class ReadCommandParserTest {

    private ReadCommandParser parser = new ReadCommandParser();

    @Test
    public void parse_validArgs_returnsReadCommand() {
        assertParseSuccess(parser, "1 /a", new ReadCommand(INDEX_FIRST_PERSON, "address"));
    }

    @Test
    public void parse_missingField_throwsParseException() {
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReadCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidField_throwsParseException() {
        assertParseFailure(parser, "1 /invalid",
                Messages.MESSAGE_INVALID_FIELD_TO_READ);
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReadCommand.MESSAGE_USAGE));
    }

    @Test
    public void fieldNameToString_validFieldIdentifiers_returnsFieldNames() throws ParseException {
        assertEquals(parser.fieldNameToString("p"), "phone");
        assertEquals(parser.fieldNameToString("a"), "address");
        assertEquals(parser.fieldNameToString("e"), "email");
        assertEquals(parser.fieldNameToString("b"), "bank account");
        assertEquals(parser.fieldNameToString("l"), "annual leave");
        assertEquals(parser.fieldNameToString("j"), "join date");
        assertEquals(parser.fieldNameToString("s"), "salary");
    }
}
