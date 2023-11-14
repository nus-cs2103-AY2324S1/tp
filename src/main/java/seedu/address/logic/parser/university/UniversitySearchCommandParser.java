package seedu.address.logic.parser.university;


import static seedu.address.logic.parser.CliSyntax.PARAMETER_UNIVERSITYNAME;
import static seedu.address.logic.parser.ParserUtil.areValuesEnclosedAndNonEmpty;

import seedu.address.logic.commands.university.UniversitySearchCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.SeplendidArgumentMap;
import seedu.address.logic.parser.SeplendidArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.messages.UsageMessage;
import seedu.address.model.university.UniversityName;
import seedu.address.model.university.UniversityNameContainsKeywordsPredicate;

/**
 * Parses the given {@code String} of arguments in the context of the SearchCommand
 * and returns a SearchCommand object for execution.
 *
 * @throws ParseException if the user input does not conform the expected format
 */

public class UniversitySearchCommandParser implements Parser<UniversitySearchCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UniversitySearchCommand
     * and returns a UniversitySearchCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UniversitySearchCommand parse(String args) throws ParseException {
        ParserUtil.AreValuesEnclosedAndNonEmptyResult areValuesEnclosedAndNonEmptyResult =
                areValuesEnclosedAndNonEmpty(args);
        if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.FAILURE) {
            throw new ParseException(UsageMessage.UNIVERSITY_SEARCH.toString());
        } else if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.EMPTY) {
            throw new ParseException(UsageMessage.UNIVERSITY_SEARCH.getValueWithEmptyArgs());
        }

        SeplendidArgumentMap parameterToArgMap =
                SeplendidArgumentTokenizer.tokenize(args,
                        PARAMETER_UNIVERSITYNAME);

        if (!ParserUtil.areArgumentsPresent(parameterToArgMap,
                PARAMETER_UNIVERSITYNAME)) {
            throw new ParseException(UsageMessage.UNIVERSITY_SEARCH.toString());
        }

        UniversityName universityName = ParserUtil.parseUniversityName(
                parameterToArgMap.getValue(PARAMETER_UNIVERSITYNAME).get());
        return new UniversitySearchCommand(new UniversityNameContainsKeywordsPredicate(universityName.getName()));

    }
}

