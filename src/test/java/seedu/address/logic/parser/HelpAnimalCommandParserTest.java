package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.parser.AnimalCommandParserTestUtil.assertParseFailure;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HelpAnimalCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.CommandEnum;

public class HelpAnimalCommandParserTest {
    public static final String VALID_COMMAND_WORD = "add";
    public static final String INVALID_NUMBERS = "123";
    public static final String INVALID_WHITESPACE = "add command";
    public static final String INVALID_ALPHANUMERIC = "add 123";
    public static final String INVALID_SYMBOLS = "@@";
    public static final String VALID_LEADING_WHITESPACE = "   " + VALID_COMMAND_WORD;
    public static final String VALID_TRAILING_WHITESPACE = VALID_COMMAND_WORD + "  ";
    public static final List<String> ALL_VALID_COMMANDS = Arrays.stream(CommandEnum.values())
        .map(CommandEnum::getCommandWord)
        .collect(Collectors.toList());

    private final HelpAnimalCommandParser helpCommandParser = new HelpAnimalCommandParser();

    @Test
    public void parseHelp_withInvalidInputs_throwsParseException() {
        assertParseFailure(helpCommandParser, INVALID_NUMBERS, HelpAnimalCommand.MESSAGE_USAGE);
        assertParseFailure(helpCommandParser, INVALID_WHITESPACE, HelpAnimalCommand.MESSAGE_USAGE);
        assertParseFailure(helpCommandParser, INVALID_ALPHANUMERIC, HelpAnimalCommand.MESSAGE_USAGE);
        assertParseFailure(helpCommandParser, INVALID_SYMBOLS, HelpAnimalCommand.MESSAGE_USAGE);
    }

    @Test
    public void parseHelp_withValidInputs_returnsHelpCommand() {
        try {
            assertEquals(HelpAnimalCommand.class, helpCommandParser.parse(VALID_COMMAND_WORD).getClass());
            assertEquals(HelpAnimalCommand.class, helpCommandParser.parse(VALID_LEADING_WHITESPACE).getClass());
            assertEquals(HelpAnimalCommand.class, helpCommandParser.parse(VALID_TRAILING_WHITESPACE).getClass());

            for (String validCommands: ALL_VALID_COMMANDS) {
                assertEquals(HelpAnimalCommand.class, helpCommandParser.parse(validCommands).getClass());
            }
        } catch (ParseException ignored) {
            fail();
        }
    }
}
