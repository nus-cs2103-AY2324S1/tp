package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TEAMS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.IdentityCode;
import seedu.address.model.person.Name;

public class EditTeamLeaderCommand extends Command {
    public static final String COMMAND_WORD = "editTeamLeader";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the Team Leader of the team "
            + "identified by its Team Name. "
            + "Parameters: "
            + PREFIX_TEAMNAME + "Team Name "
            + PREFIX_TEAMLEADER + "Team Leader Name"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TEAMNAME + "Alpha team "
            + PREFIX_TEAMLEADER + "Bob";

    public static final String MESSAGE_EDIT_TEAM_LEADER_SUCCESS = "Edited Team Leader: %1$s";
    public static final String MESSAGE_NOT_EDITED = "Your new Team Leader should be different from " +
            "your original Team Leader.";
    public static final String MESSAGE_TEAM_LEADER_NOT_FOUND = "Cannot find the developer with this Name" +
            " in the Address book.";
    public static final String MESSAGE_TEAM_NOT_FOUND = "Cannot find the team with this Team Name" +
            " in the Team book.";

    private final String teamName;
    private final Name newTeamLeaderName;

    public EditTeamLeaderCommand(String teamName, Name newTeamLeaderName) {
        requireNonNull(teamName);
        requireNonNull(newTeamLeaderName);
        this.teamName = teamName;
        this.newTeamLeaderName = newTeamLeaderName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasTeam(teamName)) {
            throw new CommandException(MESSAGE_TEAM_NOT_FOUND);
        }

        if (!model.hasPerson(newTeamLeaderName)) {
            throw new CommandException(MESSAGE_TEAM_LEADER_NOT_FOUND);
        }

        Name originalTeamLeaderName = model.getTeamLeaderOfTeam(teamName);

        if (originalTeamLeaderName.equals(newTeamLeaderName)) {
            throw new CommandException(MESSAGE_NOT_EDITED);
        }

        IdentityCode newTeamLeaderID = model.getIdentityCodeByName(newTeamLeaderName);
        model.setTeamLeaderOfTeam(teamName, newTeamLeaderID);
        model.updateFilteredTeamList(PREDICATE_SHOW_ALL_TEAMS);

        return new CommandResult(String.format(MESSAGE_EDIT_TEAM_LEADER_SUCCESS,
                Messages.editTeamLeaderFormat(teamName, originalTeamLeaderName, newTeamLeaderName)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTeamLeaderCommand)) {
            return false;
        }

        EditTeamLeaderCommand otherEditCommand = (EditTeamLeaderCommand) other;
        return teamName.equals(otherEditCommand.teamName)
                && newTeamLeaderName.equals(otherEditCommand.newTeamLeaderName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Team Name", teamName)
                .add("New Team Leader Name", newTeamLeaderName)
                .toString();
    }


}
