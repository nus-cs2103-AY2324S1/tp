package seedu.address.logic.parser;

import java.util.regex.Pattern;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

public abstract class ComponentParser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public abstract Command parseCommand(String userInput) throws ParseException;
}
