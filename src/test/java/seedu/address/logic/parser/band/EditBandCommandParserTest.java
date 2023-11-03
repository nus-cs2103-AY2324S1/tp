package seedu.address.logic.parser.band;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.GENRE_DESC_POP;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENRE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_POPSTARS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENRE_POP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_POPSTARS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MUSICIAN;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_MUSICIAN;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.band.EditBandCommand;
import seedu.address.logic.commands.band.EditBandCommand.EditBandDescriptor;
import seedu.address.logic.commands.musician.EditCommand;
import seedu.address.model.band.BandName;
import seedu.address.model.tag.Genre;
import seedu.address.testutil.EditBandDescriptorBuilder;

public class EditBandCommandParserTest {

    private static final String GENRE_EMPTY = " " + PREFIX_GENRE;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBandCommand.MESSAGE_USAGE);

    private EditBandCommandParser parser = new EditBandCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_POPSTARS, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_POPSTARS, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_POPSTARS, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 k/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, BandName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_GENRE_DESC, Genre.MESSAGE_CONSTRAINTS); // invalid genre

        // invalid name followed by valid genre
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + GENRE_DESC_POP, BandName.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Band} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + GENRE_DESC_POP + GENRE_EMPTY, Genre.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_GENRE_DESC,
                BandName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_MUSICIAN;
        String userInput = targetIndex.getOneBased() + NAME_DESC_POPSTARS + GENRE_DESC_POP;

        EditBandDescriptor descriptor = new EditBandDescriptorBuilder().withName(VALID_NAME_POPSTARS)
                .withGenres(VALID_GENRE_POP).build();
        EditBandCommand expectedCommand = new EditBandCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_MUSICIAN;
        String userInput = targetIndex.getOneBased() + NAME_DESC_POPSTARS;
        EditBandCommand.EditBandDescriptor descriptor =
                new EditBandDescriptorBuilder().withName(VALID_NAME_POPSTARS).build();
        EditBandCommand expectedCommand = new EditBandCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // genres
        userInput = targetIndex.getOneBased() + GENRE_DESC_POP;
        descriptor = new EditBandDescriptorBuilder().withGenres(VALID_GENRE_POP).build();
        expectedCommand = new EditBandCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_MUSICIAN;
        String userInput = targetIndex.getOneBased() + GENRE_EMPTY;

        EditBandDescriptor descriptor = new EditBandDescriptorBuilder().withGenres().build();
        EditBandCommand expectedCommand = new EditBandCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

