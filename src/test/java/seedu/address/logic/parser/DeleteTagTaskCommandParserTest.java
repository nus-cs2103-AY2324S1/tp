package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FINANCE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LOCATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FINANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LOCATION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.DeleteTagTaskCommand;
import seedu.address.model.tag.Tag;

public class DeleteTagTaskCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
            DeleteTagTaskCommand.MESSAGE_USAGE);

    private DeleteTagTaskCommandParser parser = new DeleteTagTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TAG_LOCATION, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TAG_DESC_LOCATION, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TAG_DESC_LOCATION, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid tag
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // valid value followed by invalid value
        assertParseFailure(parser, "1" + TAG_DESC_LOCATION + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // invalid value followed by valid value
        assertParseFailure(parser, "1" + INVALID_TAG_DESC + TAG_DESC_LOCATION, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_command_success() {
        Index targetIndex = INDEX_FIRST;
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(VALID_TAG_LOCATION));
        tags.add(new Tag(VALID_TAG_FINANCE));
        DeleteTagTaskCommand expectedCommand = new DeleteTagTaskCommand(targetIndex, tags);

        String userInput = targetIndex.getOneBased() + TAG_DESC_LOCATION + TAG_DESC_FINANCE;
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
