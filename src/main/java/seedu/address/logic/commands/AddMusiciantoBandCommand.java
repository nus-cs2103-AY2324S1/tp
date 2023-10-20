package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BINDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MINDEX;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds a musician to a band.j
 */
public class AddMusiciantoBandCommand extends Command {
    public static final String COMMAND_WORD = "addm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a musician to a band. "
            + "Parameters: "
            + PREFIX_BINDEX + "INDEX OF BAND "
            + PREFIX_MINDEX + "INDEX OF MUSICIAN\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_BINDEX + "1 "
            + PREFIX_MINDEX + "1";

    public static final String MESSAGE_SUCCESS = "New musician added to band: %1$s";
    public static final String MESSAGE_DUPLICATE_MUSICIAN = "This musician already exists in the band";

    private final Integer toAdd;
    private final Integer addInto;

    /**
     * Creates an AddCommand to add the specified {@code Musician}
     */
    public AddMusiciantoBandCommand(Integer bandIndex, Integer musicianIndex) {
        requireNonNull(musicianIndex);
        requireNonNull(bandIndex);
        addInto = bandIndex;
        toAdd = musicianIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasMusicianInBand(addInto, toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MUSICIAN);
        }

        model.addMusicianToBand(addInto, toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(addInto, toAdd)));
    }


}
