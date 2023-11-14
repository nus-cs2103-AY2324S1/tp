package transact.logic.parser;

import static transact.logic.parser.CliSyntax.PREFIX_DIRECTORY;

import java.nio.file.Path;
import java.nio.file.Paths;

import transact.logic.commands.ExportStaffCommand;
import transact.logic.parser.exceptions.ParseException;

/**
 * Parses args and returns Export Staff Command
 */
public class ExportStaffCommandParser implements Parser<ExportStaffCommand> {
    @Override
    public ExportStaffCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DIRECTORY);
        Path exportFilePath = Paths.get(argMultimap.getValue(PREFIX_DIRECTORY).get() + "/addressBook.json");
        return new ExportStaffCommand(exportFilePath);
    }

}
