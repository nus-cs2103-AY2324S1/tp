package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddEventCommand;

public class AddEventCommandParserTest {
    private final AddEventCommandParser parser = new AddEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        // dated event in user
        assertParseSuccess(parser, "user en/CS2103 Meeting h/2023-10-10 1030 1130 r/y",
                new AddEventCommand("CS2103 MEETING", "2023-10-10 1030 1130", "y"));

        // dated event in friend
        assertParseSuccess(parser, "1 en/CS2103 Meeting h/2023-10-10 1030 1130 r/y",
                new AddEventCommand("CS2103 MEETING", Index.fromOneBased(1),
                        "2023-10-10 1030 1130", "y"));

    }

    @Test
    public void parse_repeatedValue_failure() {
        String validCommand = "user en/CS2103 Meeting h/2023-10-10 1030 1130 r/y";

        // multiple event name
        assertParseFailure(parser, validCommand + " en/CS2103 Meeting",
                "You can only have 1 of each prefix!\n" + "Duplicated prefixes are: en/ ");

        // multiple event schedule
        assertParseFailure(parser, validCommand + " h/2023-10-10 1030 1130",
                "You can only have 1 of each prefix!\n" + "Duplicated prefixes are: h/ ");

        // multiple event reminder
        assertParseFailure(parser, validCommand + " r/y",
                "You can only have 1 of each prefix!\n" + "Duplicated prefixes are: r/ ");

        // multiple fields repeated
        assertParseFailure(parser, validCommand + " en/CS2103 Meeting" + " r/y",
                "You can only have 1 of each prefix!\n" + "Duplicated prefixes are: en/ r/ ");

    }

    @Test
    public void parse_fieldMissing_failure() {

        // missing event name prefix
        assertParseFailure(parser, "user h/2023-10-10 1030 1130 r/y",
                "Missing prefix(es) for en/ !\n" + "Message Usage:\n" + AddEventCommand.MESSAGE_USAGE);

        // missing event schedule prefix
        assertParseFailure(parser, "user en/CS2103 Meeting r/y",
                "Missing prefix(es) for h/ !\n" + "Message Usage:\n" + AddEventCommand.MESSAGE_USAGE);

        // missing event reminder prefix
        assertParseFailure(parser, "user en/CS2103 Meeting h/2023-10-10 1030 1130",
                "Missing prefix(es) for r/ !\n" + "Message Usage:\n" + AddEventCommand.MESSAGE_USAGE);

        // missing multiple prefixes
        assertParseFailure(parser, "user en/CS2103 Meeting",
                "Missing prefix(es) for h/ r/ !\n" + "Message Usage:\n" + AddEventCommand.MESSAGE_USAGE);

        // all prefixes missing
        assertParseFailure(parser, "user CS2103 Meeting 2023-10-10 1030 1130 y",
                "Missing prefix(es) for en/ h/ r/ !\n" + "Message Usage:\n"
                        + AddEventCommand.MESSAGE_USAGE);

        // wrong index
        assertParseFailure(parser, "wrong en/CS2103 Meeting h/2023-10-10 1030 1130 r/y",
                "Invalid index!\n" + "Index can only be 'user' or a positive integer! \n");

    }

}
