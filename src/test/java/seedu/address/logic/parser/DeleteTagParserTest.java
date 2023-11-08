package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PERSON_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TagBuilder;

public class DeleteTagParserTest {
    private DeleteTagCommandParser parser = new DeleteTagCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Set<Tag> expectedTagSet = new TagBuilder().withTag(VALID_TAG_HUSBAND).inSet();

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + PERSON_ID_DESC + TAG_DESC_HUSBAND,
                new DeleteTagCommand(Integer.parseInt(VALID_PERSON_ID), expectedTagSet));
    }

    @Test
    public void parse_multipleTags_success() {
        Set<Tag> expectedTagSet = new TagBuilder().withTag(VALID_TAG_HUSBAND).inSet();
        expectedTagSet.add(new TagBuilder().withTag(VALID_TAG_FRIEND).build());

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + PERSON_ID_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new DeleteTagCommand(Integer.parseInt(VALID_PERSON_ID), expectedTagSet));
    }

    @Test
    public void parse_duplicateTags_success() {
        Set<Tag> expectedTagSet = new TagBuilder().withTag(VALID_TAG_HUSBAND).inSet();

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + PERSON_ID_DESC + TAG_DESC_HUSBAND + TAG_DESC_HUSBAND,
                new DeleteTagCommand(Integer.parseInt(VALID_PERSON_ID), expectedTagSet));
    }

    @Test
    public void parse_invlaidTag_success() {
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;

        assertParseFailure(parser, PREAMBLE_WHITESPACE + PERSON_ID_DESC + INVALID_TAG_DESC,
                expectedMessage);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE);

        // missing person id prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TAG_DESC_HUSBAND,
                expectedMessage);

        // missing tag prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + PERSON_ID_DESC,
                expectedMessage);
    }
}
