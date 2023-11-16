package seedu.address.logic.parser.deleteroles;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.deleteroles.DeleteDeveloperRoleCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser to handle user input and create a {@link DeleteDeveloperRoleCommand}.
 * This command is used to delete a developer role by providing the role name as an argument.
 * The role name should be a single word that follows the command.
 */
public class DeleteDeveloperRoleCommandParser implements Parser<DeleteDeveloperRoleCommand> {
    /**
     * Parses user input and creates a {@link DeleteDeveloperRoleCommand} based on the provided role name.
     *
     * @param args User input arguments.
     * @return A {@link DeleteDeveloperRoleCommand} for deleting a developer role.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    @Override
    public DeleteDeveloperRoleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (!args.isBlank()) {
            String role = args.substring(1).trim();
            return new DeleteDeveloperRoleCommand(role);
        } else {
            throw new ParseException("Role cannot be empty!");
        }
    }
}
