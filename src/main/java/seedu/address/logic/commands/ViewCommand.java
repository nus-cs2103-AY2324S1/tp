package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Views specified person based on input index.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS = "Viewed Person Successfully";
    private static final String state = "view";


    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the person identified by the index number used.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private static final Logger logger = LogsCenter.getLogger(ViewCommand.class);

    private final Index targetIndex;

    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);


        if (targetIndex.getZeroBased() < 0 || targetIndex.getZeroBased() >= model.getFilteredPersonList().size()) {
            logger.info(targetIndex.getZeroBased() + "+ " + model.getFilteredPersonList().size());
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToView = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        model.view(personToView);

        logger.info("Target Index: " + targetIndex.getZeroBased());
        logger.info("Client to View: " + personToView);
        return new CommandResult(MESSAGE_SUCCESS, false, false, "view");
    }
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        ViewCommand that = (ViewCommand) other;

        return targetIndex.equals(that.targetIndex);
    }
}

