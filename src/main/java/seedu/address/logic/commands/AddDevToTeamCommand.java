
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.AddTeamCommand.MESSAGE_INVALID_PERSON;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Developer;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Team;

/**
 * Represents an AddDevToTeam command with hidden internal logic and the ability to be executed.
 * Adds a developer to a team in the address book.
 */
public class AddDevToTeamCommand extends Command {

    public static final String COMMAND_WORD = "dev2team";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds this developer to the Team. "
            + "Parameters: "
            + PREFIX_TEAMNAME + "Team Name "
            + PREFIX_NAME + "Developer Name "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TEAMNAME + "ABC "
            + PREFIX_NAME + "John Doe ";


    public static final String MESSAGE_SUCCESS = "New developer added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This developer already exists in this team!";
    public static final String MESSAGE_INVALID_TEAM = "This team already exists!";

    private final Name devToAdd;
    private final String teamToAddTo;

    /**
     * Constructs an {@code AddDevToTeamCommand} to add the specified developer to a team.
     *
     * @param teamName The name of the team to which the developer will be added.
     * @param developer The developer's name that will be added to the team.
     */
    public AddDevToTeamCommand(String teamName, Name developer) {
        requireNonNull(teamName);
        requireNonNull(developer);
        this.devToAdd = developer;
        this.teamToAddTo = teamName;
    }

    /**
     * Executes the AddDevToTeamCommand, adding a developer to a specified team.
     *
     * @param model The current state of the application model.
     * @return A CommandResult indicating the result of executing this command on the given model.
     * @throws CommandException if the team doesn't exist, the developer is already in the team, or the developer doesn't exist.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //duplicate or team doesnt exist
        if (!model.invalidAddToTeam(teamToAddTo)) {
            throw new CommandException(MESSAGE_INVALID_TEAM);
        } else if (!model.hasPerson(devToAdd)) {
            throw new CommandException(MESSAGE_INVALID_PERSON);
        } else if (model.personAlreadyInTeam(teamToAddTo, devToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        } else {
            model.addToTeam(teamToAddTo, devToAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(teamToAddTo, devToAdd)));
        }
    }

    /**
     * Checks whether another object is equal to this AddDevToTeamCommand.
     *
     * @param other the object to compare with.
     * @return true if the other object is an AddDevToTeamCommand with the same developer name, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddDevToTeamCommand)) {
            return false;
        }

        AddDevToTeamCommand otherAddCommand = (AddDevToTeamCommand) other;
        return devToAdd.equals(otherAddCommand.devToAdd);
    }

    /**
     * Returns a string representation of this AddDevToTeamCommand, including the developer's name.
     *
     * @return A string representing this command, including the developer's name.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("devToAdd", devToAdd)
                .toString();
    }
}
