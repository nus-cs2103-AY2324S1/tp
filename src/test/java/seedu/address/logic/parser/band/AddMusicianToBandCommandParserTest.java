package seedu.address.logic.parser.band;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.BAND_INDEX_DESC_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.BAND_INDEX_DESC_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BAND_INDEX_DESC_ZERO;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MUSICIAN_INDEX_DESC_NEGATIVE;
import static seedu.address.logic.commands.CommandTestUtil.MUSICIAN_INDEX_DESC_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.MUSICIAN_INDEX_DESC_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.band.AddMusicianToBandCommand;
import seedu.address.logic.parser.ParserUtil;

class AddMusicianToBandCommandParserTest {

    private AddMusicianToBandCommandParser parser = new AddMusicianToBandCommandParser();

    @Test
    public void parse_addingOneMusician_success() {
        List<Index> expectedMusicianIndices = List.of(Index.fromOneBased(1));
        Index expectedBandIndex = Index.fromOneBased(1);

        // with whitespace preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MUSICIAN_INDEX_DESC_FIRST + BAND_INDEX_DESC_FIRST,
                new AddMusicianToBandCommand(expectedBandIndex, expectedMusicianIndices));
    }

    @Test
    public void parse_addingMultipleMusicians_success() {
        List<Index> expectedMusicianIndices = List.of(Index.fromOneBased(1),
                Index.fromOneBased(2));
        Index expectedBandIndex = Index.fromOneBased(1);

        // without whitespace preamble
        assertParseSuccess(parser, BAND_INDEX_DESC_FIRST + MUSICIAN_INDEX_DESC_FIRST
                + MUSICIAN_INDEX_DESC_SECOND,
                new AddMusicianToBandCommand(expectedBandIndex, expectedMusicianIndices));
    }

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        // missing band index prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + MUSICIAN_INDEX_DESC_FIRST,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMusicianToBandCommand.MESSAGE_USAGE));
        // missing musician index prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + BAND_INDEX_DESC_FIRST,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMusicianToBandCommand.MESSAGE_USAGE));
        // missing all prefixes
        assertParseFailure(parser, PREAMBLE_WHITESPACE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMusicianToBandCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleBandIndices_failure() {
        // multiple band indices (different indices)
        assertParseFailure(parser, PREAMBLE_WHITESPACE + BAND_INDEX_DESC_FIRST + BAND_INDEX_DESC_SECOND
                + MUSICIAN_INDEX_DESC_FIRST,
                AddMusicianToBandCommand.MESSAGE_MULTIPLE_BAND_INDICES);

        // multiple band indices (same index)
        assertParseFailure(parser, PREAMBLE_WHITESPACE + BAND_INDEX_DESC_FIRST + BAND_INDEX_DESC_FIRST
                + MUSICIAN_INDEX_DESC_FIRST,
                AddMusicianToBandCommand.MESSAGE_MULTIPLE_BAND_INDICES);
    }

    @Test
    public void parse_invalidIndices_failure() {
        // negative index
        assertParseFailure(parser, INVALID_MUSICIAN_INDEX_DESC_NEGATIVE
                + BAND_INDEX_DESC_FIRST, ParserUtil.MESSAGE_INVALID_INDEX);
        // zero index
        assertParseFailure(parser, MUSICIAN_INDEX_DESC_FIRST
                + INVALID_BAND_INDEX_DESC_ZERO, ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_repeatedMusicianIndices_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + BAND_INDEX_DESC_FIRST + MUSICIAN_INDEX_DESC_FIRST
                + MUSICIAN_INDEX_DESC_FIRST,
                AddMusicianToBandCommand.MESSAGE_MUSICIAN_INDEX_REPEATED);
    }
}
