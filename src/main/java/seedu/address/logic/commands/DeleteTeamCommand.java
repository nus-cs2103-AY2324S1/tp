package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMNAME;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.team.Team;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteTeamCommand extends Command {

    public static final String COMMAND_WORD = "deleteteam";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the team identified by the team name used in the displayed team list.\n"
            + "Parameters: "
            + PREFIX_TEAMNAME + "Team Name \n"
            + "Example: " + COMMAND_WORD + PREFIX_TEAMNAME + "Team Alpha";

    public static final String MESSAGE_DELETE_TEAM_SUCCESS = "Deleted Team: %1$s";

    private final String teamToDelete;

    public DeleteTeamCommand(String teamToDelete) {
        this.teamToDelete = teamToDelete;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Team> lastShownList = model.getFilteredTeamList();

        if (!model.hasTeam(teamToDelete)) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEAM_NAME_DISPLAYED);
        }
        model.deleteTeam(teamToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TEAM_SUCCESS, teamToDelete));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteTeamCommand otherDeleteTeamCommandCommand = (DeleteTeamCommand) other;
        return teamToDelete.equals(otherDeleteTeamCommandCommand.teamToDelete);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("teamToDelete", teamToDelete)
                .toString();
    }
}
