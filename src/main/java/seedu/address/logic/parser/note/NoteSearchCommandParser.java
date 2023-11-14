package seedu.address.logic.parser.note;


import static seedu.address.logic.parser.CliSyntax.PARAMETER_TAGS;
import static seedu.address.logic.parser.ParserUtil.areValuesEnclosedAndNonEmpty;

import seedu.address.logic.commands.note.NoteSearchCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.SeplendidArgumentMap;
import seedu.address.logic.parser.SeplendidArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.messages.UsageMessage;
import seedu.address.model.note.NoteTagContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses the given {@code String} of arguments in the context of the SearchCommand
 * and returns a SearchCommand object for execution.
 *
 * @throws ParseException if the user input does not conform the expected format
 */
public class NoteSearchCommandParser implements Parser<NoteSearchCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the NoteSearchCommand
     * and returns a NoteSearchCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public NoteSearchCommand parse(String args) throws ParseException {
        ParserUtil.AreValuesEnclosedAndNonEmptyResult areValuesEnclosedAndNonEmptyResult =
                areValuesEnclosedAndNonEmpty(args);
        if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.FAILURE) {
            throw new ParseException(UsageMessage.NOTE_SEARCH.toString());
        } else if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.EMPTY) {
            throw new ParseException(UsageMessage.NOTE_SEARCH.getValueWithEmptyArgs());
        }

        SeplendidArgumentMap parameterToArgMap =
                SeplendidArgumentTokenizer.tokenize(args,
                        PARAMETER_TAGS);

        if (!ParserUtil.areArgumentsPresent(parameterToArgMap,
                PARAMETER_TAGS)) {
            throw new ParseException(UsageMessage.NOTE_SEARCH.toString());
        }

        Tag tag = ParserUtil.parseTag(
                parameterToArgMap.getValue(PARAMETER_TAGS).get());
        return new NoteSearchCommand(new NoteTagContainsKeywordsPredicate(tag.toString()));
    }
}
