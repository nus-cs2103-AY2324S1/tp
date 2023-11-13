package seedu.letsgethired.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.letsgethired.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.letsgethired.commons.core.index.Index;
import seedu.letsgethired.logic.commands.EditCommand;
import seedu.letsgethired.logic.commands.EditCommand.EditInternApplicationDescriptor;
import seedu.letsgethired.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_COMPANY,
                        PREFIX_ROLE,
                        PREFIX_CYCLE,
                        PREFIX_STATUS,
                        PREFIX_DEADLINE);

        Index index;

        try {
            index = ParserUtil.parseApplicationIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(
                PREFIX_COMPANY, PREFIX_ROLE, PREFIX_CYCLE, PREFIX_STATUS, PREFIX_DEADLINE);

        EditInternApplicationDescriptor editInternApplicationDescriptor = new EditInternApplicationDescriptor();

        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            editInternApplicationDescriptor
                    .setCompany(ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get()));
        }
        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            editInternApplicationDescriptor
                    .setRole(ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get()));
        }
        if (argMultimap.getValue(PREFIX_CYCLE).isPresent()) {
            editInternApplicationDescriptor
                    .setCycle(ParserUtil.parseCycle(argMultimap.getValue(PREFIX_CYCLE).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editInternApplicationDescriptor
                    .setStatus(ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editInternApplicationDescriptor
                    .setDeadline(ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }

        if (!editInternApplicationDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editInternApplicationDescriptor);
    }

}
