package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteCommand.DeletePersonDescriptor;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.testutil.PersonBuilder;

/**
 * As we are only doing white-box testing, our test cases do not cover path
 * variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take
 * the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    private DeletePersonDescriptor defaultDescriptor = new DeletePersonDescriptor();
        
    private Name defaultName = new Name(PersonBuilder.DEFAULT_NAME);

    private Nric defaultNric = new Nric(PersonBuilder.DEFAULT_NRIC);

    /*
     * @Test
     * public void parse_validArgs_returnsDeleteCommand() {
     * assertParseSuccess(parser, "1", new DeleteCommand(INDEX_FIRST_PERSON));
     * }
     */

    @Test
    public void test_parse_validName() {
        String userString = " n/" + PersonBuilder.DEFAULT_NAME;
        DeleteCommand deleteCommand = new DeleteCommand(null, defaultName, defaultDescriptor);
        assertParseSuccess(parser, userString, deleteCommand);
    }

    @Test
    public void test_parse_validNric() {
        String userString = " id/" + PersonBuilder.DEFAULT_NRIC;
        DeleteCommand deleteCommand = new DeleteCommand(defaultNric, null, defaultDescriptor);
        assertParseSuccess(parser, userString, deleteCommand);
    }

    @Test
    public void test_parse_descriptor() {
        String userString = " id/" + PersonBuilder.DEFAULT_NRIC + " ap/";
        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();
        descriptor.setAppointment();
        DeleteCommand deleteCommand = new DeleteCommand(defaultNric, null, descriptor);
        assertParseSuccess(parser, userString, deleteCommand);
    }

    @Test
    public void test_parse_missingNameAndNric() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
