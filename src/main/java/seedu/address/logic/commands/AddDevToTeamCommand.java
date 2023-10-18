
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
 * Adds a person to the address book.
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
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddDevToTeamCommand(String teamName, Name developer) {
        requireNonNull(teamName);
        requireNonNull(developer);
        this.devToAdd = developer;
        this.teamToAddTo = teamName;
    }

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("devToAdd", devToAdd)
                .toString();
    }
}
