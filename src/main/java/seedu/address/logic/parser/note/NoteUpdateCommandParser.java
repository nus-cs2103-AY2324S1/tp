package seedu.address.logic.parser.note;


import static seedu.address.logic.parser.CliSyntax.PARAMETER_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_INDEX;
import static seedu.address.logic.parser.ParserUtil.areValuesEnclosedAndNonEmpty;

import seedu.address.logic.commands.note.NoteUpdateCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.SeplendidArgumentMap;
import seedu.address.logic.parser.SeplendidArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.messages.UsageMessage;
import seedu.address.model.note.Content;

/**
 * Parses the given {@code String} of arguments in the context of the UpdateCommand
 * and returns a UpdateCommand object for execution.
 *
 * @throws ParseException if the user input does not conform the expected format
 */
public class NoteUpdateCommandParser implements Parser<NoteUpdateCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the NoteUpdateCommand
     * and returns a NoteUpdateCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public NoteUpdateCommand parse(String args) throws ParseException {
        ParserUtil.AreValuesEnclosedAndNonEmptyResult areValuesEnclosedAndNonEmptyResult =
                areValuesEnclosedAndNonEmpty(args);
        if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.FAILURE) {
            throw new ParseException(UsageMessage.NOTE_UPDATE.toString());
        } else if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.EMPTY) {
            throw new ParseException(UsageMessage.NOTE_UPDATE.getValueWithEmptyArgs());
        }

        SeplendidArgumentMap parameterToArgMap =
                SeplendidArgumentTokenizer.tokenize(args, PARAMETER_INDEX, PARAMETER_CONTENT);

        if (!ParserUtil.areArgumentsPresent(parameterToArgMap, PARAMETER_INDEX, PARAMETER_CONTENT)) {
            throw new ParseException(UsageMessage.NOTE_UPDATE.toString());
        }

        // All arguments should be a non-empty {@code Optional}

        try {
            Integer noteIndex = Integer.valueOf(parameterToArgMap.getValue(PARAMETER_INDEX).get());
            Content newContent = new Content(parameterToArgMap.getValue(PARAMETER_CONTENT).get());
            return new NoteUpdateCommand(noteIndex, newContent);
        } catch (NumberFormatException e) {
            Content newContent = new Content(parameterToArgMap.getValue(PARAMETER_CONTENT).get());
            return new NoteUpdateCommand(-1, newContent);
        }
    }
}
