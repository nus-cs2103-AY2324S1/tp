package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Creates a pop-up window to view the event list of a person using its displayed index from the address book.
 */
public class ViewContactEventsCommand extends Command {
    public static final String COMMAND_WORD = "viewContactEvents";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a window containing the event list of a person identified by their index number.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_EVENTS_SUCCESS = "Viewing event list.";

    private final Index targetIndex;

    public ViewContactEventsCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        return new CommandResult(MESSAGE_VIEW_EVENTS_SUCCESS, targetIndex);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewContactEventsCommand)) {
            return false;
        }

        ViewContactEventsCommand otherViewContactEventsCommand = (ViewContactEventsCommand) other;
        return targetIndex.equals(otherViewContactEventsCommand.targetIndex);
    }
}
