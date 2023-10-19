package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMNAME;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.IdentityCode;
import seedu.address.model.person.Name;

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
    public DeleteDeveloperFromTeamCommand(String teamName, Name developerToDelete) {
        this.developerToDelete = developerToDelete;
        this.teamName = teamName;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        IdentityCode developerIndentityCode = model.getIdentityCodeByName(developerToDelete);
        if (!model.hasTeam(teamName)) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEAM_NAME_DISPLAYED);
        } else if (!model.hasPerson(developerToDelete)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON);
        } else if (model.personAlreadyInTeam(teamName, developerToDelete)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_IN_TEAM);
        } else {
            model.deleteDeveloperFromTeam(teamName, developerIndentityCode);
            return new CommandResult(String.format(MESSAGE_DELETE_DEVELOPER_FROM_TEAM_SUCCESS,
                    developerToDelete.toString()));
        }
    }
}
