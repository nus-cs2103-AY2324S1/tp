package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveFromConsultCommand;
import seedu.address.logic.commands.RemoveFromConsultCommand.RemoveFromConsultationDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new RemoveFromConsultCommand object
 */
public class RemoveFromConsultCommandParser implements Parser<RemoveFromConsultCommand> {
    @Override
    public RemoveFromConsultCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveFromConsultCommand.MESSAGE_USAGE), pe);
        }

        RemoveFromConsultationDescriptor descriptor = new RemoveFromConsultationDescriptor();

        parseNamesForRemoval(argMultimap.getAllValues(PREFIX_NAME)).ifPresent(descriptor::setNames);

        // Handles no input of name for descriptor -> No changes made.
        if (descriptor.isEmptyStudents()) {
            throw new ParseException(RemoveFromConsultCommand.MESSAGE_NOT_EDITED);
        }

        return new RemoveFromConsultCommand(index, descriptor);
    }

    /**
     * Parses {@code Collection<String> names} into a {@code Set<Name>}
     * if {@code names} is non-empty.
     * If {@code names} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Name>} containing zero names.
     */
    private Optional<Set<Name>> parseNamesForRemoval(Collection<String> names) throws ParseException {
        assert names != null;

        if (names.isEmpty()) {
            return Optional.empty();
        }

        Collection<String> nameSet = names.size() == 1 && names.contains("") ? Collections.emptySet() : names;
        return Optional.of(ParserUtil.parseNames(nameSet));
    }
}
