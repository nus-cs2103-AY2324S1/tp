package seedu.address.logic.parser.deleteroles;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.deleteroles.DeleteClientRoleCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser to handle user input and create a {@link DeleteClientRoleCommand}.
 * This command is used to delete a client role by providing the role name as an argument.
 * The role name should be a single word that follows the command.
 */
public class DeleteClientRoleCommandParser implements Parser<DeleteClientRoleCommand> {
    /**
     * Parses user input into a DeleteClientRoleCommand.
     *
     * @param args User input arguments.
     * @return A DeleteClientRoleCommand to delete a client role.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    @Override
    public DeleteClientRoleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (!args.isBlank()) {
            String role = args.substring(1).trim();
            return new DeleteClientRoleCommand(role);
        } else {
            throw new ParseException("Role cannot be empty!");
        }
    }
}
