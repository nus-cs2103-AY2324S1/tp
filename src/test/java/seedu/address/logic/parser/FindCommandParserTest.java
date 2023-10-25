package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.GeneralPersonPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PersonTagContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsPredicate;
import seedu.address.model.person.StatusContainsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_nonEmptyPreambleArg_throwsParseException() {
        assertParseFailure(parser, " bobby n/Alice",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsName_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(preparePredicate(new String[]{"Alice", "", "", "", ""}));
        assertParseSuccess(parser, " n/Alice", expectedFindCommand);
    }

    @Test
    public void parse_validArgsPhone_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(preparePredicate(new String[]{"", "51", "", "", ""}));
        assertParseSuccess(parser, " p/51", expectedFindCommand);
    }

    @Test
    public void parse_validArgsEmail_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(preparePredicate(new String[]{"", "", "gmail.com", "", ""}));
        assertParseSuccess(parser, " e/gmail.com", expectedFindCommand);
    }

    @Test
    public void parse_validArgsStatus_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(preparePredicate(new String[]{"", "", "", "active", ""}));
        assertParseSuccess(parser, " s/active", expectedFindCommand);
    }

    @Test
    public void parse_validArgsTag_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(preparePredicate(new String[]{"", "", "", "", "friend important"}));
        assertParseSuccess(parser, " t/friend important", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(preparePredicate(new String[]{"Alice", "913", "gmail", "Active", "friend"}));
        assertParseSuccess(parser, " n/Alice p/913 e/gmail s/Active t/friend", expectedFindCommand);
    }

    /**
     * Parses {@code userInput} into a {@code GeneralPersonPredicate}.
     */
    private GeneralPersonPredicate preparePredicate(String[] userInput) {
        return new GeneralPersonPredicate(
                new NameContainsKeywordsPredicate(List.of(userInput[0].split("\\s+"))),
                new PhoneContainsPredicate(List.of(userInput[1].split("\\s+"))),
                new EmailContainsKeywordsPredicate(List.of(userInput[2].split("\\s+"))),
                new StatusContainsPredicate(List.of(userInput[3].split("\\s+"))),
                new PersonTagContainsKeywordsPredicate(List.of(userInput[4].split("\\s+")))
        );
    }

}
