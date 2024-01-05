package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddToConsultCommand;
import seedu.address.logic.commands.AddToConsultCommand.AddToConsultationDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new AddToConsultCommand object
 */
public class AddToConsultCommandParser implements Parser<AddToConsultCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddToConsultCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddToConsultCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToConsultCommand.MESSAGE_USAGE), pe);
        }

        AddToConsultationDescriptor addToConsultationDescriptor = new AddToConsultationDescriptor();

        parseNamesForAdd(argMultimap.getAllValues(PREFIX_NAME)).ifPresent(addToConsultationDescriptor::setNames);

        if (!addToConsultationDescriptor.isAnyStudentsAdded()) {
            throw new ParseException(AddToConsultCommand.MESSAGE_NOT_EDITED);
        }

        return new AddToConsultCommand(index, addToConsultationDescriptor);
    }

    /**
     * Parses {@code Collection<String> names} into a {@code Set<Name>}
     * if {@code names} is non-empty.
     * If {@code names} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Name>} containing zero names.
     */
    private Optional<Set<Name>> parseNamesForAdd(Collection<String> names) throws ParseException {
        assert names != null;

        if (names.isEmpty()) {
            return Optional.empty();
        }

        Collection<String> nameSet = names.size() == 1 && names.contains("") ? Collections.emptySet() : names;
        return Optional.of(ParserUtil.parseNames(nameSet));
    }
}
