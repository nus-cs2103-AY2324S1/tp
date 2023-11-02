package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.BirthdayWithinDaysPredicate;
import seedu.address.model.person.EventWithinDaysPredicate;

class RemindCommandParserTest {

    private RemindCommandParser parser = new RemindCommandParser();


    @Test
    public void parse_invalidArgs_throwsParseException() {
        // not a number
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemindCommand.MESSAGE_USAGE));


    }

    @Test
    public void parse_validArgs_returnRemindCommand() throws ParseException {

        assertParseSuccess(parser, "6", new RemindCommand(new BirthdayWithinDaysPredicate(ParserUtil.parseDays("6")),
                new EventWithinDaysPredicate(ParserUtil.parseDays("6")), ParserUtil.parseDays("6")));

        assertParseSuccess(parser, "", new RemindCommand(new BirthdayWithinDaysPredicate(ParserUtil.parseDays("7")),
                new EventWithinDaysPredicate(ParserUtil.parseDays("7")), ParserUtil.parseDays("7")));

    }
}
