package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMNAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TEAMS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.team.Team;


import java.util.List;

public class EditTeamNameCommand extends Command {
    public static final String COMMAND_WORD = "editTeamName";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the Team Name of the team "
            + "identified by its original Team Name. "
            + "Parameters: "
            + PREFIX_TEAMNAME + "Original Team Name "
            + PREFIX_TEAMNAME + "New Team Name"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TEAMNAME + "Alpha team "
            + PREFIX_TEAMNAME + "Beta team";

    public static final String MESSAGE_EDIT_TEAM_NAME_SUCCESS = "Edited Team Name: %1$s";
    public static final String MESSAGE_NOT_EDITED = "Your new Team Name should be different from " +
            "your original Team Name.";
    public static final String MESSAGE_DUPLICATE_TEAM_NAME = "This Team Name already exists in the Team book.";
    public static final String MESSAGE_TEAM_NOT_FOUND = "Cannot find the team with this original Team Name" +
            " in the Team book.";

    private final String originalTeamName;
    private final String newTeamName;

    public EditTeamNameCommand(String originalTeamName, String newTeamName) {
        requireNonNull(originalTeamName);
        requireNonNull(newTeamName);
        this.originalTeamName = originalTeamName;
        this.newTeamName = newTeamName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasTeam(originalTeamName)) {
            throw new CommandException(MESSAGE_TEAM_NOT_FOUND);
        }

        if (model.hasTeam(newTeamName)) {
            throw new CommandException(MESSAGE_DUPLICATE_TEAM_NAME);
        }

        if (originalTeamName.equals(newTeamName)) {
            throw new CommandException(MESSAGE_NOT_EDITED);
        }

        model.editTeamName(originalTeamName, newTeamName);
        model.updateFilteredTeamList(PREDICATE_SHOW_ALL_TEAMS);

        return new CommandResult(String.format(MESSAGE_EDIT_TEAM_NAME_SUCCESS,
                Messages.editTeamNameFormat(originalTeamName, newTeamName)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTeamNameCommand)) {
            return false;
        }

        EditTeamNameCommand otherEditCommand = (EditTeamNameCommand) other;
        return originalTeamName.equals(otherEditCommand.originalTeamName)
                && newTeamName.equals(otherEditCommand.newTeamName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Original Team Name", originalTeamName)
                .add("New Team Name", newTeamName)
                .toString();
    }


}
