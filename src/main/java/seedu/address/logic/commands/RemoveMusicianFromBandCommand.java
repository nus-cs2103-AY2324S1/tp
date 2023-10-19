package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BINDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MINDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.band.Band;
import seedu.address.model.musician.Musician;

/**
 * Removes a musician from a band.
 */
public class RemoveMusicianFromBandCommand extends Command {

    public static final String COMMAND_WORD = "removem";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a musician from a band. "
        + "Parameters: "
        + PREFIX_BINDEX + "INDEX OF BAND "
        + PREFIX_MINDEX + "INDEX OF MUSICIAN\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_BINDEX + "1 "
        + PREFIX_MINDEX + "1";

    public static final String MESSAGE_REMOVE_MUSICIAN_SUCCESS = "Removed musician from Band: %1$s";

    public static final String MESSAGE_MUSICIAN_NOT_IN_BAND = "Musician %1$s is not in the band";

    private final Index bandTargetIndex;
    private final Index musicianTargetIndex;

    /**
     * Creates an AddCommand to add the specified {@code Musician}
     */
    public RemoveMusicianFromBandCommand(Index bandTargetIndex, Index musicianTargetIndex) {
        requireNonNull(bandTargetIndex);
        requireNonNull(musicianTargetIndex);
        this.bandTargetIndex = bandTargetIndex;
        this.musicianTargetIndex = musicianTargetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Musician musicianToRemove = getMusician(model);
        if (!model.hasMusicianInBand(bandTargetIndex.getZeroBased(), musicianTargetIndex.getZeroBased())) {
            throw new CommandException(String.format(MESSAGE_MUSICIAN_NOT_IN_BAND, Messages.format(musicianToRemove)));
        }

        model.removeMusicianFromBand(bandTargetIndex.getZeroBased(), musicianTargetIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_REMOVE_MUSICIAN_SUCCESS, Messages.format(musicianToRemove)));
    }

    private Musician getMusician(Model model) throws CommandException {
        List<Band> lastShownBandList = model.getFilteredBandList();
        List<Musician> lastShownMusicianList = model.getFilteredMusicianList();

        if (bandTargetIndex.getZeroBased() >= lastShownBandList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BAND_DISPLAYED_INDEX);
        } else if (musicianTargetIndex.getZeroBased() >= lastShownMusicianList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MUSICIAN_DISPLAYED_INDEX);
        }

        return lastShownMusicianList.get(musicianTargetIndex.getZeroBased());
    }
}
