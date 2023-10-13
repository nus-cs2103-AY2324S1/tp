package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FilterCommand.PersonFilter;
import seedu.address.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.ParserUtil.parseTagsForUse;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        PersonFilter personFilter = new PersonFilter();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            personFilter.setName(argMultimap.getValue(PREFIX_NAME).get());
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            personFilter.setPhone(argMultimap.getValue(PREFIX_PHONE).get());
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            personFilter.setEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            personFilter.setAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        }
        parseTagsForUse(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(personFilter::setTags);

        if (!personFilter.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new FilterCommand(personFilter);
    }
}