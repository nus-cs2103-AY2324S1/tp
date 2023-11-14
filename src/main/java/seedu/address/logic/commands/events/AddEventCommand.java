package seedu.address.logic.commands.events;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_START;

import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * Adds an event to the events book.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the events book. "
            + "Parameters: "
            + PREFIX_EVENT_NAME + "EVENT_NAME "
            + PREFIX_TIME_START + "START_TIME "
            + PREFIX_TIME_END + "END_TIME "
            + "[" + PREFIX_CLIENT + "CLIENTS]... "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT_NAME + "Discuss Deliverables with John "
            + PREFIX_TIME_START + "31-12-2024 12:30 "
            + PREFIX_TIME_END + "31-12-2024 14:30 "
            + PREFIX_CLIENT + "John Doe "
            + PREFIX_LOCATION + "FreelanceBuddy HQ "
            + PREFIX_DESCRIPTION + "Settle product development deliverables for next phase";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the events book";
    private final Event toAdd;

    /**
     * Creates an AddEventCommand to add the specified {@code Event}
     */
    public AddEventCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEvent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        Set<Person> toBeAddedClients = toAdd.getClients();
        setValidClients(toBeAddedClients, model);

        model.addEvent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    /**
     * Checks and sets {@code clients} to the {@code toAdd} event if they exist in the events book
     * from {@code Model}.
     */
    private void setValidClients(Set<Person> clients, Model model) throws CommandException {
        requireNonNull(clients);
        requireNonNull(model);

        boolean hasValidClients = clients.stream().allMatch(model::isValidClient);
        if (!hasValidClients) {
            throw new CommandException(Messages.MESSAGE_CLIENT_DOES_NOT_EXIST);
        } else {
            Set<Person> validClients = model.getAllMatchedClients(clients);
            toAdd.setMatchedClientInstance(validClients);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddEventCommand)) {
            return false;
        }

        AddEventCommand otherAddCommand = (AddEventCommand) other;
        return toAdd.isSameEvent(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
