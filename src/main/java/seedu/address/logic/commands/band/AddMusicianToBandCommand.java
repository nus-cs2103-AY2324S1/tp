package seedu.address.logic.commands.band;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BINDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MINDEX;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.band.Band;
import seedu.address.model.band.BandNameContainsKeywordsPredicate;
import seedu.address.model.musician.Musician;

/**
 * Adds a musician to a band.
 */
public class AddMusicianToBandCommand extends Command {
    public static final String COMMAND_WORD = "addm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds one or more musicians to a band. "
            + "Parameters: "
            + PREFIX_BINDEX + "INDEX OF BAND "
            + PREFIX_MINDEX + "INDEX OF MUSICIAN\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_BINDEX + "1 "
            + PREFIX_MINDEX + "1";

    public static final String MESSAGE_SUCCESS = "New musicians added to band: %1$s";
    public static final String MESSAGE_DUPLICATE_MUSICIAN = "One or more of the musicians already exist in the band";
    public static final String MESSAGE_MUSICIAN_INDEX_REPEATED = "There are repeated musician indices in the command";
    public static final String MESSAGE_MULTIPLE_BAND_INDICES = "You can only add musicians to one band at a time";

    private final Index bandToAddInto;
    private final List<Index> musiciansToAdd;

    /**
     * Creates an AddCommand to add the specified {@code Musician}
     */
    public AddMusicianToBandCommand(Index bandToAddInto, List<Index> musiciansToAdd) {
        requireNonNull(bandToAddInto);
        requireNonNull(musiciansToAdd);
        this.bandToAddInto = bandToAddInto;
        this.musiciansToAdd = musiciansToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Band> lastShownBandList = model.getFilteredBandList();
        List<Musician> lastShownMusicianList = model.getFilteredMusicianList();

        if (bandToAddInto.getZeroBased() >= lastShownBandList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BAND_DISPLAYED_INDEX);
        }

        // stop execution if there are exceptions without adding any musicians
        for (Index musicianToAdd : musiciansToAdd) {
            if (musicianToAdd.getZeroBased() >= lastShownMusicianList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_MUSICIAN_DISPLAYED_INDEX);
            }

            if (model.hasMusicianInBand(bandToAddInto.getZeroBased(), musicianToAdd.getZeroBased())) {
                throw new CommandException(MESSAGE_DUPLICATE_MUSICIAN);
            }
        }

        // if no exceptions are thrown, add all the musicians to the band as ALL of them are valid
        // ensure "all or nothing" behaviour
        List<Musician> verifiedMusicians = new ArrayList<>();
        for (Index musicianToAdd : musiciansToAdd) {
            Musician musician = lastShownMusicianList.get(musicianToAdd.getZeroBased());
            verifiedMusicians.add(musician);
            model.addMusicianToBand(bandToAddInto.getZeroBased(), musicianToAdd.getZeroBased());
        }

        Band band = lastShownBandList.get(bandToAddInto.getZeroBased());
        // update the filtered band list to show ONLY the band that the musician is added to and
        // update the filtered musician list to show ONLY the members in the band
        model.updateFilteredBandMusicianList(new BandNameContainsKeywordsPredicate(band.getName().toString()));
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(band, verifiedMusicians)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddMusicianToBandCommand)) {
            return false;
        }

        AddMusicianToBandCommand otherAddMusicianToBandCommand = (AddMusicianToBandCommand) other;
        return musiciansToAdd.equals(otherAddMusicianToBandCommand.musiciansToAdd)
                && bandToAddInto.equals(otherAddMusicianToBandCommand.bandToAddInto);
    }
}
