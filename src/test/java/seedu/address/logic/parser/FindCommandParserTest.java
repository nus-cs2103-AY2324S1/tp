package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.GeneralPersonPredicate;
import seedu.address.model.person.LastContactTimeContainsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PersonTagContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsPredicate;
import seedu.address.model.person.StatusContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();
    private LocalDateTime lastContacted = LocalDateTime.MIN;

    @Test
    public void parse_nonEmptyPreambleArg_throwsParseException() {
        assertParseFailure(parser, " bobby n/Alice",
                MESSAGE_INVALID_COMMAND_FORMAT + "\n" + FindCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_validArgsName_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(preparePredicate(new String[]{"Alice", "", "", "", ""}, lastContacted));
        assertParseSuccess(parser, " n/Alice", expectedFindCommand);
    }

    @Test
    public void parse_validArgsPhone_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(preparePredicate(new String[]{"", "51", "", "", ""}, lastContacted));
        assertParseSuccess(parser, " p/51", expectedFindCommand);
    }

    @Test
    public void parse_validArgsEmail_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(preparePredicate(new String[]{"", "", "gmail.com", "", ""}, lastContacted));
        assertParseSuccess(parser, " e/gmail.com", expectedFindCommand);
    }

    @Test
    public void parse_validArgsLastContacted_returnsFindCommand() {
        LocalDateTime time = DateTimeUtil.parse("20.09.2023 1000");
        FindCommand expectedFindCommand =
                new FindCommand(preparePredicate(new String[]{"", "", "", "", ""}, time));
        assertParseSuccess(parser, " lc/20.09.2023 1000", expectedFindCommand);
    }

    @Test
    public void parse_validArgsStatus_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(preparePredicate(new String[]{"", "", "", "active", ""}, lastContacted));
        assertParseSuccess(parser, " s/active", expectedFindCommand);
    }

    @Test
    public void parse_validArgsTag_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(preparePredicate(new String[]{"", "", "", "", "friend important"}, lastContacted));
        assertParseSuccess(parser, " t/friend important", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        LocalDateTime time = DateTimeUtil.parse("20.09.2023 1000");
        FindCommand expectedFindCommand =
                new FindCommand(preparePredicate(new String[]{"Alice", "913", "gmail", "Active", "friend"}, time));
        assertParseSuccess(parser, " n/Alice p/913 e/gmail lc/20.09.2023 1000 s/Active t/friend", expectedFindCommand);
    }

    /**
     * Parses {@code userInput} into a {@code GeneralPersonPredicate}.
     */
    private GeneralPersonPredicate preparePredicate(String[] userInput, LocalDateTime lastContacted) {
        return new GeneralPersonPredicate(
                new NameContainsKeywordsPredicate(List.of(userInput[0].split("\\s+"))),
                new PhoneContainsPredicate(List.of(userInput[1].split("\\s+"))),
                new EmailContainsKeywordsPredicate(List.of(userInput[2].split("\\s+"))),
                new LastContactTimeContainsPredicate(lastContacted),
                new StatusContainsKeywordsPredicate(List.of(userInput[3].split("\\s+"))),
                new PersonTagContainsKeywordsPredicate(List.of(userInput[4].split("\\s+")))
        );
    }

}
