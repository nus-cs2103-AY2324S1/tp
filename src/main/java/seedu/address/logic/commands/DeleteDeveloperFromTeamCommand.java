package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMNAME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.IdentityCode;
import seedu.address.model.person.Name;

/**
 * Represents a DeleteDeveloperFromTeamCommand with the associated logic to remove a developer from a team.
 */
public class DeleteDeveloperFromTeamCommand extends Command {

    public static final String COMMAND_WORD = "deletedev";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the developer identified by the their name and team name used in the displayed team list.\n"
            + "Parameters: "
            + PREFIX_TEAMNAME + "Team Name "
            + PREFIX_NAME + "Developer Name \n"
            + "Example: " + COMMAND_WORD + PREFIX_TEAMNAME + "Team Alpha " + PREFIX_NAME + "John Doe";

    public static final String MESSAGE_DELETE_DEVELOPER_FROM_TEAM_SUCCESS = "Deleted developer from team: %1$s";

    private final Name developerToDelete;
    private final String teamName;
    /**
     * Constructs an {@code DeleteDeveloperFromTeamCommand} to delete the developer identified by the provided name
     * from the specified team.
     *
     * @param teamName The name of the team from which the developer is to be removed.
     * @param developerToDelete The name of the developer to be removed from the team.
     */
    public DeleteDeveloperFromTeamCommand(String teamName, Name developerToDelete) {
        this.developerToDelete = developerToDelete;
        this.teamName = teamName;

    }

    /**
     * Executes the DeleteDeveloperFromTeamCommand by removing the specified developer from the given team in the model.
     *
     * @param model The current state of the application model.
     * @return A CommandResult indicating the result of executing this command on the given model.
     * @throws CommandException if the team name or developer name is invalid
     *          , or if the developer is not part of the team.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Check if the team exists in the model
        if (!model.hasTeam(teamName)) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEAM_NAME_DISPLAYED);
        }

        // Check if the developer exists in the model
        if (!model.containsPerson(developerToDelete)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON);
        }

        // Check if the developer is part of the team
        if (!model.personAlreadyInTeam(teamName, developerToDelete)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_IN_TEAM);
        }

        // If all checks pass, remove the developer from the team
        IdentityCode developerIdentityCode = model.getIdentityCodeByName(developerToDelete);
        model.deleteDeveloperFromTeam(teamName, developerIdentityCode);

        return new CommandResult(String.format(MESSAGE_DELETE_DEVELOPER_FROM_TEAM_SUCCESS, developerToDelete));
    }


    /**
     * Compares this command with another object for equality.
     *
     * @param other The object to compare with.
     * @return true if the objects are the same or equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteDeveloperFromTeamCommand)) {
            return false;
        }

        DeleteDeveloperFromTeamCommand otherDeleteDevFromTeamCommand = (DeleteDeveloperFromTeamCommand) other;
        return developerToDelete.equals(otherDeleteDevFromTeamCommand.developerToDelete);
    }
    /**
     * Generates a string representation of this command.
     *
     * @return A string representing the team name to delete.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("developerToDelete", developerToDelete)
                .add("teamName", teamName)
                .toString();
    }
}
