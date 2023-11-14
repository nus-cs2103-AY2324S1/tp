package seedu.address.logic.parser;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CreateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorial.Tutorial;

public class CreateCommandParserTest {

    private CreateCommandParser parser = new CreateCommandParser();

    @Test
    public void parse_validArgs_returnsCreateCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, " t/01", new CreateCommand(new Tutorial("01")));
    }

    @Test
    public void parse_missingTutorialPrefix_throwsParseException() {
        Assertions.assertThrows(ParseException.class, () -> parser.parse(" 01"));
    }

    @Test
    public void parse_invalidTutorialNumber_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" t/40"));
    }

    @Test
    public void parse_multipleTutorialNumber_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" t/01 t/02"));
    }

    @Test
    public void parse_emptyArgument_throwsParseException() {
        Assertions.assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_nullArgument_throwsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

}
