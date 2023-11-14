package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import javafx.util.Pair;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) throws ParseException {
        ManageHrParser commandParser = new ManageHrParser();
        try {
            if (args.isEmpty()) {
                return new HelpCommand();
            } else {
                Pair<String, String> cmdUsage = commandParser.checkCommandUsage(args);
                String usage = cmdUsage.getKey();
                String example = cmdUsage.getValue();
                return new HelpCommand(usage, example);
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), pe);
        }
    }

}
