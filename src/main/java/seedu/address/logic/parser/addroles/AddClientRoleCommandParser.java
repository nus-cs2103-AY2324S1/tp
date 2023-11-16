package seedu.address.logic.parser.addroles;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.addroles.AddClientRoleCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input to create an AddClientRoleCommand.
 */
public class AddClientRoleCommandParser implements Parser<AddClientRoleCommand> {
    /**
     * Parses the given {@code args} and returns an AddClientRoleCommand.
     *
     * @param args User input arguments.
     * @return An AddClientRoleCommand for adding a client role.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    @Override
    public AddClientRoleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (!args.isBlank()) {
            String role = args.substring(1).trim();
            return new AddClientRoleCommand(role);
        } else {
            throw new ParseException("Role cannot be empty!");
        }
    }
}
