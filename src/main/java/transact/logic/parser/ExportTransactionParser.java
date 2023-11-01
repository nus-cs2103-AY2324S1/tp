package transact.logic.parser;

import static transact.logic.parser.CliSyntax.PREFIX_DIRECTORY;

import java.nio.file.Path;
import java.nio.file.Paths;

import transact.logic.commands.ExportTransactionCommand;
import transact.logic.parser.exceptions.ParseException;

/**
 * Parses args and returns Export Transaction Command
 */
public class ExportTransactionParser implements Parser<ExportTransactionCommand> {
    @Override
    public ExportTransactionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DIRECTORY);
        Path exportFilePath = Paths.get(argMultimap.getValue(PREFIX_DIRECTORY).get() + "/transactions.csv");
        return new ExportTransactionCommand(exportFilePath);
    }
}
