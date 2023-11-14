package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.deletecommands.DeleteCommand;
import seedu.address.logic.commands.deletecommands.DeleteCompanyCommand;
import seedu.address.logic.commands.deletecommands.DeleteInternshipCommand;
import seedu.address.logic.commands.deletecommands.DeletePersonCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "d 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_notEnoughArgs_throwsParseException() {
        assertParseFailure(parser, "p",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "c",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "i",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInternshipCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "i 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInternshipCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsCompany_throwsParseException() {
        assertParseFailure(parser, "c a",
                MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        assertParseFailure(parser, "c -1",
                MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void parse_invalidArgsPerson_throwsParseException() {
        assertParseFailure(parser, "p a",
                MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertParseFailure(parser, "p -1",
                MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void parse_invalidArgsInternship_throwsParseException() {
        assertParseFailure(parser, "i c/-1 i/1",
                MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        assertParseFailure(parser, "i c/1 i/-1",
                MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void parse_invalidInternshipCompanyPrefix_throwsParseException() {
        assertParseFailure(parser, "i 1 1 i/1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInternshipCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidInternshipPrefix_throwsParseException() {
        assertParseFailure(parser, "i 1 c/1 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInternshipCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsDeleteCompanyCommand() {
        assertParseSuccess(parser, "c 1",
                new DeleteCompanyCommand(INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY));
    }

    @Test
    public void parse_validArgs_returnsDeletePersonCommand() {
        assertParseSuccess(parser, "p 1",
                new DeletePersonCommand(INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY));
    }

    @Test
    public void parse_validArgsInternship_returnsDeleteInternshipCommand() {
        assertParseSuccess(parser, "i c/1 i/1",
                new DeleteInternshipCommand(
                        INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY, Index.fromOneBased(1)));
    }
}
