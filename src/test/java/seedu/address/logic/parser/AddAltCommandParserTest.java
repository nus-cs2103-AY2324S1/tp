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
    public void parse_allFieldsSpecified_success() {
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
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        AddAltCommand.AddAltPersonDescriptor descriptor = new AddAltCommand.AddAltPersonDescriptor();
        descriptor.setTelegram(new Telegram("@telegram"));
        descriptor.setSecondaryEmail(new Email("email@email.com"));
        Index expectedIndex = Index.fromOneBased(1);

        assertParseSuccess(
                parser,
                PREAMBLE_WHITESPACE + "1" + " "
                        + PREFIX_TELEGRAM + "@telegram" + " "
                        + PREFIX_SECONDARY_EMAIL + "email@email.com" + " ",
                new AddAltCommand(expectedIndex, descriptor));
    }

    @Test
    public void parse_oneField_success() {
        AddAltCommand.AddAltPersonDescriptor descriptor = new AddAltCommand.AddAltPersonDescriptor();
        descriptor.setTelegram(new Telegram("@telegram"));
        Index expectedIndex = Index.fromOneBased(1);
        assertParseSuccess(
                parser,
                PREAMBLE_WHITESPACE + "1" + " "
                        + PREFIX_TELEGRAM + "@telegram" + " ",
                new AddAltCommand(expectedIndex, descriptor));

        descriptor = new AddAltCommand.AddAltPersonDescriptor();
        descriptor.setSecondaryEmail(new Email("email@email.com"));
        assertParseSuccess(
                parser,
                PREAMBLE_WHITESPACE + "1" + " "
                        + PREFIX_SECONDARY_EMAIL + "email@email.com" + " ",
                new AddAltCommand(expectedIndex, descriptor));

        descriptor = new AddAltCommand.AddAltPersonDescriptor();
        descriptor.setLinkedin(new Linkedin("LINKEDIN"));
        assertParseSuccess(
                parser,
                PREAMBLE_WHITESPACE + "1" + " "
                        + PREFIX_LINKEDIN + "LINKEDIN" + " ",
                new AddAltCommand(expectedIndex, descriptor));

        descriptor = new AddAltCommand.AddAltPersonDescriptor();
        descriptor.setBirthday(new Birthday(MonthDay.of(6, 9)));
        assertParseSuccess(
                parser,
                PREAMBLE_WHITESPACE + "1" + " "
                        + PREFIX_BIRTHDAY + "09/06",
                new AddAltCommand(expectedIndex, descriptor));
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, "b/24/07/01", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", AddAltCommand.MESSAGE_NO_ADDALT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5 b/24/07/01" , MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0 b/24/07/01" , MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid telegram
        assertParseFailure(parser, "1 tg/telegram", Telegram.MESSAGE_CONSTRAINTS); // not starting with @
        assertParseFailure(parser, "1 tg/@tel", Telegram.MESSAGE_CONSTRAINTS); // not meeting 5 character requirement
        assertParseFailure(parser, "1 tg/@tel?!", Telegram.MESSAGE_CONSTRAINTS);

        // invalid birthday
        assertParseFailure(parser, "1 b/24-01-24", Birthday.MESSAGE_INVALID); // not following format
        assertParseFailure(parser, "1 b/./.", Birthday.MESSAGE_INVALID); // not valid date
        assertParseFailure(parser, "1 b/31/02", Birthday.MESSAGE_INVALID); // not a valid date

        // invalid secondaryEmail
        assertParseFailure(parser, "1 e2/yyy.com", Email.MESSAGE_CONSTRAINTS);

        // invalid linkedin
        assertParseFailure(parser, "1 li/?", Linkedin.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 li/@johndoe", Linkedin.MESSAGE_CONSTRAINTS);

        // mix of invalid and valid fields
        assertParseFailure(parser, "1 li/? tg/@telegram", Linkedin.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 li/john-doe tg/@telegram b/31/02", Birthday.MESSAGE_INVALID);
        assertParseFailure(parser, "1 li/john-doe tg/telegram b/31/02", Telegram.MESSAGE_CONSTRAINTS);
    }
}
