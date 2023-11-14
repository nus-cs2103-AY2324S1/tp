package seedu.address.logic.parser.note;


import static seedu.address.logic.parser.CliSyntax.PARAMETER_INDEX;
import static seedu.address.logic.parser.ParserUtil.areValuesEnclosedAndNonEmpty;

import seedu.address.logic.commands.note.NoteClearTagCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.SeplendidArgumentMap;
import seedu.address.logic.parser.SeplendidArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.messages.UsageMessage;

/**
 * Parses the given {@code String} of arguments in the context of the ClearTagCommand
 * and returns a ClearTagCommand object for execution.
 *
 * @throws ParseException if the user input does not conform the expected format
 */
public class NoteClearTagCommandParser implements Parser<NoteClearTagCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the NoteClearTagCommand
     * and returns a NoteClearTagCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public NoteClearTagCommand parse(String args) throws ParseException {
        ParserUtil.AreValuesEnclosedAndNonEmptyResult areValuesEnclosedAndNonEmptyResult =
                areValuesEnclosedAndNonEmpty(args);
        if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.FAILURE) {
            throw new ParseException(UsageMessage.NOTE_CLEAR_TAG.toString());
        } else if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.EMPTY) {
            throw new ParseException(UsageMessage.NOTE_CLEAR_TAG.getValueWithEmptyArgs());
        }

        SeplendidArgumentMap parameterToArgMap =
                SeplendidArgumentTokenizer.tokenize(args, PARAMETER_INDEX);

        if (!ParserUtil.areArgumentsPresent(parameterToArgMap, PARAMETER_INDEX)) {
            throw new ParseException(UsageMessage.NOTE_CLEAR_TAG.toString());
        }

        // All arguments should be a non-empty {@code Optional}

        try {
            Integer noteIndex = Integer.valueOf(parameterToArgMap.getValue(PARAMETER_INDEX).get());
            return new NoteClearTagCommand(noteIndex);
        } catch (NumberFormatException e) {
            return new NoteClearTagCommand(-1);
        }
    }
}
