package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HINT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Hint;
import seedu.address.model.card.Question;
import seedu.address.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_QUESTION, PREFIX_ANSWER, PREFIX_TAG, PREFIX_HINT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        // Only one Question and Answer allowed for each Card
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_QUESTION, PREFIX_ANSWER, PREFIX_HINT);

        EditCommand.EditCardDescriptor editCardDescriptor = new EditCommand.EditCardDescriptor();

        // Question
        if (argMultimap.getValue(PREFIX_QUESTION).isPresent()) {
            Question newQuestion = ParserUtil.parseQuestion(argMultimap.getValue(PREFIX_QUESTION).get());
            editCardDescriptor.setQuestion(newQuestion);
        }

        // Answer
        if (argMultimap.getValue(PREFIX_ANSWER).isPresent()) {
            Answer newAnswer = ParserUtil.parseAnswer(argMultimap.getValue(PREFIX_ANSWER).get());
            editCardDescriptor.setAnswer(newAnswer);
        }

        // Tags
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editCardDescriptor::setTags);

        // Hint
        if (argMultimap.getValue(PREFIX_HINT).isPresent()) {
            Hint newHint = ParserUtil.parseHint(argMultimap.getValue(PREFIX_HINT).get());
            editCardDescriptor.setHint(newHint);
        }

        // Throw exception if no details are changed
        if (!editCardDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED + "\n"
                    + EditCommand.MESSAGE_USAGE
            );
        }

        return new EditCommand(index, editCardDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code List<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code List<Tag>} containing zero tags.
     */
    private Optional<List<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptyList() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
