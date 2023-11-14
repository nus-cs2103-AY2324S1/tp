package seedu.address.logic.parser.university;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.university.UniversitySearchCommand;
import seedu.address.messages.UsageMessage;
import seedu.address.model.university.UniversityNameContainsKeywordsPredicate;


public class UniversitySearchCommandParserTest {
    private UniversitySearchCommandParser parser = new UniversitySearchCommandParser();


    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", UsageMessage.UNIVERSITY_SEARCH.toString());
    }

    @Test
    public void parse_validArgs_returnsUniversitySearchCommand() {
        // no leading and trailing whitespaces
        UniversitySearchCommand expectedUniversitySearchCommand =
                new UniversitySearchCommand(new UniversityNameContainsKeywordsPredicate(
                        "Harvard"));
        assertParseSuccess(parser, "university search [Harvard]", expectedUniversitySearchCommand);

        // multiple whitespaces between keywords
        // assertParseSuccess(parser, " \n Harvard", expectedUniversitySearchCommand);
    }

}
