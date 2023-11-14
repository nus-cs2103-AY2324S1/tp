package seedu.address.logic.parser.internship.role;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.internship.role.FindInternshipRoleCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.role.InternshipRoleNameContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindInternshipRoleParser implements Parser<FindInternshipRoleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindInternshipRoleCommand
     * and returns a FindInternshipRoleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindInternshipRoleCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindInternshipRoleCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindInternshipRoleCommand(
                new InternshipRoleNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
