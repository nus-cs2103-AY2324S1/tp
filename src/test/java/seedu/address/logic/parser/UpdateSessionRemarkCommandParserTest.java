package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UpdateSessionRemarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.session.SessionNumber;
import seedu.address.model.session.SessionRemark;

public class UpdateSessionRemarkCommandParserTest {

    private UpdateSessionRemarkCommandParser parser = new UpdateSessionRemarkCommandParser();

    @Test
    public void parse_validArgs_returnsUpdateSessionRemarkCommand() throws ParseException {
        String args = " s/1 r/Valid Remarks";
        UpdateSessionRemarkCommand expectedCommand = new UpdateSessionRemarkCommand(
                new SessionNumber("1"), new SessionRemark("Valid Remarks"));
        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_missingSessionPrefix_throwsParseException() {
        String args = " r/Valid Remarks";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_missingRemarkPrefix_throwsParseException() {
        String args = " s/1";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }
}
