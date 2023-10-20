package seedu.address.logic.parser.musician;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.musician.EditCommand;
import seedu.address.logic.commands.musician.EditCommand.EditMusicianDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG,
                        PREFIX_INSTRUMENT, PREFIX_GENRE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL);

        EditMusicianDescriptor editMusicianDescriptor = new EditMusicianDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editMusicianDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editMusicianDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editMusicianDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG))
                .ifPresent(editMusicianDescriptor::setTags);
        parseInstrumentsForEdit(argMultimap.getAllValues(PREFIX_INSTRUMENT))
                .ifPresent(editMusicianDescriptor::setInstruments);
        parseGenresForEdit(argMultimap.getAllValues(PREFIX_GENRE))
                .ifPresent(editMusicianDescriptor::setGenres);

        if (!editMusicianDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editMusicianDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Parses {@code Collection<String> instruments} into a {@code Set<Tag>} if {@code instruments} is a valid
     * collection of instruments.
     * If {@code instruments} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero instruments.
     */
    private Optional<Set<Tag>> parseInstrumentsForEdit(Collection<String> instruments) throws ParseException {
        assert instruments != null;

        if (instruments.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> instrumentSet = instruments.size() == 1 && instruments.contains("")
                ? Collections.emptySet() : instruments;
        return Optional.of(ParserUtil.parseInstruments(instrumentSet));
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
