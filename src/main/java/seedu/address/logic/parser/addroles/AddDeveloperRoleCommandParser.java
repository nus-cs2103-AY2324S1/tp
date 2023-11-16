package seedu.address.logic.parser.addroles;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.addroles.AddDeveloperRoleCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input to create an AddDeveloperRoleCommand.
 */
public class AddDeveloperRoleCommandParser implements Parser<AddDeveloperRoleCommand> {
    /**
     * Parses the given {@code args} and returns an AddDeveloperRoleCommand.
     *
     * @param args User input arguments.
     * @return An AddDeveloperRoleCommand for adding a developer role.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    @Override
    public AddDeveloperRoleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (!args.isBlank()) {
            String role = args.substring(1).trim();
            return new AddDeveloperRoleCommand(role);
        } else {
            throw new ParseException("Role cannot be empty!");
        }
    }
}
