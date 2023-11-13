package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.Set;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Meeting;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;


/**
 * Adds an event.
 * @author Yuheng
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "add_event";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the address book. \n"
            + "Parameters: "
            + PREFIX_EVENT_NAME + "EVENT_NAME "
            + PREFIX_DATE + "DATE "
            + "[" + PREFIX_START_TIME + "START_TIME] "
            + "[" + PREFIX_END_TIME + "END_TIME] "
            + "[" + PREFIX_NAME + "NAME]... "
            + "[" + PREFIX_GROUP + "GROUP]... \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT_NAME + "FumbleLog Meeting "
            + PREFIX_DATE + "2024-01-30 "
            + PREFIX_START_TIME + "1000 "
            + PREFIX_END_TIME + "1200 "
            + PREFIX_NAME + "Ken "
            + PREFIX_NAME + "Yuheng "
            + PREFIX_GROUP + "Team2 ";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    private final Meeting toAdd;

    /**
     * Creates an AddEventCommand to add the specified {@code Meeting}
     */
    public AddEventCommand(Meeting meeting) {
        requireAllNonNull(meeting);
        this.toAdd = meeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        CommandUtil.verifyEventTimes(this.toAdd);

        Set<Name> invalidNames = model.findInvalidNames(this.toAdd.getNames());

        if (!invalidNames.isEmpty()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_PERSON,
                    listInvalidNames(invalidNames)));
        }

        Set<Group> invalidGroups = model.findInvalidGroups(this.toAdd.getGroups());

        if (!invalidGroups.isEmpty()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_GROUP,
                    listInvalidGroups(invalidGroups)));
        }

        // This line must be at the end
        assert invalidNames.isEmpty() || invalidGroups.isEmpty() : "Invalid names and groups should be checked first";
        model.addEvent(this.toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatEvent(toAdd)));
    }

    private String listInvalidNames(Set<Name> invalidNames) {
        StringBuilder builder = new StringBuilder();
        for (Name name : invalidNames) {
            builder.append(name.toString());
            builder.append(", ");
        }
        builder.delete(builder.length() - 2, builder.length()); //removes the last comma
        return builder.toString();
    }

    private String listInvalidGroups(Set<Group> invalidGroups) {
        StringBuilder builder = new StringBuilder();
        for (Group group : invalidGroups) {
            builder.append(group.toString());
            builder.append(", ");
        }
        builder.delete(builder.length() - 2, builder.length()); //removes the last comma
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof AddEventCommand)) {
            return false;
        } else {
            AddEventCommand otherAddEventCommand = (AddEventCommand) other;
            return this.toAdd.equals(otherAddEventCommand.toAdd);
        }
    }
}
