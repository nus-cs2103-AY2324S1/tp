package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Adds a member to the address book.
 */
public class CreateEventCommand extends Command {

    public static final String COMMAND_WORD = "createEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates an event in CCACommander. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_LOCATION + "LOCATION "
            + PREFIX_DATE + "DATE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Party "
            + PREFIX_LOCATION + "Raffles Hall "
            + PREFIX_DATE + "2023-10-16 ";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in CCACommander. ";

    private final Event toCreate;

    /**
     * Creates an CreateEventCommand to add the specified {@code Event}
     */
    public CreateEventCommand(Event event) {
        requireNonNull(event);
        toCreate = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEvent(toCreate)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.createEvent(toCreate);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toCreate)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CreateEventCommand)) {
            return false;
        }

        CreateEventCommand otherCreateEventCommand = (CreateEventCommand) other;
        return toCreate.equals(otherCreateEventCommand.toCreate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toCreate)
                .toString();
    }
}
