package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.IdentityCode;
import seedu.address.model.person.Person;

/**
 * Represents a DeleteCommand with the associated logic to delete a person based on their displayed index.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_TEAM_LEADER_CANNOT_BE_DELETED =
            "The developer you have mentioned is a team leader, you can only edit team leader.";
    public static final String MESSAGE_DELETE_PERSON_FROM_ALL_TEAMS = "Deleted Person : %1$s from all the teams.";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index targetIndex;

    /**
     * Constructs an {@code DeleteCommand} to delete the person identified by the specified index.
     *
     * @param targetIndex The index of the person to be deleted.
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the DeleteCommand by deleting the person identified by the target index from the model.
     *
     * @param model The current state of the application model.
     * @return A CommandResult indicating the result of executing this command on the given model.
     * @throws CommandException if the target index is invalid for the current list of displayed persons.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        IdentityCode personID = personToDelete.getIdentityCode();
        if (model.developerIsTeamLeader(personID)) {
            throw new CommandException(MESSAGE_TEAM_LEADER_CANNOT_BE_DELETED);
        } else if (model.removeDeveloperFromAllTeams(personID)) {
            model.deletePerson(personToDelete);
            return new CommandResult(String.format(
                    MESSAGE_DELETE_PERSON_FROM_ALL_TEAMS, Messages.format(personToDelete)),
                    false, false, false, false, false, false, false, true);
        } else {
            model.deletePerson(personToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)),
                    false, false, false, false, false, false, false, true);
        }
    }

    /**
     * Checks whether another object is equal to this DeleteCommand.
     *
     * @param other The object to compare with.
     * @return true if the other object is a DeleteCommand with the same target index, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    /**
     * Returns a string representation of this DeleteCommand, primarily indicating the target index.
     *
     * @return A string representing this command, including the target index.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
