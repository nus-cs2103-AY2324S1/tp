package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.OPTIONAL_TAG_G01;
import static seedu.address.logic.commands.CommandTestUtil.OPTIONAL_TAG_T09;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.predicate.ContainsTagPredicate;
import seedu.address.model.tag.Tag;

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
    public void parse_validIndexArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_validAllOnlyArgs_returnsDeleteCommand() {
        Optional<Tag> tag = Optional.empty();
        assertParseSuccess(parser, "all", new DeleteCommand(tag, new ContainsTagPredicate(tag)));

        // with trailing whitespace
        assertParseSuccess(parser, "  all   ", new DeleteCommand(tag, new ContainsTagPredicate(tag)));
    }

    @Test
    public void parse_validAllWithTagArgs_returnsDeleteCommand() {
        Optional<Tag> tag2 = OPTIONAL_TAG_T09;
        Optional<Tag> tag3 = OPTIONAL_TAG_G01;
        assertParseSuccess(parser, "all tg/T09", new DeleteCommand(tag2, new ContainsTagPredicate(tag3)));

        // with trailing whitespace
        assertParseSuccess(parser, "  all   tg/T09  ", new DeleteCommand(tag2, new ContainsTagPredicate(tag3)));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndexArgs_throwsParseException() {
        // non-positive index
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidNonIndexNonAllArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidAllOnlyExtraArgs_throwsParseException() {
        assertParseFailure(parser, "all 123", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "allocated", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidAllWithTagExtraArgs_throwsParseException() {
        assertParseFailure(parser, "all 123 tg/1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "allocated tg/1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));
    }
}
