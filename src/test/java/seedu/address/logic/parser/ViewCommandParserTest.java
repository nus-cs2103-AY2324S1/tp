package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.viewcommands.ViewCommand;
import seedu.address.logic.commands.viewcommands.ViewCompanyCommand;
import seedu.address.logic.commands.viewcommands.ViewPersonCommand;

public class ViewCommandParserTest {

    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "d 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_notEnoughArgs_throwsParseException() {
        assertParseFailure(parser, "p",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsCompany_throwsParseException() {
        assertParseFailure(parser, "c a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCompanyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsPerson_throwsParseException() {
        assertParseFailure(parser, "p a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsDeleteCompanyCommand() {
        assertParseSuccess(parser, "c 1", new ViewCompanyCommand(INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY));
    }

    @Test
    public void parse_validArgs_returnsDeletePersonCommand() {
        assertParseSuccess(parser, "p 1", new ViewPersonCommand(INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY));
    }
}
