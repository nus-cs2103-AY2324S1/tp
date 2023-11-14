package seedu.address.logic.parser.band;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.band.EditBandCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditBandCommand object
 */
public class EditBandCommandParser implements Parser<EditBandCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditBandCommand
     * and returns an EditBandCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditBandCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_GENRE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBandCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME);

        EditBandCommand.EditBandDescriptor editBandDescriptor = new EditBandCommand.EditBandDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editBandDescriptor.setBandName(ParserUtil.parseBandName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        parseGenresForEdit(argMultimap.getAllValues(PREFIX_GENRE))
                .ifPresent(editBandDescriptor::setGenres);

        if (!editBandDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditBandCommand.MESSAGE_NOT_EDITED);
        }

        return new EditBandCommand(index, editBandDescriptor);
    }

    /**
     * Parses {@code Collection<String> genres} into a {@code Set<Tag>} if {@code genres} is a valid collection of
     * genres.
     * If {@code genres} contain only one element which is an empty string, it will be parsed into a {@code Set<Tag>}
     * containing zero genres.
     */
    private Optional<Set<Tag>> parseGenresForEdit(Collection<String> genres) throws ParseException {
        assert genres != null;

        if (genres.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> genreSet = genres.size() == 1 && genres.contains("") ? Collections.emptySet() : genres;
        return Optional.of(ParserUtil.parseGenres(genreSet));
    }
}
