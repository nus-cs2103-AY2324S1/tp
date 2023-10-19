package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMLEADER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMNAME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.IdentityCode;
import seedu.address.model.person.Name;
import seedu.address.model.team.Team;

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
    public static final String MESSAGE_INVALID_PERSON = "This person does not exist!";

    private String teamToAdd;
    private Name leaderToAdd;
    private IdentityCode teamLeaderIdentityCode;

    /**
     * Creates an AddTeamCommand to add the specified {@code Team}
     */
    public AddTeamCommand(String teamName, Name teamLeader) {
        requireNonNull(teamName);
        requireNonNull(teamLeader);
        teamToAdd = teamName;
        leaderToAdd = teamLeader;
    }
    //execute method WILL NOT RUN because the "team" attribute is not configured to return a formatted string.
    //update that class and then this will run
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //if person doesn't exist in the persons list, throw invalid person message
        if (!model.hasPerson(leaderToAdd)) {
            throw new CommandException(MESSAGE_INVALID_PERSON);
        }
        if (model.hasTeam(teamToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TEAM);
        }
        //gets the Person object of leader, then his identity code
        IdentityCode teamLeaderIdentityCode = model.getPersonByName(leaderToAdd).getIdentityCode();

        //model.updateFilteredPersonList(person -> person.getName().equals(leaderToAdd));
        //Person teamLeader = model.getFilteredPersonList().stream().findFirst().orElse(null);
        Team team = new Team(teamLeaderIdentityCode, teamToAdd);
        model.addTeam(team);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(team, leaderToAdd)));
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
