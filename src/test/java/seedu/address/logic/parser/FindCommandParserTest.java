package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.SubjectContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleWords_throwsParseException() {

        assertThrows(ParseException.class, () -> parser.parse("n/Alex Yeoh sb/Math"));

        assertThrows(ParseException.class, () -> parser.parse("sb/Math Chemistry"));

        assertThrows(ParseException.class, () -> parser.parse("n/Alex Yeoh sb/Math"));

        assertThrows(ParseException.class, () -> parser.parse("n/Alex sb/Math Chemistry"));
    }

    @Test
    public void parse_validOneWord_success() throws ParseException {
        parser.parse(" n/Alex");

        parser.parse(" sb/Math");
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Bob")),
                        new SubjectContainsKeywordsPredicate(Arrays.asList("Biology")));

        assertParseSuccess(parser, " n/Bob sb/Biology", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Bob \t \n sb/Biology  \t",
                expectedFindCommand);
    }

}
