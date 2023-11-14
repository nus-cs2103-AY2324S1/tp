package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRIEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditPlanCommand;
import seedu.address.logic.commands.EditPlanCommand.EditPlanDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditPlanCommand object
 */
public class EditPlanCommandParser implements Parser<EditPlanCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditPlanCommand
     * and returns an EditPlanCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditPlanCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATETIME, PREFIX_FRIEND);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPlanCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_DATETIME, PREFIX_FRIEND);

        EditPlanDescriptor editPlanDescriptor = new EditPlanDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPlanDescriptor.setPlanName(ParserUtil.parsePlanName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
            editPlanDescriptor.setPlanDateTime(
                    ParserUtil.parsePlanDateTime(argMultimap.getValue(PREFIX_DATETIME).get()));
        }
        if (argMultimap.getValue(PREFIX_FRIEND).isPresent()) {
            editPlanDescriptor.setPlanFriendName(ParserUtil.parseName(argMultimap.getValue(PREFIX_FRIEND).get()));
        }

        if (!editPlanDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPlanCommand.MESSAGE_NOT_EDITED);
        }

        return new EditPlanCommand(index, editPlanDescriptor);
    }
}
