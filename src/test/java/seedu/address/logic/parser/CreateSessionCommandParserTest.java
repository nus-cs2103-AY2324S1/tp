package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_SESSION_NUMBER;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SESSION_STUDENTS;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SESSION_NUMBER_SESSION1;
import static seedu.address.logic.commands.CommandTestUtil.SESSION_STUDENTS_STUDENTS1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CreateSessionCommand;
import seedu.address.model.person.Name;
import seedu.address.model.session.SessionNumber;

public class CreateSessionCommandParserTest {
    private CreateSessionCommandParser parser = new CreateSessionCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                + SESSION_NUMBER_SESSION1 + SESSION_STUDENTS_STUDENTS1,
                new CreateSessionCommand(new SessionNumber("1"), new Name("Bob")));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid session number
        assertParseFailure(parser, INVALID_SESSION_NUMBER + SESSION_STUDENTS_STUDENTS1,
                SessionNumber.MESSAGE_CONSTRAINTS);

        //invalid names
        assertParseFailure(parser, SESSION_NUMBER_SESSION1 + INVALID_SESSION_STUDENTS,
                Name.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_SESSION_NUMBER + INVALID_SESSION_STUDENTS,
                SessionNumber.MESSAGE_CONSTRAINTS);
    }


}
