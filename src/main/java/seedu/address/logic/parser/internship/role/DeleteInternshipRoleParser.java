package seedu.address.logic.parser.internship.role;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.internship.role.DeleteInternshipRoleCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeleteInternshipRoleCommand object
 */
public class DeleteInternshipRoleParser implements Parser<DeleteInternshipRoleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteInternshipRoleCommand
     * and returns a DeleteInternshipRoleCommand object for execution.
     *
     */
    public DeleteInternshipRoleCommand parse(String args) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            DeleteInternshipRoleCommand.MESSAGE_USAGE)
            );
        }
        Index index = ParserUtil.parseIndex(args);
        return new DeleteInternshipRoleCommand(index);
    }
}
