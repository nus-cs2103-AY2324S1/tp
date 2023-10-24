package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.team.Team;
import seedu.address.model.team.TeamContainsKeywordsPredicate;

/**
 * Finds the TeamLeader's IdentityCode for a specified team in the address book.
 */
public class FindTeamLeaderCommand extends Command {

    public static final String COMMAND_WORD = "findTeamLeader";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the TeamLeader's IdentityCode of the "
            + "specified team by the team name.\n"
            + "Parameters: TEAM_NAME\n"
            + "Example: " + COMMAND_WORD + " AlphaTeam";

    private final TeamContainsKeywordsPredicate predicate;

    public FindTeamLeaderCommand(TeamContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredTeamList(predicate);
        List<Team> filteredTeams = model.getFilteredTeamList();

        if (filteredTeams.isEmpty()) {
            return new CommandResult(String.format(Messages.MESSAGE_TEAM_NOT_FOUND, predicate));
        }
        //assume there is only one team leader in the team
        Team foundTeam = filteredTeams.get(0);
        return new CommandResult(String.format(Messages.MESSAGE_TEAM_LEADER_IDENTITY_CODE_RETRIEVED,
                foundTeam.getTeamName(),
                foundTeam.getTeamLeaderIdentityCode()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FindTeamLeaderCommand)) {
            return false;
        }

        FindTeamLeaderCommand otherFindTeamLeaderCommand = (FindTeamLeaderCommand) other;
        return predicate.equals(otherFindTeamLeaderCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
