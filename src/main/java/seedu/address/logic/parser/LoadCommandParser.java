package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE;

import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.logic.commands.LoadCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code LoadCommand} object
 */
public class LoadCommandParser implements Parser<LoadCommand> {
    public static final String MESSAGE_INVALID_FILE_NAME = "The file name cannot contain a forward slash.\n"
            + "Please try again with a different file name.\n";
    /**
     * Parses the given {@code String} of arguments in the context of the {@code LoadCommand}
     * and returns a {@code LoadCommand} object for execution.
     * @param args Arguments to be parsed
     * @return {@code LoadCommand} object for execution
     * @throws ParseException if the user input for file name does not conform the expected format
     */
    public LoadCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILE);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_FILE);
        String fileName = argMultimap.getValue(PREFIX_FILE).orElse("");
        if (fileName.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LoadCommand.MESSAGE_USAGE));
        } else if (fileName.contains("/")) {
            throw new ParseException(MESSAGE_INVALID_FILE_NAME);
        }
        Path addressBookFilePath = Paths.get("data", fileName + ".json");
        return new LoadCommand(fileName, addressBookFilePath);
    }
}
