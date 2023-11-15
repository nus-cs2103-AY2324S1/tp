package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddScheduleCommand;

public class AddScheduleCommandParserTest {
    private AddScheduleCommandParser parser = new AddScheduleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        // cca in user
        assertParseSuccess(parser, "user type/cca en/Basketball h/Monday 1030 1130",
                new AddScheduleCommand("BASKETBALL", "cca", "Monday 1030 1130"));

        // cca in friend
        assertParseSuccess(parser, "1 type/cca en/Basketball h/Monday 1030 1130",
                new AddScheduleCommand("BASKETBALL", "cca", Index.fromOneBased(1),
                        "Monday 1030 1130"));

        // module in user
        assertParseSuccess(parser, "user type/module en/CS2103 h/Monday 1030 1130",
                new AddScheduleCommand("CS2103", "module", "Monday 1030 1130"));

        // module in friend
        assertParseSuccess(parser, "1 type/module en/CS2103 h/Monday 1030 1130",
                new AddScheduleCommand("CS2103", "module", Index.fromOneBased(1),
                        "Monday 1030 1130"));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validCommand = "user type/cca en/Basketball h/Monday 1030 1130";

        // multiple event type
        assertParseFailure(parser, validCommand + " type/cca",
                "You can only have 1 of each prefix!\n" + "Duplicated prefixes are: type/ ");

        // multiple event name
        assertParseFailure(parser, validCommand + " en/CS2103",
                "You can only have 1 of each prefix!\n" + "Duplicated prefixes are: en/ ");

        // multiple event schedule
        assertParseFailure(parser, validCommand + " h/Monday 1030 1130",
                "You can only have 1 of each prefix!\n" + "Duplicated prefixes are: h/ ");

        // multiple fields repeated
        assertParseFailure(parser, validCommand + " type/cca" + " en/Basketball",
                "You can only have 1 of each prefix!\n" + "Duplicated prefixes are: en/ type/ ");

    }

    @Test
    public void parse_fieldMissing_failure() {

        // missing schedule name prefix
        assertParseFailure(parser, "user type/cca h/Monday 1030 1130",
                "Missing prefix(es) for en/ !\n" + "Message Usage:\n" + AddScheduleCommand.MESSAGE_USAGE);

        // missing schedule type prefix
        assertParseFailure(parser, "user en/Basketball h/Monday 1030 1130",
                "Missing prefix(es) for type/ !\n" + "Message Usage:\n"
                        + AddScheduleCommand.MESSAGE_USAGE);

        // missing schedule prefix
        assertParseFailure(parser, "user type/cca en/Basketball",
                "Missing prefix(es) for h/ !\n" + "Message Usage:\n"
                        + AddScheduleCommand.MESSAGE_USAGE);

        // missing multiple prefix
        assertParseFailure(parser, "user type/cca",
                "Missing prefix(es) for en/ h/ !\n" + "Message Usage:\n"
                        + AddScheduleCommand.MESSAGE_USAGE);

        // all prefixes missing
        assertParseFailure(parser, "user cca Basketball Monday 1030 1130",
                "Missing prefix(es) for en/ type/ h/ !\n" + "Message Usage:\n"
                        + AddScheduleCommand.MESSAGE_USAGE);

        // wrong index
        assertParseFailure(parser, "wrong type/cca en/CS2103 Meeting h/2023-10-10 1030 1130 r/y",
                String.format("Invalid index!\n"
                        + "Index can only be 'user' or a positive integer! \n"));
    }

}
