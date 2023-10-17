package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Team;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteTeam extends Command {

    public static final String COMMAND_WORD = "deleteteam";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the team identified by the team name used in the displayed team list.\n"
            + "Parameters: TEAM NAME \n"
            + "Example: " + COMMAND_WORD + " TEAM Alpha";

    public static final String MESSAGE_DELETE_TEAM_SUCCESS = "Deleted Team: %1$s";

    private final String teamName;

    public DeleteTeam(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Team> lastShownList = model.getFilteredTeamList();

        if (!model.hasTeam(teamName)) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEAM_NAME_DISPLAYED);
        }

//        Team teamToDelete = lastShownList.getTeam(teamName);
        model.deleteTeam(teamName);
        return new CommandResult(String.format(MESSAGE_DELETE_TEAM_SUCCESS, Messages.format(teamName)));
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

        DeleteTeam otherDeleteTeamCommand = (DeleteTeam) other;
        return teamName.equals(otherDeleteTeamCommand.teamName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("teamName", teamName)
                .toString();
    }
}
