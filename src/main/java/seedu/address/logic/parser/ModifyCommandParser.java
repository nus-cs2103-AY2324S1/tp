
package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ModifyCommand;
import seedu.address.logic.commands.ModifyCommand.EditInternshipDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ModifyCommand object
 */
public class ModifyCommandParser implements InternshipParser<ModifyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ModifyCommand
     * and returns an ModifyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ModifyCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_ROLE, PREFIX_APPLICATION_STATUS,
                        PREFIX_START_DATE, PREFIX_DURATION, PREFIX_REQUIREMENT, PREFIX_DEADLINE);

        Index index;

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COMPANY_NAME, PREFIX_ROLE, PREFIX_APPLICATION_STATUS,
                PREFIX_START_DATE, PREFIX_DURATION, PREFIX_DEADLINE);

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModifyCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.isNoPrefixPresent()) {
            throw new ParseException(ModifyCommand.MESSAGE_NOT_EDITED);
        }

        EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();

        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            editInternshipDescriptor.setCompanyName(ParserUtil
                    .parseCompanyName(argMultimap.getValue(PREFIX_COMPANY_NAME)
                            .get().strip().replaceAll("\\s+", " ")));
        }
        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            editInternshipDescriptor.setRole(ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE)
                    .get().strip().replaceAll("\\s+", " ")));
        }
        if (argMultimap.getValue(PREFIX_APPLICATION_STATUS).isPresent()) {
            editInternshipDescriptor.setApplicationStatus(ParserUtil
                    .parseApplicationStatus(argMultimap.getValue(PREFIX_APPLICATION_STATUS).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editInternshipDescriptor.setDeadline(ParserUtil
                    .parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_START_DATE).isPresent()) {
            editInternshipDescriptor.setStartDate(ParserUtil
                    .parseStartDate(argMultimap.getValue(PREFIX_START_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_DURATION).isPresent()) {
            editInternshipDescriptor.setDuration(ParserUtil
                    .parseDuration(argMultimap.getValue(PREFIX_DURATION).get()));
        }

        if (argMultimap.getValue(PREFIX_REQUIREMENT).isPresent()) {
            editInternshipDescriptor.setRequirements(ParserUtil.parseRequirements(
                    argMultimap.getAllValues(PREFIX_REQUIREMENT)));
        }

        if (!editInternshipDescriptor.isAnyFieldEdited()) {
            throw new ParseException(ModifyCommand.MESSAGE_NOT_EDITED);
        }

        return new ModifyCommand(index, editInternshipDescriptor);
    }

}

