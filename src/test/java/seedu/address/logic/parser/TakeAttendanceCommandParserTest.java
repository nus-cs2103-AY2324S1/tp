package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TakeAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attendance.AttendancePresence;
import seedu.address.model.person.Name;
import seedu.address.model.session.SessionNumber;

public class TakeAttendanceCommandParserTest {

    private final TakeAttendanceCommandParser parser = new TakeAttendanceCommandParser();

    @Test
    public void parse_validArgs_returnsTakeAttendanceCommand() throws ParseException {
        String args = " s/1 n/Alice ap/present";
        TakeAttendanceCommand expectedCommand = new TakeAttendanceCommand(
                new SessionNumber("1"), new Name("Alice"), AttendancePresence.PRESENT);
        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_validArgsWithWhitespace_returnsTakeAttendanceCommand() throws ParseException {
        String args = " s/1 n/Alice ap/ present  ";
        TakeAttendanceCommand expectedCommand = new TakeAttendanceCommand(
                new SessionNumber("1"), new Name("Alice"), AttendancePresence.PRESENT);
        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() throws ParseException {
        String invalidAttendancePresence = "s/1 n/Alice ap/pr3sent";
        assertThrows(ParseException.class, () -> parser.parse(invalidAttendancePresence));

        String invalidName = "s/1 n/Alice#@# ap/ present ";
        assertThrows(ParseException.class, () -> parser.parse(invalidName));

        String invalidSessionNumber = "s/e n/Alice ap/present";
        assertThrows(ParseException.class, () -> parser.parse(invalidSessionNumber));
    }


    @Test
    public void parse_missingSessionPrefix_throwsParseException() {
        String args = " n/Alice p/present";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_missingNamePrefix_throwsParseException() {
        String args = " s/1 p/present";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_missingPresencePrefix_throwsParseException() {
        String args = " s/1 n/Alice";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }
}
