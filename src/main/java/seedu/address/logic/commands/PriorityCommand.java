package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_PERSON_NOT_CHANGED;
import static seedu.address.logic.commands.CommandUtil.getPersonAtIndex;
import static seedu.address.model.person.Person.createPersonWithUpdatedPriority;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.priority.Priority;

/** Assigns priority to a person. **/
public class PriorityCommand extends Command {
    public static final String COMMAND_WORD = "priority";

    public static final String MESSAGE_USAGE = "Usage: \n" + COMMAND_WORD
            + " <index> "
            + "<priority>\n";

    public static final String MESSAGE_ASSIGN_PRIORITY_SUCCESS = "Updated priority of customer: %1$s";

    private static final Logger logger = LogsCenter.getLogger(PriorityCommand.class);

    private final Index index;
    private final Priority priority;

    /**
     * Constructs a PriorityCommand.
     *
     * @param index of the person in the filtered list to assign priority to.
     * @param priority assigned to the person.
     */
    public PriorityCommand(Index index, Priority priority) {
        requireNonNull(index);
        requireNonNull(priority);

        this.index = index;
        this.priority = priority;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        logger.fine("PriorityCommand executing...");

        Person personToUpdate = getPersonAtIndex(model, index);
        Person updatedPerson = createPersonWithUpdatedPriority(personToUpdate, priority);

        checkIsOldPriority(personToUpdate);

        model.setPerson(personToUpdate, updatedPerson);

        return new CommandResult(String.format(MESSAGE_ASSIGN_PRIORITY_SUCCESS, Messages.format(updatedPerson)));
    }

    private void checkIsOldPriority(Person personToUpdate) throws CommandException {
        if (personToUpdate.hasSamePriority(priority)) {
            logger.finer("Executing failed due to provided priority is the same as previously assigned priority");
            throw new CommandException(MESSAGE_PERSON_NOT_CHANGED);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PriorityCommand)) {
            return false;
        }

        PriorityCommand otherPriorityCommand = (PriorityCommand) other;
        return priority.equals(otherPriorityCommand.priority) && index.equals(otherPriorityCommand.index);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("priority", priority)
                .toString();
    }
}
