package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Team;

/**
 * Adds a person to the address book.
 */
public class AddTeamCommand extends Command {

    public static final String COMMAND_WORD = "newteam";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new team. "
            + "Parameters: "
            + PREFIX_TEAMNAME + "TeamName "
            + PREFIX_TEAMLEADER + "TeamLeader "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TEAMNAME + "ABC "
            + PREFIX_TEAMLEADER + "JOHN DOE ";

    public static final String MESSAGE_SUCCESS = "New team added: %1$s";
    public static final String MESSAGE_DUPLICATE_TEAM = "This team already exists in the project. Choose a new name!";

    private Team teamToAdd;

    /**
     * Creates an AddTeamCommand to add the specified {@code Team}
     */
    public AddTeamCommand(Team team) {
        requireNonNull(team);
        teamToAdd = team;
    }
    //execute method WILL NOT RUN because the "team" attribute is not configured to return a formatted string.
    //update that class and then this will run
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTeam(teamToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TEAM);
        }

        model.addTeam(teamToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(teamToAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddDevCommand)) {
            return false;
        }

        AddTeamCommand otherAddCommand = (AddTeamCommand) other;
        return teamToAdd.equals(otherAddCommand.teamToAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("teamToAdd", teamToAdd)
                .toString();
    }
}
