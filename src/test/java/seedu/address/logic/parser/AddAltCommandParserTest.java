package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINKEDIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SECONDARY_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.MonthDay;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddAltCommand;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Linkedin;
import seedu.address.model.person.Telegram;


public class AddAltCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAltCommand.MESSAGE_USAGE);
    private AddAltCommandParser parser = new AddAltCommandParser();

    @Test
    public void parse_validArgs_success() {
        // Heuristic: Each Valid Input at Least Once in a Positive Test Case.
        // Valid inputs for each alternative contact field: Null, Non-empty (follows format for respective fields)

        // All non-empty.
        AddAltCommand.AddAltPersonDescriptor descriptor = new AddAltCommand.AddAltPersonDescriptor();
        descriptor.setTelegram(new Telegram("@telegram"));
        descriptor.setLinkedin(new Linkedin("LINKEDIN"));
        descriptor.setSecondaryEmail(new Email("email@email.com"));
        descriptor.setBirthday(new Birthday(MonthDay.of(6, 9)));
        Index expectedIndex = Index.fromOneBased(1);

        assertParseSuccess(
                parser,
                PREAMBLE_WHITESPACE + "1" + " "
                        + PREFIX_TELEGRAM + "@telegram" + " "
                        + PREFIX_LINKEDIN + "LINKEDIN" + " "
                        + PREFIX_SECONDARY_EMAIL + "email@email.com" + " "
                        + PREFIX_BIRTHDAY + "09/06",
                new AddAltCommand(expectedIndex, descriptor));

        // All null except Telegram.
        descriptor = new AddAltCommand.AddAltPersonDescriptor();
        descriptor.setTelegram(new Telegram("@telegram"));
        expectedIndex = Index.fromOneBased(1);
        assertParseSuccess(
                parser,
                PREAMBLE_WHITESPACE + "1" + " "
                        + PREFIX_TELEGRAM + "@telegram" + " ",
                new AddAltCommand(expectedIndex, descriptor));

        // All null except Secondary Email.
        descriptor = new AddAltCommand.AddAltPersonDescriptor();
        descriptor.setSecondaryEmail(new Email("email@email.com"));
        assertParseSuccess(
                parser,
                PREAMBLE_WHITESPACE + "1" + " "
                        + PREFIX_SECONDARY_EMAIL + "email@email.com" + " ",
                new AddAltCommand(expectedIndex, descriptor));
    }

    @Test
    public void parse_invalidFormat_failure() {
        // Heuristic: No More Than One Invalid Input In A Test Case. Used all pairs strategy to exhaustively test.

        // No index specified
        assertParseFailure(parser, "b/24/07", MESSAGE_INVALID_FORMAT);

        // No prefixes specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // No index and no prefixes specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // Duplicate prefixes
        assertParseFailure(parser, "1 b/24/07 b/25/07", Messages.MESSAGE_DUPLICATE_FIELDS + "b/");

        // Invalid prefix
        assertParseFailure(parser, "1 i/string b/24/07", ParserUtil.MESSAGE_NOT_A_INDEX);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // Heuristic: Boundary value analysis

        // One value below boundary
        assertParseFailure(parser, "-1 b/24/07/01" , ParserUtil.MESSAGE_INVALID_INDEX);

        // Value at boundary
        assertParseFailure(parser, "0 b/24/07/01" , ParserUtil.MESSAGE_INVALID_INDEX);
    }

}
