package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINKEDIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SECONDARY_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAltCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Linkedin;
import seedu.address.model.person.Telegram;

public class AddAltCommandParserTest {
    private AddAltCommandParser parser = new AddAltCommandParser();

    @Test
    public void parse_success() {
        AddAltCommand.AddAltPersonDescriptor descriptor = new AddAltCommand.AddAltPersonDescriptor();
        descriptor.setTelegram(new Telegram("@telegram"));
        descriptor.setLinkedin(new Linkedin("LINKEDIN"));
        descriptor.setSecondaryEmail(new Email("email@email.com"));
        Index expectedIndex = Index.fromOneBased(1);

        assertParseSuccess(
                parser,
                PREAMBLE_WHITESPACE + "1" + " "
                        + PREFIX_TELEGRAM + "@telegram" + " "
                        + PREFIX_LINKEDIN + "LINKEDIN" + " "
                        + PREFIX_SECONDARY_EMAIL + "email@email.com",
                new AddAltCommand(expectedIndex, descriptor));
    }
}
