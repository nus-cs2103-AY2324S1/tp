package seedu.address.logic.parser.band;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.BAND_INDEX_DESC_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BAND_INDEX_DESC_ZERO;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MUSICIAN_INDEX_DESC_NEGATIVE;
import static seedu.address.logic.commands.CommandTestUtil.MUSICIAN_INDEX_DESC_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.band.RemoveMusicianFromBandCommand;
public class RemoveMusicianFromBandCommandParserTest {
    private RemoveMusicianFromBandCommandParser parser = new RemoveMusicianFromBandCommandParser();

    @Test
    public void parse_addingOneMusician_success() {
        Index expectedMusicianIndex = Index.fromOneBased(1);
        Index expectedBandIndex = Index.fromOneBased(1);

        // with whitespace preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MUSICIAN_INDEX_DESC_FIRST + BAND_INDEX_DESC_FIRST,
                new RemoveMusicianFromBandCommand(expectedBandIndex, expectedMusicianIndex));
    }

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        // missing band index prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + MUSICIAN_INDEX_DESC_FIRST,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveMusicianFromBandCommand.MESSAGE_USAGE));
        // missing musician index prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + BAND_INDEX_DESC_FIRST,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveMusicianFromBandCommand.MESSAGE_USAGE));
        // missing all prefixes
        assertParseFailure(parser, PREAMBLE_WHITESPACE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveMusicianFromBandCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndices_failure() {
        // negative index
        assertParseFailure(parser, INVALID_MUSICIAN_INDEX_DESC_NEGATIVE
                + BAND_INDEX_DESC_FIRST, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveMusicianFromBandCommand.MESSAGE_USAGE));
        // zero index
        assertParseFailure(parser, MUSICIAN_INDEX_DESC_FIRST
                + INVALID_BAND_INDEX_DESC_ZERO, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveMusicianFromBandCommand.MESSAGE_USAGE));
    }
}
